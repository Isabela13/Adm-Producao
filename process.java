import java.io.*;   
import java.util.*;   
import javax.swing.*;   

public class process {   
    private static String nome;   
    private static String [] sai; 
    static RandomAccessFile objeto;
    // O contrutor deve ter o mesmo nome da Classe   
    public static  void Ler(String nomeArquivo){    
        nome = nomeArquivo;   
    }   
    // método que retorna o vetor contendo as informações do arquivo  
    public static String[] criarVetor() {
        try {
            File arq = new File(nome);   
            objeto = new RandomAccessFile(arq , "rw");
            System.out.println(objeto.length());
            sai = new String[(int)objeto.length()]; // inicializa o vetor com o tamanho do arquivo
            for (int i = 0; i < objeto.length(); i++) {
                sai[i]= objeto.readLine();
            }  
            return sai;
        } catch (FileNotFoundException ex) { // trata as exceções do tipo FileNotFoundException 
            ex.printStackTrace();
        } catch (IOException ex) { // trata as exceções do tipo IOException 
            ex.printStackTrace();
        }     
        return null; // só retorna null se der algum erro
    }
    
    public static void main(String[] args) {
    	Ler("arch1.txt");
    	criarVetor();
    	System.out.println("OI");
    }
}