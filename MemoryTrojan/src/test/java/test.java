
import com.google.gson.Gson;
import com.mechoy.bean.ResponseBean;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/11
 * Description
 */
public class test {
    public static void main(String[] args) {
        ResponseBean xxx = new ResponseBean();
        xxx.setCode("000000");
        xxx.setMessage("nihao");
        String res = new Gson().toJson(xxx);
        System.out.println(res);
    }
}
