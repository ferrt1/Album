package programaAlbum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participante {
	private AlbumTradicional album;
	private ArrayList<Figuritas> _figuritas;
	private Integer _DNI;
	private String _nombre;
	private String tipoAlbum;
	private boolean seUsoCodigoPromocional;

	public Participante(Integer DNI, String nombre) {
		_DNI = DNI;
		_nombre = nombre;
		_figuritas = new ArrayList<Figuritas>();
	}

	public void pegarFiguritas() {
		if(tipoAlbum.equals("Extendido")) {
			album.pegarFiguritasExtendido(_figuritas);
		}
		album.pegarFiguritas(_figuritas);
	}


	public Integer buscarFiguritaRepetida() {
		int codigo = _figuritas.get(0).get_codigo();
		for(int i = 0; i < _figuritas.size(); i++) {
			for(int j = i + 1; j < _figuritas.size(); j++) {
				if(_figuritas.get(j).equals(_figuritas.get(i)))
					return _figuritas.get(i).get_codigo();
			}
		}
		return codigo-1;
	}

	public boolean contieneFigurita(int codigo) {
		boolean ret = false;
		for(int i = 0; i < _figuritas.size(); i++) {
				ret = ret || _figuritas.get(i).get_codigo().equals(codigo);
		}
		return ret;
	}


	public Figuritas devuelveFiguritaPorCodigo(int codigo) {
		Figuritas f = new Figuritas(null, null, null, null);
		for(int i = 0; i < _figuritas.size(); i++) {
			if(_figuritas.get(i).get_codigo().equals(codigo)) {
				f = _figuritas.get(i);
			}
		}
		return f;
	}

	public boolean completoPais(String pais) {
		return album.completoElPais(pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
		return Objects.equals(_DNI, other._DNI);
	}

	public String toString() {
		return _nombre.toString();
	}

	public AlbumTradicional getAlbum() {
		return album;
	}


	public void setAlbum(AlbumTradicional album) {
		this.album = album;
	}

	public ArrayList<Figuritas> get_figuritas() {
		return _figuritas;
	}

	public void set_figuritas(List<Figuritas> list) {
		this._figuritas = (ArrayList) list;
	}



	public Integer get_DNI() {
		return _DNI;
	}

	public String get_nombre() {
		return _nombre;
	}

	public String getTipoAlbum() {
		return tipoAlbum;
	}

	public void setTipoAlbum(String tipoAlbum) {
		this.tipoAlbum = tipoAlbum;
	}

	public boolean isSeUsoCodigoPromocional() {
		return seUsoCodigoPromocional;
	}

	public void setSeUsoCodigoPromocional(boolean seUsoCodigoPromocional) {
		this.seUsoCodigoPromocional = seUsoCodigoPromocional;
	}


}
