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
public class TokenOperador extends Token {
        
        private static Map<String, String> opLogico = new HashMap<String, String>();

    static {
        HashMap<String, String> aMap = new HashMap<String, String>();
        aMap.put("&&", "AND");
        aMap.put("||", "OR");
        aMap.put(">", "GRT");
        aMap.put("<", "LET");
        aMap.put("<=", "LTE");
        aMap.put(">=", "GTE");
        aMap.put("==", "EQV");
        aMap.put("!=", "DIFF");
        opLogico = Collections.unmodifiableMap((Map<String, String>) aMap);
    }
    
    
    
	public TokenOperador(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo(opLogico.get(lexema.getLexema()));
                this.setValor(lexema.getLexema());
	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor()+" >";
	}

}
