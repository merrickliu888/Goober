package com.goober;
import java.util.List;

public interface GooberCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
