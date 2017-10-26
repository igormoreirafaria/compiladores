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
}
