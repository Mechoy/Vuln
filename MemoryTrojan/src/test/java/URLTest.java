import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/17
 * Description
 */
public class URLTest {
    final static String path = "ser.bin";

    public static void main(String[] args) throws IOException {
        System.out.println(111);
        Object obj;

        // 赋值
        obj = new URL("http://ed370692.ipv6.1433.eu.org.");

        obj.hashCode();
    }

    public static void serialize(Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }

    public static Object deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        return o;
    }
}
