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


====================================================================================

# Factorial Master: Estudo de Complexidade Computacional e Escalabilidade em Java

Este projeto foi desenvolvido com o objetivo de analisar a **Complexidade Computacional** no cálculo de fatoriais de grande escala, comparando diferentes abordagens de algoritmos em Java. O estudo foca na eficiência de processamento, gestão de memória e o impacto do paralelismo.

---

## 🎯 Objetivos do Projeto

Conforme os requisitos do enunciado, o sistema busca medir e otimizar:

1.  **Δt (Variação de Tempo):** Identificar o tempo de resposta conforme a amostragem sobe.
2.  **Δm (Variação de Memória):** Monitorar o consumo de memória Heap e o impacto do Garbage Collector.
3.  **Δε (Esforço/Delta Epsilon):** Analisar a eficiência algorítmica e o custo computacional (Single-core vs Multi-core).

---

## 🧠 Descrição das Implementações (Motores de Cálculo)

O sistema dispõe de 4 funções principais, cada uma representando uma estratégia diferente de resolução:

### 1. F1 - Hybrid (Single-core / Low Memory)
*   **Abordagem:** Utiliza tipos primitivos (`long`) para acumular valores, convertendo para `BigInteger` apenas quando atinge o limite de 8 bytes da variável.
*   **Ponto Ideal:** Sistemas com hardware limitado. Evita o overhead de threads e mantém eficiência máxima de memória no limite dos primitivos.

### 2. F2 - Stream (Parallel / Multi-core / Karatsuba)
*   **Abordagem:** Utiliza Java Streams para processamento paralelo. 
*   **Diferencial:** Ao lidar com números gigantescos, o Java utiliza internamente o **Algoritmo de Karatsuba**, que otimiza a multiplicação de bits, tornando-o extremamente escalável para alto volume de dados.

### 3. F3 - Linear (Ingênua / Baseline)
*   **Abordagem:** Cálculo linear simples utilizando `BigInteger` desde o início.
*   **Limitação:** A imutabilidade do `BigInteger` gera milhões de instâncias na memória Heap, causando um gargalo severo de performance devido às constantes interrupções do **Garbage Collector (GC)**. Serve como contra-exemplo de otimização.

### 4. F4 - Ultra Hybrid (Batching + Parallel Tree)
*   **Abordagem:** O algoritmo mais eficiente do projeto. Trabalha com processamento em **Lotes (Batches)** de 500 números.
*   **Funcionamento:** Processa cada lote com a lógica de primitivos (F1) e combina os resultados usando uma redução em árvore paralela, unindo baixo consumo de memória com altíssima velocidade.

---

## 📊 Análise de Desempenho (Benchmarks)

Com base nos testes realizados com **500.000 iterações**, observamos os seguintes deltas:


| Métrica | Algoritmo F3 (Linear) | Algoritmo F4 (Ultra Hybrid) |
| :--- | :--- | :--- |
| **Tempo (Δt)** | ~70.107 ms | **~1.129 ms** |
| **Memória (Δm)** | Alta instabilidade (GC) | Estável e Otimizada |
| **Escalabilidade** | Baixa (Exponencial) | Alta (Linear/Estável) |

> **Conclusão do Estudo:** O uso de processamento paralelo e a gestão inteligente de tipos (Primitivos vs Objetos) reduziram o tempo de execução em mais de **60 vezes**, provando que o design do algoritmo é tão importante quanto o hardware disponível.

---

## 📂 Estrutura de Arquivos

*   `FactorialMaster.java`: Contém a lógica central e os 4 motores de cálculo.
*   `Main.java`: Interface via terminal para interação e execução dos testes.
*   `fatorial_number.txt`: Arquivo de saída gerado com persistência otimizada (Buffer de 8MB).
*   **Gráficos de Performance:** Analisados via Python/Pandas. [Acesse o Google Colab aqui](https://colab.research.google.com).

---

## 🚀 Como Executar

1. Compile os fontes:
   ```bash
   javac Main.java FactorialMaster.java
