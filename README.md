# High Concurrency FinTech Engine (TSRBank): Enterprise Core & Low-Latency Transaction Engine

A comprehensive banking infrastructure simulation platform built using raw **Java 21** and **Jakarta Servlets**. While the administrative and support modules handle standard enterprise operations (such as profile routing, loans, and system audits), the critical **peer-to-peer capital transfer pipeline (the "Hot Path") was isolated and re-engineered** into a framework-agnostic, low-latency transaction core designed to withstand high-concurrency bursts under strict resource limitations.

---

## 🚀 Key Engineering Benchmarks (Hot-Path Isolation)
* **High-Throughput Processing:** Designed for a systemic architectural baseline of **10,000+ TPS**.
* **Hardware Saturation:** Achieved and sustained a stable **1,000+ TPS** profile under extreme resource limits on a single **AWS EC2 t3.micro** instance (1 vCPU, 1GB RAM) by aggressively optimizing memory allocation and maximizing hardware I/O bounds.
* **Zero-GC Hot-Path:** Eliminated "Stop-the-World" Garbage Collection latency spikes during peak high-concurrency bursts of **800+ concurrent users**.
* **Crash Resilience:** Implemented a data recovery subsystem ensuring **100% durability** of dirty or uncommitted transaction states following un-signaled server failures.

*Detailed execution records and raw metrics are documented under the `/benchmarks` directory.*

---

## 📐 Dual-Plane System Architecture

In high-scale financial technology platforms, generic web frameworks introduce massive runtime abstraction and unpredictable thread blockages. To mitigate this, this platform cleanly splits the application into two distinct operating domains:

### 1. The Control Plane (`/core-banking-app`)
Manages standard, low-velocity enterprise business rules using traditional web abstractions.
* **Stack:** Jakarta Servlets, DBCP Connection Pooling, Apache Tomcat, MySQL.
* **Features:** Multi-role user authentication, state-driven loan underwriting pipelines, secure administrative audit trails, and transactional ledger tracking.

### 2. The Data Plane (`/low-latency-engine`)
A highly optimized, microsecond-sensitive processing core written entirely in raw Core Java to manage the high-velocity peer-to-peer balance transfer vector.

* **Lock-Free Concurrency (LMAX Disruptor Paradigm):** Bypasses standard thread-contention bottlenecks found in traditional Java `BlockingQueue` architectures. It leverages memory-aligned `AtomicLong` indexing sequences and Compare-And-Swap (CAS) operations to process ledger shifts concurrently.
* **Pre-Allocated Object Pooling:** Pre-allocates memory for transaction event schemas during initialization. Inbound transaction details mutate fields within pre-existing objects rather than instantiating new objects on the heap, preventing JVM Garbage Collection loops on the critical execution path.
* **Custom Write-Ahead Logging (WAL):** Enforces explicit ACID durability parameters. Every state mutation is sequenced and serialized to an append-only digital journal file *prior* to primary in-memory engine commits. If a crash occurs, a boot-time recovery parser scans the WAL log sequentially to rebuild state arrays seamlessly.
* **Optimized Persistence Architecture:** Completely replaces generic ORMs with direct **JDBC native drivers**, configured with an aggressive **HikariCP** connection lifecycle policy and vector-based **JDBC Batching** to prevent standard `Broken Pipe` socket exhaustion during transaction spikes.

---

## 📂 Repository Blueprint

```text
HighConcurrencyFinTechEngine-TSRBank/
├── core-banking-code/        # Jakarta Servlet baseline app (Control Plane)
├── low-latency-engine/       # Close-to-the-metal Java 21 transaction files (Data Plane)
├── benchmark-test-logs/      # Production stress test performance logs (.txt format)
├── database-MySql/           # Production ACID schema layouts (.sql scripts)
└── README.md                 # Master system documentation
```

---

## 🧮 Technology Foundations & System Primitives
* **Language:** Java 21 (Utilizing native concurrency extensions and memory-boundary optimization)
* **Environment Infrastructure:** AWS EC2 (Resource-constrained t3.micro benchmarking platform)
* **Database Drivers:** Core Native JDBC Drivers + HikariCP Connection Pools
* **Data Layer:** MySQL 8.0 (Strictly isolated using explicit ACID boundary configurations)
