import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Simulation {
    public static void main(String[] args) {

        SurvivalEngine engine = new SurvivalEngine();
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // ---------- Input parameters ---------
        System.out.print("Number of prey species: ");
        int numberOfPrey = scanner.nextInt();

        System.out.print("Number of predator species: ");
        int numberOfPredator = scanner.nextInt();

        System.out.print("Generations: ");
        int totalGenerations = scanner.nextInt();

        System.out.print("Mutation rate (0-1): ");
        double mutationRate = scanner.nextDouble();


        // ----------- Initial populations ----------
        ArrayList<Prey> preyPopulation = new ArrayList<>();
        ArrayList<Predator> predatorPopulation = new ArrayList<>();

        // adding preys to the environment
        for (int i = 0; i < numberOfPrey; i++){
        	double speed = Math.round((rand.nextDouble() * (8.0 - 2.5) + 2.5) * 10.0) / 10.0;
    		double strength = Math.round((rand.nextDouble() * (8.0 - 3.5) + 5.0) * 10.0) / 10.0;
    		double distance = Math.round((rand.nextDouble() * (8.0 - 2.5) + 2.5) * 10.0) / 10.0;
    		double energy = Math.round((rand.nextDouble() * (8.0 - 3.0) + 5.0) * 10.0) / 10.0;
            preyPopulation.add(new Prey(speed, strength, energy, distance, new NeuralNetwork(4, 8, 5, 3)));
        }

        // adding predators to environment
        for(int i = 0; i < numberOfPredator; i++){
        	double speed = Math.round((rand.nextDouble() * (10.0 - 2.5) + 2.5) * 10.0) / 10.0;
    		double strength = Math.round((rand.nextDouble() * (10.0 - 5.0) + 5.0) * 10.0) / 10.0;
    		double distance = Math.round((rand.nextDouble() * (10.0 - 2.5) + 2.5) * 10.0) / 10.0;
    		double energy = Math.round((rand.nextDouble() * (10.0 - 3.0) + 5.0) * 10.0) / 10.0;
            predatorPopulation.add(new Predator(speed, strength, energy, distance, new NeuralNetwork(4, 8, 5, 3)));
        }


        // ---------------- GENERATION LOOP ------------------
        for(int gen = 1; gen <= totalGenerations; gen++){

            ArrayList<Prey> nextPreyGen = new ArrayList<>();
            ArrayList<Predator> nextPredatorGen = new ArrayList<>();

  		    System.out.println("\n============================================");
            System.out.println("\t\tGeneration #" + gen);
            System.out.println("--------------------------------------------");

            // Current population before reproduction
            System.out.println("> Current Prey Population       : " + preyPopulation.size());
            System.out.println("> Current Predator Population   : " + predatorPopulation.size());
    
  		    // 1. Survival Phase
  		    engine.survivalPhase(preyPopulation, predatorPopulation);
            // popultaion of prey and predators survived 
            System.out.println("> Survived Prey Population      : " + preyPopulation.size());
            System.out.println("> Survived Predator Population  : " + predatorPopulation.size());
    
            // 2. Reproduction Phase
            engine.reproductionPhase(preyPopulation, predatorPopulation, nextPreyGen, nextPredatorGen, mutationRate);
            // population after reproduction 
            System.out.println("> Prey Offspring Population     : " + nextPreyGen.size());
            System.out.println("> Predator Offspring Population : " + nextPredatorGen.size());

            // 3. Transforming the whole population to next generation 
            engine.addSurvivors(preyPopulation, predatorPopulation, nextPreyGen, nextPredatorGen);

            // updating the population 
            engine.updatePopulation(preyPopulation, predatorPopulation, nextPreyGen, nextPredatorGen);
            System.out.println("--------------------------------------------");
            // final population going to next geration
            System.out.println("> Final Prey Population         : " + nextPreyGen.size());
            System.out.println("> Final Predator Population     : " + nextPredatorGen.size());

            // condition for extinction
            if (preyPopulation.size() == 0 || predatorPopulation.size() == 0) {
                System.out.println("Extinction happened. Ending simulation.");
                break;
            }

            // closing statements
            double preyPredRatio = preyPopulation.size() > 0 ? (double) preyPopulation.size() / predatorPopulation.size() : 0;
            System.out.printf("> Prey/Predator Ratio           : %.2f\n", preyPredRatio);
            System.out.println("============================================\n");

            // holding for a second before proceeding 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted!");
            }
            
  		}
	}
}