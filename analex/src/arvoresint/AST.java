/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresint;

import java.util.ArrayList;
/**
 *
 * @author brunos1212
 */
public class AST {
    private String nome; //nome do lexema, <, a, num
    private ArrayList<AST> filhos = new ArrayList<>(); // filhos do nodo
    private String tipo; // id, expr, if
    
    public AST(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<AST> getFilhos() {
        return filhos;
    }

    public void setFilhos(AST filhos) {
        this.filhos.add(filhos);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
