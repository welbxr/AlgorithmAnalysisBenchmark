# Factorial Master: Estudo de Complexidade Computacional e Escalabilidade em Java

Este projeto foi desenvolvido com o objetivo de analisar a **Complexidade Computacional** no cálculo de fatoriais de grande escala, comparando diferentes abordagens de algoritmos em Java. O estudo foca na eficiência de processamento, gestão de memória e o impacto do paralelismo.

---

# 🎯 Objetivos do Projeto

Conforme os requisitos do enunciado, o sistema busca medir e otimizar:

1. **Δt (Variação de Tempo):** Identificar o tempo de resposta conforme a amostragem aumenta.
2. **Δm (Variação de Memória):** Monitorar o consumo de memória Heap e o impacto do Garbage Collector.
3. **Δε (Esforço / Delta Epsilon):** Analisar a eficiência algorítmica e o custo computacional (Single-core vs Multi-core).

---

# 🧠 Descrição das Implementações (Motores de Cálculo)

O sistema possui **4 funções principais**, cada uma representando uma estratégia distinta para cálculo de fatoriais em grande escala. O objetivo é demonstrar como **diferentes escolhas de arquitetura de algoritmo impactam diretamente na eficiência, escalabilidade e consumo de memória**.

---

# 1️⃣ F1 - Hybrid (Single-core / Low Memory)

## Abordagem

Esta função prioriza **baixo consumo de memória e simplicidade estrutural**, utilizando uma abordagem híbrida entre **tipos primitivos (`long`) e `BigInteger`**.

## Funcionamento

* Utiliza um **loop `for` com controle de fluxo por variáveis primitivas**.
* Inicia os cálculos utilizando o tipo **`long`**, que ocupa **apenas 8 bytes de memória**.
* A cada operação, o algoritmo verifica se o valor está próximo do limite de armazenamento do tipo `long`.
* Quando esse limite é atingido:

  * O valor é convertido para **`BigInteger`**.
  * O resultado é multiplicado com outros valores previamente agrupados.
* Todo o processamento ocorre de forma **linear (single-core)**.

## Características

* Baixo consumo de memória.
* Estrutura simples e previsível.
* Evita overhead de paralelismo.

## Ponto Ideal de Uso

Indicado para:

* Sistemas com **hardware limitado**
* Ambientes **single-core**
* Cálculos de **fatoriais menores ou médios**

A estratégia evita o custo de gerenciamento de **threads ou streams paralelas**, mantendo a eficiência máxima possível dentro do limite dos tipos primitivos.

---

# 2️⃣ F2 - Stream (Parallel / Multi-core / Karatsuba)

## Abordagem

Esta implementação utiliza **Java Streams com processamento paralelo**, explorando **múltiplos núcleos da CPU** para acelerar o cálculo.

## Funcionamento

* Utiliza **Streams paralelas** para dividir o trabalho entre múltiplos threads.
* Cada thread executa multiplicações parciais de forma independente.
* Quando os números atingem um tamanho significativo em bits, o Java passa a utilizar internamente o **algoritmo de Karatsuba** para multiplicação de grandes números.

## Algoritmo de Karatsuba

O algoritmo de **Karatsuba** otimiza multiplicações de grandes números ao reduzir o número de operações necessárias, sendo significativamente mais eficiente do que a multiplicação tradicional bit a bit.

Isso resulta em:

* Melhor desempenho para números grandes
* Redução do custo computacional

## Características

* Processamento **multi-core**
* Alta escalabilidade
* Boa eficiência para grandes volumes de dados

## Limitação

Quando aplicado a **números pequenos ou baixo volume de dados**, o algoritmo perde eficiência devido ao **overhead de gerenciamento de paralelismo**.

## Ponto Ideal de Uso

Mais indicado para:

* **Grandes volumes de cálculo**
* Sistemas **multi-core**
* Processamento de **fatoriais extremamente grandes**

---

# 3️⃣ F3 - Linear (Ingênua / Baseline)

## Abordagem

Esta é a implementação **mais simples e direta**, servindo como **baseline de comparação** para os demais algoritmos.

## Funcionamento

* Utiliza **`BigInteger` desde o início do processamento**.
* Realiza multiplicações sequenciais percorrendo os valores.
* O loop utilizado é um **`while`**, cuja condição precisa ser recalculada a cada iteração.

## Problema da Imutabilidade

A classe **`BigInteger` é imutável**, o que significa que cada operação de multiplicação cria **uma nova instância do objeto**.

Isso gera:

* **Milhões de objetos temporários na Heap**
* Alto consumo de memória
* Pressão constante no **Garbage Collector**

## Impacto do Garbage Collector

