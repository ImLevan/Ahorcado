package ahorcandoLogica;

public class Palabra {
	private String _palabra;
	private Integer _puntaje;
	private String _pista;

	public Palabra(String palabra, Integer puntaje, String pista) {
		_palabra = palabra;
		_puntaje = puntaje;
		_pista = pista;
	}

	public Integer getPuntaje() {
		return _puntaje;
	}

	public String getPista() {
		return _pista;
	}

	@Override
	public String toString() {
		return _palabra;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_palabra == null) ? 0 : _palabra.hashCode());
		result = prime * result + ((_pista == null) ? 0 : _pista.hashCode());
		result = prime * result + ((_puntaje == null) ? 0 : _puntaje.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palabra other = (Palabra) obj;
		if (_palabra == null) {
			if (other._palabra != null)
				return false;
		} else if (!_palabra.equals(other._palabra))
			return false;
		if (_pista == null) {
			if (other._pista != null)
				return false;
		} else if (!_pista.equals(other._pista))
			return false;
		if (_puntaje == null) {
			if (other._puntaje != null)
				return false;
		} else if (!_puntaje.equals(other._puntaje))
			return false;
		return true;
	}
}

