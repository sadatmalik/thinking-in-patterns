package com.sadatmalik.factories.staticfactorymethod;

import com.sadatmalik.factories.BadShapeCreationException;
import com.sadatmalik.unittesting.UnitTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * One approach is to make the factory a static method of the base class.
 *
 * The factory() method takes an argument that allows it to determine what
 * type of Shape to create; it happens to be a String in this case, but it
 * could be any set of data.
 *
 * The factory() is now the only other code in the system that needs to be
 * changed when a new type of Shape is added (the initialization data for the
 * objects will presumably come from somewhere outside the system, and not be
 * a hard-coded array as in the example).
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