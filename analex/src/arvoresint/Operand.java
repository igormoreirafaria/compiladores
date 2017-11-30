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
public class Operand {
    private String name; //nome da variavel 
    private Temp temporary; // nome do temporario associado à variavel
    private VariavelTabela tableEntry; // endereço na tabela de simbolos onde a variavel esta armazenada
    private int type; // funcao de fazer a verificação de tipos
    
    public Operand() {
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void setTableEntry(VariavelTabela tableEntry){
        this.tableEntry = tableEntry;
    }
    
    public void setTemporary(Temp temp) {
        temporary = temp;
    }
    public Temp getTemporary() {
        return temporary;
    }
}
