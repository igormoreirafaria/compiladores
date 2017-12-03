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
public class While extends AST{
    Label begin;
    public While(String nome) {
        super(nome);
    }
    
    public void toString(AST node) {
        System.out.println("< While=" + node.getNome() + " >");
        xml.add("< While=" + node.getNome() + " >");
        for(AST x : node.getFilhos()) {
            if(x==null)break;
            x.toString(x);
        }
        System.out.println("</While>");
        xml.add("</While>");
    }

    public float evaluate () {
        float condicao = filhos.get(0).evaluate();
        if (filhos.get(1).getNome().equals("c_true") ){
            while(condicao == 1){
                    valor = filhos.get(1).evaluate();
                    condicao = filhos.get(0).evaluate();
            }
        }
        return valor;
    }
    
    public void generateCode() {
        begin = new Label();
        filhos.get(0).true_label = new Label();
        filhos.get(0).false_label = next = new Label();
        filhos.get(1).next = begin;
        System.out.println(begin.getName() + ":");
        filhos.get(0).generateBranchCode();
        if(filhos.get(0).getAddress() != null) {
            System.out.println("if " + filhos.get(0).getAddress().getName() + " == 0 goto " + filhos.get(0).false_label.getName());
        }else {
            System.out.println(filhos.get(0).true_label.getName() + ":");
        }
        filhos.get(1).generateCode();
        System.out.println("goto " + begin.getName());
        System.out.println(next.getName());
    }
}
