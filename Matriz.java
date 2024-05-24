import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Classe que vai gerar a matriz e implementar algumas funções nela
public class Matriz {

    // Variável que vai receber quantas linhas a tabela tem
    public int linhas = 0;

    // Método que vai gerar a matriz
    public int[][] GeraMatriz(String caminho) throws IOException
    {
        // Instancia o objeto leitor da classe BufferedReader, recebendo o caminho do arquivo para leitura
        BufferedReader leitor = new BufferedReader(new FileReader(caminho));
        // Instancia o objeto leitor1 da classe BufferedReader, esse leitor vai servir apenas para saber qual o tamanho da tabela
        BufferedReader leitor1 = new BufferedReader(new FileReader(caminho));

        // Variável que vai receber o tamanho da tabela
        int size = 0;
        // Loop que vai verificar o tamanho da tabela
        while((leitor1.readLine()) != null)
        {
            size++;
        }
        // Após contagem leitor1 fecha o arquivo
        leitor1.close();

        // Váriavel que vai receber todos os valores do arquivo em uma matriz
        int[][] matriz=new int [size][size];

        // Váriavel que vai receber todas as linhas do arquivo
        String line;

        // Enquanto estiver linhas no arquivo o loop vai rodar
        while((line = leitor.readLine()) != null)
        {
            // Vetor que vai receber os valores da linha separados por virgula (já desconsiderando a virgula)
            String[] separado = line.split(",");

            // Loop vai receber os valores do vetor separado e armazenar na matriz
            for (int x = 0; x< separado.length; x++)
            {
                int num = Integer.parseInt((separado[x]));
                // Linhas percorre as linhas e x as colunas
                matriz[linhas][x] = num;
            }
            // Somando um após cada linha ser lida
            linhas++;
        }
        // Após lido, fechar arquivo
        leitor.close();
        // Retorna matriz com todos os valores do arquivo
        return matriz;
    }

    // Método que vai fazer a decisão de qual cidade o Caixeiro vai em seguida
    public int[] Viagem(int[][] matriz, int atual, ArrayList<Integer>naoVisitadas, boolean ultimaCidade, int origem)
    {
        boolean primeira = true;
        int analisado = 0;
        int maisProximoOrigem = 0;
        int[] resposta = new int[2];
        int destino = 0;
        int distancia = 0;

        if(ultimaCidade)
        {
            resposta[0] = origem;
            resposta[1] = matriz[origem][atual];
            return resposta;
        }

        for(int x=1; x<linhas; x++)
        {
            analisado = matriz[origem][x];

            if(naoVisitadas.contains(x))
            {
                if (primeira)
                {
                    maisProximoOrigem = analisado;
                    primeira = false;
                    destino = x;
                    distancia = matriz[x][atual];
                }

                if(analisado <= maisProximoOrigem)
                {
                    maisProximoOrigem = analisado;
                    destino = x;
                    distancia = matriz[x][atual];
                }
            }
        }

        resposta[0] = destino;
        resposta [1] = distancia;

        return resposta;

    }
}