O GC precisa limpar continuamente as instâncias criadas durante o loop, causando:

* Interrupções frequentes na execução
* Queda severa de performance

## Características

* Implementação simples
* Fácil de entender
* Extremamente ineficiente em larga escala

## Conclusão

Esta abordagem representa uma **forma ingênua de resolver o problema**, pois:

* Utiliza `BigInteger` desde o início
* Desperdiça memória
* Não explora paralelismo
* Escala muito mal

---

# 4️⃣ F4 - Ultra Hybrid (Batching + Parallel Tree)

## Abordagem

Este é o **algoritmo mais eficiente desenvolvido no projeto**, combinando:

* Processamento em paralelo
* Uso otimizado de memória
* Agrupamento de cálculos em **lotes (batches)**

## Funcionamento

O algoritmo trabalha em três etapas principais.

### 1️⃣ Processamento em Lotes

Os números são agrupados em **batches (lotes)**.

Cada lote:

* Realiza cálculos utilizando **variáveis `long`**
* Aproveita ao máximo o limite de **8 bytes da variável**

### 2️⃣ Conversão Inteligente

Quando o valor atinge o limite do `long`:

* Ele é convertido para **`BigInteger`**
* Passa a ser tratado como número de grande escala

### 3️⃣ Redução Paralela (Tree Reduction)

Os resultados dos lotes são combinados através de:

* **Streams paralelas**
* Redução em **estrutura de árvore**

Isso permite combinar resultados de forma extremamente eficiente.

## Características

* Alto paralelismo
* Baixo consumo de memória
* Excelente escalabilidade
* Redução significativa do tempo de execução

## Conclusão

Este algoritmo combina as vantagens de:

* **F1 → eficiência de memória**
* **F2 → paralelismo**
* **F3 → precisão do BigInteger**

Resultando na implementação **mais rápida e equilibrada do projeto**.

---

# 📊 Análise de Desempenho (Benchmarks)

Com base nos testes realizados com **500.000 iterações**, observamos os seguintes resultados:

| Métrica            | Algoritmo F3 (Linear)   | Algoritmo F4 (Ultra Hybrid) |
| ------------------ | ----------------------- | --------------------------- |
| **Tempo (Δt)**     | ~70.107 ms              | **~1.129 ms**               |
| **Memória (Δm)**   | Alta instabilidade (GC) | Estável e otimizada         |
| **Escalabilidade** | Baixa (Exponencial)     | Alta (Linear / Estável)     |

### Resultado do Estudo

O uso de:

* **processamento paralelo**
* **gestão inteligente de tipos de dados**
* **redução de alocações na Heap**

reduziu o tempo de execução em **mais de 60 vezes**, demonstrando que **o design do algoritmo pode ter impacto maior que o próprio hardware**.

---

# 📂 Estrutura de Arquivos

```
FactorialMaster.java
Main.java
fatorial_number.txt
```

## Arquivos

**FactorialMaster.java**

Contém:

* Os **4 motores de cálculo**
* Lógica central do algoritmo
* Implementação das estratégias de otimização

**Main.java**

Interface via **terminal**, responsável por:

* Receber parâmetros
* Executar os algoritmos
* Mostrar resultados e métricas

**fatorial_number.txt**

Arquivo de saída contendo os resultados gerados.

O sistema utiliza **buffer de escrita de 8MB** para otimizar a persistência em disco.

---

# 📈 Gráficos de Performance

Os gráficos de análise foram gerados utilizando **Python + Pandas**.

Link do notebook:

https://colab.research.google.com/drive/1v5AXhfaBTjjP6YYTMAaFyuoCORebHJ-k?usp=sharing

---

# 📚 Conclusão do Estudo

O objetivo deste estudo foi demonstrar **como a eficiência de um algoritmo depende diretamente das escolhas de implementação**.

Durante o desenvolvimento foi possível observar na prática:

### Impacto do tipo de dado

* `long` → **8 bytes**
* `BigInteger` → cerca de **80 bytes**

Ou seja, **aproximadamente 10x mais memória**.

### Gestão de Heap

Grande número de objetos temporários:

* Aumenta o consumo de memória
* Força atuação frequente do **Garbage Collector**

### Limites das variáveis

Trabalhar próximo aos limites do tipo `long` permite:

* Aproveitar o máximo de desempenho
* Reduzir a necessidade de objetos complexos

### Paralelismo

O uso de **Streams paralelas** permite:

* Distribuir carga entre núcleos
* Melhorar drasticamente a escalabilidade

---

# 🚀 Como Executar

## 1️⃣ Compilar os arquivos

```bash
javac Main.java
javac FactorialMaster.java
```

## 2️⃣ Executar o programa

```bash
java Main
```
