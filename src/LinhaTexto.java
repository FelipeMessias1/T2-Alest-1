/**
 * @author Isabel H. Manssour
 */
public class LinhaTexto 
{
    private String linha;
    private String palavras[];
    private int contPalavras;
    
    /**
     * Recebe a string da linha que sera armazenada.
     * @param lin String com a linha de texto
     */
    public void setLine(String lin) {
        linha = lin;
       
        linha = linha.replaceAll("\\t"," "); // substitui tab por espaco em branco
        linha = linha.replaceAll(",",""); // para remover vírgulas
        linha = linha.replaceAll("\\.",""); // para remover ponto final
        linha = linha.replaceAll("\\?",""); // para remover ponto interrogacao
        linha = linha.replaceAll("\\!",""); // para remover ponto exclamacao
        linha = linha.replaceAll("\\(","");
        linha = linha.replaceAll("\\)","");
        linha = linha.replaceAll("\\@","");
        linha = linha.replaceAll("\\#","");
        linha = linha.replaceAll("\\%","");
        linha = linha.replaceAll("\\¨","");
        linha = linha.replaceAll("\\*","");
        linha = linha.replaceAll("\\-","");
        linha = linha.replaceAll("\\_","");
        linha = linha.replaceAll("\\{","");
        linha = linha.replaceAll("\\}","");
        linha = linha.replaceAll("\\[","");
        linha = linha.replaceAll("\\]","");
        linha = linha.replaceAll("\\/","");
        linha = linha.replaceAll("\\:","");
        linha = linha.replaceAll("\\;","");
        linha = linha.replaceAll("\\<","");        
        linha = linha.replaceAll("\\>","");  
        linha = linha.replaceAll("\\'","");
        linha = linha.replaceAll("\\1","");
        linha = linha.replaceAll("\\2","");   
        linha = linha.replaceAll("\\3","");
        linha = linha.replaceAll("\\4","");
        linha = linha.replaceAll("\\5","");
        linha = linha.replaceAll("\\6","");
        linha = linha.replaceAll("\\7","");
        linha = linha.replaceAll("\\8","");
        linha = linha.replaceAll("\\9","");
 
        palavras = linha.split(" "); // divide a string pelo espaco em branco 
        contPalavras = 0;
    }
    
    /**
     * Retorna uma palavra da linha.
     * @return a palavra, ou null caso nao tenha mais palavras.
     */
    public String getNextWord() {
      String pal = null;
      if (contPalavras < palavras.length) {
          pal = palavras[contPalavras];
          contPalavras++;
      }
      return pal;
    }
}
