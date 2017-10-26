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
public class Num extends Expr{
    
    public Num(String nome) {
        super(nome);
    }
    
    /**
     *
     * @return
     */
    @Override
    public float evaluate() {
        return Float.parseFloat(nome);
    }           
    public void toString(AST node) {
        System.out.println("< NUM value=" + node.getNome() + ">");
        xml.add("< NUM value=" + node.getNome() + ">");
    }
}
