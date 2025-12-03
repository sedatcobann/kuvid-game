# Fast and Furious – KUVID-Style Atom Shooter Game  
A Java-based 2D educational action game where the player shoots atoms and molecules, manages inventory, blends compounds, uses power-ups, and interacts with reaction blocks — all implemented with an object‑oriented architecture and Swing UI.

---

## 📑 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Gameplay](#gameplay)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Dependencies](#dependencies)
- [Testing](#testing)
- [Future Improvements](#future-improvements)

---

## 📌 Overview
This project implements a chemistry‑themed shooting game inspired by KUVID.  
The player controls a shooter that fires atoms or molecules toward falling targets.  
The environment simulates movement patterns, collisions, breaking/blending of components, and inventory/power‑up effects.

This project demonstrates:
- Object‑oriented programming principles  
- MVC-like separation of concerns  
- Collision detection and movement physics  
- Swing GUI rendering  
- Game state persistence  
- JUnit testing for gameplay logic  

---

## ✨ Features

### 🎮 Core Gameplay
- Control a shooter and fire atoms/molecules.
- Incoming α, β, γ, and σ atoms fall toward the player.
- Reaction blocks and molecule compounds influence gameplay.

### 🧪 Chemistry Simulation
- Blend atoms into molecules using the **Blender**.
- Break molecules back into atoms.
- Manage atomic types with different stability levels and radii.

### ⚙️ Game Mechanics
- Multiple movement patterns (linear, zig-zag, circular).
- Collision detection for:
  - Shooter projectiles
  - Falling atoms/molecules
  - Power-ups
- Inventory system for:
  - Blended molecules
  - Dropped power-ups

### 💾 Persistence
- Save/load game state using JSON (custom file handling).

### 🧩 UI
- Java Swing–based UI with:
  - Game panel
  - Shooter sprites
  - Atom/molecule images
  - Reaction block icons
  - Inventory indicators

---

## 🎮 Gameplay
- Use the shooter to fire atoms toward incoming targets.
- Catch or collide with power-ups to gain special abilities.
- Blend atoms to create advanced molecules with the Blender.
- Avoid collisions with harmful molecules.
- Reach the end condition by clearing waves or surviving long enough.

---

## 🏛 Architecture

The project follows a structured modular design:

```
Application Layer
│
├── Domain Layer (game logic)
│   ├── GameEnvironment
│   ├── Controller
│   ├── Atom / Molecule / MoleculeCompound
│   ├── Movement patterns
│   ├── Blender and Inventory
│   └── Collision & physics
│
├── UI Layer (Swing)
│   ├── GameFrame
│   ├── Board
│   └── Renderers & Sprites
│
└── Persistence Layer
    ├── Save & load system
    └── JSON file interaction
```

---

## 📁 Project Structure

```
src/
├── application/        # Bridges UI and domain logic
├── domain/             # Core game mechanics
│   ├── Atom types (alpha, beta, gamma, sigma)
│   ├── Molecules & compounds
│   ├── Movement patterns
│   ├── Collision logic
│   ├── Power-ups
│   └── Blender + Inventory
├── persistence/        # Saving/loading game state
├── ui/                 # Swing UI and assets
│   ├── GameFrame
│   └── Board & sprites
└── test/               # JUnit tests
```

---

## ▶️ How to Run

### Option 1 — Running from an IDE (Eclipse/IntelliJ)
1. Import the project as a Java project.
2. Add the required MongoDB driver JARs (already included in directory).
3. Run the main UI launcher:
   ```
   src/ui/GameFrame.java
   ```

### Option 2 — Compile via CLI
```sh
javac -d bin $(find src -name "*.java")
java -cp bin ui.GameFrame
```

---

## 📦 Dependencies
Included JARs:
- `mongo-java-driver-3.11.2.jar`
- `mongodb-driver-core-3.11.2.jar`

Standard libraries:
- Java Swing
- Java Collections
- Java AWT

Testing:
- JUnit 4

---

## 🧪 Testing
JUnit tests are located in:

```
test/test/
```

Tests cover:
- Atom blending  
- Atom breaking  
- Movement pattern changes  
- Collision behavior  
- Reaction block interactions  
- Field/radius checks  

Run with:
```sh
java -cp .:junit.jar org.junit.runner.JUnitCore test.<TestName>
```

---

## 🚀 Future Improvements
- Convert UI to JavaFX for smoother gameplay.
- Add sound effects and animations.
- Introduce level system and difficulty progression.
- Develop a scoring system.
- Add configurable settings (speed, spawn rate, volume).
- Refactor controllers into cleaner MVC modules.
- Improve save/load format (use Gson or Jackson).

---

## 📜 License
This project is for educational purposes under COMP302 coursework.

---

## 🙌 Author
Developed by **Sedat Çoban**  
