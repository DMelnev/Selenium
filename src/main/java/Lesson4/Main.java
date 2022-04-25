/**
 * Class Main
 *
 * @author Melnev Dmitriy
 * @version 2022-
 **/
package Lesson4;

public class Main {
    public static void main(String[] args) {

        System.out.println(rectangleSquare(3, 4, 5));
    }

    public static double rectangleSquare(double a, double b, double c) {

        if (a <= 0 || b <= 0 || c <= 0) return 0;
        double p = (a + b + c) / 2D;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
