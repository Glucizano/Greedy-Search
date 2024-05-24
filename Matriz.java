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
        // Booleano que será true se for a primeira iteração do loop
        boolean primeira = true;
        // Variável que armazenara distancia que esta sendo analisada
        int analisado = 0;
        // Variável que armazena a distância da cidade mais próxima do objetivo
        int maisProximoOrigem = 0;
        // Vetor que armazena as duas respostas:
        // resposta[0] = Qual cidade o caixeiro viajará
        // resposta[1] = Qual a distância que o caixeiro percorrerá
        int[] resposta = new int[2];

        // Se for a última cidade para qual o caixeiro vai viajar, seu destino é sua cidade de origem
        if(ultimaCidade)
        {
            resposta[0] = origem;
            resposta[1] = matriz[origem][atual];
            return resposta;
        }

        // Loop em que cada iteração será uma cidade sendo analisado
        for(int x=1; x<linhas; x++)
        {
            // A análise é feita sempre na linha da cidade de origem, visto que, a cidade de origem é seu objetivo
            analisado = matriz[origem][x];

            // Se a cidade não foi visitada ainda, ela é um destino valido
            if(naoVisitadas.contains(x))
            {
                // Se for a primeira iteração do loop
                if (primeira)
                {
                    // A cidade análisada será a mais próxima da origem por enquanto
                    maisProximoOrigem = analisado;
                    // Apartir de agora as próximas iterações não serão mais a primeira
                    primeira = false;
                    // O destino e a distancia que o caixeiro vai percorrer será a da primeira cidade por enquanto
                    resposta[0] = x;
                    resposta[1] = matriz[x][atual];
                }

                // Se a cidade analisada estiver mais perto que a cidade mais proxima do objetivo até o momento
                if(analisado <= maisProximoOrigem)
                {
                    // Então a cidade sendo analisada será a nova cidade mais próxima do objetivo
                    maisProximoOrigem = analisado;
                    // O valor atual do x é a cidade
                    resposta[0] = x;
                    // matriz[x][atual] é a distancia que o caixeiro vai percorrer para ir para a cidade analisada
                    resposta[1] = matriz[x][atual];
                }
            }
        }
        // Retorna a resposta com os dois valores
        return resposta;
    }
}
