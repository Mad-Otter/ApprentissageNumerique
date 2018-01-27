package algorithms;

import java.util.ArrayList;

public class Layer {
    double[] neurons;
    double[] theta;
    double[] gradient;
    Activation activation;
    ArrayList<Connection> inputs;
    ArrayList<Connection> outputs;

    public Layer(int nbNeurons, Activation activ){
        neurons = new double[nbNeurons];
        theta = new double[nbNeurons];
        gradient = new double[nbNeurons];
        activation = activ;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    public void addInput(Connection c){
        inputs.add(c);
    }
    public void addOutput(Connection c){
        outputs.add(c);
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

    public void setGradient(double[] input){
        if(input.length == gradient.length)
            gradient = input;
        else
            System.out.println("Error in setGradient() : wrong input size");
    }

    public void propagation(){
        for(int i=0; i<neurons.length; i++)
            neurons[i] = 0;
        for(Connection c:inputs)
            c.propagation();
        System.out.print("Layer neurons : ");
        for(int i=0; i<neurons.length; i++) {
            neurons[i] = activation.transfert(neurons[i] + theta[i]);
            System.out.print(neurons[i]+" ");
        }
        System.out.println("");
    }

    public void backpropagation(){
        for(int i=0; i<gradient.length; i++)
            gradient[i] = 0;
        for(Connection c:outputs)
            c.backpropagation();
        System.out.print("Layer gradients : ");
        for(int i=0; i<gradient.length; i++) {
            gradient[i] = activation.derivative(neurons[i])*gradient[i];
            System.out.print(gradient[i]+" ");
        }
        System.out.println("");
    }

}
