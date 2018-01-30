package algorithms;

import data.Data;
import data.Element;

public class DeepLearning implements Classifier{
    InputLayer in;
    OutputLayer out;
    Layer C1, C2, H1, H2, H3;
    Convolution inC1, C1C2;
    FullConnection C2H1, H1H2, H2H3, H3out, sharedWeight;

    Data trainingData;

    public DeepLearning(){
        in = new InputLayer(4);
        C1 = new Layer(6, new Activation.reLu());
        C2 = new Layer(4, new Activation.reLu());
        H1 = new Layer(2, new Activation.reLu());
        H2 = new Layer(2, new Activation.reLu());
        H3 = new Layer(2, new Activation.tanh());
        out = new OutputLayer(3);

        inC1 = new Convolution(in, C1, 2, 2);
        C1C2 = new Convolution(C1, C2, 2, 2);
        C2H1 = new FullConnection(C2, H1);
        H1H2 = new FullConnection(H1, H2);
        H2H3 = new FullConnection(H2, H3);
        H3out = new FullConnection(H3, out);

        sharedWeight = new FullConnection(in, H1);
        sharedWeight.addOutput(H2);
        sharedWeight.addOutput(H3);

        trainingData = null;

    }

    @Override
    public String getConsensus(Data data, Element element) {
        if (!data.equals(trainingData))
            train(data);
        in.setNeurons(element);
        out.propagation();

        out.reset();
        return null;
    }

    private void train(Data data) {
        out.setClasses(data.getLabels());


        out.propagation();

        out.setGradient(new double[]{1});
        //out.setGradient(out.calculGradient(out.neurons, new double[] {1}));
        in.backpropagation();

    }
}
