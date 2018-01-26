package algorithms;

public class DeepLearning implements Classifier{
    public DeepLearning(){
        Layer H1 = new Layer(2, new Activation.reLu());
        Layer H2 = new Layer(2, new Activation.reLu());
        Layer H3 = new Layer(2, new Activation.tanh());

        FullConnection H1H2 = new FullConnection(H1, H2);
        FullConnection H2H3 = new FullConnection(H2, H3);
    }
}
