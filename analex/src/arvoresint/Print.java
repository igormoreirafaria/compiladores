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
public class Print extends AST{
    
    public Print(String nome) {
        super(nome);
    }
    
    public void toString(AST node) {
        System.out.println("<Print>");
        xml.add("<Print>");
        for( AST x : node.getFilhos()) {
            if(x == null)break;
            x.toString(x);
        }
        System.out.println("</Print>");
        xml.add("</Print>");
    }
    
}
