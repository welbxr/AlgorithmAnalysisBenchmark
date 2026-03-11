using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

//TRABALHO DE LUIZ SANTANA DE ARAUJO - N047HF6
//TRABALHO DE WELBER WILLIAM DA SILVA - F352951

namespace AnaliseDeAlgoritmos.Models
{
    public class Analise
    {
         public static void Analisa(string algorithmName, Func<int, object> algorithm, int n)
        {
            GC.Collect();
            GC.WaitForPendingFinalizers();
            GC.Collect();

            long memoryBefore = GC.GetTotalMemory(true);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            var result = algorithm(n);

            stopwatch.Stop();

            long memoryAfter = GC.GetTotalMemory(true);

            long deltaMemory = memoryAfter - memoryBefore;
            long deltaTime = stopwatch.ElapsedMilliseconds;

            Console.WriteLine($"Algoritmo: {algorithmName}");
            Console.WriteLine($"Entrada (Δe): {n}");
            Console.WriteLine($"Tempo de Execução (Δt): {deltaTime} ms");
            Console.WriteLine($"Uso de Memoria (Δm): {deltaMemory} bytes");
            Console.WriteLine("----------------------------------");
        }
    }
}