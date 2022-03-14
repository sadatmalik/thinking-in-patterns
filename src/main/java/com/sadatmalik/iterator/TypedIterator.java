package com.sadatmalik.iterator;

import java.util.Iterator;

/**
 * A typed iterator impose the constraint that the type of objects that it
 * iterates over be of a particular type.
 *
 * We can use the Decorator design pattern, and create a class that simply
 * wraps the existing Enumeration or Iterator, generating a new object that
 * has the iteration behavior that we want.
 *
 * You may argue that this is a Proxy pattern, but it’s more likely Decorator
 * because of its intent.
 *
 * @author sm@creativefusion.net
 */

public class TypedIterator implements Iterator {
    private Iterator imp;
    private Class type;

    public TypedIterator(Iterator it, Class type) {
        imp = it;
        this.type = type;
    }

    public boolean hasNext() {
        return imp.hasNext();
    }

    public void remove() {
        imp.remove();
    }

    public Object next() {
        Object obj = imp.next();
        if(!type.isInstance(obj))
            throw new ClassCastException(
                    "TypedIterator for type " + type +
                            " encountered type: " + obj.getClass());
        return obj;
    }
}