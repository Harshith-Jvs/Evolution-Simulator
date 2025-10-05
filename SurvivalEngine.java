import java.util.ArrayList;
import java.util.Random;

public class SurvivalEngine{

	// survival phase
	public void survivalPhase(
                ArrayList<Prey> preyPopulation, 
                ArrayList<Predator> predatorPopulation){

        // energy cost for a decision
		final double runCostPrey = 3.5;
    	final double runCostPred = 3.0;
    	final double fightCostPrey = 5.0;
    	final double fightCostPred = 3.0;
    	final double restGain = 0.2;
    	final double foodGain = 1.5;

        // mortality rates for random deaths based on current population
        final double mortalityRatePrey = Math.min(0.5, 0.02 + (preyPopulation.size() / 1000.0));
		final double mortalityRatePredator = Math.min(0.5, 0.03 + (predatorPopulation.size() / 1000.0));

    	// looping through each predator to all preys
    	for (int i = 0; i < predatorPopulation.size(); i++) {
        	Predator predator = predatorPopulation.get(i);

        	// predator will hunt at most one prey
        	boolean predatorHasEaten = false;

        	for (int j = 0; j < preyPopulation.size(); j++) {
        	    Prey prey = preyPopulation.get(j);
				
				// Run, Fight, Rest 
        	    String preyDec = prey.decide();
        	    String predDec = predator.decide();
	
        	    boolean preyDies = false;
        	    boolean predatorDies = false;

        	  	// -------- LOGIC: RUN -------
        	  	if (preyDec.equals("Run")) {
              		if (predDec.equals("Run") || predDec.equals("Fight")) {
              		    if (prey.speed > predator.speed) {
              		        prey.energy -= runCostPrey;
              		        predator.energy -= runCostPred;
              		    } else if (prey.speed < predator.speed) {
              		        preyDies = true;
              		        predator.energy -= runCostPred;
              		        predator.energy += foodGain;
              		        predatorHasEaten = true;
              		    } else {
              		        prey.energy -= runCostPrey;
              		        predator.energy -= runCostPred;
              		    }
              		} 
              		else { // predator rests
              		    prey.energy -= runCostPrey;
              		    predator.energy += restGain;
              		}
            	}

            	// -------- LOGIC: FIGHT -------
            	else if (preyDec.equals("Fight")) {
            		if (predDec.equals("Run") || predDec.equals("Fight")) {
            			if(prey.strength > predator.strength) {
            				predatorDies = true;
            				prey.energy -= fightCostPrey;
            			} else if(prey.strength < predator.strength) {
            				preyDies = true;
            				predator.energy -= fightCostPred;
            				predator.energy += foodGain;
            				predatorHasEaten = true;
            			} else {
            				prey.energy -= fightCostPrey/2;
            				predator.energy -= fightCostPred/2;
            			}
            		}
            		else { // predator rests
            			predator.energy += restGain;
            		}
            	}

            	// --------- LOGIC: REST -------
            	else { // prey rests
            	    if (predDec.equals("Run") || predDec.equals("Fight")) {
            	        preyDies = true;
            	        predator.energy += foodGain;
            	        predatorHasEaten = true;
            	    } else { // both rest
            	        prey.energy += restGain;
            	        predator.energy += restGain;
            	    }
            	}

            	// Apply deaths
           		if (preyDies) { // remove prey from env
           		    prey.energy = -1; // marking dead
           		}

           		if (predatorDies) {
           		    predator.energy = -1;
           		    break; // move to next predator
           		}

           		// if predator has eaten then stop hunting
           		if (predatorHasEaten) break;
           	}         
		}

        // removing creatures with zero or less energy 
        preyPopulation.removeIf(p -> p.energy <= 0);
        predatorPopulation.removeIf(p -> p.energy <= 0);

        // random deaths
        preyPopulation.removeIf(prey -> Math.random() < mortalityRatePrey);
        predatorPopulation.removeIf(pred -> Math.random() < mortalityRatePredator);

        // energy decay for metabolism
        for (Prey prey : preyPopulation) prey.energy -= 0.5;
        for (Predator predator : predatorPopulation) predator.energy -= 0.8;
	}


