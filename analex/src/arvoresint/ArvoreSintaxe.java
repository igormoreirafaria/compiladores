/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import tokens.Lexema;
import tokens.Token;
import tokens.TokenIdentificador;

/**
 *
 * @author igor
 */
public class ArvoreSintaxe {
    
    private AST filho = new AST("Root");
    private HashMap<String, VariavelTabela> tabelaSimbolos = new HashMap<>();
    private int i = 4;
    
    public ArvoreSintaxe(ArrayList<Token> token){
        while(i < token.size()){ 
            System.out.println("Lendo o token " + token.get(i).getSimbolo());
            filho.setFilhos(Bloco(token));
            if(i+1 >= token.size()) {
                break;
            }
            i++;
        }
    }
    
    public AST ComandoEnquanto(ArrayList<Token> tokens){
        While node = null; 
        
        if(tokens.get(i).getSimbolo().matches("WHILE")){
            node = new While(tokens.get(i).getSimbolo());
            System.out.println("Criando comando " + tokens.get(i).getSimbolo());
            i++;
            System.out.println("Criando comando " + tokens.get(i).getSimbolo());
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                node.setFilhos(Expressao(tokens));
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                       if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                            
                           i++;
        
                            System.out.println("While comandos " + tokens.get(i).getSimbolo());
                            AST a = Comando(tokens);
                            AST b = null;
                            while( a != null){
                                if ( b == null) {
                                    b = new AST("c_true");
                                    System.out.println("Criando o nodo while c_true b==null");
                                }
                               
                                b.setFilhos(a);                                    
                                a = Comando(tokens);
                            }
                            System.out.println("Criando o nodo while c_true");
                            node.setFilhos(b);
                        }
                       if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                           i++;
                       }
                    }
            }
        }
        return node;
    }
    
     public AST Comandos(ArrayList<Token> tokens, AST pai){
        System.out.println("Cheguei em comando " + tokens.get(i).getSimbolo());
         AST node = Comando(tokens);
         
        if(node != null){
            if(pai != null){
             pai.setFilhos(node);
            }
            Comandos(tokens, pai);
            System.out.println("Retornando o comando " + node.getNome());
            return node;
 
        }
        return null;
    }
    
    public AST Comando(ArrayList<Token> tokens){
        AST node;
        if( tokens.get(i).getSimbolo().matches("SEMI")){
            i++;
        }
        if( i+1 >= tokens.size()){
            return null;
        }
        node = Bloco(tokens);
        if(node != null){
            return node;
        }
        Declaracoes(tokens);
        node = Atribuicoes(tokens); 
        if(node != null){
            return node;
        }
        node = ComandoSe(tokens);
        if(node != null) {
            return node;
        }
        node = ComandoEnquanto(tokens);
        if(node != null) return node;
//        node = LeituraEscrita(tokens));
//        if(node != null) return node;
        return null;
    }
    
      public boolean Declaracoes(ArrayList<Token> tokens){
        String a = Tipo(tokens);
        System.out.println("Entrando em declaraçoes" + a);
        if(a != null){
            if(tokens.get(i).getSimbolo().matches("ID")){
                System.out.println("Entrando em declaraçoes" + a);
                if(a.equals("INT")){
                    tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela<String, Integer>("INT"));
                    System.out.println("Criando a variavel INT " + tokens.get(i).getStringLexema());
                    
                }else if(a.equals("FLOAT")){
                    tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela<String, Float>("FLOAT"));
                    System.out.println("Criando a variavel FLOAT " + tokens.get(i).getStringLexema());
                }
                i++;
                Decl2(tokens, a);
                i++;
                return false;
            }else { 
                return true;
            }
        }
        return true;
    }
      
      public String Tipo(ArrayList<Token> tokens){
        
        if(tokens.get(i).getSimbolo().matches("INT") || tokens.get(i).getSimbolo().matches("FLOAT") || tokens.get(i).getSimbolo().matches("CHAR")){
            String a = tokens.get(i).getSimbolo();
            i++;
            return a;
        }
        return null;
    }
    
    public boolean Decl2(ArrayList<Token> tokens, String a){
        if(tokens.get(i).getSimbolo().matches("COMMA")){
            i++;
            if(tokens.get(i).getSimbolo().matches("ID")){
                System.out.println("Entrando em declaraçoes" + a);
                if(a.equals("INT")){
                    tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela<String, Integer>("INT"));
                    System.out.println("Criando a variavel INT " + tokens.get(i).getStringLexema());
                    
                }else if(a.equals("FLOAR")){
                    tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela<String, Float>("FLOAT"));
                    System.out.println("Criando a variavel FLOAT " + tokens.get(i).getStringLexema());
                }
                i++;
                Decl2(tokens, a);

                return true;
            }
        }
        return false;
    }

    public AST ComandoSe(ArrayList<Token> tokens){
        If node = null;
        System.out.println("Entrando no if " + tokens.get(i).getSimbolo());
        if(tokens.get(i).getSimbolo().matches("IF")){
            node = new If("if");
            System.out.println("Criando o nodo if");
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                node.setFilhos(Expressao(tokens));
                if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                        i++;
                        System.out.println(" O que tem nessa porra " + tokens.get(i).getSimbolo());
                        AST a = Comandos(tokens, null);
                        if(a != null) {
                            AST b = new AST("c_true");
                            b.setFilhos(a);
                            System.out.println("Criando nodo IF c_true " + tokens.get(i).getSimbolo());
                            node.setFilhos(b);
                        } else {
                            System.out.println("a == null");
                        }
                    }
                    if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                        i++;
                        System.out.println(" Else " + tokens.get(i).getSimbolo());
                        
                        AST c = ComandoSeNao(tokens);
                        if (c != null) {
                            AST d = new AST("c_false");
                            System.out.println("Criando o nodo if c_false");
                            d.setFilhos(c);
                            node.setFilhos(d);
                        }else {
                            System.out.println("Else null");
                        }  
                    }
                }
            }
        }
        return node;
    }
    
    public AST ComandoSeNao(ArrayList<Token> tokens){
        System.out.println("Comando se nao " + tokens.get(i).getSimbolo());
        if(tokens.get(i).getSimbolo().matches("ELSE")){
            i++;
            System.out.println("Entrei no else." + tokens.get(i).getSimbolo());
            AST node = Comando(tokens);
            return node;
        }
        return null;
    }
    
    public AST Bloco(ArrayList<Token> tokens){
        System.out.println("Entrando no bloco " + tokens.get(i).getSimbolo());
        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
             System.out.println("-------------------------------------- " + tokens.get(i).getSimbolo());
            i++;
            System.out.println("AQUI " + tokens.get(i).getSimbolo());
            AST node;
            node = Comandos(tokens, filho);
            if(tokens.get(i).getSimbolo().matches("RTR")){
                i+=2;
            }
            System.out.println("-------------------------------------- " + tokens.get(i).getSimbolo());
            System.out.println("Saindo do bloco " + tokens.get(i).getSimbolo());
            i++;
            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                 System.out.println("-------------------------------------- " + tokens.get(i).getSimbolo());
             System.out.println("Saindo do bloco " + tokens.get(i).getSimbolo());
             return node;
            }
        }
        return null;
    }
    
    public AST Atribuicoes(ArrayList<Token> tokens){
        System.out.println("Entrando no nodo ATTR " + tokens.get(i-1).getSimbolo());
        Attr node = null;
        if(tokens.get(i).getSimbolo().matches("ID")){    
            AST id = new AST(tokens.get(i).getStringLexema());
            System.out.println("Crianod o nodo ID " + tokens.get(i).getStringLexema());
            i++;
            if(tokens.get(i).getSimbolo().matches("ATTR")){
                node = new Attr("=");
                System.out.println("Criando Nodo ATTR "+ tokens.get(i).getStringLexema());
                node.setFilhos(id);
                i++;
                AST e = Expressao(tokens);
                if(e != null){
                    node.setFilhos(e);
                }
                i++; 
            }
        }
        return node;
    }
    
    public Expr Expressao(ArrayList<Token> tokens){
        Expr conjuncao = Conjuncao(tokens);
        Expr node = ExpressaoOpc(tokens, conjuncao);
        if(node == null) {
            System.out.println("retornando termo");
            return conjuncao;
            
        }
        return node;
    }
    public Expr ExpressaoOpc(ArrayList<Token> tokens, Expr conjuncao){
        if(tokens.get(i).getSimbolo().matches("OR")){
            LogicalOp node = new LogicalOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo OR");
            node.setFilhos(conjuncao);
            i++;
            conjuncao = Conjuncao(tokens);
            node.setFilhos(conjuncao);
            node.setFilhos(ExpressaoOpc(tokens, conjuncao));
            return node;
        }
        return null;
    }
    public Expr Conjuncao(ArrayList<Token> tokens){
        Expr igual = Igualdade(tokens);
            
        Expr node = ConjuncaoOpc(tokens, igual);
        if(node == null) {
            return igual;
        }
     
        return node;
    }
    
    public Expr ConjuncaoOpc(ArrayList<Token> tokens, AST igualdade){
        if(tokens.get(i).getSimbolo().matches("AND")){
            LogicalOp node = new LogicalOp(tokens.get(i).getStringLexema());
            if( igualdade != null){
                node.setFilhos(igualdade);
            }
            System.out.println("Criando um nodo AND");
            i++;
            Expr igual = Igualdade(tokens);
            if( igual != null){
                node.setFilhos(igual);
            }
            Expr e = ConjuncaoOpc(tokens, null);
            if(e != null) {
                node.setFilhos(e);
            }
            return node;
        }
        return null;
    }
    public Expr Igualdade(ArrayList<Token> tokens){
        Expr relacao = Relacao(tokens);
        Expr node = (IgualdadeOpc(tokens, relacao));
        if( node == null) {
            System.out.println("retornando termo");
            return relacao;
        }
        return node;
    }
    
    public Expr IgualdadeOpc(ArrayList<Token> tokens, AST relacao){
        Expr node = OpIgual(tokens);
        if( node == null) {
            return null;
        }
        node.setFilhos(relacao);
        if(node != null){
            node.setFilhos(Relacao(tokens));
            node.setFilhos(IgualdadeOpc(tokens, null));
            return node;
        }
        return null;
    }
    
    public Expr OpIgual(ArrayList<Token> tokens){
        RelOp node = null;
        if(tokens.get(i).getSimbolo().matches("EQV")){
            node = new RelOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo Equivalente");
            i++;
        }else if(tokens.get(i).getSimbolo().matches("DIFF")){
            node = new RelOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo Diferente");
            i++;
        }
        return node;
    }
    
    public Expr Relacao(ArrayList<Token> tokens){
        Expr add = Adicao(tokens);
        Expr node = RelacaoOpc(tokens, add);
        if( node == null ) {
            return add;
        }
        return node;
    }
    public Expr RelacaoOpc(ArrayList<Token> tokens, AST add){
        Expr node = OpRel(tokens);
        if(node !=null){
            if(add != null){
                node.setFilhos(add);
            }
            node.setFilhos(Adicao(tokens));
            Expr e = RelacaoOpc(tokens, null);
            if(e != null){
                node.setFilhos(e);
            }
            return node;
        }
        return null;
    }
    public Expr OpRel(ArrayList<Token> tokens){
        RelOp node = null;
        if(tokens.get(i).getSimbolo().matches("LET")){
             node = new RelOp(tokens.get(i).getStringLexema());
             System.out.println("Criando um nodo LET" + tokens.get(i).getStringLexema());
            i++;
        }else if(tokens.get(i).getSimbolo().matches("LTE")){
            node = new RelOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo LTE" + tokens.get(i).getStringLexema());
            i++;
        }else if(tokens.get(i).getSimbolo().matches("GRT")){
            node = new RelOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo GRT" + tokens.get(i).getStringLexema());
            i++;
        }else if(tokens.get(i).getSimbolo().matches("GTE")){
            node = new RelOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo GTE" + tokens.get(i).getStringLexema());
            i++;
        }
        return node;
    }
    
    public Expr Adicao(ArrayList<Token> tokens){
        Expr termo = Termo(tokens);
        ArithOp node = AdicaoOpc(tokens, termo);
        if (node == null) {
            System.out.println("retornando termo");
            return termo;
        }
  
        return node;
    }
    
    public ArithOp AdicaoOpc(ArrayList<Token> tokens, AST termo){
        ArithOp node = OpAdicao(tokens);
        if (node == null) {
            return null;
        }
        node.setFilhos(termo);
        
        AST b = Termo(tokens);
        
        ArithOp a = AdicaoOpc(tokens, b);
        if(a != null){
            node.setFilhos(a);
        }else {
            node.setFilhos(b);
        }
        
        
        return node;
    }
    
    public ArithOp OpAdicao(ArrayList<Token> tokens){
        ArithOp node = null;
        System.out.println("Entrei 1 " + tokens.get(i).getStringLexema());
        if(tokens.get(i).getSimbolo().matches("ADD")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo +.");
            i++;
        }else if(tokens.get(i).getSimbolo().matches("SUB")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            System.out.println("Criando um nodo -");
            i++;
        }
        return node;
    }
    public Expr Termo(ArrayList<Token> tokens){
        Expr termo = Fator(tokens);
        Expr node = TermoOpc(tokens, termo);
        if( node == null) {
            System.out.println("retornando termo");
            return termo;
        }
        return node;
    }
    
    public Expr TermoOpc(ArrayList<Token> tokens, AST termo){
        Expr node;
        node = OpMult(tokens);
        if(node == null){
            return null;
        }
        if(termo != null){
            node.setFilhos(termo);
        }
        if(node != null){
            node.setFilhos(Fator(tokens));
            node.setFilhos(TermoOpc(tokens, null));
            return node;
        }
        return null;
    }
    
    public Expr OpMult(ArrayList<Token> tokens){
        ArithOp node = null;
        if(tokens.get(i).getSimbolo().matches("MUL")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            System.out.println("Criando o nodo MULT");
            i++;
        }else if(tokens.get(i).getSimbolo().matches("DIV")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            System.out.println("Criando o nodo DIV");
            i++;
        }
        return node;
    }
    public Expr Fator(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("ID")){
            Id node = new Id(tokens.get(i).getStringLexema());
            System.out.println("Criando o nodo ID " + tokens.get(i).getStringLexema());
            i++;
            return node;
        }else if(tokens.get(i).getSimbolo().matches("INTEGER_CONST")){
            Num node = new Num(tokens.get(i).getStringLexema());
            System.out.println("Criando o nodo NUM_INT " + tokens.get(i).getStringLexema());
            i++;
            return node;
        }else if(tokens.get(i).getSimbolo().matches("FLOAT_CONST")){
            Num node = new Num(tokens.get(i).getStringLexema());
            System.out.println("Criando o nodo NUM_FLOAT " + tokens.get(i).getStringLexema());
            i++;
            return node;
        }else if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
            i++;
            Expr node = Expressao(tokens);
            if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                i++;
            }
            return node;
        }   
        return null;
    }
    
    public AST getFilho() {
        return filho;
    }
    
    public void printaArvore() {
        //System.out.print("Filho " + filho.getFilhos().get(0).getFilhos().get(1).getFilhos().get(1).getFilhos().get(0).getNome());
        for(AST x : filho.getFilhos()) {
            if(x != null){
                System.out.println(x.getNome());
                System.out.println("-----------------");
                
            }
        }
        for(AST x : filho.getFilhos()) {
            printaFilhos(x, x);
        }

    }
    
   
    public void printaFilhos(AST a, AST anterior){;
        System.out.println("====================== ");
        if (a == null) return;
        if(a.getFilhos().size() == 0) {
            return;
        }
        for(AST x : a.getFilhos()) {
            if( x == null) {
                break;
            }
            System.out.println(x.getNome() + " " + anterior.getNome()+ " " + anterior.getFilhos().size());            
        }
        for(AST x : a.getFilhos()) {
            if (x != null){
                printaFilhos(x, x);
            }
        }
        
    }
    
    public void variaveisPrograma(){
        Set<String> a = tabelaSimbolos.keySet();
        
        for(String x : a){
            System.out.println(x + " " + tabelaSimbolos.get(x).toString());
            
        }
    }
    public void evaluate() {
        
    }
    
}
