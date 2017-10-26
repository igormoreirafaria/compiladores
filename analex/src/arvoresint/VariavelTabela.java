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
public class VariavelTabela {
    private String tipo;
    private float valor;

    public VariavelTabela(String tipo){
        this.tipo = tipo;
    }
    
    public void setValor(float valor){
        this.valor = valor;
    }
    
    public float getValor(){
        return valor;
    }
    
    public String getTipo(){
        return tipo;
    }
    public String toString() {
        return " " + valor;
    }
}
