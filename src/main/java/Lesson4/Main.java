/**
 * Class Main
 *
 * @author Melnev Dmitriy
 * @version 2022-
 **/
package Lesson4;

public class Main {
    public static void main(String[] args) {
        ;
        if (rectangleSquare(3, 4, 5) != 7.0D) {
            System.out.println("Test filed");
        } else {
            System.out.println("Test passed");
        }

    }

    public static double rectangleSquare(double a, double b, double c) {

        if (a <= 0 || b <= 0 || c <= 0) return 0;
        double p = (a + b + c) / 2D;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static double division(double divisible, double divider) throws ArithmeticException{
        return divisible / divider;
    }
}
