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
    public void generateBranchCode () {
        address = new Operand();
        filhos.get(0).generateBranchCode();
        filhos.get(1).generateBranchCode();
    }
    public void generateRValueCode (){
         next = new Label();
         false_label = new Label();
         filhos.get(0).generateCode();
         filhos.get(1).generateCode();
         //System.out.println(address.getName() + " = " + filhos.get(0).getAddress().getName() + " - " + filhos.get(1).getAddress().getName());
         System.out.println("if " + filhos.get(0).getAddress().getName() + nome + filhos.get(1).getAddress().getName()+ " goTo "+ false_label.getName());
         address = new Operand();
         address.setTemporary(new Temp());
         address.setName(address.getTemporary().getName());
         System.out.println("\t"+address.getName()+ " = 0");
         
         System.out.println("\tgoTo "+next.getName());
         System.out.println(false_label.getName()+": \n \t" + address.getName()+" = 1");
         System.out.println(next.getName() + ":");
    }
    public Operand getAddress () {
        return address;
    }   

    
}
