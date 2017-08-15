/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex.Token;

/**
 *
 * @author igor
 */
public class TokenIdentificador extends Token {
	private boolean declarado;
	private String tipo;
	private String registrador;
	
	public TokenIdentificador(Lexema lexema) {
		this.declarado = false;
		this.setLexema(lexema);
		this.setSimbolo("ID");
		this.setValor(lexema.getLexema());
	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor() + " >";
	}

	public boolean isDeclarado() {
		return declarado;
	}

	public void setDeclarado(boolean declarado) {
		this.declarado = declarado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRegistrador() {
		return registrador;
	}

	public void setRegistrador(String registrador) {
		this.registrador = registrador;
	}
}
