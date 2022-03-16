package com.sadatmalik.factories.abstractfactory;

import com.sadatmalik.unittesting.UnitTest;

/**
 * The Abstract Factory pattern has not one but several factory methods.
 * Each of the factory methods creates a different kind of object.
 *
 * The idea is that at the point of creation of the factory object, you
 * decide how all the objects created by that factory will be used.
 *
 * The example given in Design Patterns implements portability across
 * various graphical user interfaces (GUIs): you create a factory object
 * appropriate to the GUI that you’re working with, and from then on when
 * you ask it for a menu, button, slider, etc. it will automatically create
 * the appropriate version of that item for the GUI.
 *
 * This example suppose you are creating a general-purpose gaming
 * environment, and you want to be able to support different types of
 * games.
 *
 * @author sm@creativefusion.net
 */
interface Obstacle {
    void action();
}

interface Player {
    void interactWith(Obstacle o);
}

class Kitty implements Player {
    public void interactWith(Obstacle ob) {
        System.out.print("Kitty has encountered a ");
        ob.action();
    }
}

class KungFuGuy implements Player {
    public void interactWith(Obstacle ob) {
        System.out.print("KungFuGuy now battles a ");
        ob.action();
    }
}

class Puzzle implements Obstacle {
    public void action() {
        System.out.println("Puzzle");
    }
}

class NastyWeapon implements Obstacle {
    public void action() {
        System.out.println("NastyWeapon"); }
}

// The Abstract Factory:
interface GameElementFactory {
    Player makePlayer();
    Obstacle makeObstacle();
}

// Concrete factories:
class KittiesAndPuzzles implements GameElementFactory {
    public Player makePlayer() {
        return new Kitty();
    }
    public Obstacle makeObstacle() {
        return new Puzzle();
    }
}

class KillAndDismember implements GameElementFactory {
    public Player makePlayer() {
        return new KungFuGuy();
    }
    public Obstacle makeObstacle() {
        return new NastyWeapon();
    }
}

class GameEnvironment {
    private GameElementFactory gef;
    private Player p;
    private Obstacle ob;

    public GameEnvironment(GameElementFactory factory) {
        gef = factory;
        p = factory.makePlayer();
        ob = factory.makeObstacle();
    }

    public void play() {
        p.interactWith(ob);
    }
}

public class AbstractFactory extends UnitTest {
    GameElementFactory
            kp = new KittiesAndPuzzles(),
            kd = new KillAndDismember();

    GameEnvironment
            g1 = new GameEnvironment(kp),
            g2 = new GameEnvironment(kd);

    public void test1() {
        g1.play();
    }

    public void test2() {
        g2.play();
    }

    public static void main(String args[]) {
        AbstractFactory g = new AbstractFactory();
        g.test1();
        g.test2();
    }
}