Project Title: Evolutionary Predator-Prey Simulation  
------------------------------------------------------

Description:
------------
This project is a simulation of natural evolution using a **Neuro-Evolutionary Algorithm**.  
It models how two species — prey and predators — interact, survive, reproduce, and evolve over generations.  
The simulation uses genetic mutation, survival strategies (run, fight, rest), and random mortality to mimic natural selection.

The goal is to simulate realistic evolution and observe how species adapt over time.

Features:
---------
1. **Dynamic Evolution:** Creatures evolve their traits (speed, strength, distance, energy) through generations.
2. **Neural Network Decision-Making:** Each creature has a neural network brain deciding its behavior.
3. **Survival Logic:** Run, Fight, Rest choices based on energy and situation.
4. **Random Deaths:** Natural mortality rate to simulate real-life unpredictability.
5. **Mutation Mechanism:** Mutation of traits and neural network weights for evolution.
6. **Extinction Events:** Populations can naturally go extinct if conditions are harsh.
7. **Detailed CLI Output:** Shows generation data with prey/predator counts and survival statistics.
8. **Easy Configuration:** Input species count, generations, mutation rate.

Project Structure:
------------------
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

Key Algorithms:
---------------
- **Survival Phase:** Determines outcomes of interactions between prey and predators.
- **Reproduction Phase:** Determines offspring based on energy levels and probability.
- **Mutation:** Gaussian mutation applied to traits and neural network weights.
- **Neural Network:** Feedforward network with sigmoid activation and decision-making output.

Potential Improvements:
-----------------------
- Implement a GUI for better visualization.
- Add more environmental factors (e.g., food scarcity, weather effects).
- Make mortality rate dynamic based on environmental pressure.
- Introduce more species for complex ecosystem simulation.

Author:
-------
Name:   Jvs Harshtih<br>
GitHub: https://github.com/Harshith-Jvs<br>
Email:  harshith.jvs2006@gmail.com



