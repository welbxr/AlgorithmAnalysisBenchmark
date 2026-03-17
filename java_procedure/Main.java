import java.math.BigInteger;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        FactorialMaster calculator = new FactorialMaster();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número para o cálculo do fatorial: ");
        long n = scanner.nextLong();

        //F1
        //calculator.hybridCalculator(n);

        //F2
        //calculator.streamCalculator(n);

        //F3
        //calculator.linearCalculator(BigInteger.valueOf(n));

        // F4
        calculator.ultraHybridCalculator(n);
    }


}


    // Função 1 (single-core, agrupamento de valores até atingir o limite de um long, menos escalável)
    // 500.000 Iterações -> 710009ms - 41,02ms
    // 200.000 Iterações -> 10376ms - 7,71MB
    // 100.000 Iterações -> 2518ms   - 107,35MB
    // 1.000 Iterações -> 2ms  -   0,96MB

    // Função 2 (mais escalável e baixo consumo de memória, multi-core)
    // 500.000 Iterações -> 1129ms   - 224,75MB
    // 200.000 Iterações -> 382ms    - 330,15MB
    // 100.000 Iterações -> 197ms    - 60,05MB
    // 1.000 Iterações -> 7ms        - 1,29MB


    // Função 3 (sem otimização e sem escalabilidade, single-core)
    // 500.000 Iterações -> 70107ms   - 379,01MB
    // 200.000 Iterações -> 10576ms   - 4,09MB
    // 100.000 Iterações -> 2573ms    - 244,81MB
    // 1.000 Iterações -> 3ms         - 1,92MB
