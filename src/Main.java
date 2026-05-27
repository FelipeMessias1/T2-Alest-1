/**
 * Classe que inicializa a execução da aplicacao.
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) 
    {
    //Leitura do arquivo StopWords
     ArquivoTexto arquivoS = new ArquivoTexto(); // objeto que gerencia o arquivo
     LinhaTexto linhaS = new LinhaTexto(); // objeto que gerencia uma linha
     String lS;
     LinkedListOfString listaS = new LinkedListOfString();//Lista com as palavras de StopWord

     arquivoS.open("StopWords.txt");
     String stopWord = null;
     do  // laco que passa em cada linha do arquivo
     {
         lS = arquivoS.getNextLine();
         if (lS==null) // acabou o arquivo?
         break;
         linhaS.setLine(lS); // define o texto da linha
         do // laco que passa em cada palavra de uma linha
         {
             String palavra = linhaS.getNextWord();// obtem a proxima palavra da linha
             if (palavra == null)// acabou a linha
             {
                 break;
             }
            
             stopWord=(palavra.toLowerCase());
             listaS.orderedAdd(stopWord);
            
         } while (true);
     } while (true);
     arquivoS.close();

     //Escolha do arquivo pelo usuário
     String arquivoEscolhido = null;;
     QualArquivo();
     Scanner teclado = new Scanner(System.in);
     int arquivoX = teclado.nextInt();
     while(arquivoX<1 || arquivoX>5)
     {
      System.out.println("Opção inválida, digite um número válido");
      arquivoX = teclado.nextInt();
     } 
     if(arquivoX == 1)
     {
      arquivoEscolhido = "ArquivoExemplo.txt";
     }
       if(arquivoX == 2)
     {
      arquivoEscolhido = "alice.txt";
     }
       if(arquivoX == 3)
     {
      arquivoEscolhido = "cocoaandchocolate.txt";
     }
       if(arquivoX == 4)
     {
      arquivoEscolhido = "java.txt";
     } 
       if(arquivoX == 5)
     {
      arquivoEscolhido = "respostaParaJavaTxt.txt";
     } 



    //Leitura do arquivo escolhido
    int nLinha = 0;
    int nPagina = 0;
    int quantidadePalavras =0;
    int quantidadeStopWords=0;
    ListaOrdenadaDePalavras listaP = new ListaOrdenadaDePalavras();

    ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
    LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
    String l;

    arquivo.open(arquivoEscolhido);
    
    do  // laco que passa em cada linha do arquivo
    {
        l = arquivo.getNextLine();
        if (l==null) // acabou o arquivo?
           break;
        nLinha++; // conta mais uma linha lida do arquivo
        if (nLinha == 40) // chegou ao fim da pagina?
        {
            nLinha = 0;
            nPagina++;
        }
        linha.setLine(l); // define o texto da linha
        do // laco que passa em cada palavra de uma linha
        {
            String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
            if (palavra == null)// acabou a linha
            {
                break;
            }
             
            palavra = palavra.toLowerCase();//converte a palavra para minuscula
            quantidadePalavras++;
            if(listaS.containsElement(palavra) ==null)//verifica se a palavra é uma StopWord
            {
             listaP.orderedAdd(palavra,nPagina);//adiciona a palavra para a lista
            }

            else
            {
              quantidadeStopWords++;
              }
         } while (true);

    } while (true);
    arquivo.close();    
    
      int opçao = 0;
      teclado.nextLine();
    while (opçao !=5) {
    try
    {
      Menu();
       opçao = teclado.nextInt();
       teclado.nextLine();
     
      switch(opçao)
      {
       case 1://Exibir o índice remissivo
       {
        System.out.println(listaP);
        break;
       }

       case 2://Exibir o percentual de StopWords do texto   
       {
        double percentual = ((double)quantidadeStopWords/quantidadePalavras) *100; 
        System.out.println(percentual +"% das palavras do texto são StopWords");
        break;
       }
      
       case 3://Encontrar a palavra mais frequente
       {
        String palavraMaisFrequente = listaP.getMaiorContP();
        System.out.println("A palavra mais frequente no arquivo é : " + palavraMaisFrequente);
        break;
       }

       case 4://Pesquisar uma Palavra   
       {
         System.out.println("Qual palavra você gostaria de pesquisar?");
         String p = teclado.nextLine();
         System.out.println(listaP.get(p));
         break;
       }
      
       case 5://Encerrar o programa
       {
        System.out.println("Programa encerrado");  
        break;
       }
        
       default:
       {
        System.out.println("Operação inválida, digite novamente"); 
        Menu();
        opçao = teclado.nextInt();
       }

    }
    }
    catch(Exception e)
    {
     System.out.println("Operação inválida, digite novamente"); 
     Menu();
      opçao = teclado.nextInt(); 
      teclado.nextLine();
    }
    }
    }

   
  public static void QualArquivo()
  {
   System.out.println("Qual arquivo deseja ler?");
   System.out.println("[1] Arquivo exemplo");
   System.out.println("[2] ALice no Pais das Maravilhas");
   System.out.println("[3] Cocoa and Chocolate");
   System.out.println("[4] Java");
   System.out.println("[5] Resposta para Java");
  }  

  public static void Menu()
  {
   System.out.println("Qual operação deseja fazer?");
   System.out.println("[1] Exibir o índice remissivo");
   System.out.println("[2] Exibir o percentual de StopWords do texto");
   System.out.println("[3] Encontrar a palavra mais frequente");
   System.out.println("[4] Pequisar uma palavra");
   System.out.println("[5] Encerrar o programa");
  }
}

