package algorithms;

import data.Data;
import data.Element;

import java.util.ArrayList;


public class IRIS_NN implements Classifier{
    InputLayer in;
    OutputLayer out;
    Layer C1, C2, H1, H2, H3;
    Convolution inC1, C1C2;
    FullConnection C2H1, H1H2, H2H3, H3out, sharedWeight;
    ArrayList<Connection> allLayers;

    Data trainingData;

    public IRIS_NN(){
        allLayers = new ArrayList<>();

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
        addAll(in,C1,C2,H1,H2,H3,out,inC1,C1C2,C2H1,H1H2,H2H3,H3out,sharedWeight);
    }

    private void addAll(Connection ... all){
        for(Connection c:all){
            allLayers.add(c);
        }
    }

    private void printAll(){
        for(Connection c:allLayers){
            System.out.println(c.toString());
        }
        System.out.println("");
    }


    @Override
    public String getConsensus(Data data, Element element) {
        if (!data.equals(trainingData))
            train(data);
        in.setNeurons(element);
        out.propagation();
        out.reset();
        String pred = out.predict();
        System.out.println(pred);
        return pred;
    }

    private void train(Data data) {
        out.setClasses(data.getLabels());
        for(Connection c: allLayers)
            c.init();
        for (int k = 0; k < 10000; k++) { // TODO early stopping
            Element element = data.getRandomElement();
            Epsilon.update(k);
            in.setNeurons(element);
            out.propagation();
            out.calculGradient(element.getLabel());
            in.backpropagation();
            //printAll();
        }
    }
}
