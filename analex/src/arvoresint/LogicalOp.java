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
public class LogicalOp extends Expr{
    Label true_label;
    Label false_label;
    
    public LogicalOp(String nome) {
        super(nome);
    }
    
    public void toString(AST node) {
        System.out.println("<LogicaOp op=" + node.getNome() + " >");
        xml.add("<LogicaOp op=" + node.getNome() + " >");
        for(AST x : node.getFilhos()) {
            if(x==null)break;
            x.toString(x);
        }
        System.out.println("</LogicalOp>");
        xml.add("</LogicalOp>");
    }
    
    public float evaluate() {
        boolean aux;
        System.out.println("Avaliando " + filhos.get(0).getNome() + " " + nome + " " + filhos.get(1).getNome());
        switch(nome) {
            case "&&":
                aux = (filhos.get(0).evaluate() > 0) && (filhos.get(1).evaluate()>0);
                if (aux == true){
                    valor = 1;
                }else {
                    valor =  0;
                }
                break;
            case "||":
                aux = (filhos.get(0).evaluate() > 0) || (filhos.get(1).evaluate()>0);
                if (aux == true){
                    valor = 1;
                }else {
                    valor = 0;
                }
                break;
        }
        
        return valor;
    }
    public void generateBranchCode() {
        
    }
}
