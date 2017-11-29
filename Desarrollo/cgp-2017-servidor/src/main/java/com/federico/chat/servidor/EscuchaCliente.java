package com.federico.chat.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import com.federico.chat.comandos.Comando;
import com.federico.chat.comandos.ComandosServidor;
import com.federico.chat.mensajeria.Paquete;
import com.federico.chat.mensajeria.PaqueteConectados;
import com.federico.chat.mensajeria.PaqueteConexion;
import com.federico.chat.modelos.Conectado;
import com.federico.chat.modelos.Usuario;
import com.google.gson.Gson;

public class EscuchaCliente extends Thread{

	private Socket socket;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String ip;
	private String nombreUsuario;
	
	public EscuchaCliente(Socket socket, ObjectOutputStream salida, ObjectInputStream entrada, String ip,
			String nombreUsuario) {
		this.socket = socket;
		this.salida = salida;
		this.entrada = entrada;
		this.ip = ip;
		this.nombreUsuario = nombreUsuario;
	}

	public void run() {
		ComandosServidor comando;
		Paquete paquete;
		String cadenaLeida = null;
		try {
			cadenaLeida = (String) entrada.readObject();
			while(!((paquete = Comando.gson.fromJson(cadenaLeida, Paquete.class)).dameOperacion() == Comando.DESCONEXION)) {
				paquete = Comando.gson.fromJson(cadenaLeida, Paquete.class);
				comando = (ComandosServidor)paquete.devolverComando(paquete.dameOperacion());
				comando.guardaCadenaLeida(cadenaLeida);
				comando.setEscuchaCliente(this);
				comando.ejecutar();
				cadenaLeida = (String) entrada.readObject();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			salida.close();
			entrada.close();
			socket.close();
			PaqueteConexion pa = Comando.gson.fromJson(cadenaLeida, PaqueteConexion.class);
			for(EscuchaCliente es : Servidor.listadoConectados) {
				if(es.getNombreUsuario().equals(pa.getNombreUsuario())) {
					Servidor.listadoConectados.remove(es);
				}
			}
			
			PaqueteConectados paq = new PaqueteConectados(Comando.DESCONEXION);
			paq.setListadoConectados(generarListadoConectado());
			String objeto = Comando.gson.toJson(paq);
			
			for(EscuchaCliente es : Servidor.listadoConectados) {
				es.salida.writeObject(objeto);
			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}

	private LinkedList<Conectado> generarListadoConectado(){
		LinkedList<Conectado> listadoAEnviar = new LinkedList<Conectado>();
		for(EscuchaCliente cliente : Servidor.listadoConectados) {
			listadoAEnviar.add(new Conectado(new Usuario(cliente.getNombreUsuario(), cliente.getIp())));
		}
		return listadoAEnviar;
	}
	
	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public ObjectOutputStream getSalida() {
		return salida;
	}


	public void setSalida(ObjectOutputStream salida) {
		this.salida = salida;
	}


	public ObjectInputStream getEntrada() {
		return entrada;
	}


	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
