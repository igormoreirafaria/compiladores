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
public class If extends AST{
    Label next;
    public If(String nome) {
        super(nome);
    }
    
    public void toString(AST node) {
        System.out.println("<If>");
        xml.add("<If>");
        for (AST x : node.getFilhos()) {
            if(x == null)break;
            x.toString(x);
        }
        System.out.println("</If>");
        xml.add("</If>");
    }
    
    public float evaluate() {
        float a = filhos.get(0).evaluate();
        System.out.println("Valor do if " + a );
        if(filhos.size() > 1 ){
            if( a == 1 && filhos.get(1).getNome().equals("c_true")){
                filhos.get(1).evaluate();
            }if( a == 0 && filhos.get(2).getNome().equals("c_false")){
                filhos.get(2).evaluate();
            }
        }
        
        return valor;
    }
    public void generateCode() {
        next = new Label();
        if(filhos.size() == 3) {
            filhos.get(0).setTrue_label(new Label());
            filhos.get(0).setFalse_label(new Label());
            filhos.get(0).next = filhos.get(1).next = next;
            filhos.get(0).generateBranchCode();
            if(filhos.get(0).getAddress() != null) {
                System.out.println("if " + filhos.get(0).getAddress().getName() + " == 0 goto " + filhos.get(0).false_label.getName());   
            }else {
                System.out.println(filhos.get(0).true_label.getName() + ":");
            }
            
            filhos.get(1).generateCode();
            System.out.println("goto " + next.getName());
            System.out.println(filhos.get(0).false_label.getName() + ":");
            filhos.get(2).generateCode();
        }else {
            filhos.get(0).true_label = new Label();
            filhos.get(0).false_label = filhos.get(1).next = next;
            filhos.get(0).generateBranchCode();
            if(filhos.get(0).getAddress() != null) {
                System.out.println("if " + filhos.get(0).getAddress().getName() + " == 0 goto " + filhos.get(0).false_label.getName());   
            }else {
                System.out.println(filhos.get(0).true_label.getName() + ":");
            }
            filhos.get(1).generateCode();
        }
        System.out.println(next.getName() + ":");
    }
        
}
