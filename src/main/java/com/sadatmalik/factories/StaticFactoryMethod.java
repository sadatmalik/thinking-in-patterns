package com.sadatmalik.factories;

import com.sadatmalik.unittesting.UnitTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * One approach is to make the factory a static method of the base class.
 *
 * @author sm@creativefusion.net
 */
abstract class Shape {
    public abstract void draw();
    public abstract void erase();

    // static factory method
    public static Shape factory(String type) throws BadShapeCreationException {
        if(type.equals("Circle"))
            return new Circle();

        if(type.equals("Square"))
            return new Square();

        throw new BadShapeCreationException(type);
    }
}

class Circle extends Shape {
    Circle() {
    } // Friendly constructor

    public void draw() {
        System.out.println("Circle.draw");
    }

    public void erase() {
        System.out.println("Circle.erase");
    }
}

class Square extends Shape {
    Square() {
    } // Friendly constructor

    public void draw() {
        System.out.println("Square.draw");
    }

    public void erase() {
        System.out.println("Square.erase");
    }
}

public class StaticFactoryMethod extends UnitTest {
    String shapeList[] = { "Circle", "Square",
        "Square", "Circle", "Circle", "Square" };

    List shapes = new ArrayList();

    public void test() {
        try {
            for(int i = 0; i < shapeList.length; i++)
                shapes.add(Shape.factory(shapeList[i]));
        } catch(BadShapeCreationException e) {
            e.printStackTrace(System.err);
            assert(false); // Fail the unit test
        }

        Iterator i = shapes.iterator();

        while(i.hasNext()) {
            Shape s = (Shape)i.next();
            s.draw();
            s.erase();
        }
    }

    public static void main(String args[]) {
        new StaticFactoryMethod().test();
    }
}