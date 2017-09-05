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
public class TokenNumeroFLOAT extends Token {

	public TokenNumeroFLOAT(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo("FLOAT_CONST");
		this.setValor(lexema.getLexema());
	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor() + " >";
	}
}

