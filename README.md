# 🧠 Modeling the Evolution of Decision-Making Through Neuroevolutionary Algorithms

This is a **Neuroevolution Simulation** based project that models how intelligent behavior can emerge from simple evolutionary rules.  
It demonstrates how **evolutionary pressure** can give rise to **decision-making abilities**, using artificial neural systems as an **analogy for early nervous systems** that once evolved on Earth.

Just as the **Bohr atomic model** doesn’t literally represent what a real atoms look like but captures their core principles,  
this project doesn’t recreate real biological evolution — instead, it **abstracts** its logic.  
It shows how **variation**, **selection**, and **inheritance** can drive the **emergence of adaptive intelligence** over generations.


## 🧩 Core Concept
The simulation explores two evolving digital species — **prey** and **predators** — that interact, survive, reproduce, and mutate across generations.  
Each creature has a **neural network brain** controlling its behavior, enabling it to **decide** whether to run, fight, or rest based on energy and context.

Through the iterative process of **neuroevolution**, the population naturally adapts to environmental pressures —  
mirroring how, in nature, millions of years of evolution shaped the first decision-making systems in living organisms.

----------------

## ⚙️ Features

1. **Dynamic Evolution:**  
   Creatures evolve their traits (speed, strength, distance, and energy) over generations through mutation and selection.

2. **Neural Network Decision-Making:**  
   Each creature has a simple feedforward neural network that determines actions like *run*, *fight*, or *rest*.

3. **Survival Logic:**  
   Behavior is chosen based on situational awareness and energy levels, simulating instinctive survival choices.

4. **Random Mortality:**  
   Includes a probabilistic natural death rate to reflect real-world unpredictability.

5. **Mutation Mechanism:**  
   Gaussian mutation is applied to both biological traits and neural network weights.

6. **Extinction Events:**  
   Populations can die out completely if environmental or evolutionary pressures become too harsh.

7. **Detailed CLI Output:**  
   Displays generation-by-generation statistics — including prey/predator counts, survival ratios, and extinction events.

8. **Easy Configuration:**  
   Input species count, number of generations, and mutation rate directly from the console.

----------------

## 🗝️ Key Algorithms

- **Survival Phase:**  
  Determines interaction outcomes between prey and predators based on energy, strength, and strategy.

- **Reproduction Phase:**  
  Controls offspring generation probability based on fitness (energy) and survival.

- **Mutation Phase:**  
  Introduces small Gaussian variations in both physical traits and neural weights to simulate evolution.

- **Neural Network Logic:**  
  Implements a simple feedforward network with sigmoid activation to make decisions dynamically.

----------------

## 🧬 Biological Analogy
This simulation is not a literal model of real evolution but a **computational mirror** of its principles.  
While real organisms inherit **DNA blueprints**, here the digital creatures inherit **neural weights** — representing a compressed model of inherited learning.  
It offers a glimpse into how **evolutionary pressure can give rise to adaptive decision-making**, much like how primitive nervous systems evolved in early life.

> **A Neuroevolutionary simulation modeling the emergence of adaptive decision-making in early life analogs.**

----------------

## Project Structure

- `Simulation.java` — main program, runs the simulation loop.
- `SurvivalEngine.java` — contains survival phase, reproduction phase, mutation logic, and population management.
- `NeuralNetwork.java` — defines the neural network brain for creatures, with random weights and biases.
- `Creature.java` — parent class for Prey and Predator classes.


Simulation output will show generation-by-generation statistics including:
- Survived population
- Offspring population
- Final population going into next generation
- Prey/Predator ratio
- Extinction events (if any)

----------------

## Author:
Name:   Jvs Harshtih<br>
GitHub: https://github.com/Harshith-Jvs<br>
Email:  harshith.jvs2006@gmail.com

----------------
