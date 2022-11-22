package ahorcandoInterfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import ahorcandoLogica.Records;

public class RecordsJPanel extends JPanel {

	private Records _records;
	private String _playerName;
	private JTable _table;
	private int _puntajeObtenido;
	private Image _imagen;
	private boolean _puntosSuficientes = false;

	public RecordsJPanel() {
		
		this.setLayout(null);
		recordsJLabel();
		_records = new Records();
		_records.cargarPuntajes();
	}

	public void agregarPuntaje() {
		if (_records.puntajeSuficiente(getPuntajeObtenido())) {
			_records.agregarPuntaje(getPlayerName(), getPuntajeObtenido());
		}
		cargarPuntajes();
		setPlayerName("");
		setPuntajeObtenido(0);
	}

	private void recordsJLabel() {
		JLabel recordsJL = new JLabel("RECORDS");
		recordsJL.setForeground(Color.BLUE);
		recordsJL.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		recordsJL.setBounds(415, 11, 105, 29);
		this.add(recordsJL);
	}

	private void cargarPuntajes() {
		recordsJLabel();
		crearTabla();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Player");
		model.addColumn("Record");

		for (int i = 0; i < _records.getPuntajes().length; i++) {
			model.addRow(new String[] { (String) _records.getPuntajes()[i].getNombre(),
					_records.getPuntajes()[i].getPuntos().toString() });
		}

		_table.setModel(model);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.RIGHT);
		TableColumn tc = _table.getColumnModel().getColumn(1);
		tc.setCellRenderer(dtcr);
	}

	private void crearTabla() {
		_table = new JTable();
		_table.setShowGrid(false);
		_table.setFont(new Font("Sylfaen", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		_table.setGridColor(Color.LIGHT_GRAY);
		_table.setForeground(Color.BLACK);
		_table.setBorder(null);
		_table.setBounds(187, 52, 547, 352);
		_table.setRowHeight(30);
		_table.setBackground(Color.cyan);
		_table.setOpaque(false);
		this.add(_table);
	}

	public void setPuntosSuficientes(int puntos) {
		this._puntosSuficientes = _records.puntajeSuficiente(puntos);
	}

	public String getPlayerName() {
		return this._playerName;
	}

	public void setPlayerName(String playerName) {
		this._playerName = playerName;
	}

	public int getPuntajeObtenido() {
		return _puntajeObtenido;
	}

	public void setPuntajeObtenido(int puntajeObtenido) {
		this._puntajeObtenido = puntajeObtenido;
	}

	public boolean getPuntosSuficientes() {
		return this._puntosSuficientes;
	}

	@Override
	public void paint(Graphics graphics) {
		_imagen = new ImageIcon(getClass().getResource("/ahorcandoInterfaz/fondo.jpg")).getImage();
		graphics.drawImage(_imagen, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(graphics);
	}
}
