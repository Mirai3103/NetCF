package Io;

import java.io.Serializable;

@FunctionalInterface
public interface Callback {
    void invoke(Serializable arg);
}
