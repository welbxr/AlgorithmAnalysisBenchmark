# Análise de Complexidade Computacional – Fatorial e Fibonacci em C# e Java

## PROJETO POR:
    ### LUIZ SANTANA DE ARAUJO - N047HF6
    ### WELBER WILLIAM DA SILVA - F352951
## Descrição do Projeto

Este projeto implementa algoritmos para cálculo de **fatorial** e da **sequência de Fibonacci** em C#, com o objetivo de analisar aspectos básicos de **complexidade computacional**.

Durante a execução dos algoritmos são coletadas métricas de desempenho considerando três variáveis principais:

- **Δe (Delta de entrada)** – tamanho da entrada do algoritmo  
- **Δt (Delta de tempo)** – tempo de execução  
- **Δm (Delta de memória)** – consumo de memória durante a execução  

O programa executa os algoritmos para diferentes valores de entrada, permitindo observar como o tempo de processamento e o uso de memória variam conforme o aumento da complexidade do problema.

---

## Objetivos

- Implementar um algoritmo para cálculo de **fatorial**
- Implementar um algoritmo para cálculo da **sequência de Fibonacci**
- Executar os algoritmos com **valores de entrada progressivamente maiores**
- Medir **tempo de execução**
- Medir **consumo de memória**
- Relacionar os resultados com conceitos básicos de **complexidade computacional**

---

## Variáveis de Análise

### Δe – Delta de Entrada

Representa o tamanho da entrada utilizada no algoritmo.

Exemplos de entradas utilizadas no programa:

5, 10, 20, 50, 100, 200, 500


O aumento de Δe permite observar como o algoritmo se comporta conforme o problema cresce.

---

### Δt – Delta de Tempo

Representa o tempo necessário para executar o algoritmo.

A medição é realizada utilizando a classe:

**System.Diagnostics.Stopwatch**


O tempo é exibido em milissegundos.

---

### Δm – Delta de Memória

Representa a diferença de memória utilizada antes e depois da execução do algoritmo.

A medição é feita utilizando:

**GC.GetTotalMemory()**


O valor exibido corresponde ao aumento de memória utilizado durante a execução.
