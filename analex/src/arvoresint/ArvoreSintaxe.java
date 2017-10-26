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
    private HashMap<String, VariavelTabela> tabelaSimbolos = null;
    private int i = 4;
    
    public ArvoreSintaxe(ArrayList<Token> token){
        while(i < token.size()){ 
            filho.setFilhos(Bloco(token, filho));
            if(i+1 >= token.size()) {
                break;
            }
            i++;
        }
    }
    
    public int evaluate () {
        filho.evaluate();
        return 0;
    }
    
    public void toString(String node) {
        System.out.println("<AST>");
        filho.add("<AST>");
        for( AST x : filho.getFilhos()) {
            if(x==null)break;
                x.toString(x);
        }
        System.out.println("</AST>");
        filho.add("</AST>");
        filho.save("teste1");
    }
    public AST ComandoEnquanto(ArrayList<Token> tokens){
        While node = null; 
        
        if(tokens.get(i).getSimbolo().matches("WHILE")){
            node = new While(tokens.get(i).getSimbolo());
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                node.setFilhos(Expressao(tokens));
                    if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                        i++;
                       if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                           i++;
                           AST b = new AST("c_true");
                            AST a = Comandos(tokens, b);
                            
                            while( a != null){
                                if ( b == null) {
                                    b = new AST("c_true");
                                }
                                b.setFilhos(a);                                    
                                a = Comando(tokens);
                            }
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
         AST node = Comando(tokens);
          if(node != null){
            if(pai != null){
             pai.setFilhos(node);
            }
            AST a = Comandos(tokens, pai);
            return null;
        }
         if(Declaracoes(tokens, pai)){
             Comandos(tokens, pai);
         }
        return node;
    }
    
    public AST Comando(ArrayList<Token> tokens){
        AST node;
        
        if( tokens.get(i).getSimbolo().matches("SEMI") || tokens.get(i).getSimbolo().matches("COMMA")){
            i++;
        }
        System.out.println(" lendo " + tokens.get(i).getStringLexema());
        if( i+1 >= tokens.size()){
            return null;
        }
        node = Bloco(tokens, null);
        if(node != null){
            return node;
        }
        node = Atribuicoes(tokens); 
        if(node != null){
             return node;
        }
        node = ComandoSe(tokens);
        if(node != null) {
            return node;
        }
        node = ComandoEnquanto(tokens);
        if(node != null){
            return node;
        }
        node = LeituraEscrita(tokens);
        if(node != null){
            return node;
        }
        return null;
    }
    
      public boolean Declaracoes(ArrayList<Token> tokens, AST pai){
        String a = Tipo(tokens);
        System.out.println(" Declaracao " + a);
        if(a != null){
            System.out.println(" Declaracao asd" + tokens.get(i).getSimbolo());
            if(tokens.get(i).getSimbolo().matches("ID")){
                
                if(a.equals("INT")){
                    Id.tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela("INT"));
                    System.out.println(" Declaracao " + tokens.get(i).getStringLexema());
                }else if(a.equals("FLOAT")){
                    Id.tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela("FLOAT"));
                }
                i++;
                System.out.println(" ATR " + tokens.get(i).getStringLexema());
                if(tokens.get(i).getSimbolo().matches("ATTR")){
                     System.out.println(" ATR " + tokens.get(i).getStringLexema());
                     i--;
                    pai.setFilhos(Atribuicoes(tokens));
                    i--;
                    
                }
                Decl2(tokens, a, pai);
                return true;
            }
            return false;
        }

        return false;
    }
      
      public String Tipo(ArrayList<Token> tokens){
        
        if(tokens.get(i).getSimbolo().matches("INT") || tokens.get(i).getSimbolo().matches("FLOAT") || tokens.get(i).getSimbolo().matches("CHAR")){
            String a = tokens.get(i).getSimbolo();
            i++;
            return a;
        }
        return null;
    }
    
    public boolean Decl2(ArrayList<Token> tokens, String a, AST pai){
        System.out.println(" Declaracao ," + tokens.get(i).getStringLexema());
        if(tokens.get(i).getSimbolo().matches("COMMA")){
            i++;
            if(tokens.get(i).getSimbolo().matches("ID")){
                if(a.equals("INT")){
                    Id.tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela("INT"));
                }else if(a.equals("FLOAT")){
                    Id.tabelaSimbolos.put(tokens.get(i).getStringLexema(), new VariavelTabela("FLOAT"));
                    System.out.println(" Declaracao " + tokens.get(i).getStringLexema());
                }
                i++;
                System.out.println(" Declaracao " + tokens.get(i).getStringLexema());
                if(tokens.get(i).getSimbolo().matches("ATTR")){
                    System.out.println(" ATR dc " + tokens.get(i).getStringLexema());
                    i--;
                    pai.setFilhos(Atribuicoes(tokens));   
                    i--;
                }
                Decl2(tokens, a, pai);
                return true;
            }
        }
        return false;
    }
    public AST LeituraEscrita(ArrayList<Token> tokens){
        if(tokens.get(i).getSimbolo().matches("PRT")){
            Print node = new Print("print");
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                System.out.println("Entrei aqui1 " + tokens.get(i).getStringLexema());
                node.setFilhos(Expressao(tokens));
                System.out.println("Sai aqui1 " + tokens.get(i).getStringLexema());
                if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    return node;
                }

            }
        }else if(tokens.get(i).getSimbolo().matches("SCF")){
            Read node = new Read("scanf");
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                node.setFilhos(Fator(tokens));
                if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    return node;
                }
            }
        }
        return null;
    }

    public AST ComandoSe(ArrayList<Token> tokens){
        If node = null;
        if(tokens.get(i).getSimbolo().matches("IF")){
            node = new If("if");
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_PAR")){
                i++;
                node.setFilhos(Expressao(tokens));
                if(tokens.get(i).getSimbolo().matches("CLOSE_PAR")){
                    i++;
                    if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                        i++;
                        AST b = new AST("c_true");
                        Comandos(tokens, b);
                        node.setFilhos(b);
                    }
                    if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                        i++;
                        AST d = new AST("c_false");
                        if(ComandoSeNao(tokens, d)) {
                            node.setFilhos(d);
                        }
//                        if (c != null) {
//                            d.setFilhos(c);
//                            node.setFilhos(d);
//                            return node;
//                        } else {
//                            System.out.println("ELSE NULL PORRA");
//                        }
                    }
                }
            }
        }
        return node;
    }
    
    public boolean ComandoSeNao(ArrayList<Token> tokens, AST pai){
        if(tokens.get(i).getSimbolo().matches("ELSE")){
            i++;
            if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
                i++;
                Comandos(tokens, pai);
                if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                    i++;
                    return true;
                }
            }else if(tokens.get(i).getSimbolo().matches("IF")) {
                pai.setFilhos(ComandoSe(tokens));
                return true;
            }
        }
        return false;
    }
    
    public AST Bloco(ArrayList<Token> tokens, AST pai){
        if(tokens.get(i).getSimbolo().matches("OPEN_BRACKET")){
            i++;
            AST node;
            node = Comandos(tokens, pai);
            if(tokens.get(i).getSimbolo().matches("RTR")){
                i+=2;
            }
            if(tokens.get(i).getSimbolo().matches("CLOSE_BRACKET")){
                i++;
             return node;
            }
        }
        return null;
    }
    
    public AST Atribuicoes(ArrayList<Token> tokens){
        Attr node = null;
        if(tokens.get(i).getSimbolo().matches("ID")){ 
            Id id = new Id(tokens.get(i).getStringLexema());
            i++;
            if(tokens.get(i).getSimbolo().matches("ATTR")){
                node = new Attr("=");
                node.setFilhos(id);
                i++;
                AST e = Expressao(tokens);
                if(e != null){
                    node.setFilhos(e);
                }
                i++; 
            }else{ 
                i++;
            }
        }
        return node;
    }
    
    public Expr Expressao(ArrayList<Token> tokens){
        Expr conjuncao = Conjuncao(tokens);
        Expr node = ExpressaoOpc(tokens, conjuncao);
        if(node == null) {
            return conjuncao;
            
        }
        return node;
    }
    public Expr ExpressaoOpc(ArrayList<Token> tokens, Expr conjuncao){
        if(tokens.get(i).getSimbolo().matches("OR")){
            LogicalOp node = new LogicalOp(tokens.get(i).getStringLexema());
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
            i++;
        }else if(tokens.get(i).getSimbolo().matches("DIFF")){
            node = new RelOp(tokens.get(i).getStringLexema());
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
            i++;
        }else if(tokens.get(i).getSimbolo().matches("LTE")){
            node = new RelOp(tokens.get(i).getStringLexema());
            i++;
        }else if(tokens.get(i).getSimbolo().matches("GRT")){
            node = new RelOp(tokens.get(i).getStringLexema());
            i++;
        }else if(tokens.get(i).getSimbolo().matches("GTE")){
            node = new RelOp(tokens.get(i).getStringLexema());
            i++;
        }
        return node;
    }
    
    public Expr Adicao(ArrayList<Token> tokens){
        Expr termo = Termo(tokens);
        ArithOp node = AdicaoOpc(tokens, termo);
        if (node == null) {
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
        if(tokens.get(i).getSimbolo().matches("ADD")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            i++;
        }else if(tokens.get(i).getSimbolo().matches("SUB")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            i++;
        }
        return node;
    }
    public Expr Termo(ArrayList<Token> tokens){
        Expr termo = Fator(tokens);
        Expr node = TermoOpc(tokens, termo);
        if( node == null) {
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
            i++;
        }else if(tokens.get(i).getSimbolo().matches("DIV")){
            node = new ArithOp(tokens.get(i).getStringLexema());
            i++;
        }
        return node;
    }
    public Expr Fator(ArrayList<Token> tokens){
        
        if(tokens.get(i).getSimbolo().matches("ID")){
            Id node = new Id(tokens.get(i).getStringLexema());
            i++;
            return node;
        }else if(tokens.get(i).getSimbolo().matches("INTEGER_CONST")){
            Num node = new Num(tokens.get(i).getStringLexema());
            i++;
            return node;
        }else if(tokens.get(i).getSimbolo().matches("FLOAT_CONST")){
            Num node = new Num(tokens.get(i).getStringLexema());
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
        Set<String> a = Id.tabelaSimbolos.keySet();
        
        for(String x : a){
            System.out.println(x + " " + Id.tabelaSimbolos.get(x).toString());
            
        }
    }
}
