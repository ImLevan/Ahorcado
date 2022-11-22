package ahorcandoLogica;

import java.util.Arrays;
import java.util.Collections;

public class Records {
	
	private Usuario[] _usuarioPuntajes;

	public Records() {
		_usuarioPuntajes = new Usuario[10];
	}

	public void cargarPuntajes() {
		_usuarioPuntajes[0] = new Usuario("------", 0);
		_usuarioPuntajes[1] = new Usuario("------", 0);
		_usuarioPuntajes[2] = new Usuario("------", 0);
		_usuarioPuntajes[3] = new Usuario("------", 0);
		_usuarioPuntajes[4] = new Usuario("------", 0);
		_usuarioPuntajes[5] = new Usuario("------", 0);
		_usuarioPuntajes[6] = new Usuario("------", 0);
		_usuarioPuntajes[7] = new Usuario("------", 0);
		_usuarioPuntajes[8] = new Usuario("------", 0);
		_usuarioPuntajes[9] = new Usuario("------", 0);
	}

	private void ordenarPuntajes() {
		Arrays.sort(_usuarioPuntajes, Collections.reverseOrder());
	}

	public void agregarPuntaje(String nombre, int puntos) {
		_usuarioPuntajes[9] = puntajeSuficiente(puntos) ? new Usuario(nombre, puntos) : _usuarioPuntajes[9];
		ordenarPuntajes();
	}
	
	public boolean puntajeSuficiente(int puntos) {
		return puntos > _usuarioPuntajes[9].getPuntos();
	}

	public Usuario[] getPuntajes() {
		return _usuarioPuntajes;
	}
}
