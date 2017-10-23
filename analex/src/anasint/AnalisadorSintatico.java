/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasint;

import tokens.Token;
import java.util.ArrayList;
import arvoresint.*;
/**
 *
 * @author igor
 */
public class AnalisadorSintatico {
    private int i=0;
    private AST arvore;
    
    public void Run(ArrayList<Token> tokens){
        while(i != tokens.size()){
            if(!Programa(tokens)){
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
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("RTR")){
                                i++;
                                if(tokens.get(i).getSimbolo().matches("INTEGER_CONST")){
                                    i++;
                                    if(tokens.get(i).getSimbolo().matches("SEMI")){
                                        i++;
                                        if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                            i++;
                                            return false;
                                        }else{
                                            System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                            
                                            return true;
                                        }
                                    }else{
                                        System.out.println("missing token SEMI at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                        i++;
                                        return true;
                                    }
                                }else{
                                    System.out.println("missing token INTEGER_CONST at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                    i++;
                                    return true;
                                }                                
                            }else{
                                System.out.println("missing token RTR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                i++;
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            i++;
                            return true;
                        }
                    }else{
                        System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        i++;
                        return true;
                    }
                }else{
                    System.out.println("missing token OPEN_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                    i++;
                    return true;
                }
            }else{
                System.out.println("missing token MAIN at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                i++;
                return true;
            }
        }else{
            System.out.println("missing token INT at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
            i++;
            return true;
        }
    }
    
    public boolean Comandos(ArrayList<Token> tokens){
        if(!Comando(tokens)){
            Comandos(tokens);
            return false;
        }
        return true;
    }
    
    
    public boolean Comando(ArrayList<Token> tokens){
        if(!Bloco(tokens)|| !Declaracoes(tokens) || !Atribuicoes(tokens) || !ComandoSe(tokens) || !Repeticoes(tokens) || !LeituraEscrita(tokens)){
            return false;
        }
        return true;
    }
    
    public boolean LeituraEscrita(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("PRT")){
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                if(tokens.get(i).getSimbolo().matches("COMMA")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("ID") || tokens.get(i).getSimbolo().matches("INTEGER_CONST") || tokens.get(i).getSimbolo().matches("FLOAT_CONST")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                            i++;
                            if(tokens.get(i).getSimbolo().matches("SEMI")){
                                i++;
                                return false;
                            }
                        }
                    }else{
                        System.out.println("missing token ID or INTEGER_CONST or FLOAT_CONST");
                        if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                            i++;
                            if(tokens.get(i).getSimbolo().matches("SEMI")){
                                i++;
                                return false;
                            }
                        }
                    }
                }else if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("SEMI")){
                        i++;
                        return false;
                    }
                }
            }
        }else if(tokens.get(i).getSimbolo().matches("SCF")){
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                if(tokens.get(i).getSimbolo().matches("COMMA")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("ID")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                            i++;
                            if(tokens.get(i).getSimbolo().matches("SEMI")){
                                i++;
                                return false;
                            }
                        }
                    }else{
                        System.out.println("missing token ID");
                        if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                            i++;
                            if(tokens.get(i).getSimbolo().matches("SEMI")){
                                i++;
                                return false;
                            }
                        }
                    }
                }
            }        
        }
        return true;
    }
    
    public boolean Bloco(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
            i++;
            Comandos(tokens);
            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
             i++;
             return false;
            }else{
                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                i++;
                return true;
            }
        }
        return true;
    }
    
    public boolean Atribuicoes(ArrayList<Token> tokens){
        
        if(tokens.get(i).getSimbolo().matches("ID")){

            
            i++;       
            if(tokens.get(i).getSimbolo().matches("ATTR")){
                i++;
                Expressao(tokens);
                if(tokens.get(i).getSimbolo().matches("SEMI")){
                    i++;
                    return false;
                }
                System.out.println("missing token SEMI at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                i++;
                return true;   
            }else if(tokens.get(i).getSimbolo().matches("INC") || tokens.get(i).getSimbolo().matches("DEC")){
                i++;
                if(tokens.get(i).getSimbolo().matches("SEMI")){
                    i++;
                    return false;
                }
                System.out.println("missing token SEMI at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                return true;
            }
            System.out.println("missing token ATTR or INC or DEC at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
            return true;
        }
        return true;       
    }

    public boolean ComandoSe(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("IF")){
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                if(!Expressao(tokens)){
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                ComandoSeNao(tokens);
                                return false;
                            }else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                                i++;
                                ComandoSeNao(tokens);
                                return false;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                ComandoSeNao(tokens);
                                return false;
                            }else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                                return true;
                            }
                        }
                    }else{
                        System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                 
                        i++;
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                ComandoSeNao(tokens);
                                return false;
                            }else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            i++;
                            return true;
                        }
                    }
                }
                System.out.println("missing expression at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                
                if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                        i++;
                        Comandos(tokens);
                        if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                            i++;
                            ComandoSeNao(tokens);
                            return false;
                        }else{
                            System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                            return true;
                        }
                    }else{
                        System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        i++;
                        Comandos(tokens);
                        if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                            i++;
                            ComandoSeNao(tokens);
                            return false;
                        }else{
                            System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                            return true;
                        }
                    }
                }else{
                    System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                 

                    if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                        i++;
                        Comandos(tokens);
                        if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                            i++;
                            ComandoSeNao(tokens);
                            return false;
                        }else{
                            System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                            return true;
                        }
                    }else{
                        System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        i++;
                        return true;
                    }
                }
            }else{
                System.out.println("missing token OPEN_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                
                Expressao(tokens);
                if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                        i++;
                        Comandos(tokens);
                        if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                            i++;
                            ComandoSeNao(tokens);
                            return false;
                        }else{
                            System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                           
                            return true;
                        }
                    }else{
                        System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        i++;
                        return true;
                    }
                }else{
                    System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                 
                    return true;
                }
            }
        }
        return true;
    }
    
    public boolean ComandoEnquanto(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("WHILE")){
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                if(!Expressao(tokens) ){
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                              
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                return true;
                            }                          
                        }
                    }else{
                        System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                i++;
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                return true;
                            }                          
                        }                                
                    }
                }else{
                    System.out.println("Missing Expression at " +"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                                
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");                                
                                return true;
                            }                          
                        }
                    }else{
                        System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                i++;
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                return true;
                            }                          
                        }
                    }
                
                }
            }else{
                System.out.println("missing token OPEN_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                if(!Expressao(tokens) ){
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            i++;
                            Comandos(tokens);
                            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                i++;
                                return false;
                            }
                            else{
                                System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                return true;
                            }
                        }else{
                            System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            return true;
                        }
                    }else{
                        System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        return true;
                    }
                }
                
            }
        }
        return true;
    }
    
    public boolean ComandoSeNao(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("ELSE")){
            i++;
            Comandos(tokens);
            return false;
        }
        return true;
    }
    
    
    public boolean Repeticoes(ArrayList<Token> tokens){
        
        if(!ComandoEnquanto(tokens) || !ComandoFor(tokens)){
            return false;
        }        
        return true;
    }
    
    public boolean ComandoFor(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("FOR")){
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                if(!Atribuicoes(tokens) || !Declaracoes(tokens)){
                    Expressao(tokens);
                    if(tokens.get(i).getSimbolo().matches("SEMI")){
                        i++;
                        IncDec(tokens);
                        if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                            i++;
                            if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                                i++;
                                Comandos(tokens);
                                if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                                    i++;
                                    return false;
                                }else{
                                    System.out.println("missing token CLOSE_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                    return true;
                                }
                            }else{
                                System.out.println("missing token OPEN_BRACKET at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                                return true;
                            }
                        }else{
                            System.out.println("missing token CLOSE_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                            return true;
                        }
                    }else{
                        System.out.println("missing token SEMI at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                        return true;
                    }
                }
            }else{
                System.out.println("missing token OPEN_PAR at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                return true;
            }
        }
        return true;
    }
    
    public boolean IncDec(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("ID")){
            i++;
            if(tokens.get(i).getSimbolo().matches("INC") || tokens.get(i).getSimbolo().matches("DEC")){
                i++;
                return false;
            }else{
                System.out.println("missing token INC or DEC at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                return true;
            }
        }else if(tokens.get(i).getSimbolo().matches("INC") || tokens.get(i).getSimbolo().matches("DEC")){
            i++;
            if(tokens.get(i).getSimbolo().matches("ID")){
                i++;
                return false;
            }
        }
        return true;
    }
    
    public boolean Expressao(ArrayList<Token> tokens){
        if(!Conjuncao(tokens)){
            ExpressaoOpc(tokens);
            return false;
        }
        return true;
    }
    public boolean ExpressaoOpc(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("OR")){
            i++;
            Conjuncao(tokens);
            ExpressaoOpc(tokens);
            return false;
        }
        return true;
    }
    public boolean Conjuncao(ArrayList<Token> tokens){
        if(!Igualdade(tokens)){
            ConjuncaoOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean ConjuncaoOpc(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("AND")){
            i++;
            Igualdade(tokens);
            ConjuncaoOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean Igualdade(ArrayList<Token> tokens){
        if(!Relacao(tokens)){
            IgualdadeOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean IgualdadeOpc(ArrayList<Token> tokens){
        if(!OpIgual(tokens)){
            Relacao(tokens);
            IgualdadeOpc(tokens);
            return true;
        }
        return true;
    }
    
    public boolean OpIgual(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("EQV")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("DIFF")){
            i++;
            return false; 
        }
        return true;
    }
    
    public boolean Relacao(ArrayList<Token> tokens){
        if(!Adicao(tokens)){
            RelacaoOpc(tokens);
            return false;
        }
        return true;
    }
    public boolean RelacaoOpc(ArrayList<Token> tokens){
        if(!OpRel(tokens)){
            Adicao(tokens);
            RelacaoOpc(tokens);
            return false;
        }
        return true;
    }
    public boolean OpRel(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("LET")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("LTE")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("GRT")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("GTE")){
            i++;
            return false;
        }
        return true;
    }
    
    public boolean Adicao(ArrayList<Token> tokens){
        if(!Termo(tokens)){
            AdicaoOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean AdicaoOpc(ArrayList<Token> tokens){
        if(!OpAdicao(tokens)){
            Termo(tokens);
            AdicaoOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean OpAdicao(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("ADD")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("SUB")){
            i++;
            return false;
        }
        return true;
    }
    public boolean Termo(ArrayList<Token> tokens){
        if(!Fator(tokens)){
            TermoOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean TermoOpc(ArrayList<Token> tokens){
        if(!OpMult(tokens)){
            Fator(tokens);
            TermoOpc(tokens);
            return false;
        }
        return true;
    }
    
    public boolean OpMult(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("MUL")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("DIV")){
            i++;
            return false;
        }
        return true;
    }
    
    public boolean Fator(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("ID")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("INTEGER_CONST")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("FLOAT_CONST")){
            i++;
            return false;
        }else if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
            i++;
            Expressao(tokens);
            if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                i++;
                return false;
            }
        }        
        return true;
    }
    
    public boolean Declaracoes(ArrayList<Token> tokens){
       
        if(!Tipo(tokens)){
            if(tokens.get(i).getSimbolo().matches("ID")){
                i++;
                Decl2(tokens);
                return false;
            }
            System.out.println("missing token ID at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
            i++;
            return true;
        }
        return true;
    }
    
    public boolean Tipo(ArrayList<Token> tokens){
        
        if(tokens.get(i).getSimbolo().matches("INT") || tokens.get(i).getSimbolo().matches("FLOAT") || tokens.get(i).getSimbolo().matches("CHAR")){
            i++;
            return false;
        }
        //System.out.println("missing token Tipo at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
        return true;
    }
    
    public boolean Decl2(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("COMMA")){
            i++;
            if(tokens.get(i).getSimbolo().matches("ID")){
                i++;
                Decl2(tokens);
                return false;
            }else{
                System.out.println("missing token ID at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
                return true;
            }
        }else if(tokens.get(i).getSimbolo().matches("SEMI")){
            i++;
            Declaracoes(tokens);
            return false;
        }else if(tokens.get(i).getSimbolo().matches("ATTR")){
            i++;
            Expressao(tokens);
            Decl2(tokens);
            return false;
        }
        System.out.println("missing token COMMA or SEMI at "+"Posição: [" + tokens.get(i).getLexema().getLinha() + " , " + tokens.get(i).getLexema().getColuna() + "]");
        return true;
    }
}
