package testapp.class11;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.OutputType;
import testapp.BaseTest;

import java.io.File;
import java.io.IOException;

public class ScreenShot extends BaseTest {
    @Test
    public void screenShot(){
        //截图
        try {
            Thread.sleep(5000);
            File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
            File file = new File(System.getProperty("user.dir")+"/src/main/resources/demo.png");
            FileUtils.copyFile(screenshotAs,file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
