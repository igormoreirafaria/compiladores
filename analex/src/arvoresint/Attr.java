/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresint;

/**
 *
 * @author brunos1212
 */
public class Attr extends AST{
  
    public Attr(String nome) {
        super(nome);
    }
    
    public void toString(AST node) {
        System.out.println("< Attr op='" + node.getNome() + "' >");
        xml.add("< Attr op='" + node.getNome() + "' >");
        for(AST x : node.getFilhos()) {
            x.toString(x);
        }
        System.out.println("</Attr>");
        xml.add("</Attr>");
    }
    
    public float evaluate() {
        valor = filhos.get(1).evaluate();
        filhos.get(0).setValor(valor);
        System.out.println("Valor ATTR " + valor);
        return valor;
    }
    
    public void generateCode () {
        filhos.get(0).generateRValueCode();
        filhos.get(1).generateCode();
        System.out.println( this.filhos.get(0).getNome()+ " = " + filhos.get(1).getAddress().getName());
    }
    public Operand getAddress() {
        return address;
    }
}
