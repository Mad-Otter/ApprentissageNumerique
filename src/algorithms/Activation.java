package algorithms;

import static java.lang.Double.max;

public interface Activation {

    double transfert(double input);

    class reLu implements Activation{
        @Override
        public double transfert(double input) {
            return max(input,0);
        }
    }

    class tanh implements Activation{
        @Override
        public double transfert(double input) {
            return Math.tanh(input);
        }
    }

}
