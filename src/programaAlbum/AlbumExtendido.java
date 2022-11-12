package programaAlbum;

import java.util.ArrayList;
import java.util.HashMap;

public class AlbumExtendido extends AlbumTradicional {

	private HashMap<String, PaginaAlbumExtendido> _paginasExtendido;
	

	public AlbumExtendido() {
		_paginasExtendido = new HashMap<String, PaginaAlbumExtendido>();
	}
	
	@Override
	public void pegarFiguritasExtendido(ArrayList<Figuritas> figuritas) {
		for(Figuritas f: figuritas) {
			for(String pais: _paginasExtendido.keySet()) {
				if(f.get_pais().equals(pais)){
					_paginasExtendido.get(pais).pegarFigurita(f);
				}
			}
		}
	}
	

	public HashMap<String, PaginaAlbumExtendido> get_paginasExtendido() {
		return _paginasExtendido;
	}

	public void set_paginasExtendido(HashMap<String, PaginaAlbumExtendido> _paginasExtendido) {
		this._paginasExtendido = _paginasExtendido;
	}
	
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append(super.toString());
		st.append("\n");
		st.append("SECTOR TOP 10: ");
		for(String pais: _paginasExtendido.keySet()) {
			st.append("|");
			st.append("Pais Sede ");
			st.append("[");
			st.append(pais);
			st.append("]");
			st.append(" ");
			st.append(_paginasExtendido.get(pais));
			st.append("|");
		}
		
		return st.toString();
	}
	
}
