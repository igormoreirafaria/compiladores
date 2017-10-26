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
public class RelOp extends Expr{

    
    public RelOp(String nome) {
        super(nome);
    }
    public void toString(AST node) {
        System.out.println("< RelOp='" + node.getNome() + "' >");
        xml.add("< RelOp='" + node.getNome() + "' >");
        for(AST x : node.getFilhos()) {
            if(x==null)break;
            x.toString(x);
        }
        System.out.println("</RelOp>");
        xml.add("</RelOp>");
    }
    
    public float evaluate() {
        boolean aux = true;
        System.out.println("Avaliando " + filhos.get(0).getNome() + " " + nome + " " + filhos.get(1).getNome());
         switch(nome) {
             case "<":
                 aux = filhos.get(0).evaluate() < filhos.get(1).evaluate();
                 break;
             case "<=":
                 aux = filhos.get(0).evaluate()<= filhos.get(1).evaluate();
                 break;
             case ">":
                 aux = filhos.get(0).evaluate() > filhos.get(1).evaluate();
                 break;
             case ">=":
                 aux = filhos.get(0).evaluate() >= filhos.get(1).evaluate();
                 break;
             case "==":
                 aux = filhos.get(0).evaluate() == filhos.get(1).evaluate();
                 break;    
         }
         if(aux == true){
             return 1;
         }else {
             return 0;
         }
    }
    
}
