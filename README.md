<a id="readme-top"></a>
# BSTLib

[![CI Status](https://github.com/spbu-coding-2024/trees-trees-team-8/actions/workflows/ci.yml/badge.svg)](https://github.com/spbu-coding-2024/trees-trees-team-8/actions)
[![MIT License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## About the Project
While there are many tree implementations available, we found no library that provides a **unified interface** for BST, AVL, and Red-Black Trees with a focus on **educational clarity** and **production readiness**. This project aims to fill that gap.

### Why BSTLib?
- **Your time matters**: Stop reinventing the wheel. We provide battle-tested implementations of core algorithms.
- **Learn by example**: Perfect for students studying data structures, with clean code and detailed documentation.
- **DRY principles**: Reusable components for tree operations let you focus on your unique logic.

We welcome contributions! If you have suggestions, open an [issue](https://github.com/spbu-coding-2024/trees-trees-team-8/issues) or submit a PR.

---

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Testing](#testing)
- [Team](#team)
- [License](#license)

---

## Features
### Supported Tree Types
| Structure       | Balancing Mechanism          | Key Methods                     |
|-----------------|------------------------------|---------------------------------|
| **BST**         | None                         | `insert()`, `delete()`, `search()` |
| **AVL Tree**    | Height-based rotations       | `getBalanceFactor()`, `rotateLeft()` |
| **Red-Black**   | Color rules                  | `fixInsertion()`, `balanceRemoval()` |

### Core Functionality

- #### BSTree
```kotlin
interface Tree<K : Comparable<K>, V> {
    fun insert(key: K, value: V)
    fun delete(key: K)
    fun search(key: K): V?
    fun iterator(): Iterator<Pair<K, V>>
}
```
- #### AVLTree
```kotlin
class AVLTree<K : Comparable<K>, V> : BinarySearchTree<K, V, AVLNode<K, V>>() {
    override fun insert(key: K, value: V) { /* height balancing logic */ }
    fun getBalanceFactor(key: K): Int { ... }
}
```
- #### RBTree
```kotlin
class RBTree<K : Comparable<K>, V> : BinarySearchTree<K, V, RBNode<K, V>>() {
    override fun insert(key: K, value: V) { /* color balancing logic */ }
    private fun fixInsertion(node: RBNode<K, V>) { ... }
}
```

---

## Installation
<details> 
<summary><strong>Command Line</strong></summary>

- ##### 1. Clone the Repository
```bash
git clone https://github.com/your-team/bstlib.git
```
- ##### 2. Navigate to Project Directory
```bash
cd bstlib
```

- ##### 3. Build the project
```bash
./gradlew build
```

- ##### 4. Run all tests
```bash
./gradlew test
```
</details>

<details>
<summary><strong>Open in IntelliJ IDEA</strong></summary>


- ##### 1. Open the Project

  Launch IntelliJ IDEA.

  Select File → Open.

  Choose the bstlib folder (cloned repository).

- ##### 2. Configure Gradle

  IDEA will automatically detect the Gradle project.

  Wait for dependencies to sync (check progress bar in the bottom-right corner).

- ##### 3. Run Tests from IDEA

  Navigate to src/test/kotlin/team8/bstlib/.

  Right-click on a test class (e.g., AVLTreeTest.kt).

  Select Run 'AVLTreeTest'.
</details>

---

## Testing

- ##### Run All Tests
```bash
./gradlew test
```

- ##### Run AVL-Specific Tests
```bash
./gradlew test --tests "team8.bstlib.AVLTreeTest"
```

**Reports:** Generated in `lib/build/reports/tests/test/index.html`.


---

## Features

- **Unified API** for all tree types.
- **In-order iteration** (ascending key order).
- **Balancing Mechanisms**:
    - AVL: Height balance (`|balance factor| ≤ 1`).
    - Red-Black: Strict color rules.

---

## Documentation

Generate HTML docs:
```bash
./gradlew dokkaHtml
```
## Team


- [@Nikolai Klevakin](https://github.com/NIcolaiKl) – Project graphics, Presentation.
- [@Lev Beyzer](https://github.com/lev666) – AVL Tree implementation, BST.
- [@Mikhail Ivanov](https://github.com/ivanovm-main) – Red-Black Tree logic, Core architecture, BST.


---
## License

[MIT License](https://github.com/spbu-coding-2024/trees-trees-team-8/blob/main/LICENSE). Free for educational and commercial use.

<p style="text-align: right;">
  <a href="#readme-top">↑ Back to top</a>
</p>