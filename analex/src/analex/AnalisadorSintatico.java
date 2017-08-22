/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;

import analex.Token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class AnalisadorSintatico {
    private int i=0;
    
    public void Run(ArrayList<Token> tokens){
        
       
        while(i != tokens.size()){
            if(Programa(tokens) == false){
                break;
            }
            
        }     
       
        
        
        
    }
    
    public boolean Programa(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("INT")){
            i++;
            if(tokens.get(i).getSimbolo().matches("MAIN")){
                i++;
                if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Declaracoes(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return true;
                            }else{
                                System.out.println("missing token at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                return false;
                            }
                        }else{
                            System.out.println("missing token at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            return false;
                        }
                    }else{
                        System.out.println("missing token at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        return false;
                    }
                }else{
                    System.out.println("missing token at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                    return false;
                }
            }else{
                System.out.println("missing token at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                return false;
            }
        }else{
            System.out.println("missing token at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
            return false;
        }
    }
    
    public boolean Declaracoes(ArrayList<Token> tokens){
        System.out.println("chegou em declaracoes");
        return false;
    }
    
    
}
