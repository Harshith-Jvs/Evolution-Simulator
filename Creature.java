public class Creature{
    double speed;
    double strength;
    double energy;
    double distance;
    NeuralNetwork brain;

    public Creature(double speed, double strength, double energy, double distance, NeuralNetwork brain){
        this.speed = speed;
        this.strength = strength;
        this.energy = energy;
        this.distance = distance;
        this.brain = brain;
    }

    public double[] getInputs(){
        return new double[]{speed, strength, energy, distance};
    }

    public String decide(){
        return brain.decide(getInputs());
    }

    @Override
	public String toString() {
	    return "Speed: " + speed +
	           ", Strength: " + strength +
	           ", Energy: " + energy +
	           ", Distance: " + distance;
	}
}


// class prey extending the creature class
class Prey extends Creature{
    public Prey(double speed, double strength, double energy, double distance, NeuralNetwork brain){
        super(speed, strength, energy, distance, brain);
    }
}


// class predator extending the creature class 
class Predator extends Creature{
    public Predator(double speed, double strength, double energy, double distance, NeuralNetwork brain){
        super(speed, strength, energy, distance, brain);
    }
}