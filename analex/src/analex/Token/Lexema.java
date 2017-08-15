/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex.Token;

/**
 *
 * @author igor
 */
public class Lexema {
	private String lexema;
	private int linha;
	private int coluna;

	public Lexema(String lexema, int linha, int coluna) {
		this.lexema = lexema;
		this.linha = linha;
		this.coluna = coluna;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
}
