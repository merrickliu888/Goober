package com.goober;

import java.util.List;
import java.util.Map;

public class GooberClass implements GooberCallable {
    final String name;
    private final Map<String, GooberFunction> methods;

    GooberClass(String name, Map<String, GooberFunction> methods) {
        this.name = name;
        this.methods = methods;
    }

    GooberFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        GooberInstance instance = new GooberInstance(this);

        GooberFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }

    @Override
    public int arity() {
        GooberFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }

    @Override
    public String toString() {
        return "<" + name + " class>";
    }
}
