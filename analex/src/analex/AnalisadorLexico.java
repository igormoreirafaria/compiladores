/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;

import analex.Token.Lexema;
import analex.Token.Token;
import analex.Token.TokenIdentificador;
import analex.Token.TokenNumero;
import analex.Token.TokenOpAnalitico;
import analex.Token.TokenOpIncDec;
import analex.Token.TokenOperador;
import analex.Token.TokenPalavraReservada;
import analex.Token.TokenSeparador;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor
 */
public class AnalisadorLexico {

    private final ArrayList<Token> tokens = new ArrayList<>();

    public ArrayList<Token> getTokens() {
        return tokens;
    }
   
    public void Run(BufferedReader br) {
        char cabeca = 0;
        String token = "";
        String linha;
        try {
            int i = 0;
            while (br.ready()) {
                linha = br.readLine();
                for (int j = 0; j < linha.length(); ++j) {

                    cabeca = linha.charAt(j);
                    //System.out.println(cabeca);

                    //trata #include<>
                    if (cabeca == '#') {
                        token = "";
                        break;
                    }
                    
                    //trata espaço, tab e quebra de linha
                    
                    if((int)cabeca == 32 || (int)cabeca == 9 || (int)cabeca == 10) {
                        if(token.length() > 0){
                            regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                            token = "";
                        }
                        continue;
                    }
                    if(cabeca == '(' || cabeca == ')' || cabeca == '{'  || cabeca == '}' || cabeca == ';' || cabeca == ',' || cabeca == '[' || cabeca == ']'){
                        if(token.length() > 0){
                            regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));       
                            token = "";
                        }
                        if(cabeca == '(' || cabeca == ')' || cabeca == '{'  || cabeca == '}' || cabeca == ';' || cabeca == ',' || cabeca == '[' || cabeca == ']'){
                            regexMatch(new Lexema("" + cabeca, i + 1, (j+1) - token.length()));
                        }
                        continue;
                    }
                    
                    //literal em aspas simples
                    if(cabeca == '\''){
                        token = token + cabeca;
                        ++j;
                        for(;j<linha.length() && '\''!=linha.charAt(j);++j){
                            cabeca = linha.charAt(j);
                            token = token + cabeca;
                        }
                        cabeca = linha.charAt(j);
                        token = token + cabeca;
                        continue;
                    }
                    
                    //literal em aspas duplas
                    if(cabeca == '"'){
                        token = token + cabeca;
                        ++j;
                        for(;j<linha.length() && '"'!=linha.charAt(j);++j){
                            cabeca = linha.charAt(j);
                            token = token + cabeca;
                        }
                        cabeca = linha.charAt(j);
                        token = token + cabeca;
                        continue;
                    }
                    
                    //op aritmeticos, inc e dec
                    if(cabeca == '+' || cabeca == '-' || cabeca == '*' || cabeca == '/' || cabeca == '^'){
                        if(token.length() > 0){
                            regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                            token = "";
                        }
                        token = token + cabeca;
                        ++j;
                        cabeca = linha.charAt(j);
                        if(token.charAt(0) == '+' && cabeca == '+'){
                            token = token + cabeca;
                            regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                            token = "";
                        }else if(token.charAt(0) == '-' && cabeca == '-'){
                            token = token + cabeca;
                            regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                            token = "";
                        }
                        else{
                            regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                            token = "";
                            if((int)cabeca != 32 && (int)cabeca != 9 && (int)cabeca != 10) {
                                token = token + cabeca;
                            }
                        }
                        continue;
                    }
                    
                    //op Logicos e atribuição
                    if(cabeca == '&' || cabeca == '|' || cabeca == '<' || cabeca == '>' || cabeca == '=' || cabeca == '!') {
                        if(token.length() > 0) {
                                regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                                token = "";
                        }
                        token = token + cabeca;
                        ++j;
                        cabeca = linha.charAt(j);
                        if(cabeca == '+') {
                                --j;
                                continue;
                        }
                        if(cabeca == '&' || cabeca == '|' || cabeca == '=') {
                                token = token + cabeca;
                                regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                                token = "";
                        } else {
                                regexMatch(new Lexema(token, i + 1, (j+1) - token.length()));
                                token = "";
                                if((int)cabeca != 32 && (int)cabeca != 9 && (int)cabeca != 10) {
                                        token = token + cabeca;
                                }
                        }
                        continue;
                    }
                    token = token + cabeca;
                }
                
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(AnalisadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StringIndexOutOfBoundsException o) {

        }
    }
    
    public void regexMatch(Lexema token){
        if(token.getLexema().matches(ExpressoesRegulares.regexPalavrasReservadas)){
            tokens.add(new TokenPalavraReservada(token));
        }else if(token.getLexema().matches(ExpressoesRegulares.regexSeparadores)){          
            tokens.add(new TokenSeparador(token));
        }else if(token.getLexema().matches(ExpressoesRegulares.regexNumeros)){
            tokens.add(new TokenNumero(token));
        }else if(token.getLexema().matches(ExpressoesRegulares.regexIndentificadores)){
            tokens.add(new TokenIdentificador(token));
        }else if(token.getLexema().matches(ExpressoesRegulares.regexOperadoresLogicos)){
            tokens.add(new TokenOperador(token));
        }else if(token.getLexema().matches(ExpressoesRegulares.regexOperadoresAritmeticos)){
            tokens.add(new TokenOpAnalitico(token));
        }else if(token.getLexema().matches(ExpressoesRegulares.regexOperadorIncremento)){
            tokens.add(new TokenOpIncDec(token));
        }
    }
}
