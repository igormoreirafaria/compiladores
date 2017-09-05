/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author igor
 */
public class TokenOpAnalitico extends Token{
    
       private static Map<Character, String> opAnalitico = new HashMap<Character, String>();

    static {
        HashMap<Character, String> aMap = new HashMap<Character, String>();
        aMap.put('+', "ADD");
        aMap.put('-', "SUB");
        aMap.put('/', "DIV");
        aMap.put('*', "MUL");
        aMap.put('^', "POW");
        aMap.put('=', "ATTR");
        opAnalitico = Collections.unmodifiableMap((Map<Character, String>) aMap);
    }
    
    public TokenOpAnalitico(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo(opAnalitico.get(lexema.getLexema().charAt(0)));
                this.setValor(lexema.getLexema());
	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor()+" >";
	}
}
