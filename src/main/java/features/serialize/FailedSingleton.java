package features.serialize;

import java.io.Serializable;

/**
 * Missing readResolve, after deserialized, a new instance will be created
 */
public class FailedSingleton implements Serializable {

    private volatile static FailedSingleton singleton;
    private FailedSingleton (){}
    public static FailedSingleton getSingleton() {
        if (singleton == null) {
            synchronized (FailedSingleton.class) {
                if (singleton == null) {
                    singleton = new FailedSingleton();
                }
            }
        }
        return singleton;
    }
}
