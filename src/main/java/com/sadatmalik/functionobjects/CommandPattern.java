package com.sadatmalik.functionobjects;

import com.sadatmalik.unittesting.UnitTest;

import java.util.ArrayList;
import java.util.List;

/**
 * The primary point of Command is to allow you to hand a desired
 * action to a method or object.
 *
 * In the below example, this provides a way to queue a set of actions
 * to be performed collectively. In this case, it allows you to dynamically
 * create new behavior, something you can normally only do by writing new
 * code but in the example can be done by interpreting a script.
 *
 * See the Interpreter pattern if what you need to do gets very complex.
 *
 * @author sm@creativefusion.net
 */
interface Command {
    void execute();
}

class Hello implements Command {
    public void execute() {
        System.out.print("Hello ");
    }
}

class World implements Command {
    public void execute() {
        System.out.print("World! ");
    }
}

class IAm implements Command {
    public void execute() {
        System.out.print("I'm the command pattern!");
    }
}

// An object that holds commands:
class Macro {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command c) {
        commands.add(c);
    }

    public void run() {
        for (Command command : commands)
            command.execute();
    }
}

public class CommandPattern extends UnitTest {
    Macro macro = new Macro();

    public void test() {
        macro.add(new Hello());
        macro.add(new World());
        macro.add(new IAm());
        macro.run();
    }

    public static void main(String[] args) {
        new CommandPattern().test();
    }
}