package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultiLayerPerceptron implements Classifier{

	Layer in, out;
	double[] input;
	double[] output;
	double[] target;
	private Scanner scan;
  String url;
	
	public MultiLayerPerceptron(int nbNeurons){
		in = new InputLayer(240);
		out = new OutputLayer(39, new Activation.tanh());
		Layer H = new Layer(nbNeurons, new Activation.tanh());
		
		input = new double[240];
		output = new double[39];
		
		initNeurons(url);
		
		FullConnection inH = new FullConnection(in, H);
		FullConnection Hout = new FullConnection(H, out);
		
		inH.initWeights();
		Hout.initWeights();
				
	}
  
	public void initNeurons(String url) {

	}
  }

