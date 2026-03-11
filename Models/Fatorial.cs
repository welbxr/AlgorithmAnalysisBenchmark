using System;
using System.Numerics;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

//TRABALHO DE LUIZ SANTANA DE ARAUJO - N047HF6
//TRABALHO DE WELBER WILLIAM DA SILVA - F352951

namespace AnaliseDeAlgoritmos.Models
{
    public class Fatorial
    {
    public static BigInteger Calcular(int n)
        {
            BigInteger result = 1;

            for (int i = 2; i <= n; i++)
            {
                result *= i;
            }

            return result;
        }
    }
}