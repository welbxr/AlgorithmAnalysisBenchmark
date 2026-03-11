using AnaliseDeAlgoritmos.Models;

//TRABALHO DE LUIZ SANTANA DE ARAUJO - N047HF6
//TRABALHO DE WELBER WILLIAM DA SILVA - F352951

Console.WriteLine("ANÁLISE DE ALGORITMOS");
        Console.WriteLine("=================================\n");

        int[] exemplos = { 5, 10, 20, 50, 100, 200, 500 };

        foreach (int n in exemplos)
        {
            Console.WriteLine("--FATORIAL--");
            Analise.Analisa(
                "Fatorial",
                x => Fatorial.Calcular(x),
                n
            );

            Console.WriteLine("--FIBONACCI--");
            Analise.Analisa(
                "Fibonacci",
                x => Fibonacci.Calcular(x),
                n
            );
        }

        Console.WriteLine("\nAnalise Finalizada.");
        Console.ReadKey();