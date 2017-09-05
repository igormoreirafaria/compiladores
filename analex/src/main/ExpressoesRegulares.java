/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author igor
 */
public interface ExpressoesRegulares {
    public static final String regexFLOAT = "\\d+\\.\\d+";
    public static final String regexINT = "\\d+";
    public static final String regexPalavrasReservadas = "printf|char|void|int|float|double|if|else|for|while|return|continue|break|scanf|main";
    public static final String regexSeparadores = ";|\\[|\\]|\\(|\\)|\\{|\\}|,";
    public static final String regexOperadoresAritmeticos = "\\^|\\+|-|/|\\*|=";
    public static final String regexOperadorIncremento = "\\+\\+|\\-\\-";
    public static final String regexOperadoresLogicos = "&&|\\|\\||<|>|<=|>=|==|!=";
    public static final String regexIndentificadores = "[a-zA-Z][\\da-zA-Z_]*";
}
