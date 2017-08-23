/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex.Token;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author igor
 */
public class TokenSeparador extends Token {

    private static Map<Character, String> separador = new HashMap<Character, String>();

    static {
        HashMap<Character, String> aMap = new HashMap<Character, String>();
        aMap.put(';', "SEMI");
        aMap.put('[', "OPEN_BRACE");
        aMap.put(']', "CLOSE_BRACE");
        aMap.put('(', "OPEN_PAR");
        aMap.put(')', "CLOSE_PAR");
        aMap.put('{', "OPEN_BRACKET");
        aMap.put('}', "CLOSE_BRACKET");
        aMap.put('\"', "QUOTATION");
        aMap.put('\'', "APOST");
        aMap.put(',', "COMMA");
        aMap.put('.', "DOT");
        aMap.put('\t', "TAB");
        aMap.put('\n', "ENTER");
        aMap.put(' ', "SPACE");
        separador = Collections.unmodifiableMap((Map<Character, String>) aMap);
    }
    
	public TokenSeparador(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo(separador.get(lexema.getLexema().charAt(0)));
                this.setValor(lexema.getLexema());

	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor()+" >";
	}

}
