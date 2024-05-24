import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static String caminho = "C:\\Users\\gabriel.lucizano\\Documents\\aaIA\\abc2.txt";

    public static void main(String[] args) throws IOException
    {
        // Vetor que vai receber a resposta do método Viagem
        int[] resposta = new int[2];
        // Variável que possui a distância porcorrida no total
        int distanciaPercorrida = 0;
        // Criando um objeto minhaMatriz da classe Matriz
        Matriz minhaMatriz = new Matriz();
        // Variavel matriz vai receber a matriz gerada pelo método GeraMatriz
        int[][] matriz = minhaMatriz.GeraMatriz(caminho);
        // Lista de cidades não visitadas pelo caixeiro
        ArrayList <Integer> naoVisitadas = new ArrayList<>();

        // Preenchendo a lista de não visitadas usando o tamanho da tabela
        for(int x=1;x<minhaMatriz.linhas;x++)
        {
            naoVisitadas.add(x);
        }
        // Cria o objeto scanner da classe Scanner que vai ler os dados digitados do usuario
        Scanner scanner = new Scanner(System.in);

        // Pergunta para o usuário qual cidade começar e armazena na variável origem
        System.out.println("Por qual cidade começar? ");
        int origem = scanner.nextInt();

        // remove a origem da lista de não visitadas
        naoVisitadas.remove(Integer.valueOf(origem));

        // Variável atual se refere a onde o caixeiro está atualmente, e inicialmente tem o valor de origem
        int atual = origem;

        /*
        Esse loop é a viagem do caixeiro, o número máximo de iterações é o número de cidades na tabela,
        visto que, uma cidade não será visitada mais que uma vez (fora a cidade de origem)
         */
        for(int x = 0; x<minhaMatriz.linhas; x++)
        {
            // Se a lista de não visitadas estiver vazia, então o caixeiro retornara para sua cidade origem
            if(naoVisitadas.size() == 0)
            {
                System.out.println("Ja visitei todas as cidades");
                // Chama o método Viagem passando true como 4° parametro, denotando ser a última viagem
                resposta = minhaMatriz.Viagem(matriz, atual, naoVisitadas, true, origem);
                // Reposta[0] é a cidade para qual o caixeiro vai
                atual = resposta[0];
                // Soma a distância percorrida (resposta[1]) ao valor total (distanciaPercorrida)
                distanciaPercorrida = distanciaPercorrida + resposta[1];
                System.out.println("Cheguei na cidade "+atual);
                System.out.println("A cidade "+origem+" é minha cidade de origem. Encerrei minha jornada");
                System.out.println("\n======== Percorri a distancia: "+distanciaPercorrida+" ========");
                break;
            }
            // Caixeiro vai para a cidade mais próxima
            resposta = minhaMatriz.Viagem(matriz, atual, naoVisitadas, false, origem);
            atual = resposta[0];
            distanciaPercorrida = distanciaPercorrida + resposta[1];

            naoVisitadas.remove(Integer.valueOf(atual));
            // Imprime na tela a cidade para qual foi
            System.out.println("Cheguei na cidade "+atual);

            // Se a cidade atual for igual a de origem antes da lista de cidades não visitados esvaziar, então é um erro
            if(atual == origem)
            {
                System.out.println("Erro: cheguei ao meu destino antecipadamente");
                break;
            }
        }
    }
}
