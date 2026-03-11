using System;
using System.Numerics;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

//TRABALHO DE LUIZ SANTANA DE ARAUJO - N047HF6
//TRABALHO DE WELBER WILLIAM DA SILVA - F352951

namespace AnaliseDeAlgoritmos.Models
{
    public class Fibonacci
    {
        public static BigInteger Calcular(int n)
        {
            if (n <= 1)
                return n;

            BigInteger a = 0;
            BigInteger b = 1;
            BigInteger temp;

            for (int i = 2; i <= n; i++)
            {
                temp = a + b;
                a = b;
                b = temp;
            }

            return b;
        }
    }
}