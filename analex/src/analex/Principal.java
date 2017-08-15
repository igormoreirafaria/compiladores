/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;

import analex.Token.Token;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 *
 * @author igor
 */
public class Principal {
    private static AnalizadorLexico analex;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        analex = new AnalizadorLexico();
        final String nomeArquivoEntrada, nomeArquivoSaida;
        final Scanner sc = new Scanner(System.in);
        final InputStream is;
        final OutputStream os;
        final InputStreamReader isr;
        final OutputStreamWriter osw;
        final BufferedReader br;
        final BufferedWriter bw;
        
        System.out.println("Digite o nome do Arquivo de Entrada");
        nomeArquivoEntrada = sc.nextLine();
        nomeArquivoSaida = "arquivoSaida.txt";
        
        try {
            is = new FileInputStream(nomeArquivoEntrada);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            os = new FileOutputStream(nomeArquivoSaida);
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
           
            analex.Run(br);
            
            System.out.println("\n--- Lista de Tokens: --- \n");
            bw.write("--- Lista de Tokens: ---");
            bw.newLine();
            bw.newLine();
            
            for(Token t : analex.getTokens()) {
                System.out.print("Token: ");
                System.out.println( t.getToken());
                System.out.println("Posição: [" + t.getLexema().getLinha() + " , " + t.getLexema().getColuna() + "]");
                System.out.println(" ");

                bw.write("Token: ");
                bw.write( t.getToken());
                bw.newLine();
                bw.write("Posição: [" + t.getLexema().getLinha() + " , " + t.getLexema().getColuna() + "]");
                bw.newLine();
                bw.newLine();				
            }
            bw.flush();
            is.close();
            isr.close();
            br.close();
            os.close();
            osw.close();
            bw.close();
        }catch(IOException ioe){
               System.out.println("erro na leitura");
        } 

        
    }
    
}
