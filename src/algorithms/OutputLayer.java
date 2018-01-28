package algorithms;

public class OutputLayer extends Layer{

    public OutputLayer(int nbNeurons, Activation activ) {
        super(nbNeurons, activ);
    }

    public double[] backpropagation(){
        backprop = false;
        return gradient;
    }
}
