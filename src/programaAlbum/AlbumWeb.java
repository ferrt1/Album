package programaAlbum;

import java.util.Random;

public class AlbumWeb extends AlbumTradicional{
	private Integer codigoPromocional;
	
	public AlbumWeb() {
		super();
		// TODO Auto-generated constructor stub
		codigoPromocional = generarCodigoPromocional();
	}
	
	public Integer generarCodigoPromocional() {
		int random = (int) Math.random()*10000;
		return random;
	}
	
	public Integer getCodigoPromocional() {
		return codigoPromocional;
	}
	
	
}
