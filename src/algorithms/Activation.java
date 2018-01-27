package algorithms;

import static java.lang.Double.max;

public interface Activation {

    double transfert(double input);
    double derivative(double input);

    class reLu implements Activation{
        @Override
        public double transfert(double input) {
            return max(input,0);
        }

        @Override
        public double derivative(double input) {
            if(input > 0)
                return 1;
            else
                return 0;
        }
    }

    class tanh implements Activation{
        @Override
        public double transfert(double input) {
            return Math.tanh(input);
        }

        @Override
        public double derivative(double input) {
            return 1 - input*input;
        }
    }
}
