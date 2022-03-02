package com.allocation.backend.projeto.entity;

public enum Area {
	BIOLOGICAS(1), EXATAS(2), HUMANAS(3);

	private int valorArea;

	Area(int valor) {
		valorArea = valor;
	}

	public int getValorArea() {
		return valorArea;
	}

	public void setValorArea(int valorArea) {
		this.valorArea = valorArea;
	}
	
	
}
