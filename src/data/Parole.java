package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parole extends Data {
	
	private double[] inputs;
	private double[] outputs;
	private ArrayList<double[]> in;
	private ArrayList<double[]> out;
	
	
	public Parole(String url) {
		in = new ArrayList<double[]>();
		out = new ArrayList<double[]>();
		
		inputs = new double[240];
		outputs = new double[39];

	    try {	
			Scanner scan = new Scanner(new File(url));
			while(scan.hasNext()) {
				int i=0; 
				while(i<240) {
					inputs[i]=Double.valueOf(scan.next());
					i++;
				}
				in.add(inputs);
				int j = Integer.valueOf(scan.next())-1;
				outputs[j]= Double.valueOf(j+1);
				out.add(outputs);
				outputs = new double[39];
				
			}
			
			scan.close();
	 }catch(FileNotFoundException e) {
		 System.out.println(e);
	 }		
	}

	public double[] getInput(int i) {
		return in.get(i);
	}
	
	public double[] getTarget(int i) {
		return out.get(i);
	}
	
	public int inputSize() {
		return in.size();
	}

	@Override
	public ArrayList<String> getLabels() {
		// TODO Auto-generated method stub
		return null;
	}

}
