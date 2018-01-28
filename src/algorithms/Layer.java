package algorithms;

public class Layer extends Connection{
    double[] neurons;
    double[] theta;
    double[] gradient;
    Activation activation;

    public Layer(int nbNeurons, Activation activ){
        super();
        neurons = new double[nbNeurons];
        theta = new double[nbNeurons];
        gradient = new double[nbNeurons];
        activation = activ;
    }

    public void setNeurons(double[] input){
        if(input.length == neurons.length)
            neurons = input;
        else
            System.out.println("Error in setNeurons() : wrong input size");
    }

    public void setTheta(double[] input){
        if(input.length == theta.length)
            theta = input;
        else
            System.out.println("Error in setTheta() : wrong input size");
    }

    public void setGradient(double[] input){
        if(input.length == gradient.length)
            gradient = input;
        else
            System.out.println("Error in setGradient() : wrong input size");
    }

    public double[] propagation(){
        if(!backprop) {
            for (int i = 0; i < neurons.length; i++)
                neurons[i] = 0;
            for (Connection c : inputs) {
                double[] values = c.propagation();
                for (int i = 0; i < neurons.length; i++)
                    neurons[i] += values[i];
            }
            System.out.print("Layer neurons : ");
            for (int i = 0; i < neurons.length; i++) {
                neurons[i] = activation.transfert(neurons[i] + theta[i]);
                System.out.print(neurons[i] + " ");
            }
            System.out.println("");
            backprop = true;
        }
        return neurons;
    }

    public double[] backpropagation(){
        if(backprop) {
            for (int i = 0; i < gradient.length; i++)
                gradient[i] = 0;
            for (Connection c : outputs) {
                double[] values = c.backpropagation();
                for (int i = 0; i < values.length; i++)
                    gradient[i] += values[i];
            }
            System.out.print("Layer gradient : ");
            for (int i = 0; i < neurons.length; i++) {
                gradient[i] = activation.derivative(neurons[i])*gradient[i];
                System.out.print(gradient[i] + " ");
            }
            System.out.println("");
            backprop = false;
        }
        return gradient;
    }

}
