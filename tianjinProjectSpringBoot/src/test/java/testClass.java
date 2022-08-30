import com.tianjin.frontLoaded.TianjinApplication;
import com.tianjin.frontLoaded.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpSession;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@SpringBootTest(classes = TianjinApplication.class)
public class testClass {

    @Autowired
    private AuthController authController;


    @Test
    public void testMethod01(){
        authController.logout();

    }
}
