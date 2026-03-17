import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FactorialMaster
{
    private static double accumulatedGlobalMemoryKB = 0;
    private long memoryBefore;

     //FUNÇÃO 1:
    public void hybridCalculator(long n)
    {
        prepareExecution("1 - HYBRID (Híbrida/Single-core)");
        long inicio = System.nanoTime();

        BigInteger result = BigInteger.ONE;
        if (n >= 2)
        {
            long acumuladorLocal = 1;
            for (long i = 1; i <= n; i++)
            {
                if (i > 0 && acumuladorLocal > Long.MAX_VALUE / i)
                {
                    result = result.multiply(BigInteger.valueOf(acumuladorLocal));
                    acumuladorLocal = i;
                } else {    acumuladorLocal *= i;   }
            }
            result = result.multiply(BigInteger.valueOf(acumuladorLocal));
        }

        finalizeExecution(inicio, result);
    }

    //FUNÇÃO 2:
    public void streamCalculator(long n)
    {
        prepareExecution("2 - STREAM (Paralela/Multi-core)");
        long inicio = System.nanoTime();

        BigInteger result = LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);

        finalizeExecution(inicio, result);
    }

    //FUNÇÃO 3:
    public void linearCalculator(BigInteger valueInput)
    {
        prepareExecution("3 - LINEAR (Não Otimizada/Single-core)");
        long inicio = System.nanoTime();

        BigInteger result = BigInteger.ONE;
        BigInteger tempInput = valueInput;

        while (tempInput.compareTo(BigInteger.ONE) > 0) {
            result = result.multiply(tempInput);
            tempInput = tempInput.subtract(BigInteger.ONE);
        }
        finalizeExecution(inicio, result);
    }

    //FUNÇÃO 4:
    public void ultraHybridCalculator(long n) {
        prepareExecution("4 - ULTRA HYBRID (Balanced Tree + Karatsuba)");
        long inicio = System.nanoTime();

        if (n < 2) {
            finalizeExecution(inicio, BigInteger.ONE);
            return;
        }
        final int BATCH_SIZE = 500;

        long numBlocos = (n / BATCH_SIZE) + 1;

        List<BigInteger> partialPorducts = LongStream.range(0, numBlocos)
                .parallel()
                .mapToObj(b ->
                {
                    long start = 1 + (b * BATCH_SIZE);
                    long limit = Math.min(start + BATCH_SIZE - 1, n);
                    long p = 1;

                    BigInteger res = BigInteger.ONE;

                    for (long j = start; j <= limit; j++) {
                        if (p > Long.MAX_VALUE / j) {
                            res = res.multiply(BigInteger.valueOf(p));
                            p = j;
                        } else {
                            p *= j;
                        }
                    }
                    return res.multiply(BigInteger.valueOf(p));
                }).collect(Collectors.toList());

        BigInteger result = parallelTreeReduce(partialPorducts);

        finalizeExecution(inicio, result);

    }

    // Função recursiva para processar as multiplicações de forma paralela

    private BigInteger parallelTreeReduce(List<BigInteger> list)
    {
        if (list.isEmpty()) return BigInteger.ONE;
        return list.parallelStream().reduce(BigInteger.ONE, BigInteger::multiply);
    }

    // --- MÉTODOS DE UTILITÁRIOS PARA MEDIÇÃO E EXIBIÇÃO --- \\

    private void prepareExecution(String name)
    {
        System.out.println("\n-------------------------------------------");
        System.out.println("Iniciando " + name + "...");
        System.gc(); // Tenta limpar a heap antes de começar
        Runtime runtime = Runtime.getRuntime();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
    }

    private void finalizeExecution(long startTime, BigInteger result)
    {
        long endTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        long timeMs = (endTime - startTime) / 1_000_000;
        double memoryUsedKB = (memoryAfter - memoryBefore) / 1024.0;

        // Proteção contra variações negativas do Garbage Collector
        if (memoryUsedKB < 0) memoryUsedKB = 0.01;
        accumulatedGlobalMemoryKB += memoryUsedKB;

        // EXIBIÇÃO DOS RESULTADOS
        //System.out.println("RESULTADO DO FATORIAL: " + result);
        System.out.println("Tempo: " + timeMs + " ms");
        System.out.println(String.format("Memória desta execução: %.2f KB", memoryUsedKB));

        saveToFileOptimized(result);
    }

    private void saveToFileOptimized(BigInteger result)
    {
        // Gerando um buffer de 8MB
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("fatorial.txt"), 8 * 1024 * 1024))
        {
            writer.write(result.toString());
            System.out.println("Arquivo gerado com sucesso!");
        } catch (IOException e)
        {
            System.err.println("Erro de I/O");
        }
    }
}
