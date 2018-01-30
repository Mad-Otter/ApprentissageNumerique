package algorithms;

import data.Element;

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

    public void setNeurons(Element element) {
        for(int i=0; i<neurons.length; i++)
            neurons[i] = element.getVectorValue(i);
    }
}
