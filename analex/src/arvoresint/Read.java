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
public class Read extends AST{
    
    public Read(String nome) {
        super(nome);
    }
    
    public float evaluate() {
        return 0;
    }
    
    public void toString(AST node) {
        System.out.println("<Read>");
        xml.add("<Read>");
        for(AST x : node.getFilhos()){
            if(x==null)break;
            x.toString(x);
        }
        System.out.println("</Read>");
        xml.add("</Read>");
    }
    
}
