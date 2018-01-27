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

    public void setNeurons(double[] input){
        if(input.length == neurons.length)
            neurons = input;
        else
            System.out.println("Error in setNeurons() : wrong input size");
    }

    public void setTheta(double[] input){
        if(input.length == theta.length)
            theta = input;
        else
            System.out.println("Error in setTheta() : wrong input size");
    }

    public void update(){
        for(int i=0; i<neurons.length; i++)
            neurons[i] = 0;
        for(Connection c:inputs)
            c.update();
        System.out.print("Layer neurons : ");
        for(int i=0; i<neurons.length; i++) {
            neurons[i] = activation.transfert(neurons[i] + theta[i]);
            System.out.print(neurons[i]+" ");
        }
        System.out.println("");
    }

}
