import java.io.*;   
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	

    public static float convertTo100(float input)
    {
        String input_string = Float.toString(input);
        BigDecimal inputBD = new BigDecimal(input_string);
        String hhStr = input_string.split("\\.")[0];
        BigDecimal output = new BigDecimal(Float.toString(Integer.parseInt(hhStr)));
        output = output.add((inputBD.subtract(output).divide(BigDecimal.valueOf(60), 10, BigDecimal.ROUND_HALF_EVEN)).multiply(BigDecimal.valueOf(100)));

        return Float.parseFloat(output.toString());
    }

    
	public static void main(String[] args) throws IOException, ParseException {
    	//Leitura do arquivo e armazenamento em um ArrayList
    	String local = "/home/grad/si/16/imsp/ADM-Prod/arch1.txt"; //preciso de ajuda para mudar issssssso 
    	ArrayList<String> linhasDoArquivo = getLinhasArquivo(new File (local));
    	
    	//Converte o tempo do arquivo para MILISEGUNDOS e grava no array de int processos
    	int processos[]= new int[linhasDoArquivo.size()];
    	SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        for(int a = 0; a< linhasDoArquivo.size(); a++){
        	 Date time = sdf.parse(linhasDoArquivo.get(a));
        	 Date timiMiliS = time;
        	 SimpleDateFormat formatador = new SimpleDateFormat("mmssSSS");           
        	 int dataAtual =  Integer.parseInt(formatador.format(timiMiliS));
        	 processos[a]=dataAtual;
        }
        //Arquivo de saida com o tempo gasto na fila
        Formatter saida = new Formatter("/home/grad/si/16/imsp/workspace/ADM-PROD/src/out.txt");
       
    	//Criei essa variavel para altermos de forma mais facil o tempo de abertura do programa
    	int tempoAbertura =  1;  // EM MILISEGUNDOS //para esse tempo o processador nao vai ficar ocioso ## 0.5 milisegundos fica ocioso
    	
    	// Preenchimento do vetor com o tempo de abertura do programa 
    	int  arrayTempoAbertura[] = new int[linhasDoArquivo.size()];
    	
		//A primeira posicao sera 0. Ja que sera o primeiro que sera aberto
    	arrayTempoAbertura[0] = 0;
    	for (int i = 1; i<linhasDoArquivo.size() ; i++){
    		arrayTempoAbertura[i] =  arrayTempoAbertura[i-1] + tempoAbertura;
    	}
    	
    	int tempoTotalFila[] = new int[linhasDoArquivo.size()];
    	
    	int tempoOcioso = 0;
    	for(int i=0; i<linhasDoArquivo.size() ; i++){
    		if(arrayTempoAbertura[i]<tempoOcioso){
    			tempoTotalFila[i]=tempoOcioso-arrayTempoAbertura[i];
    			tempoOcioso += processos[i];
    		}else{
    			tempoTotalFila[i]=0;
    			tempoOcioso=tempoOcioso+arrayTempoAbertura[i]+processos[i];
    		}
    		 saida.format(""+tempoTotalFila[i]+"\n");
   
    	}
    	 saida.close();
    }
}