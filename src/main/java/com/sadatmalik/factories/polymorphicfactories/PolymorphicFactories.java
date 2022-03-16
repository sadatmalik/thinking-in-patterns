package com.sadatmalik.factories.polymorphicfactories;

import com.sadatmalik.factories.BadShapeCreationException;
import com.sadatmalik.unittesting.UnitTest;

import java.util.*;

/**
 * In this example the factory methods are in a separate class as virtual functions.
 * Notice also that the specific Shape classes are dynamically loaded on demand.
 *
 * the factory method appears in its own class, ShapeFactory, as the create( ) method.
 * This is a protected method which means it cannot be called directly, but it can be
 * overridden.
 *
 * The subclasses of Shape must each create their own subclasses of ShapeFactory and
 * override the create( ) method to create an object of their own type.
 *
 * However, it seems that much of the time you don’t need the intricacies of the
 * polymorphic factory method, and a single static method in the base class (as shown
 * in StaticFactoryMethod) will work fine.
 *
 * @author sm@creativefusion.net
 */
interface Shape {
    void draw();
    void erase();
}

abstract class ShapeFactory {
    private static final Map<String, ShapeFactory> factories = new HashMap<>();

    public static void addFactory(String id, ShapeFactory f) {
        factories.put(id, f);
    }

    protected abstract Shape create();

    // A Template Method:
    public static Shape createShape(String id) throws BadShapeCreationException {
        if(!factories.containsKey(id)) {
            try {
                // Load dynamically
                Class.forName("com.sadatmalik.factories.polymorphicfactories." + id);
            } catch(ClassNotFoundException e) {
                throw new BadShapeCreationException(id);
            }
            // See if it was put in:
            if(!factories.containsKey(id))
                throw new BadShapeCreationException(id);
        }
        return ((ShapeFactory)factories.get(id)).create();
    }
}

class Circle implements Shape {
    private Circle() {}

    public void draw() {
        System.out.println("Circle.draw");
    }

    public void erase() {
        System.out.println("Circle.erase");
    }

    private static class Factory extends ShapeFactory {
        protected Shape create() {
            return new Circle();
        }
    }

    static {
        ShapeFactory.addFactory("Circle", new Circle.Factory());
    }
}

class Square implements Shape {
    private Square() {}

    public void draw() {
        System.out.println("Square.draw");
    }

    public void erase() {
        System.out.println("Square.erase");
    }

    private static class Factory extends ShapeFactory {
        protected Shape create() {
            return new Square();
        }
    }

    static {
        ShapeFactory.addFactory("Square", new Square.Factory());
    }
}

public class PolymorphicFactories extends UnitTest {
    String shlist[] = { "Circle", "Square", "Square", "Circle", "Circle", "Square" };

    List shapes = new ArrayList();

    public void test() {
        try {
            for(int i = 0; i < shlist.length; i++)
                shapes.add(ShapeFactory.createShape(shlist[i]));
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
        new PolymorphicFactories().test();
    }
}
