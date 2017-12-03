/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresint;

import java.util.HashMap;
/**
 *
 * @author brunos1212
 */
public class Id extends Expr{
    public static HashMap<String, VariavelTabela> tabelaSimbolos = new HashMap<>();
    
    public Id(String nome) {
        super(nome);
    }
    public void toString(AST node) {
        String tipo;
        if (tabelaSimbolos.get(nome) == null){
            tipo = "null";
        }else {
            tipo = tabelaSimbolos.get(nome).getTipo();
        }
        System.out.println("<Id lexema=" + nome + " tipo=" + tipo + " >");
        xml.add("<Id lexema=" + nome + " tipo=" + tipo + " >");
    }
    public float evaluate() {
        if(tabelaSimbolos.get(nome) == null) {
            System.out.println("NULLs");
        }
        return (float) tabelaSimbolos.get(nome).getValor();
    }
    public void setValor(float valor) {
        System.out.println("Valor Id " + valor);
        tabelaSimbolos.get(nome).setValor(valor);
    }
    public void generateCode () {
        if(address == null) {
            address = new Operand();
            address.setTableEntry(tabelaSimbolos.get(nome));
            address.setName(nome);
        }
    }
    public void generateBranchCode() {
        generateCode();
    }
    public void generateRValueCode() {
        
        generateCode();
    }
    public Operand getAddress() {
        return address;
    }
}
