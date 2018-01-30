package algorithms;

import java.util.ArrayList;

public class OutputLayer extends Layer{
    ArrayList<String> classes;

    public OutputLayer(int nbNeurons) {
        super(nbNeurons, new Activation.reLu());
    }

    public double[] backpropagation(){
        backprop = false;
        return gradient;
    }

    public void setClasses(ArrayList<String> classes){
        this.classes = classes;
    }

    public String predict(){
        softmax();
        int max = 0;
        for(int i=1; i<neurons.length; i++)
            if(neurons[i] > neurons[max])
                max = i;
        return classes.get(max);
    }

    private void softmax(){
        double sum = 0;
        for(int i=0; i<neurons.length; i++){
            neurons[i] = Math.exp(neurons[i]);
            sum += neurons[i];
        }
        for(int i=0; i<neurons.length; i++) {
            neurons[i] = neurons[i] / sum;
        }
    }
}
