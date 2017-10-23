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
public class VariavelTabela <K, J>{
    private K tipo;
    private J valor;

    public VariavelTabela(K tipo){
        this.tipo = tipo;
    }
    
    public void setValor(J valor){
        this.valor = valor;
    }
    
    public J getValor(){
        return valor;
    }
    
    public String toString() {
        return " " + valor;
    }
}
