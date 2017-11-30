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
        if(nome.equals("||")) {
            filhos.get(0).true_label = true_label;
            filhos.get(0).false_label = new Label();
            filhos.get(1).true_label = true_label;
            filhos.get(1).false_label = false_label;
            
            filhos.get(0).generateBranchCode();
            if(filhos.get(0).getAddress() != null) {
                System.out.println("if " + filhos.get(0).getAddress().getName() + " != 1 goto " + filhos.get(0).true_label.getName());
            }else {
                System.out.println(filhos.get(0).false_label.getName() +  ":" );
            }
           
            filhos.get(1).generateBranchCode();    
            if (filhos.get(1).getAddress() != null) {
                System.out.println("if " + filhos.get(1).getAddress().getName() + " == 0 goto " + filhos.get(1).false_label.getName());
            }else {
                System.out.println("goto " + filhos.get(1).true_label.getName());
            }
            
        }else if(nome.equals("&&")) {
            filhos.get(0).true_label = new Label();
            filhos.get(0).false_label = false_label;
            filhos.get(1).true_label = true_label;
            filhos.get(1).false_label = false_label;
            
            filhos.get(0).generateBranchCode();
            if(filhos.get(0).getAddress() != null) {
                System.out.println("if " + filhos.get(0).getAddress().getName() + " == 0 goto " + filhos.get(0).false_label.getName());
            }else {
                System.out.println(filhos.get(0).true_label.getName() +  ":" );
            }
           
            filhos.get(1).generateBranchCode();    
            if (filhos.get(1).getAddress() != null) {
                System.out.println("if " + filhos.get(1).getAddress().getName() + " == 0 goto " + filhos.get(1).false_label.getName());
            }else {
                System.out.println("goto " + filhos.get(1).true_label.getName());
            }
        }
    }
    
    public void generateRValueCode() {
        next = new Label();
        address = new Operand();
        address.setTemporary(new Temp());
        address.setName(address.getTemporary().getName());
        if(nome.equals("||")) {
            filhos.get(0).false_label = new Label();
            
            filhos.get(0).generateBranchCode();
            
            System.out.println("if " + filhos.get(0).getAddress().getName() + " != 1 goto " + filhos.get(0).false_label.getName());
           
            System.out.println( address.getName() + " = 1");
            System.out.println("goto " + next.getName());
            System.out.println(filhos.get(0).false_label.getName() + ":");
            
            filhos.get(1).generateBranchCode();    
            System.out.println("if " + filhos.get(1).getAddress().getName() + " == 0 goto " + next.getName());
            System.out.println( address.getName() + " = 0");
            System.out.println(next.getName() + ":");
        }else if(nome.equals("&&")) {
            filhos.get(0).false_label = new Label();
            
            filhos.get(0).generateBranchCode();
            
            System.out.println("if " + filhos.get(0).getAddress().getName() + " == 0 goto " + filhos.get(0).false_label.getName());
            
            System.out.println( address.getName() + " = 0");
            System.out.println("goto " + next.getName());
            System.out.println(filhos.get(0).false_label.getName() + ":");
            
            filhos.get(1).generateBranchCode();    
            System.out.println("if " + filhos.get(1).getAddress().getName() + " == 0 goto " + next.getName());
            System.out.println( address.getName() + " = 1");
            System.out.println(next.getName() + ":");
        }
    }
    @Override
    public Operand getAddress() {
        return address;
    }
}
