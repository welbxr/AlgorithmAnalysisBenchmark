# Computational Complexity Analysis – Factorial and Fibonacci in C# and Java

## Developed by:
- Luiz Santana de Araujo
- Welber Willian da Silva

## Project Description

This project implements algorithms to calculate **Factorials** and the **Fibonacci sequence** in C#, aiming to analyze fundamental aspects of **computational complexity**.

During execution, performance metrics are collected based on three primary variables:

- **Δe (Input Delta)** – The size of the algorithm's input.
- **Δt (Time Delta)** – Total execution time.
- **Δm (Memory Delta)** – Memory consumption during execution.

The program runs these algorithms with various input values, allowing for the observation of how processing time and memory usage scale as the problem's complexity increases.

---

## Objectives

- Implement an algorithm for **Factorial** calculation.
- Implement an algorithm for the **Fibonacci sequence**.
- Execute algorithms with **progressively larger input values**.
- Measure **execution time**.
- Measure **memory consumption**.
- Correlate results with core **computational complexity** concepts.

---

## Analysis Variables

### Δe – Input Delta

Represents the size of the input used in the algorithm.

Examples of inputs used in the program:
`5, 10, 20, 50, 100, 200, 500`

Increasing Δe reveals how the algorithm behaves as the problem scales.

---

### Δt – Time Delta

Represents the time required to execute the algorithm.

Measurements are performed using the following class:
**`System.Diagnostics.Stopwatch`**

Time is displayed in milliseconds.

---

### Δm – Memory Delta

Represents the difference in memory usage before and after the algorithm's execution.

Measurements are performed using:
**`GC.GetTotalMemory()`**

The displayed value corresponds to the memory increase observed during execution.
