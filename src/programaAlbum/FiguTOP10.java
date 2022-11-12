package programaAlbum;

public class FiguTOP10 extends Figuritas {
	private String _balon;
	

	public FiguTOP10(String pais,String balon, Integer posicion, Integer codigo, Integer valorBase) {
		super(pais, posicion, codigo, valorBase);
		_balon = balon;
	}
	
	public String get_balon() {
		return _balon;
	}
	
	public void set_balon(String balon) {
		this._balon = balon;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		if(get_posicion() == 0) {
			st.append("Oro: ");
			st.append(_balon);
		}
		else {
			st.append("Plata : ");
			st.append(_balon);
		}
		return st.toString();
	}
}
