# Java Implementation

public class MathFormulas {

    // Method to calculate the area of a circle
    public static double areaOfCircle(double radius) {
        return Math.PI * radius * radius;
    }

    // Method to calculate the area of a rectangle
    public static double areaOfRectangle(double length, double width) {
        return length * width;
    }

    // Method to calculate the area of a triangle
    public static double areaOfTriangle(double base, double height) {
        return 0.5 * base * height;
    }

    // Method to calculate the quadratic formula roots
    public static double[] quadraticFormula(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new double[]{}; // No real roots
        } else {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{root1, root2};
        }
    }

    // Method to apply the Pythagorean theorem
    public static double pythagoreanTheorem(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    // Method to calculate simple interest
    public static double simpleInterest(double principal, double rate, double time) {
        return (principal * rate * time) / 100;
    }

    // Method to calculate compound interest
    public static double compoundInterest(double principal, double rate, double time, int timesCompounded) {
        return principal * Math.pow((1 + rate / (timesCompounded * 100)), timesCompounded * time);
    }

    public static void main(String[] args) {
        // Testing the formulas
        System.out.println("Area of Circle (radius 5): " + areaOfCircle(5));
        System.out.println("Area of Rectangle (5x10): " + areaOfRectangle(5, 10));
        System.out.println("Area of Triangle (base 4, height 6): " + areaOfTriangle(4, 6));
        
        double[] roots = quadraticFormula(1, -3, 2);
        if (roots.length > 0) {
            System.out.println("Quadratic Formula Roots: " + roots[0] + ", " + roots[1]);
        } else {
            System.out.println("No real roots for the given quadratic equation.");
        }
        
        System.out.println("Hypotenuse (3,4): " + pythagoreanTheorem(3, 4));
        System.out.println("Simple Interest (P=1000, R=5, T=3): " + simpleInterest(1000, 5, 3));
        System.out.println("Compound Interest (P=1000, R=5, T=3, n=4): " + compoundInterest(1000, 5, 3, 4));
    }
}
