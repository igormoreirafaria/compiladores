/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;

/**
 *
 * @author igor
 */
public interface ExpressoesRegulares {
    public static final String regexNumeros = "\\d+\\.\\d+|\\d+";
    public static final String regexPalavrasReservadas = "printf|char|void|int|float|double|if|else|for|while|return|continue|break|read|main";
    public static final String regexSeparadores = ";|\\[|\\]|\\(|\\)|\\{|\\}|,";
    public static final String regexOperadoresAritmeticos = "\\^|\\+|-|/|\\*|=";
    public static final String regexOperadorIncremento = "\\+\\+|\\-\\-";
    public static final String regexOperadoresLogicos = "&&|\\|\\||<|>|<=|>=|==|!=";
    public static final String regexIndentificadores = "[a-zA-Z][\\da-zA-Z_]*";
}
