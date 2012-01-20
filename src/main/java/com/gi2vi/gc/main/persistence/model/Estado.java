package com.gi2vi.gc.main.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "estado")
public class Estado {

	public enum EstadoMapping {
		idEstado("idEstado"), nombre("nombre"), clave("clave"), pais("pais");

		private final String field;

		private EstadoMapping(String field) {
			this.field = field;
		}

		public String getField() {
			return field;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstado;
	private String nombre;
	private String clave;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idPais", name = "idPais")
	private Pais pais;

	public Estado() {
		super();
	}

	public Estado(Integer idEstado, String nombre, String clave, Pais pais) {
		super();
		this.idEstado = idEstado;
		this.nombre = nombre;
		this.clave = clave;
		this.pais = pais;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}