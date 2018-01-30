package algorithms;

public class FullConnection extends Connection {
    double[][] weights;
    double[] activations;
    double[] gradients;

    public FullConnection(Layer in, Layer out) {
        super();
        addInput(in);
        addOutput(out);
        weights = new double[out.neurons.length][in.neurons.length];
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
    public double[] propagation() {
        if(!backprop) {
            for (int i = 0; i < activations.length; i++) {
                double sum = 0;
                for(Connection in : inputs) {
                    double[] values = in.propagation();
                    for (int j = 0; j < values.length; j++)
                        sum += values[j] * weights[i][j];
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
            for (int i = 0; i < gradients.length; i++) {
                double sum = 0;
                for (Connection out : outputs) {
                    double[] values = out.backpropagation();
                    for (int j = 0; j < values.length; j++)
                        sum += values[j] * weights[j][i];
                }
                gradients[i] = sum;
                for(int j=0; j<weights[0].length; j++)
                    weights[i][j] -= activations[j]*gradients[i]*Epsilon.e;
            }
            backprop = false;
        }
        return gradients;
    }
    
       public void initWeights() {
	   for(int i=0;i<weights.length;i++) {
		   for(int j=0;j<weights[0].length;j++) {
			   weights[i][j]=(Math.random()*2)-1;
		   }
	   }
   }
}
