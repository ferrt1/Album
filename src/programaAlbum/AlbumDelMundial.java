package programaAlbum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AlbumDelMundial implements IAlbumDelMundial {
	Fabrica fabrica;
	HashMap<Integer, Participante> _participantes;
	HashMap<Integer, AlbumTradicional> _albums;
	HashSet<Participante> _ganadores;

	public AlbumDelMundial() {
		_participantes = new HashMap<Integer, Participante>();
		_albums = new HashMap<Integer, AlbumTradicional>();
		_ganadores = new HashSet<>();
		fabrica = new Fabrica();
	}

	
	private Integer generarCodigo() {
		int random = (int) Math.random()*1000;
		if(_albums.containsKey(random)) {
			random = (int) Math.random()*1000;
		}
		return random;
	}

	
	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		Participante nuevoParticipante = new Participante(dni, nombre);
		Integer codigo = generarCodigo();
		if(_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante esta registrado");

		if(!(tipoAlbum.equals("Tradicional") || tipoAlbum.equals("Web") || tipoAlbum.equals("Extendido")))
			throw new IllegalArgumentException("El album debe ser Tradicional, Web o Extendido");

		_participantes.put(dni, nuevoParticipante);

		if(tipoAlbum.equals("Tradicional")) {
			_participantes.get(dni).setAlbum(fabrica.crearAlbumTradicional());
			_participantes.get(dni).setTipoAlbum("Tradicional");
		}
		else if(tipoAlbum.equals("Web")) {
			_participantes.get(dni).setAlbum(fabrica.crearAlbumWeb());
			_participantes.get(dni).setTipoAlbum("Web");
		}
		else if(tipoAlbum.equals("Extendido")) {
			_participantes.get(dni).setAlbum(fabrica.crearAlbumExtendido());
			_participantes.get(dni).setTipoAlbum("Extendido");
		}
		_albums.put(codigo, nuevoParticipante.getAlbum());
		return codigo;
	}



	@Override
	public void comprarFiguritas(int dni) {
		if(!_participantes.containsKey(dni)) {
			throw new RuntimeException("El participante no esta registrado");
		}
		_participantes.get(dni).get_figuritas().addAll(fabrica.generarSobre(1));
	}	



	@Override
	public void comprarFiguritasTop10(int dni) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");
		if(!_participantes.get(dni).getTipoAlbum().equals("Extendido"))
			throw new RuntimeException("El participante no tiene album extendido");

		_participantes.get(dni).get_figuritas().addAll(fabrica.generarSobreTop10(1));
	}


	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");
		if(!_participantes.get(dni).getTipoAlbum().equals("Web"))
			throw new RuntimeException("El participante no tiene album extendido");
		if(_participantes.get(dni).isSeUsoCodigoPromocional())
			throw new RuntimeException("El participante ya uso su codigo tradicional");

		_participantes.get(dni).get_figuritas().addAll(fabrica.generarSobre(1));
		_participantes.get(dni).setSeUsoCodigoPromocional(true);
	}



	@Override
	public List<String> pegarFiguritas(int dni) {
		if(!_participantes.containsKey(dni)) {
			throw new RuntimeException("El participante no esta registrado");
		}
		_participantes.get(dni).pegarFiguritas();
		return _participantes.get(dni).getAlbum().getFigusPegadas();
	}


	@Override
	public boolean llenoAlbum(int dni) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");

		return _participantes.get(dni).getAlbum().cantidadFiguritasPegadas() == 384;
	}



	@Override
	public String aplicarSorteoInstantaneo(int dni) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");
		if(!_participantes.get(dni).getTipoAlbum().equals("Tradicional"))
			throw new RuntimeException("El participante no tiene album tradicional");
		if(_participantes.get(dni).getAlbum().getSeUsoPremio().equals(1))
			throw new RuntimeException("El participante ya uso el codigo");

		return _participantes.get(dni).getAlbum().getPremioInstantaneo();
	}



	@Override
	public int buscarFiguritaRepetida(int dni) {
		return _participantes.get(dni).buscarFiguritaRepetida();
	}



	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");
		if(!(_participantes.get(dni).contieneFigurita(codFigurita))) {
			throw new RuntimeException("El participante no tiene esa figurita");
		}
		if(_participantes.get(dni).get_figuritas().size()== 0)
			return false;



		for(Integer participanteDni: _participantes.keySet()) {
			if(!(participanteDni.equals(dni))) {
				if(_participantes.get(dni).getTipoAlbum().equals(_participantes.get(participanteDni).getTipoAlbum())
						&& _participantes.get(participanteDni).get_figuritas().size() != 0) {
					for(Figuritas f: _participantes.get(participanteDni).get_figuritas()) {
						if(f.get_valor() <= _participantes.get(dni).devuelveFiguritaPorCodigo(codFigurita).get_valor()) 
							return true;
					}
				}
			}
		}
		return false;
	}


	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");
		if(_participantes.get(dni).get_figuritas().size()== 0)
			return false;

		Integer codFigurita = buscarFiguritaRepetida(dni);
			for(Integer participanteDni: _participantes.keySet()) {
				if(!(participanteDni.equals(dni))) {
					if(_participantes.get(dni).getTipoAlbum().equals(_participantes.get(participanteDni).getTipoAlbum())
							&& _participantes.get(participanteDni).get_figuritas().size() != 0) {
						for(Figuritas f: _participantes.get(participanteDni).get_figuritas()) {
							Integer valor = f.get_valor();
							if(!_participantes.get(dni).contieneFigurita(codFigurita)) {
								Integer valor2 = _participantes.get(dni).devuelveFiguritaPorCodigo(codFigurita+1).get_valor();
								if(valor <= valor2) 
									return true;
							}
							Integer valor2 = _participantes.get(dni).devuelveFiguritaPorCodigo(codFigurita).get_valor();
								if(valor <= valor2) 
								return true;
						}
					}
				}
			}
		return false;
	}


	@Override
	public String darNombre(int dni) {
		if(!(_participantes.containsKey(dni)))
			throw new RuntimeException("El participante no esta registrado");
		return _participantes.get(dni).get_nombre();
	}



	@Override
	public String darPremio(int dni) {
		if(!_participantes.containsKey(dni)) 
			throw new RuntimeException("El participante no esta registrado");
		if(!llenoAlbum(dni)) {
			throw new RuntimeException("El participante no lleno el album");
		}
		if(_participantes.get(dni).getTipoAlbum().equals("Tradicional") && llenoAlbum(dni)) {
			return "Pelota";
		}
		if(_participantes.get(dni).getTipoAlbum().equals("Web") && llenoAlbum(dni)) {
			return "Camiseta oficial de la seleccion";
		}
		if(_participantes.get(dni).getTipoAlbum().equals("Web") && llenoAlbum(dni)) {
			return "Pelota y viaje";
		}

		return null;
	}

	@Override
	public String listadoDeGanadores() {
		
		Iterator<Integer> iterador = _participantes.keySet().iterator();
		while(iterador.hasNext()) {
			Integer dni = iterador.next();
			if(llenoAlbum(dni)) 
				_ganadores.add(_participantes.get(dni));
		}
		if(_ganadores.size() ==0)
			return "";
		StringBuilder st = new StringBuilder();
		for(Participante p: _ganadores) {
			st.append("(");
			st.append(p.get_DNI());
			st.append(")");
			st.append(p.get_nombre());
			st.append(": ");
			st.append(p.getTipoAlbum());
			st.append(" ");
		}
		return st.toString();
	}

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		List<String> participanteCompletoPais = new ArrayList<String>();
		for(Integer dni: _participantes.keySet()) {
			if(_participantes.get(dni).completoPais(nombrePais)) {
				participanteCompletoPais.add(_participantes.get(dni).toString());
			}
		}
		return participanteCompletoPais;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		for(Integer dni: _participantes.keySet()) {
			st.append("Participante: ");
			st.append(_participantes.get(dni));
			st.append(",");
			st.append(" con su album: ");
			st.append("\n");
			st.append(_participantes.get(dni).getAlbum());
			st.append("\n");
			if(llenoAlbum(dni)) 
				st.append("Logro llenar su album!");
			else if(!llenoAlbum(dni)) {
				st.append("No logro llenarlo :(");
			}
			st.append("\n");
			st.append("==================================");
			st.append("\n");
		}

		return st.toString();
	}
}