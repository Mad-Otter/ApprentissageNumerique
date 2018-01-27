package algorithms;

public class FullConnection extends Connection {
    Layer input;
    Layer output;
    double[][] weights;

    public FullConnection(Layer in, Layer out){
        input = in;
        output = out;
        weights = new double[out.neurons.length][in.neurons.length];
        output.addInput(this);
        input.addOutput(this);
    }

    public void setWeights(double[][] input){
        if(input.length == weights.length && input[0].length == weights[0].length)
            weights = input;
        else
            System.out.println("Error in setWeights() : wrong input size");
    }

    @Override
    public void propagation(){
        for(int i=0; i<output.neurons.length; i++){
            double sum = 0;
            for(int j=0; j<input.neurons.length; j++)
                sum += input.neurons[j]*weights[i][j];
            output.neurons[i] += sum;
        }
    }

    @Override
    public void backpropagation() {
        for(int i=0; i<input.gradient.length; i++){
            double sum = 0;
            for(int j=0; j<output.gradient.length; j++)
                sum += output.gradient[j]*weights[j][i];
            input.gradient[i] += sum;
        }
    }

}
