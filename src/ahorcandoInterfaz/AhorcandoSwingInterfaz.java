package ahorcandoInterfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import ahorcandoLogica.AhorcandoSwing;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AhorcandoSwingInterfaz {

	private JFrame _juegoFrame;
	private AhorcandoSwing _juego;
	private JTextField _letraArriesgada;
	private JLabel _letrasIngresadas;
	private JLabel _puntaje;
	private JLabel _intentosDisponibles;
	private JLabel _palabraACompletar;
	private JLabel _mostrarUnaPista;
	private JLabel _puntajeFinalTexto;
	private JLabel _imagenFondo;
	private RecordsJPanel recordsContentPane;
	private String playerName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AhorcandoSwingInterfaz window = new AhorcandoSwingInterfaz();
					window._juegoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AhorcandoSwingInterfaz() {
		_juego = new AhorcandoSwing();
		_letraArriesgada = new JTextField();
		_intentosDisponibles = new JLabel("");
		_letrasIngresadas = new JLabel();
		_letrasIngresadas.setForeground(Color.BLACK);
		_puntaje = new JLabel("0");
		_palabraACompletar = new JLabel("");
		_mostrarUnaPista = new JLabel("");
		_puntajeFinalTexto = new JLabel("");
		_imagenFondo = new JLabel();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		initialize();
	}

	private void initialize() {
		_juegoFrame = new JFrame();
		_juegoFrame.setBounds(100, 100, 948, 539);
		_juegoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_juegoFrame.getContentPane().setLayout(null);
		recordsContentPane = new RecordsJPanel();
		playerName = "";

		_imagenFondo.setIcon(new ImageIcon(AhorcandoSwingInterfaz.class.getResource("/ahorcandoInterfaz/fondo.jpg")));
		_imagenFondo.setBounds(0, 0, 940, 515);

		_juegoFrame.setResizable(false);
		menuJuego();
	}

	private void menuJuego() {
		JLabel menuDelJuegoTexto = new JLabel("Ahorcado");
		menuDelJuegoTexto.setFont(new Font("Tahoma", Font.PLAIN, 99));
		menuDelJuegoTexto.setBounds(279, 89, 425, 130);
		_juegoFrame.getContentPane().add(menuDelJuegoTexto);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButton botonEspaol = new JRadioButton("Espa\u00F1ol");
		buttonGroup.add(botonEspaol);
		botonEspaol.setBounds(261, 447, 109, 23);
		
		JRadioButton botonIngles = new JRadioButton("Ingles");
		buttonGroup.add(botonIngles);
		botonIngles.setBounds(575, 447, 109, 23);
		
		JButton botonJugar = new JButton("Jugar");
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(botonEspaol.isSelected()) {
					_juegoFrame.getContentPane().removeAll();
					_juegoFrame.repaint();
					_juego.setEsIngles(false);
					jugar();
				}
				else if(botonIngles.isSelected()){
					_juegoFrame.getContentPane().removeAll();
					_juegoFrame.repaint();
					_juego.setEsIngles(true);
					jugar();
				}else {
					JOptionPane.showMessageDialog(null, "Debes elegir un idioma para jugar!!");
				}
			}
		});
		botonJugar.setBounds(364, 279, 223, 42);
		botonJugar.setFont(new Font("Tahoma", Font.PLAIN, 30));

		JButton botonRecords = new JButton("Records");
		botonRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				records();
			}
		});
		botonRecords.setBounds(364, 350, 223, 42);
		botonRecords.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		_juegoFrame.getContentPane().add(botonEspaol);
		_juegoFrame.getContentPane().add(botonIngles);
		_juegoFrame.getContentPane().add(botonJugar);
		_juegoFrame.getContentPane().add(botonRecords);
		
		_juegoFrame.getContentPane().add(_imagenFondo);
	}

	private void jugar() {

		_mostrarUnaPista.setText("");
		_juego.inicializarJuego();

		_palabraACompletar.setFont(new Font("Tahoma", Font.PLAIN, 75));
		_palabraACompletar.setBounds(194, 88, 674, 130);
		_juegoFrame.getContentPane().add(_palabraACompletar);

		_juego.nuevaJugada();
		mostrarPalabra();

		JLabel letrasTexto = new JLabel("Letras: ");
		letrasTexto.setBounds(10, 406, 400, 130);
		letrasTexto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		_juegoFrame.getContentPane().add(letrasTexto);

		_letrasIngresadas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		_letrasIngresadas.setBounds(102, 406, 380, 130);
		_juegoFrame.getContentPane().add(_letrasIngresadas);

		mostrarLetrasIngresadas();

		JLabel puntajeTexto = new JLabel("Puntaje: ");
		puntajeTexto.setBounds(10, 20, 120, 25);
		puntajeTexto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_juegoFrame.getContentPane().add(puntajeTexto);

		_puntaje.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_puntaje.setBounds(91, 20, 96, 26);
		_juegoFrame.getContentPane().add(_puntaje);

		mostrarPuntaje();

		JLabel intentosDisponiblesTexto = new JLabel("Intentos :");
		intentosDisponiblesTexto.setBounds(760, 20, 180, 25);
		intentosDisponiblesTexto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_juegoFrame.getContentPane().add(intentosDisponiblesTexto);

		_intentosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_intentosDisponibles.setBounds(866, 19, 96, 26);
		_juegoFrame.getContentPane().add(_intentosDisponibles);

		mostrarIntentos();

		_mostrarUnaPista.setBounds(169, 11, 540, 40);
		_juegoFrame.getContentPane().add(_mostrarUnaPista);
		_mostrarUnaPista.setFont(new Font("Tahoma", Font.PLAIN, 20));

		_letraArriesgada.setBounds(310, 294, 153, 79);
		_letraArriesgada.setFont(new Font("Tahoma", Font.PLAIN, 60));
		_juegoFrame.getContentPane().add(_letraArriesgada);
		_letraArriesgada.setColumns(10);
		final Integer maximoDeCaracteres = 1;
		_letraArriesgada.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (_letraArriesgada.getText().length() >= maximoDeCaracteres)
					e.consume();
			}
		});

		JButton siguienteBoton = new JButton("Siguiente palabra!");
		JButton dameUnaPista = new JButton("Dame una pista!");
		JButton terminarBoton = new JButton("Salir del juego");
		JButton arriesgarBoton = new JButton("Arriesga!");

		siguienteBoton.setEnabled(false);
		siguienteBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(_juego.seAcabaronLasPalabras()) {
					terminarJuego();
				}
				_juego.nuevaJugada();
				mostrarLetrasIngresadas();
				mostrarIntentos();
				_mostrarUnaPista.setText("");
				mostrarPalabra();
				siguienteBoton.setEnabled(false);
				dameUnaPista.setEnabled(true);
				arriesgarBoton.setEnabled(true);
			}
		});
		siguienteBoton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		siguienteBoton.setBounds(473, 340, 214, 33);
		_juegoFrame.getContentPane().add(siguienteBoton);

		dameUnaPista.setEnabled(true);
		dameUnaPista.setHorizontalAlignment(SwingConstants.LEADING);
		dameUnaPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dameUnaPista.setEnabled(false);
				_mostrarUnaPista.setText(_juego.damePista());
			}
		});
		dameUnaPista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dameUnaPista.setBounds(715, 314, 180, 35);
		_juegoFrame.getContentPane().add(dameUnaPista);

		terminarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguienteBoton.setEnabled(false);
				dameUnaPista.setEnabled(true);
				arriesgarBoton.setEnabled(true);
				terminarJuego();
			}
		});
		terminarBoton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		terminarBoton.setBounds(719, 459, 205, 33);
		_juegoFrame.getContentPane().add(terminarBoton);

		arriesgarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Character letra = _letraArriesgada.getText().length() > 0 ? _letraArriesgada.getText().charAt(0) : '*';
				_letraArriesgada.setText("");
				try {
					_juego.procesarLetra(letra);
					if (_juego.palabraCompleta()) {
						siguienteBoton.setEnabled(true);
						dameUnaPista.setEnabled(false);
						arriesgarBoton.setEnabled(false);
					}
					mostrarLetrasIngresadas();
					mostrarPalabra();
					mostrarPuntaje();
					mostrarIntentos();
					
					if (_juego.noHayMasIntentos())
						terminarJuego();

				} catch (IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(null, "Caracter invalido. Se espera una letra del abecedario.");
				}
			}
		});
		arriesgarBoton.setBounds(473, 294, 145, 33);
		arriesgarBoton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_juegoFrame.getContentPane().add(arriesgarBoton);

		_juegoFrame.getContentPane().add(_imagenFondo);
	}

	private void mostrarPalabra() {
		String estadoDePalabra = _juego.getEstadoDePalabra();
		StringBuilder estadoDePalabraMostrar = new StringBuilder("");
		for (int i = 0; i < estadoDePalabra.length(); i++) {
			estadoDePalabraMostrar.append(estadoDePalabra.charAt(i) + " ");
		}
		_palabraACompletar.setText(estadoDePalabraMostrar.toString());
	}

	private void mostrarLetrasIngresadas() {
		StringBuilder estadoDeLetrasIngresadas = new StringBuilder("");
		for(Character letra : _juego.getLetrasArriesgadas()) {
			estadoDeLetrasIngresadas.append(letra + " ");
		}
		_letrasIngresadas.setText(estadoDeLetrasIngresadas.toString());
	}

	private void mostrarIntentos() {
		Integer intentos = _juego.getCantidadDeIntentos();
		_intentosDisponibles.setText(intentos.toString());
	}

	private void mostrarPuntaje() {
		Integer puntos = _juego.getPuntaje();
		_puntaje.setText(puntos.toString());
	}

	private void terminarJuego() {

		_juegoFrame.getContentPane().removeAll();
		_juegoFrame.repaint();

		JLabel finDelJuegoTexto = new JLabel("Fin del juego");
		finDelJuegoTexto.setFont(new Font("Tahoma", Font.PLAIN, 99));
		finDelJuegoTexto.setBounds(135, 88, 589, 130);
		_juegoFrame.getContentPane().add(finDelJuegoTexto);

		Integer puntajeInt = Integer.parseInt(_puntaje.getText());
		String mensajeDeValoracion = puntajeInt >= 50 ? "Bien hecho!" : "Podrias hacerlo mejor...";
		_puntajeFinalTexto.setText("Hiciste " + puntajeInt + " puntos. " + mensajeDeValoracion);
		_puntajeFinalTexto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		_puntajeFinalTexto.setBounds(125, 189, 730, 130);
		_juegoFrame.getContentPane().add(_puntajeFinalTexto);

		JButton reiniciarBoton = new JButton("Volver a jugar!");
		reiniciarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_juegoFrame.getContentPane().removeAll();
				_juegoFrame.repaint();
				menuJuego();
			}
		});
		reiniciarBoton.setBounds(719, 459, 205, 33);
		reiniciarBoton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_juegoFrame.getContentPane().add(reiniciarBoton);

		JButton botonRecords = new JButton("Records");
		botonRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				records();
				_puntaje.setText("0");
			}
		});
		botonRecords.setBounds(364, 459, 205, 33);
		botonRecords.setFont(new Font("Tahoma", Font.PLAIN, 20));
		_juegoFrame.getContentPane().add(botonRecords);
		
		_juegoFrame.getContentPane().add(_imagenFondo);

		recordsContentPane.setPuntosSuficientes(puntajeInt);
		if (recordsContentPane.getPuntosSuficientes()) {
			playerName = JOptionPane.showInputDialog("ingrese su nombre");
			recordsContentPane.setPlayerName(playerName);
			recordsContentPane.setPuntajeObtenido(Integer.parseInt(_puntaje.getText()));
		}	
	}
	
	private void records() {
		_juegoFrame.getContentPane().removeAll();
		_juegoFrame.repaint();
		recordsContentPane.agregarPuntaje();
		_juegoFrame.setContentPane(recordsContentPane);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_juegoFrame.getContentPane().removeAll();
				_juegoFrame.repaint();
				menuJuego();
			}
		});
		okButton.setBounds(429, 450, 89, 23);
		_juegoFrame.getContentPane().add(okButton);
	}
}
