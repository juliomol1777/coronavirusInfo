package com.codeM.coronavirusInfo.model;

public class EstadisticasRegion {
	
	private String estado;
    private String pais;
    private int ultimosCasos;
    private int diferenciaDiaria;
    
    
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getUltimosCasos() {
		return ultimosCasos;
	}
	public void setUltimosCasos(int ultimosCasos) {
		this.ultimosCasos = ultimosCasos;
	}
	public int getDiferenciaDiaria() {
		return diferenciaDiaria;
	}
	public void setDiferenciaDiaria(int diferenciaDiaria) {
		this.diferenciaDiaria = diferenciaDiaria;
	}
	
	@Override
	public String toString() {
		return "EstadisticasRegion [estado=" + estado + ", pais=" + pais + ", ultimosCasos=" + ultimosCasos
				+ ", diferenciaDiaria=" + diferenciaDiaria + "]";
	}
	
}