    // reproduction phase
    public void reproductionPhase(
                ArrayList<Prey> preyPopulation, 
                ArrayList<Predator> predatorPopulation,
                ArrayList<Prey> nextPreyGen,
                ArrayList<Predator> nextPredatorGen,
                double mutationRate){

        double energyThreshold = 5.0;
        double reproduceEnergyCostPrey = 2.0;
        double reproduceEnergyCostPred = 2.5;

        for (Prey prey : preyPopulation){
        if (prey.energy > energyThreshold) {
            double reproduceProbability = prey.energy / (prey.energy + 8); 
            if (Math.random() < reproduceProbability) {
                NeuralNetwork childBrain = mutate(prey.brain, mutationRate);
                nextPreyGen.add(new Prey(
                    mutateTrait(prey.speed, mutationRate),
                    mutateTrait(prey.strength, mutationRate),
                    Math.max(prey.energy / 2, 3.0),
                    Math.max(2.0, mutateTrait(prey.distance, mutationRate)),
                    childBrain
                ));
                prey.energy -= reproduceEnergyCostPrey;
            }
        } 
        }

        for (Predator predator : predatorPopulation) {
            if (predator.energy > energyThreshold) {
                double reproduceProbability = predator.energy / (predator.energy + 10);
                if (Math.random() < reproduceProbability) {
                    NeuralNetwork childBrain = mutate(predator.brain, mutationRate);
                    nextPredatorGen.add(new Predator(
                        mutateTrait(predator.speed, mutationRate),
                        mutateTrait(predator.strength, mutationRate),
                        Math.max(predator.energy / 2, 3.0),
                        Math.max(2.0, mutateTrait(predator.distance, mutationRate)),
                        childBrain
                    ));
                    predator.energy -= reproduceEnergyCostPred;
                }
            } 
        }
    }


    // adding survivors to next gen
    public void addSurvivors(
                ArrayList<Prey> preyPopulation, 
                ArrayList<Predator> predatorPopulation,
                ArrayList<Prey> nextPreyGen,
                ArrayList<Predator> nextPredatorGen){

        // adding the survived preys to new generation
        for (Prey prey : preyPopulation) {
            if (prey.energy > 0) nextPreyGen.add(prey);
        }

        // adding the survived predators to new generation
        for (Predator predator : predatorPopulation) {
            if (predator.energy > 0) nextPredatorGen.add(predator);
        }
    }


    // update the population 
    public void updatePopulation(
                ArrayList<Prey> preyPopulation, 
                ArrayList<Predator> predatorPopulation,
                ArrayList<Prey> nextPreyGen,
                ArrayList<Predator> nextPredatorGen){

        preyPopulation.clear();
        preyPopulation.addAll(nextPreyGen);
    
        predatorPopulation.clear();
        predatorPopulation.addAll(nextPredatorGen);
    }


    // mutation method for weights in neural network
    public NeuralNetwork mutate(NeuralNetwork parentBrain, double mutationRate) {
        NeuralNetwork childBrain = new NeuralNetwork(
            parentBrain.inputSize,
            parentBrain.nodes_in_layer1,
            parentBrain.nodes_in_layer2,
            parentBrain.outputSize
        );

        for (int i = 0; i < parentBrain.w1.length; i++)
            for (int j = 0; j < parentBrain.w1[i].length; j++)
                childBrain.w1[i][j] = mutateTrait(parentBrain.w1[i][j], mutationRate);

        for (int i = 0; i < parentBrain.w2.length; i++)
            for (int j = 0; j < parentBrain.w2[i].length; j++)
                childBrain.w2[i][j] = mutateTrait(parentBrain.w2[i][j], mutationRate);

        for (int i = 0; i < parentBrain.w3.length; i++)
            for (int j = 0; j < parentBrain.w3[i].length; j++)
                childBrain.w3[i][j] = mutateTrait(parentBrain.w3[i][j], mutationRate);

        for (int i = 0; i < parentBrain.bias_w1.length; i++)
            childBrain.bias_w1[i] = mutateTrait(parentBrain.bias_w1[i], mutationRate);

        for (int i = 0; i < parentBrain.bias_w2.length; i++)
            childBrain.bias_w2[i] = mutateTrait(parentBrain.bias_w2[i], mutationRate);

        for (int i = 0; i < parentBrain.bias_w3.length; i++)
            childBrain.bias_w3[i] = mutateTrait(parentBrain.bias_w3[i], mutationRate);

        return childBrain;
    }


    // mutation method for traits
    public double mutateTrait(double trait, double mutationRate) {
        double mutation = new Random().nextGaussian() * mutationRate;
        double result = trait + mutation;
        return Math.max(0.1, Math.min(10.0, result));
    }


}