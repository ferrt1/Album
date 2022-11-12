package programaAlbum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaginaAlbumExtendido extends PaginaAlbum{

	public PaginaAlbumExtendido() {
		super();
	}
	
	@Override
	public void crearPagina() {
		for(int i = 0; i < 2; i++) {
			get_posiciones().put(i, null);
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		for(Integer posicion: get_posiciones().keySet()) {
			st.append(get_posiciones().get(posicion));
			st.append(", ");
			}
		return st.toString();
		}
	}


