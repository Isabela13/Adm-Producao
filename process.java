import java.io.*;   
import java.util.*;   
  

public class process {   
	
	public static ArrayList<String> getLinhasArquivo( File file ) throws FileNotFoundException, IOException{
	    ArrayList<String> linhas;
	        
	    try (BufferedReader leitor = new BufferedReader( new FileReader(file) )) {
	       linhas = new ArrayList<>();
	       String linha = "";
	            
	       while( (linha = leitor.readLine()) != null ){
	          if( linha.length() > 0 )
	              linhas.add(linha);
	       }
	    }
	    return linhas;
	}
	
    
	public static void main(String[] args) throws IOException {
    	//Leitura do arquivo e armazenamento em um ArrayList
    	String local = "/home/grad/si/16/imsp/ADM-Prod/arch1.txt"; //preciso de ajuda para mudar issssssso 
    	ArrayList<String> linhasDoArquivo = getLinhasArquivo(new File (local));
    	
    	//Criei essa variavel para altermos de forma mais facil o tempo de abertura do programa
    	double tempoAbertura =  (0.1);  // para esse tempo o processador nao vai ficar ocioso
    	
    	// Preenchimento do vetor com o tempo de abertura do programa 
    	double arrayTempoAbertura[] = new double[linhasDoArquivo.size()];
    	
		//A primeira posicao sera 1. Ja que sera o primeiro que sera aberto
    	arrayTempoAbertura[0] = 0;
    	for (int i = 1; i<linhasDoArquivo.size() ; i++){
    		arrayTempoAbertura[i] =  arrayTempoAbertura[i-1] + tempoAbertura;
    	}
    	
    	double tempoGastoFila[] = new double[linhasDoArquivo.size()];
    	
    	for(int i=1; i<linhasDoArquivo.size() ; i++){
    		int tempoOcioso = 0;
    		if(arrayTempoAbertura[i]<tempoOcioso){
    			tempoGastoFila[i]=tempoOcioso-arrayTempoAbertura[i];
    			tempoOcioso=tempoOcioso + Integer.parseInt(String.valueOf(linhasDoArquivo.get(i)));//essa conversao noa ta dando certo!!
    			
    		}
    	}
    	
    	
    	/*System.out.println(linhasDoArquivo.get(i));*/
    	
    }
}