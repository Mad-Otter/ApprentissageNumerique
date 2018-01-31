package algorithms;

import data.Data;
import data.Element;

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
		out = new OutputLayer(39);
		Layer H = new Layer(nbNeurons, new Activation.tanh());
		
		input = new double[240];
		output = new double[39];
		
		initNeurons(url);
		
		FullConnection inH = new FullConnection(in, H);
		FullConnection Hout = new FullConnection(H, out);
		
		inH.init();
		Hout.init();
				
	}
  
	public void initNeurons(String url) {

	}

	@Override
	public String getConsensus(Data data, Element element) {
		return null;
	}
}

