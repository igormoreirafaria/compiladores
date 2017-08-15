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
public class TokenOpIncDec extends Token {
        
        private static Map<String, String> opIncDec = new HashMap<String, String>();

    static {
        HashMap<String, String> aMap = new HashMap<String, String>();
        aMap.put("++", "INC");
        aMap.put("--", "DEC");
        opIncDec = Collections.unmodifiableMap((Map<String, String>) aMap);
    }
    
    
    
	public TokenOpIncDec(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo(opIncDec.get(lexema.getLexema()));
                this.setValor(lexema.getLexema());
	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor()+" >";
	}

}
