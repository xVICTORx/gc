package com.gi2vi.gc.main.persistence.model.util;

public enum Estatus {
	ACTIVO("ACTIVO"), INACTIVO("INACTIVO");

	private final String estatus;

	private Estatus(String estatus) {
		this.estatus = estatus;
	}

	public String getEstatus() {
		return estatus;
	}

}
