import com.bjpowernode.commons.utils.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

/**
 * ClassName:MyTest
 * Package:PACKAGE_NAME
 * Description: 描述信息
 *
 * @date:2022/3/14 23:42
 * @author:白白白
 */
public class MyTest {


    @Test
    public void test01(){
       if (DateUtils.formatDate(new Date()).compareTo("2023-09-12") > 0){
           System.out.println("用户过期");
       }else
           System.out.println("用户没过期");
    }

}
