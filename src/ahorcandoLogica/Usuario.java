package ahorcandoLogica;

public class Usuario implements Comparable<Usuario>{
	
	private String _nombre;
	private Integer _puntaje;
	
	public Usuario(String nombre, Integer puntaje)  {
		_nombre = nombre;
		_puntaje = puntaje;
	}

	public String getNombre() {
		return _nombre;
	}

	public Integer getPuntos() {
		return _puntaje;
	}
	
	@Override
	public int compareTo(Usuario otroUsuaro) {
		return (int) _puntaje - (int) otroUsuaro.getPuntos();
	}

}
