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
public class ArithOp extends Expr{
    
    public ArithOp(String nome) {
        super(nome);
    }
    
    public void toString(AST node) {
        System.out.println("<ArithOp op='" + node.getNome() + "' >");
        xml.add("< ArithOp op='" + node.getNome() + "' >");
        for(AST x : node.getFilhos()) {
            if(x == null)break;
            x.toString(x);
        }
        System.out.println("</ArithOp>");
        xml.add("</ArithOp>");
    }
    
    public float evaluate () {
        switch(nome){
            case "+":
                valor =  filhos.get(0).evaluate() + filhos.get(1).evaluate();
                System.out.println(filhos.get(0).evaluate() + " + " +  filhos.get(1).evaluate() + " = " + valor);
                break;
            case "-":
                valor = filhos.get(0).evaluate() - filhos.get(1).evaluate();
                System.out.println(filhos.get(0).evaluate() + " - " +  filhos.get(1).evaluate() + " = " + valor);
                break;
            case "*":
                valor = filhos.get(0).evaluate() * filhos.get(1).evaluate();
                System.out.println(filhos.get(0).evaluate() + " * " +  filhos.get(1).evaluate() + " = " + valor);
                break;
            case "/":
                valor = filhos.get(0).evaluate() / filhos.get(1).evaluate();
                System.out.println(filhos.get(0).evaluate() + " / " +  filhos.get(1).evaluate() + " = " + valor);
                break;    
               
        }
        return valor;
    }
    public void generateBranchCode() {
        address = new Operand();
        filhos.get(0).generateBranchCode();
        filhos.get(1).generateBranchCode();
        address.setTemporary(new Temp());
        address.setName(address.getTemporary().getName());
 
        System.out.println(address.getTemporary().getName() + " = " + filhos.get(0).getAddress().getName() + nome + filhos.get(1).getAddress().getName());
    }
    public void generateRValueCode() {
        address = new Operand();
        filhos.get(0).generateRValueCode();
        filhos.get(1).generateRValueCode();
        address.setTemporary(new Temp());
        
        address.setName(address.getTemporary().getName());
        
        System.out.println(address.getName() + " = " + filhos.get(0).getAddress().getName() + " " + nome + " " + filhos.get(1).getAddress().getName());
        
    }
    public Operand getAddress() {
        return address;
    }
}

