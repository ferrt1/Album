package programaAlbum;

import java.util.Objects;

public class Figuritas {
	private String _pais;
	private String _nombreJugador; //Lo tengo por que se pedia en la 1er parte pero no lo utilizo
	private Integer _codigo;
	private Integer _valor;
	private Integer _posicion;

	public Figuritas(String pais,Integer posicion, Integer codigo, Integer valorBase) {
		this._pais = pais;
		this._codigo = codigo;
		this._posicion = posicion;
		this._valor = valorBase;
	}

	
	public Integer get_codigo() {
		return _codigo;
	}
	
	public void set_codigo(Integer codigo) {
		this._codigo = codigo;
	}
	
	public String get_pais() {
		return _pais;
	}

	public void set_pais(String _pais) {
		this._pais = _pais;
	}

	public Integer get_posicion() {
		return _posicion;
	}
	

	public void set_posicion(Integer _posicion) {
		this._posicion = _posicion;
	}
	
	public Integer get_valor() {
		return _valor;
	}
	
	public void set_valor(double d) {
		this._valor = (int)d;
	}

	
	public String toString() {
		return _pais + " [" + _posicion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(_pais, _posicion);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		if(!(obj instanceof Figuritas))
			return false;
		
		Figuritas otro = (Figuritas) obj;
		
		return _codigo.equals(otro._codigo);
		
	}
	
	
}
