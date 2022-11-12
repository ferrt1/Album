package programaAlbum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PaginaAlbum {
	
	private HashMap<Integer, Figuritas> _posiciones;
	private List<Figuritas> _pegadas;
	

	public PaginaAlbum() {
		_posiciones = new HashMap<Integer, Figuritas>();
		_pegadas = new ArrayList<Figuritas>();
		crearPagina();
	}

	public void crearPagina() {
		for(int i = 0; i < 12; i++) {
			_posiciones.put(i, null);
		}
	}
	
	public boolean completoPais() {
		boolean ret = true;
		for(Integer posiciones: _posiciones.keySet()) {
				ret = true && _posiciones.get(posiciones)!=null;
		}
		return ret;
	}
	

	public void pegarFigurita(Figuritas figurita) {
			for(Integer posicion : _posiciones.keySet()) {
				if(posicion.equals(figurita.get_posicion()) && _posiciones.get(posicion)==null) {
					_posiciones.put(posicion, figurita);
					_pegadas.add(figurita);
				}
			}
	}
	
	public List<Figuritas> get_pegadas() {
		return _pegadas;
	}

	public HashMap<Integer, Figuritas> get_posiciones() {
		return _posiciones;
	}

	public void set_posiciones(HashMap<Integer, Figuritas> _posiciones) {
		this._posiciones = _posiciones;
	}

	
		
	public String toString() {
		StringBuilder st = new StringBuilder();
		for(Integer posicion: _posiciones.keySet()) {
			st.append(_posiciones.get(posicion));
			if(posicion != _posiciones.size()-1)
				st.append(", ");
			st.append(" ");
			}
		return st.toString();
	}
}
