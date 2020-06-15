package testapp.class10;


/**
 *Hamcrest 断言练习，对应录播课程10-10
 *  */


import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class hamcrestTest {
    private  static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".common.MainActivity");
        desiredCapabilities.setCapability("noReset", "true");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void priceAsert(){
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("alibaba");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();
        //点击股票标签
        driver.findElement(By.xpath("//*[@resource-id='com.xueqiu.android:id/title_text'][@text='股票']")).click();
        //获取股票价格
        String realprice = driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();
        //200d 需要制定数据类型未double
        //Double.parseDouble() string 转double
        //变量的值大于指定值时，测试通过greaterThan
        assertThat("股票价格对比",Double.parseDouble(realprice),greaterThan(200d));
    }

    @AfterAll
    public static void tearDown() {

        driver.quit();
    }
}
