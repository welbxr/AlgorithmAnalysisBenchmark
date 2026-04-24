# Java Performance Benchmark: Scalability, Concurrency, and Memory Management Study

This project performs an **algorithmic efficiency** and **computational complexity** analysis of large-scale factorial calculations. Its main focus is benchmarking different Java architectures while evaluating their direct impact on hardware resources (CPU/RAM) and optimizing **JVM (Java Virtual Machine)** usage.

---

## 🛠️ Technical Stack and Applied Skills

*   **Languages:** Java (JDK 17+) and Python (Pandas/Matplotlib for data analysis).
*   **Performance & Concurrency:** Java Streams API (Parallel), multithreading, Karatsuba algorithm.
*   **Resource Management:** Heap memory monitoring, **Garbage Collector (GC)** optimization, and **Buffered I/O**.
*   **Core Concepts:** Big O complexity, data structures, and object immutability.

---

## 🎯 Project Goals (Performance Metrics)

The system uses three fundamental indicators to measure software efficiency:

1.  **Δt (Time Variation / Latency):** Measures execution time as processing load increases.
2.  **Δm (Memory Variation / Throughput):** Monitors memory allocation in the **heap** and pressure on the **Garbage Collector**.
3.  **Δε (Computational Effort):** Analyzes processing cost in **single-core vs. multi-core** scenarios.

---

## 🧠 Calculation Engine Architecture (Software Engineering)

The project compares four strategies, showing how design decisions affect **scalability** and hardware consumption.

### 1️⃣ F1 - Hybrid (Single-Core / Low Memory)
*   **Focus:** Optimization for constrained hardware.
*   **Strategy:** Uses primitive types (`long` - 8 bytes) with deferred conversion to `BigInteger`.
*   **Advantage:** Avoids parallelism overhead and minimizes object creation in RAM.

### 2️⃣ F2 - Stream (Parallel / Multi-Core / Karatsuba)
*   **Focus:** Vertical scalability and parallel processing.
*   **Strategy:** Uses **parallel streams** to distribute work across multiple CPU cores.
*   **Differential:** Includes an internal implementation of the **Karatsuba algorithm**, optimizing large-integer multiplication.

### 3️⃣ F3 - Linear (Baseline / Inefficient)
*   **Focus:** Bottleneck demonstration.
*   **Issue:** High instability due to **BigInteger immutability**.
*   **Impact:** Generates millions of temporary instances, causing slowdowns from excessive **Garbage Collector** activity.

### 4️⃣ F4 - Ultra Hybrid (Batching + Parallel Tree)
*   **Focus:** Industrial-grade performance (**state-of-the-art**).
*   **Strategy:** Combines batching with **tree reduction**.
*   **Result:** The most balanced approach, combining low memory consumption with maximum CPU core utilization.

---

## 📊 Results and Benchmarks (Sample: 500,000 iterations)


| Metric | Algorithm F3 (Linear) | Algorithm F4 (Ultra Hybrid) |
| :--- | :--- | :--- |
| **Response Time (Δt)** | ~70,107 ms | **~1,129 ms (60x faster)** |
| **Memory Stability** | Critical (GC spikes) | Stable and optimized |
| **Scalability Efficiency** | Exponential (inefficient) | Linear / stable |

---

## 🚀 How to Run and Requirements

### Prerequisites
*   **Java Development Kit (JDK) 11 or newer.**
*   **Environment:** At least 2 GB of free RAM for high-load tests (especially for engine F3).
