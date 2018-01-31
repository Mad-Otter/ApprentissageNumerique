package algorithms;

import java.util.ArrayList;

public abstract class Connection {
    ArrayList<Connection> inputs;
    ArrayList<Connection> outputs;
    boolean backprop;

    public Connection(){
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        backprop = false;
    }

    public void addInput(Connection in){
        if(!inputs.contains(in)) {
            inputs.add(in);
            in.addOutput(this);
        }
    }

    public void addOutput(Connection out){
        if(!outputs.contains(out)) {
            outputs.add(out);
            out.addInput(this);
        }
    }

    public void reset(){
        backprop = false;
        for(Connection in: inputs)
            in.reset();
    }

    public abstract void init();
    public abstract String toString();

    public abstract double[] propagation();
    public abstract double[] backpropagation();
}
