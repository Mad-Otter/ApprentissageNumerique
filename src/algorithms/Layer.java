package algorithms;

import java.util.ArrayList;

public class Layer {
    double[] neurons;
    Activation activation;
    ArrayList<Connection> inputs;

    public Layer(int nbNeurons, Activation activ){
        neurons = new double[nbNeurons];
        activation = activ;
        inputs = new ArrayList<>();
    }

    public void addInput(Connection c){
        inputs.add(c);
    }

}
