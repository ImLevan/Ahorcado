package ahorcandoLogica;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AhorcandoSwing {

	private Set<Palabra> _palabrasFacilEspaol;
	private Set<Palabra> _palabrasDificilEspaol;
	private Set<Palabra> _palabrasFacilIngles;
	private Set<Palabra> _palabrasDificilIngles;
	private Set<Character> _letrasArriesgadas;
	private Palabra _palabraSeleccionada;
	private String _estadoDePalabra;
	private Integer _cantidadDeIntentos;
	private Integer _puntaje;
	private Boolean _ayudaPista;
	private Boolean _esIngles;
	private final Integer _puntajeMaximoModoFacil = 50;
	private final Integer _cantidadInicialDeIntentos = 6;
	private final Integer _cantidadInicialDePuntos = 0;

	public AhorcandoSwing() {
		_palabrasFacilEspaol = new HashSet<>();
		_palabrasDificilEspaol = new HashSet<>();
		_palabrasFacilIngles = new HashSet<>();
		_palabrasDificilIngles = new HashSet<>();
		_letrasArriesgadas = new HashSet<>();
		_ayudaPista = false;
		_esIngles = false;
	}

	public void inicializarJuego() {
		cargarPalabras();
		_cantidadDeIntentos = _cantidadInicialDeIntentos;
		_puntaje = _cantidadInicialDePuntos;
		borrarSetLetrasArriesgadas();
	}

	public void nuevaJugada() {
		borrarSetLetrasArriesgadas();
		if(_esIngles) {
			seleccionarPalabra(_palabrasFacilIngles, _palabrasDificilIngles);
		}else {
			seleccionarPalabra(_palabrasFacilEspaol, _palabrasDificilEspaol);
		}
		_cantidadDeIntentos = _cantidadInicialDeIntentos;
		_ayudaPista = false;
	}

	public void procesarLetra(Character letra) throws IllegalArgumentException {
		if (Character.isAlphabetic(letra)) {
			Character.toLowerCase(letra);
			cargarLetraIngresada(letra);
			if (letraEstaEnLaPalabra(letra)) {
				actualizarEstadoDePalabra(letra);
				if (palabraCompleta()) {
					aumentarPuntaje();
				}
			} else {
				restarPuntaje();
				disminuirIntentos();
			}
		} else {
			throw new IllegalArgumentException("El metodo debe ser llamado con un caracter alfanumerico: " + letra);
		}
	}

	private void seleccionarPalabra(Set<Palabra> faciles, Set<Palabra> dificiles) {
		_estadoDePalabra = "";
		Random seleccionaCadenaAdivinar = new Random();
		Set<Palabra> listaDePalabras = _puntaje <= _puntajeMaximoModoFacil ? faciles : dificiles;
		int resultado = seleccionaCadenaAdivinar.nextInt(listaDePalabras.size());
		int i = 0;
		for (Palabra p : listaDePalabras) {
			if (i == resultado) {
				_palabraSeleccionada = p;
				break;
			} else {
				i++;
			}
		}

		inicializarEstadoDePalabra();
		eliminarPalabraDelSet();
	}

	private void actualizarEstadoDePalabra(char letra) {
		StringBuilder nuevoEstado = new StringBuilder("");
		for (int i = 0; i < _palabraSeleccionada.toString().length(); i++) {
			if (_palabraSeleccionada.toString().charAt(i) == letra) {
				nuevoEstado.append(letra);
			} else {
				nuevoEstado.append(_estadoDePalabra.charAt(i));
			}
		}
		_estadoDePalabra = nuevoEstado.toString();
	}

	private boolean letraEstaEnLaPalabra(char letra) throws IllegalArgumentException {
		return _palabraSeleccionada.toString().indexOf(letra) != -1;
	}

	private void cargarPalabras() {
		final Integer puntajePalabrasFaciles = 10;
		final Integer puntajePalabrasDificiles = 15;
		if(!_esIngles) {
			_palabrasFacilEspaol.add(new Palabra("isla", puntajePalabrasFaciles, "Porcion de tierra rodeada de agua"));
			_palabrasFacilEspaol.add(new Palabra("alga", puntajePalabrasFaciles, "Planta que vive en el agua"));
			_palabrasFacilEspaol.add(new Palabra("muelle", puntajePalabrasFaciles, "Permite a barcos atracar"));
			_palabrasFacilEspaol.add(new Palabra("mar", puntajePalabrasFaciles, "Masa de agua salada"));
			_palabrasFacilEspaol.add(new Palabra("playa", puntajePalabrasFaciles, "Extension de arena en la orilla del mar"));
			_palabrasFacilEspaol.add(new Palabra("arena", puntajePalabrasFaciles, "El 'suelo' de la playa"));
			_palabrasFacilEspaol.add(new Palabra("ancla", puntajePalabrasFaciles, "Utilizado para fijar el barco al mar"));
			_palabrasFacilEspaol.add(new Palabra("ballena", puntajePalabrasFaciles, "Uno de los cetaceos mas grandes "));
			_palabrasFacilEspaol.add(new Palabra("coral", puntajePalabrasFaciles, "Invertebrado de pequeo tamao"));
			_palabrasFacilEspaol.add(new Palabra("calamar", puntajePalabrasFaciles, "Molusco marino de cuerpo alargado"));
			_palabrasFacilEspaol.add(new Palabra("marea", puntajePalabrasFaciles, "Cambio del nivel del mar"));
			_palabrasFacilEspaol
					.add(new Palabra("espuma", puntajePalabrasFaciles, "Burbujas que se forma sobre algunos liquidos"));
			_palabrasFacilEspaol.add(new Palabra("mapa", puntajePalabrasFaciles, "Representa una parte de la tierra en papel"));
			_palabrasFacilEspaol.add(new Palabra("barco", puntajePalabrasFaciles, "Transporte en el mar/oceano"));
			_palabrasFacilEspaol.add(new Palabra("ostra", puntajePalabrasFaciles, "Puede contener una perla dentro"));
			_palabrasFacilEspaol.add(new Palabra("perla", puntajePalabrasFaciles, "Utilizado para joyeria, brillante"));
			_palabrasFacilEspaol.add(new Palabra("pirata", puntajePalabrasFaciles, "Persona que asalta barcos"));
			_palabrasFacilEspaol.add(new Palabra("pulpo", puntajePalabrasFaciles, "Molusco de 8 tentaculos"));
			_palabrasFacilEspaol.add(new Palabra("remo", puntajePalabrasFaciles, "Instrumento para remar"));
			_palabrasFacilEspaol.add(new Palabra("proa", puntajePalabrasFaciles, "Parte delantera de una embarcacion"));
			_palabrasFacilEspaol.add(new Palabra("popa", puntajePalabrasFaciles, "Parte posterior de una embarcacion"));
			_palabrasFacilEspaol.add(new Palabra("balsa", puntajePalabrasFaciles, "Embarcacion pequea de madera"));
			_palabrasFacilEspaol.add(new Palabra("capitan", puntajePalabrasFaciles, "Maxima autoridad a bordo"));
			_palabrasDificilEspaol.add(new Palabra("arrecife", puntajePalabrasDificiles, "Comunidad marina poco profunda"));
			_palabrasDificilEspaol.add(new Palabra("astillero", puntajePalabrasDificiles, "Lugar donde se reparan buques"));
			_palabrasDificilEspaol.add(new Palabra("bermudas", puntajePalabrasDificiles, "Pantalon corto hasta las rodillas"));
			_palabrasDificilEspaol.add(new Palabra("bikini", puntajePalabrasDificiles, "Traje de bao femenino"));
			_palabrasDificilEspaol.add(new Palabra("brujula", puntajePalabrasDificiles, "Instrumento de orientacion"));
			_palabrasDificilEspaol.add(new Palabra("bitacora", puntajePalabrasDificiles, "Lugar donde se pone la brujula"));
			_palabrasDificilEspaol
					.add(new Palabra("cangrejo", puntajePalabrasDificiles, "Crustaceo con patas en forma de pinzas"));
			_palabrasDificilEspaol.add(new Palabra("tripulacion", puntajePalabrasDificiles, "Encargados de manejar un barco"));
			_palabrasDificilEspaol
					.add(new Palabra("corriente", puntajePalabrasDificiles, "Movimiento de las aguas en el oceano"));
			_palabrasDificilEspaol
					.add(new Palabra("escotilla", puntajePalabrasDificiles, "Abertura en la cubierta de una embarcacion"));
			_palabrasDificilEspaol.add(new Palabra("orilla", puntajePalabrasDificiles, "Parte de la tierra que toca el mar"));
			_palabrasDificilEspaol.add(new Palabra("estribor", puntajePalabrasDificiles, "Costado derecho de una embarcacion"));
			_palabrasDificilEspaol.add(new Palabra("medusa", puntajePalabrasDificiles, "Tambien llamada aguavivas"));
			_palabrasDificilEspaol.add(new Palabra("gaviota", puntajePalabrasDificiles, "Ave que habita en las costas"));
			_palabrasDificilEspaol.add(new Palabra("tortuga", puntajePalabrasDificiles, "Reptil con duro caparazon"));
			_palabrasDificilEspaol.add(new Palabra("pelicano", puntajePalabrasDificiles, "Ave acuatica de largo pico"));
			_palabrasDificilEspaol.add(new Palabra("marinero", puntajePalabrasDificiles, "Tripulante de embarcacion"));
			_palabrasDificilEspaol.add(new Palabra("naufragio", puntajePalabrasDificiles, "Hundimiento de una embarcacion"));
			_palabrasDificilEspaol.add(new Palabra("pescador", puntajePalabrasDificiles, "Persona que pesca"));
			_palabrasDificilEspaol.add(new Palabra("sirena", puntajePalabrasDificiles, "Ser mitologico con torso de mujer"));
			_palabrasDificilEspaol.add(new Palabra("tsunami", puntajePalabrasDificiles, "Ola de grandes dimensiones"));
			_palabrasDificilEspaol.add(new Palabra("tormenta", puntajePalabrasDificiles,
					"Fenomeno meteorologico producido por vientos fuertes"));
			_palabrasDificilEspaol.add(
					new Palabra("polizon", puntajePalabrasDificiles, "Persona que embarca clandestinamente en un barco"));
		}else {
			_palabrasFacilIngles.add(new Palabra("island", puntajePalabrasFaciles, "Porcion de tierra rodeada de agua"));
			_palabrasFacilIngles.add(new Palabra("alga", puntajePalabrasFaciles, "Planta que vive en el agua"));
			_palabrasFacilIngles.add(new Palabra("dock", puntajePalabrasFaciles, "Permite a barcos atracar"));
			_palabrasFacilIngles.add(new Palabra("sea", puntajePalabrasFaciles, "Masa de agua salada"));
			_palabrasFacilIngles.add(new Palabra("beach", puntajePalabrasFaciles, "Extension de arena en la orilla del mar"));
			_palabrasFacilIngles.add(new Palabra("sand", puntajePalabrasFaciles, "El 'suelo' de la playa"));
			_palabrasFacilIngles.add(new Palabra("anchor", puntajePalabrasFaciles, "Utilizado para fijar el barco al mar"));
			_palabrasFacilIngles.add(new Palabra("whale", puntajePalabrasFaciles, "Uno de los cetaceos mas grandes "));
			_palabrasFacilIngles.add(new Palabra("coral", puntajePalabrasFaciles, "Invertebrado de pequeo tamao"));
			_palabrasFacilIngles.add(new Palabra("squid", puntajePalabrasFaciles, "Molusco marino de cuerpo alargado"));
			_palabrasFacilIngles.add(new Palabra("wave", puntajePalabrasFaciles, "Cambio del nivel del mar"));
			_palabrasFacilIngles
					.add(new Palabra("foam", puntajePalabrasFaciles, "Burbujas que se forma sobre algunos liquidos"));
			
			_palabrasDificilIngles.add(new Palabra("bank", puntajePalabrasDificiles, "Parte de la tierra que toca el mar"));
			_palabrasDificilIngles.add(new Palabra("starboard", puntajePalabrasDificiles, "Costado derecho de una embarcacion"));
			_palabrasDificilIngles.add(new Palabra("jellyfish", puntajePalabrasDificiles, "Tambien llamada aguavivas"));
			_palabrasDificilIngles.add(new Palabra("seagull", puntajePalabrasDificiles, "Ave que habita en las costas"));
			_palabrasDificilIngles.add(new Palabra("turtle", puntajePalabrasDificiles, "Reptil con duro caparazon"));
			_palabrasDificilIngles.add(new Palabra("pelican", puntajePalabrasDificiles, "Ave acuatica de largo pico"));
			_palabrasDificilIngles.add(new Palabra("sailor", puntajePalabrasDificiles, "Tripulante de embarcacion"));
			_palabrasDificilIngles.add(new Palabra("shipwreck", puntajePalabrasDificiles, "Hundimiento de una embarcacion"));
			_palabrasDificilIngles.add(new Palabra("fisherman", puntajePalabrasDificiles, "Persona que pesca"));
			_palabrasDificilIngles.add(new Palabra("siren", puntajePalabrasDificiles, "Ser mitologico con torso de mujer"));
			_palabrasDificilIngles.add(new Palabra("tsunami", puntajePalabrasDificiles, "Ola de grandes dimensiones"));
			_palabrasDificilIngles.add(new Palabra("storm", puntajePalabrasDificiles,
					"Fenomeno meteorologico producido por vientos fuertes"));
		}
	}

	private void inicializarEstadoDePalabra() {
		for (int i = 0; i < _palabraSeleccionada.toString().length(); i++) {
			_estadoDePalabra += '_';
		}
	}

	private void eliminarPalabraDelSet() {
		if (_palabrasFacilEspaol.contains(_palabraSeleccionada)){
			_palabrasFacilEspaol.remove(_palabraSeleccionada);
		}
		else if(_palabrasDificilEspaol.contains(_palabraSeleccionada)) {
			_palabrasDificilEspaol.remove(_palabraSeleccionada);
		}
		else if (_palabrasFacilIngles.contains(_palabraSeleccionada)){
			_palabrasFacilIngles.remove(_palabraSeleccionada);
		}
		else {
			_palabrasDificilIngles.remove(_palabraSeleccionada);
		}
	}

	public void setEsIngles(boolean idioma) {
		_esIngles = idioma;
	}
	
	private void aumentarPuntaje() {
		final Double puntajeConAyuda = 0.5;

		_puntaje += (int) (!_ayudaPista ? _palabraSeleccionada.getPuntaje()
				: _palabraSeleccionada.getPuntaje() * puntajeConAyuda);
	}

	private void restarPuntaje() {
		final Integer puntajeEnModoFacil = 2;
		final Integer puntajeEnModoDificil = 4;

		if (juegoEnModoFacil()) {
			_puntaje -= puntajeEnModoFacil;
		} else {
			_puntaje -= puntajeEnModoDificil;
		}
		if (_puntaje < 0) {
			_puntaje = 0;
		}
	}

	private boolean juegoEnModoFacil() {
		return _puntaje <= _puntajeMaximoModoFacil;
	}

	private void cargarLetraIngresada(Character letra) {
		_letrasArriesgadas.add(letra);
	}

	private void borrarSetLetrasArriesgadas() {
		_letrasArriesgadas.removeAll(_letrasArriesgadas);
	}

	private void disminuirIntentos() {
		_cantidadDeIntentos--;
	}

	public boolean palabraCompleta() {
		return _estadoDePalabra.equals(_palabraSeleccionada.toString());
	}

	public String getPalabraSeleccionada() {
		return _palabraSeleccionada.toString();
	}

	public String getEstadoDePalabra() {
		return _estadoDePalabra;
	}

	public Integer getCantidadDeIntentos() {
		return _cantidadDeIntentos;
	}

	public Integer getPuntaje() {
		return _puntaje;
	}

	public Set<Character> getLetrasArriesgadas() {
		return _letrasArriesgadas;
	}
	
	public String damePista() {
		_ayudaPista = true;
		return _palabraSeleccionada.getPista();
	}

	public boolean seAcabaronLasPalabras() {
		if(_esIngles) {
			return _palabrasFacilIngles.size() == 0 && _palabrasDificilIngles.size() == 0;
		}
		return _palabrasFacilEspaol.size() == 0 && _palabrasDificilEspaol.size() == 0;
	}

	public boolean noHayMasIntentos() {
		return _cantidadDeIntentos < 0;
	}
}
