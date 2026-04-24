# FinSafe Java Transaction Processor

A console-based digital wallet transaction processor that prevents overdrafts through strict validation and exception-safe transaction handling.

## 1) Project Title & Catchy Description

### Why
This project solves accidental overdraft and invalid transaction issues in wallet-style balance management by enforcing validation rules before account state changes.

### What
This is a Java **CLI/console application** (not a web app) with a menu-driven interface for deposits, withdrawals, and mini statement viewing.

### Visuals
No UI layer is implemented in code; this project runs entirely in the terminal.

**Demo Video:** [Watch the demo here](https://drive.google.com/file/d/16F12MGJtDRif84mcXPZHQkpDn0hMVSa2/view?usp=sharing)

## 2) Key Features

- Strict encapsulation with private account state and public behavior methods.
- Custom domain exception: `InSufficientFundsException` for overdraft attempts.
- Built-in exception handling for invalid inputs/amounts (`IllegalArgumentException`, parse failures).
- Rolling transaction history using `ArrayList<Double>` capped at the latest 5 successful transactions.
- Interactive, non-terminating menu loop that continues after recoverable errors.

## 3) Tech Stack

- **Language:** Java
- **Libraries/APIs:** Java Standard Library (`java.util.Scanner`, `java.util.ArrayList`)
- **Application Type:** Console/CLI application
- **Infrastructure:** None required by current codebase

## 4) Getting Started

### Prerequisites

- JDK with `javac` and `java` available on your PATH.
- A terminal environment.

### Installation

```bash
cd /home/npd-ai/Downloads/Mini-Projects/FinSafe-Java
javac src/*.java
```

### Environment Configuration

No `.env` file or environment variables are required by this codebase.

## 5) Usage Examples

### Quick Start

```bash
cd /home/npd-ai/Downloads/Mini-Projects/FinSafe-Java
java -cp src FinSafeApp
```

### What you can do in the menu

- `1` Deposit
- `2` Withdraw
- `3` View History
- `4` Exit

## 6) Architecture & Data Flow

High-level runtime flow:

1. `FinSafeApp` starts and initializes `Account` from user input.
2. User selects an operation from the menu loop.
3. `Account.deposit(amount)` or `Account.processTransaction(amount)` validates inputs.
4. On success, balance updates and transaction is logged.
5. If history exceeds 5 entries, oldest entry is removed.
6. `printMiniStatement()` displays current balance and recent transactions.
7. Errors are caught in the app layer and shown without terminating the program.

## 7) API Documentation / Reference

### Class: `InSufficientFundsException`

- **Purpose:** Signals withdrawal attempts larger than available balance.
- **Constructor:**
  - `InSufficientFundsException(String message)`
- **Output:** Exception instance carrying a user-facing error message.

### Class: `Account`

- **Constructor:**
  - `Account(String accountHolder, double initialBalance)`
- **Methods:**
  - `void deposit(double amount)`
    - **Input:** positive/zero amount
    - **Behavior:** adds to balance, logs successful transaction
    - **Error:** throws `IllegalArgumentException` for negative values
  - `void processTransaction(double amount) throws InSufficientFundsException`
    - **Input:** withdrawal amount
    - **Behavior:** deducts from balance if valid, logs successful transaction
    - **Errors:** `IllegalArgumentException` (negative), `InSufficientFundsException` (amount > balance)
  - `void printMiniStatement()`
    - **Output:** account holder, current balance, and recent transaction list
  - `double getBalance()`, `String getAccountHolder()`
    - **Output:** read-only accessors for private state

### Class: `FinSafeApp`

- **Entry point:** `public static void main(String[] args)`
- **Role:** Handles input parsing, menu routing, exception catching, and application lifecycle.

## 8) Development & Testing

### Development

- Edit files under `src/`:
  - `InSufficientFundsException.java`
  - `Account.java`
  - `FinSafeApp.java`

### Build

```bash
cd /home/npd-ai/Downloads/Mini-Projects/FinSafe-Java
javac src/*.java
```

### Run

```bash
java -cp src FinSafeApp
```

### Testing

No automated test framework is currently implemented in code; validation is manual through interactive runs.

### Linting / Branching

No linting tool configuration or branching strategy is defined in the current codebase.

## 9) Roadmap & Known Issues

### Roadmap

- Add automated unit tests for `Account` and exception scenarios.
- Add package structure and build tooling (e.g., Maven/Gradle).
- Add transaction timestamps and richer statement formatting.

### Known Issues

- Requires JDK tools installed locally (`javac` and `java`).
- No persistent storage; all account state resets on application restart.
- No automated tests currently included.
