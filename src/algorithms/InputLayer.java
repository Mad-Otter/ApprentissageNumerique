package algorithms;

public class InputLayer extends Layer{

    public InputLayer(int nbNeurons) {
        super(nbNeurons, new Activation.reLu());
    }

    public double[] propagation(){
        return neurons;
    }

    public double[] backpropagation(){
        for (Connection c : outputs)
            c.backpropagation();
        return null;
    }
}
