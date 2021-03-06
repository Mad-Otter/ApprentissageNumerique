package algorithms;

public class Convolution extends Connection {
    double[][] weights;
    double[] activations;
    double[] gradients;
    int size;
    int nbFilters;

    public Convolution(Layer in, Layer out, int size, int nbFilters) {
        super();
        addInput(in);
        addOutput(out);
        this.size = size;
        this.nbFilters = nbFilters;
        weights = new double[nbFilters][size];
        activations = new double[out.neurons.length];
        gradients = new double[in.neurons.length];
    }

    public void setWeights(double[][] input) {
        if (input.length == weights.length && input[0].length == weights[0].length)
            weights = input;
        else
            System.out.println("Error in setWeights() : wrong input size");
    }

    @Override
    public void init() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = (Math.random() * 2) - 1;
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (double[] d : weights)
            for (double d2 : d)
                s += " " + d2;
            s += "/";
        return s;
    }

    @Override
    public double[] propagation() {
        if (!backprop) {
            int filterSize = activations.length / nbFilters;
            for (int i = 0; i < activations.length; i++) {
                double sum = 0;
                for (Connection in : inputs) {
                    double[] values = in.propagation();
                    for (int j = 0; j < size; j++)
                        sum += values[(i % filterSize + j) % values.length] * weights[i / filterSize][j];
                }
                activations[i] = sum;
            }
            backprop = true;
        }
        return activations;
    }

    @Override
    public double[] backpropagation() {
        if (backprop) {
            int filterSize = gradients.length / nbFilters;
            for (int i = 0; i < gradients.length; i++) {
                double sum = 0;
                for (Connection out : outputs) {
                    double[] values = out.backpropagation();
                    for (int j = 0; j < nbFilters; j++)
                        for (int k = 0; k < size; k++)
                            if (i - k >= 0 && i - k <= filterSize) sum += values[i - k] * weights[j][k];
                }
                gradients[i] = sum;
            }
            backprop = false;
        }
        return gradients;
    }
}
