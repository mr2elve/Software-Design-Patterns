import java.util.ArrayList;
import java.util.List;

public class ShapeAreaCalculator {

    interface Shape {
        void accept(Visitor visitor);
    }

    class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    class Rectangle implements Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    class Triangle implements Shape {
        private double base;
        private double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        public double getBase() {
            return base;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    interface Visitor {
        void visit(Circle circle);
        void visit(Rectangle rectangle);
        void visit(Triangle triangle);
    }

    class AreaCalculator implements Visitor {
        private double totalArea;

        public double getTotalArea() {
            return totalArea;
        }

        @Override
        public void visit(Circle circle) {
            totalArea += Math.PI * Math.pow(circle.getRadius(), 2);
        }

        @Override
        public void visit(Rectangle rectangle) {
            totalArea += rectangle.getWidth() * rectangle.getHeight();
        }

        @Override
        public void visit(Triangle triangle) {
            totalArea += 0.5 * triangle.getBase() * triangle.getHeight();
        }
    }

    public static void main(String[] args) {
        ShapeAreaCalculator calculator = new ShapeAreaCalculator();
        List<Shape> shapes = new ArrayList<>();

        shapes.add(calculator.new Circle(5));
        shapes.add(calculator.new Rectangle(4, 6));
        shapes.add(calculator.new Triangle(3, 7));

        AreaCalculator areaCalculator = calculator.new AreaCalculator();

        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }

        System.out.println("Total Area: " + areaCalculator.getTotalArea());
    }
}
