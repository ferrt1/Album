package programaAlbum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AlbumTradicional {
	private Integer codigo;
	private HashMap<String, PaginaAlbum> _paginas;
	private List<String> figusPegadas;
	private String premioInstantaneo;
	private Integer seUsoPremio= 0;
	
	public AlbumTradicional() {
		_paginas = new HashMap<String, PaginaAlbum>();
		figusPegadas = new ArrayList<String>();
	}
	
	
	public void pegarFiguritas(ArrayList<Figuritas> figuritas) {
		for(Figuritas f: figuritas) {
			for(String pais: _paginas.keySet()) {
				if(f.get_pais().equals(pais)){
					_paginas.get(pais).pegarFigurita(f);
				}
			}
		}
	}
	
	//Se utiliza en paginaAlbumExtendido
	public void pegarFiguritasExtendido(ArrayList<Figuritas> figuritas) {
	}
	
	public boolean completoElPais(String pais) {
		return _paginas.get(pais).completoPais();
	}
	
	public void figusPegadas() {
		for(String pais: _paginas.keySet()) {
			for(Figuritas f: _paginas.get(pais).get_pegadas()) {
				if(!(figusPegadas.contains(f.toString())))
					figusPegadas.add(f.toString());
			}
		}
	}
	
	public Integer cantidadFiguritasPegadas() {
		return figusPegadas.size();
	}
	
	
	public String getPremioInstantaneo() {
		seUsoPremio +=1;
		return premioInstantaneo;
	}


	public void setPremioInstantaneo(String premioInstantaneo) {
		this.premioInstantaneo = premioInstantaneo;
	}
	
	public Integer getSeUsoPremio() {
		return seUsoPremio;
	}

	public List<String> getFigusPegadas() {
		figusPegadas();
		return figusPegadas;
	}


	public HashMap<String, PaginaAlbum> get_paginas() {
		return _paginas;
	}

	public void set_paginas(HashMap<String, PaginaAlbum> _paginas) {
		this._paginas = _paginas;
	}


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		if(!(obj instanceof AlbumTradicional))
			return false;
		AlbumTradicional otro = (AlbumTradicional) obj;
		
		return otro.getCodigo().equals(codigo);
		
	}
	
	public String toString() {
		StringBuilder st = new StringBuilder();
		for(String pais: _paginas.keySet()) {
			st.append("Seleccion de ");
			st.append(pais);
			st.append(": ");
			st.append(_paginas.get(pais));
		}
		return st.toString();
	}
	
	
}
