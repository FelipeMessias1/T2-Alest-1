import java.util.List;
import org.w3c.dom.Node;

/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 */

public class ListaOrdenadaDePalavras 
{
    private class Palavra//funciona como Node
    {
        public String s;//a palavra
        public ListaDeOcorrencias listaOcorrencias;//lista das paginas onde a palavra se encontra
        public int contP=0;//contador de quantas vezes uma palavra apareceu no texto;
        public Palavra next;    
        public Palavra prev;
        public Palavra(String s) 
        {
            this.s = s;
            next = null;
            prev = null;
            this.contP=0;
            listaOcorrencias = new ListaDeOcorrencias();
        }
    }
    private Palavra header;
    private Palavra trailer;
    private Palavra current;
    private int count;

    @Override
    public String toString()
    {
     StringBuilder x = new StringBuilder();   
     Palavra aux = header.next;
     for(int i=0; i< count; i++)
     {
      x.append(aux.s + " Foi encontrado nas páginas:" + aux.listaOcorrencias.toString() + "\n");  
      aux = aux.next;  
     }  
     return x.toString(); 
    }
    
    public ListaOrdenadaDePalavras()
    {
        header = new Palavra(null);
        trailer = new Palavra(null);
        header.next = trailer;
        trailer.prev = header; 
        count =0;
    }

    public String getMaiorContP()// método que retorna a palavra mais frequente do arquivo lido
    {
     Palavra aux = header.next;
     Palavra maisP = aux;
    while(aux!=trailer)
    {
      if(aux.contP>maisP.contP)
      {
       maisP= aux;
      }
      aux = aux.next;  
      }  
     return maisP.s;
    }

    public void adicionaListaOcorrencias(String palavra,int paginas)
    {
     Palavra aux = contains(palavra);
     if(aux != null)
     {
      aux.listaOcorrencias.add(paginas);  
     }   
    }

      public Palavra contains(String x) 
    {
        Palavra aux =header.next;
        while(aux != trailer)
        {
         if(aux.s.equals(x))
         {
          return aux;
         }   
         aux = aux.next;   
        }
        return null;
    }  

    public String get(String x)
    {
        StringBuilder s = new StringBuilder();
      Palavra p = contains(x);
      if(p !=null)
      {
      Palavra aux = header.next;
      boolean encontrada = false;
      while(aux!=trailer)
      {
        if(aux.s.equals(x))
        {
           if(encontrada == true) 
           {
             s.append(",");
            }
         s.append(x + " Foi encontrada nas páginas " + aux.listaOcorrencias.toString());
         encontrada =true;
        }
       aux= aux.next; 
      }
    }
      else
      {
       s.append(x + " não foi encontrado no texto"); 
      }
    
     return s.toString();  
    } 

     public boolean isEmpty() 
     {
        if(count == 0)
        return true;
        else 
        return false;
    }  

    public int size() 
    {
     return count;
    }  

    public void clear() 
    {
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }
   
    private Palavra getPalavraIndex(int index)
    {
        Palavra aux = null;
        if (index < count/2) { // caminha do inicio para o meio
            aux = header.next;
            for(int i=0; i<index; i++)
                aux = aux.next;
        }
        else { // caminha do fim para o meio
            aux = trailer.prev;
            for(int i=count-1; i>index; i--)
                aux = aux.prev;
        }
        return aux;
    }

    public String get(int index) 
    { 
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Palavra aux = getPalavraIndex(index);
        return aux.s;
    }
    
    public void reset() 
    {
     current = header.next;
    }

    public String next() {
        if (current != trailer) {
            String x = current.s;
            current = current.next;
            return x;
        }
        return null;
    }    


    
    public Palavra getPalavra(String palavra) {
        Palavra aux = header.next;
        while (aux != trailer) {
            if (aux.s.equals(palavra)) {
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }

    public void orderedAdd (String element, int nPagina)// Método feito por Isabel Harb Manssour (com algumas alterações)  
    { 
        Palavra aux = getPalavra(element); // verifica se ja contem element para não inserir duplicado
        if (aux != null) {  // se nao contem element, insere
           aux.listaOcorrencias.add(nPagina);
           aux.contP++;//adiciona mais um ao contador de palavra
        }
        else{
            Palavra n = new Palavra(element);
             n.listaOcorrencias.add(nPagina);
            if (header.next == trailer) { 
                // se a lista está vazia
                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } 
            else if (element.compareTo(header.next.s)<0) { 
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            }
            else if (element.compareTo(trailer.prev.s)>0) {
                // se for maior que o ultimo, insere no final
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            }
            else {
                // senao procura a posicao correta para insercao
                aux = header.next;
                boolean inseriu=false;
                while (aux!=trailer && !inseriu) {
                    if (element.compareTo(aux.s)<0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev=aux.prev;
                        aux.prev.next = n;
                        aux.prev = n;
                    }
                    aux = aux.next;
                }
            }
            count++;
        }
    }
}
