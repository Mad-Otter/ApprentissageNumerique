package algorithms;

import data.Data;
import data.Element;
import data.Parole;

public class MultiLayerPerceptron implements Classifier{


	Layer in, out, H;
	double[] input, output, target;
	
	Parole data_parole;
	double error;
	FullConnection inH, Hout;

	
	public MultiLayerPerceptron(){
		in = new InputLayer(240);
		out = new OutputLayer(39);
		
		
		input = new double[240];
		output = new double[39];
		target = new double[39];
		
		data_parole = new Parole("parole.dt");		
		error =0;		
	}
	
	public double execute(int nbNeurons) {
		initialization(nbNeurons);
		
		int iter=1;
		while(iter <100) {			
				 in.backpropagation();
				 in.setNeurons(data_parole.getInput(iter));
				 target = data_parole.getTarget(iter);
				 out.propagation();
				 double errors[] = out.calculGradient(out.neurons, target);
				 out.setGradient(errors);
				 error = entropie_relative(errors, target);			 
				 iter++;
			System.out.println("Erreur =" + error);
			System.out.println("Nombre d'itérations:" + iter);
		}		
		return error;
	}
	
	public void initialization(int nbNeurons) {
		H = new Layer(nbNeurons, new Activation.tanh());
		in.setNeurons(data_parole.getInput(0));
		target = data_parole.getTarget(0);
		
		inH = new FullConnection(in, H);
		Hout = new FullConnection(H, out);
		inH.init();
		Hout.init();
		
		out.propagation();
		double errors[] = out.calculGradient(out.neurons, target);
		out.setGradient(errors);
		error = entropie_relative(errors, target);
		System.out.println("Erreur="+error);
	}

	public double entropie_relative(double[] errors, double[] target) {
		/*partie softmax*/
		errors = softmax(errors);
		target = softmax(target);
		double res = 0;
		if(errors.length == target.length) {
			for(int i =0;i<errors.length;i++) {
				res += target[i]*Math.log(errors[i]/target[i]);
			}
		}else
			System.out.println("Error entropie_relative() : wrong size");
		return res = (res*(-1))/100;
	}
	
	public double[] softmax(double[] values) {
		double[] res= new double[values.length];
		double sum =0;
		for(int i=0; i<values.length;i++) {
			sum += Math.exp(values[i]);
		}
		for(int i=0;i<res.length;i++) {
			res[i] = Math.exp(values[i])/sum;
		}
		return res;
	}
	
	@Override
	public String getConsensus(Data data, Element element) {
		// TODO Auto-generated method stub
		return null;
	}

}
