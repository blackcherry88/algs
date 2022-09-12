package features.serialize;

import features.serialize.FailedSingleton;
import features.serialize.Singleton;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SerializeTest {

    @Test
    public void testFailedSingletonSerialize() throws IOException, ClassNotFoundException {
        String fileName = "failed_singleton_serialize";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fileName))) {
            oos.writeObject(FailedSingleton.getSingleton());
            //Read Obj from file
            FailedSingleton newInstance = (FailedSingleton) ois.readObject();
            assertNotEquals(newInstance, FailedSingleton.getSingleton());
        }
    }

    @Test
    public void testSingletonSerialize() throws IOException, ClassNotFoundException {
        String fileName = "singleton_serialize";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
             ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fileName))) {
            oos.writeObject(Singleton.getSingleton());
            //Read Obj from file
            Singleton newInstance = (Singleton) ois.readObject();
            assertEquals(newInstance, Singleton.getSingleton());
        }
    }
}