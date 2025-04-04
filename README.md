<a id="readme-top"></a>
# BSTLib

[![CI Status](https://github.com/spbu-coding-2024/trees-trees-team-8/actions/workflows/ci.yml/badge.svg)](https://github.com/spbu-coding-2024/trees-trees-team-8/actions)
[![MIT License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Overview

While many tree implementations exist, we have not found any libraries that provide a unified interface for BST, AVL, and Red-Black Trees with a focus on educational clarity and production readiness. This project aims to fill this gap. It includes three types of triage:
- **BST (Binary Search Tree):** A basic binary search tree without self-balancing.
- **AVL Tree:** A self-balancing tree using height-based rotations.
- **Red-Black Tree:** A self-balancing tree enforcing color properties.

Each implementation offers core operations such as `insert`, `delete`, and `search` while maintaining internal consistency and performance guarantees.

---

## Table of Contents
- [Installation](#installation)
- [Testing](#testing)
- [Team](#team)
- [License](#license)

---

## Installation
#### Clone the repository and build the project:

```bash
git clone https://github.com/spbu-coding-2024/trees-trees-team-8.git
cd trees-trees-team-8
./gradlew build
```

---

## Testing

#### Run tests using Gradle:
```bash
./gradlew test
```  

> [!IMPORTANT]
>
> Test reports are generated at:
>
> ```swift
> lib/build/reports/tests/test/index.html


## Team


- [@Nikolai Klevakin](https://github.com/NIcolaiKl) – Project graphics, Presentation.
- [@Lev Beyzer](https://github.com/lev666) – AVL Tree implementation, BST.
- [@Mikhail Ivanov](https://github.com/ivanovm-main) – Red-Black Tree logic, Core architecture, BST.


---
## License

[<b>MIT License</b>](LICENSE). Free for educational and commercial use.

<p style="text-align: right;">
  <a href="#readme-top">↑ Back to top</a>
</p>