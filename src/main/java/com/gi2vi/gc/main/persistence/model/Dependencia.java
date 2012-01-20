package com.gi2vi.gc.main.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "dependencia")
public class Dependencia {

	public enum DependenciaMapping {
		idDependencia("idDependencia"), nombre("nombre"), telefono("telefono"), direccion(
				"direccion"), cp("cp"), municipio("municipio"), estado("estado");

		private final String field;

		private DependenciaMapping(String field) {
			this.field = field;
		}

		public String getField() {
			return field;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDependencia;
	private String nombre;
	private String telefono;
	private String direccion;
	private String cp;
	private String municipio;
	@ManyToOne
	@JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
	private Estado estado;

	public Dependencia() {
		super();
	}

	public Dependencia(Integer idDependencia, String nombre, String telefono,
			String direccion, String cp, String municipio, Estado estado) {
		super();
		this.idDependencia = idDependencia;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.cp = cp;
		this.municipio = municipio;
		this.estado = estado;
	}

	public Integer getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}