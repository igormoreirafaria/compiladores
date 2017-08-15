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
public class TokenNumero extends Token {

	public TokenNumero(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo(lexema.getLexema());
		this.setValor("NUM");
	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor() + " >";
	}
}

