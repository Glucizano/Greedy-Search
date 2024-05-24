import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static String caminho = "C:\\Users\\gabriel.lucizano\\Documents\\aaIA\\abc.txt";

    public static void main(String[] args) throws IOException
    {
        // Vetor que vai receber a resposta do método Viagem
        int[] resposta = new int[2];
        // Variável que possui a distância porcorrida no total
        int distanciaPercorrida = 0;
        // Criando um objeto minhaMatriz da classe Matriz
        Matriz minhaMatriz = new Matriz();
        // Variavel matriz vai receber a matriz gerada pelo método GeraMatriz que recebe o caminho do arquivo com argumento
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

        // Pergunta para o usuário qual cidade começar
        System.out.println("Por qual cidade começar? ");
        // Armazena o que o usuário digitou na variável origem
        int origem = scanner.nextInt();

        // Remove a origem da lista de não visitadas
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
                System.out.println("Voltei para a cidade "+origem+", minha cidade de origem. Percorri a distancia "+resposta[1]);
                System.out.println("\n======== Distancia total percorrida: "+distanciaPercorrida+" ========");
                break;
            }
            // Caxeiro vai fazer uma viagem e o metodo Viagem vai escolher uma cidade que ainda não foi visitada e a mais proxima do objetivo
            resposta = minhaMatriz.Viagem(matriz, atual, naoVisitadas, false, origem);
            // Agora a cidade atual do caixeiro é a resposta[0]
            atual = resposta[0];
            // É somado no total da distancia percorrida a distancia que o caixeiro percorreu até essa cidade
            distanciaPercorrida = distanciaPercorrida + resposta[1];

            // É removida da lista de cidades não visitadas a cidade atual
            naoVisitadas.remove(Integer.valueOf(atual));
            // Imprime na tela a cidade para qual foi e a distancia percorrida
            System.out.println("Cheguei na cidade "+atual+ ". Percorri a distancia: "+resposta[1]);
        }
    }
}
