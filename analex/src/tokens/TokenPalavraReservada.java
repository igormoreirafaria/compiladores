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
public class TokenPalavraReservada extends Token {
    private static Map<String, String> palavrasReservada = new HashMap<String, String>();
    static {
        HashMap<String, String> aMap = new HashMap<String, String>();
        aMap.put("char", "CHAR");
        aMap.put("int", "INT");
        aMap.put("float", "FLOAT");
        aMap.put("if", "IF");
        aMap.put("for", "FOR");
        aMap.put("while", "WHILE");
        aMap.put("else","ELSE");
        aMap.put("return", "RTR");
        aMap.put("continue", "CTN");
        aMap.put("break", "BRK");
        aMap.put("main", "MAIN");
        aMap.put("print", "PRT");
        aMap.put("read", "SCF");
        
        palavrasReservada = Collections.unmodifiableMap((Map<String, String>) aMap);
    }
    
    
    
    public TokenPalavraReservada(Lexema lexema) {
		this.setLexema(lexema);
		this.setSimbolo(palavrasReservada.get(lexema.getLexema()));
                this.setValor(lexema.getLexema());

	}

	@Override
	public String getToken() {
		return "< " + this.getSimbolo() + " , " + this.getValor()+" >";
	}
}
