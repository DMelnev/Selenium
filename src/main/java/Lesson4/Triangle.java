package Lesson4;

public class Triangle {
    public double triangleSquare(int a, int b, int c){
        if (!checkTriangle(a,b,c)) throw new TriangleException();
        double p = (a + b + c) / 2D;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    private boolean checkTriangle(int a, int b, int c) {
        return (a < b + c) && (b < a + c) && (c < a + b);
    }

    public double division(int divisible, int divider) { //for testing @Nested
        if (divider == 0) throw new ArithmeticException();
        return divisible / divider;
    }
}
