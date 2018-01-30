package algorithms;

public class Epsilon {
    private static double e0 = 0.1;
    private static double tau = 10;
    public static double e = 0.1;

    public static void update(int t){
        e = e0 * tau/(tau+t);
    }
}
