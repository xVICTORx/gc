package com.gi2vi.gc.main.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "pais")
public class Pais {

	public enum PaisMapping {
		idPais("idPais"), nombre("nombre"), codigo("codigo");

		private String field;

		private PaisMapping(String field) {
			this.field = field;
		}

		public String getField() {
			return field;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPais;
	private String nombre;
	private String clave;

	public Pais() {
		super();
	}

	public Pais(Integer idPais, String nombre, String clave) {
		super();
		this.idPais = idPais;
		this.nombre = nombre;
		this.clave = clave;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}