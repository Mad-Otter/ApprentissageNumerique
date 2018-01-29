package algorithms;

import data.Data;
import data.Element;

public class DeepLearning implements Classifier{
    public DeepLearning(){
        Layer in = new InputLayer(3);
        Layer C1 = new Layer(4, new Activation.reLu());
        Layer out = new OutputLayer(1, new Activation.reLu());

        Convolution inC1 = new Convolution(in, C1, 2, 2);
        FullConnection C1out = new FullConnection(C1, out);

        in.setNeurons(new double[]{3, 2, 1});
        C1.setTheta(new double[]{0, 0, 0, 0});

        inC1.setWeights(new double[][]{{1, 1},{2, 2}});
        C1out.setWeights(new double[][]{{1,1,1,1}});

        out.propagation();


        out.setGradient(new double[]{1});
        //out.setGradient(out.calculGradient(out.neurons, new double[] {1}));
        in.backpropagation();
    }

    @Override
    public String getConsensus(Data data, Element element) {
        return null;
    }
}
