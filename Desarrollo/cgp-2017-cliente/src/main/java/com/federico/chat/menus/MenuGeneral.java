package com.federico.chat.menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MenuGeneral extends JFrame {

	private JPanel contentPane;
	private JButton btnEnviar;
	private JButton btnPrivado;
	private JList<String> listadoConectados;
	private JTextArea areaMensaje;
	private JTextArea areaConversacion;
	private DefaultListModel<String> modelo = new DefaultListModel<>();
	
	
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		MenuGeneral.setDefaultLookAndFeelDecorated(true);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGeneral frame = new MenuGeneral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(240,240,240));
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 248, 346);
		contentPane.add(panel);
		panel.setLayout(null);
		setTitle("Chat General - Privado");
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(153, 180, 209), 2));
		panel_1.setBounds(10, 36, 228, 237);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		listadoConectados = new JList<String>();
		scrollPane.setViewportView(listadoConectados);
		
		btnPrivado = new JButton("Privado");
		btnPrivado.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/privado.png")));
		btnPrivado.setBounds(10, 289, 111, 47);
		panel.add(btnPrivado);
		
		JLabel lblConectados = new JLabel("Conectados");
		lblConectados.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/usuarios.png")));
		lblConectados.setBounds(10, 11, 111, 23);
		panel.add(lblConectados);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(268, 11, 353, 346);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(153, 180, 209), 2));
		panel_3.setBounds(10, 35, 333, 183);
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);
		
		areaConversacion = new JTextArea();
		scrollPane_1.setViewportView(areaConversacion);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(153, 180, 209), 2));
		panel_4.setBounds(10, 271, 231, 64);
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_4.add(scrollPane_2);
		
		areaMensaje = new JTextArea();
		scrollPane_2.setViewportView(areaMensaje);
		
		JLabel lblConversacion = new JLabel("Conversacion");
		lblConversacion.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/conversacion.png")));
		lblConversacion.setBounds(10, 11, 107, 24);
		panel_2.add(lblConversacion);
		
		JLabel lblNewLabel = new JLabel("Mensajes");
		lblNewLabel.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/mensaje.png")));
		lblNewLabel.setBounds(10, 234, 107, 26);
		panel_2.add(lblNewLabel);
		
		btnEnviar = new JButton("");
		btnEnviar.setIcon(new ImageIcon(MenuGeneral.class.getResource("/com/federico/chat/recursos/enviar.png")));
		btnEnviar.setBounds(254, 271, 89, 64);
		panel_2.add(btnEnviar);
	}
	
	
	public void actualizarListaConectados(String elemento) {
		modelo.addElement(elemento);
		listadoConectados.setModel(modelo);
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public JButton getBtnPrivado() {
		return btnPrivado;
	}

	public JList<String> getListadoConectados() {
		return listadoConectados;
	}

	public JTextArea getAreaMensaje() {
		return areaMensaje;
	}

	public JTextArea getAreaConversacion() {
		return areaConversacion;
	}

}
