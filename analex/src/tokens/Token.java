/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * @author igor
 */
public class Token {
    
        private String token;
	private String simbolo;
	private String valor;
	private Lexema lexema;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Lexema getLexema() {
		return lexema;
	}

	public void setLexema(Lexema lexema) {
		this.lexema = lexema;
	}
	
	public String getStringLexema() {
		return this.lexema.getLexema();
	}
    
    
}
