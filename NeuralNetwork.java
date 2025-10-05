import java.util.Random;
import java.util.Arrays;

/*							NOTE
	Here the outputSize must be equal to 3
	since the decide() method has only 3 outcomes
*/

public class NeuralNetwork{

	// weight vairables
	public double[][] w1; // input → hidden1
    public double[][] w2; // hidden1 → hidden2
    public double[][] w3; // hidden2 → output

    // biases variables
    public double[] bias_w1; 
	public double[] bias_w2;
	public double[] bias_w3;

    // other variables
    int inputSize;
    int outputSize;
    int nodes_in_layer1;
    int nodes_in_layer2;

    // initializing weights and biases according to node size
    NeuralNetwork(int inputSize, int nodes_in_layer1, int nodes_in_layer2, int outputSize){

    	// initializing values
    	this.nodes_in_layer1 = nodes_in_layer1;
    	this.nodes_in_layer2 = nodes_in_layer2;
    	this.inputSize = inputSize;
    	this.outputSize = outputSize;

    	// creating the weights
        w1 = randomMatrix(inputSize, nodes_in_layer1);
        w2 = randomMatrix(nodes_in_layer1, nodes_in_layer2);
        w3 = randomMatrix(nodes_in_layer2, outputSize);

        // creating the biases 
	    bias_w1 = new double[nodes_in_layer1];
	    bias_w2 = new double[nodes_in_layer2];
	    bias_w3 = new double[outputSize];
	    Arrays.fill(bias_w1, 0.0);
	    Arrays.fill(bias_w2, 0.0);
	    Arrays.fill(bias_w3, 0.0);
    }

     // creating a random matrix with given rows and columns
   	private double[][] randomMatrix(int rows, int columns){
   		Random rand = new Random();
   		double[][] array = new double[rows][columns];
   		for(int i = 0; i < rows; i++){
   			for(int j = 0; j < columns; j++){
   				// also adding some -ve values to matrix 
   				double value = (rand.nextDouble() * 2) - 1;
   				array[i][j] = Math.round(value*100.0)/100.0;
   			}
   		} return array;
   	}

    // sigmoid function 
    private double sigmoid(double number){
    	// this returns a value between 0 and 1
    	return 1.0 / (1.0 + Math.exp(-number));
    }

    // argmax function
    private int argmax(double[] arr) {
    	int maxId = 0;
    	for (int i = 1; i < arr.length; i++)
    	    if (arr[i] > arr[maxId])
    	        maxId = i;
    	return maxId;
	}


    // computing the hidden layer 1
    private double[] computeHiddenLayer1(double[] bodyInputs){
    	double[] hiddenLayer1Output = new double[nodes_in_layer1];
    	for(int i = 0; i < nodes_in_layer1; i++){
    		double sum = bias_w1[i];
    		for(int j = 0; j < inputSize; j++)
    			sum += w1[j][i]*bodyInputs[j];
    		hiddenLayer1Output[i] = sigmoid(sum);
    	} return hiddenLayer1Output;
    }

    // computing the hidden layer 2
    private double[] computeHiddenLayer2(double[] hiddenLayer1Output){
    	double[] hiddenLayer2Output = new double[nodes_in_layer2];
    	for(int i = 0; i < nodes_in_layer2; i++){
    		double sum = bias_w2[i];
    		for(int j = 0; j < nodes_in_layer1; j++)
    			sum += w2[j][i]*hiddenLayer1Output[j];
    		hiddenLayer2Output[i] = sigmoid(sum);
    	} return hiddenLayer2Output;
    }

    // computing the decision method 
    public String decide(double[] bodyInputs){
    	double[] h1Output = computeHiddenLayer1(bodyInputs);
    	double[] h2Output = computeHiddenLayer2(h1Output);
    	double[] outputs  = new double[outputSize];

    	// getting the final scores for decision
    	for(int i = 0; i < outputSize; i++){
    		outputs[i] = bias_w3[i];
    		for(int j = 0; j < nodes_in_layer2; j++)
    			outputs[i] += w3[j][i]*h2Output[j];
    		outputs[i] = sigmoid(outputs[i]);
    	}

    	 // finding maximum output index
   		int maxIndex = argmax(outputs);

    	// return decision based on index
    	if(outputSize == 3){
    	    switch (maxIndex){
    	        case 0: return "Run";
    	        case 1: return "Fight";
    	        case 2: return "Rest";
    	        default: return "Unknown";
    	   	}
    	} else
    	    return "Decision not set for this index";
    }

    // method to dislay the weights
    public void displayWeights() {
        System.out.println("Weights for W1: " + Arrays.deepToString(w1));
        System.out.println("Weights for W2: " + Arrays.deepToString(w2));
        System.out.println("Weights for W3: " + Arrays.deepToString(w3));
    }

    // method to display the biases
    public void displayBiases() {
        System.out.println("Bias for W1: " + Arrays.toString(bias_w1));
        System.out.println("Bias for W2: " + Arrays.toString(bias_w2));
        System.out.println("Bias for W3: " + Arrays.toString(bias_w3));
    }
}