package algorithms;

import java.util.ArrayList;

public class Layer {
    double[] neurons;
    double[] theta;
    Activation activation;
    ArrayList<Connection> inputs;

    public Layer(int nbNeurons, Activation activ){
        neurons = new double[nbNeurons];
        theta = new double[nbNeurons];
        activation = activ;
        inputs = new ArrayList<>();
    }

    public void addInput(Connection c){
        inputs.add(c);
    }

    public void update(){
        for(int i=0; i<neurons.length; i++)
            neurons[i] = 0;
        for(Connection c:inputs)
            c.update();
        for(int i=0; i<neurons.length; i++)
            neurons[i] = activation.transfert(neurons[i]+theta[i]);
    }

}
