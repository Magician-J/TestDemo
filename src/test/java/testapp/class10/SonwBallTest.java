package testapp.class10;

/**
 * @author jiaoyl
 * @date 2020/6/14 18:48
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 1.对应录播课程10-7
 * 2.雪球app搜索阿里巴巴
 * 3.定位阿里巴巴香港股票的价格
 */
public class SonwBallTest {
    static AppiumDriver<MobileElement> driver;
    static WebDriverWait wait;
    @BeforeAll
    static void setUp() {
        //企业微信
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".common.MainActivity");
        desiredCapabilities.setCapability("noReset", "true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }
    @AfterAll
    static void tearDown() {
//        driver.quit();
    }

    @Test
    void CurrentPriceTest(){
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("alibaba");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();
        System.out.println(driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText());
    }
    //uiautomator 定位
    @Test
    public void uiautomatorSelcTest(){
        //uiautomator是安卓原生的，需要使用安卓driver,强转为AndroidDriver
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) this.driver;
        //通过resourceId定位
//        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/tab_name\").text(\"交易\")").click();
        //通过className定位
//        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"行情\")").click();
        //id找到父节点，通过text定位子节点
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/scroll_view\").childSelector(text(\"热门\"))").click();
        //id找到子节点，然后找到父节点，再定位到我的
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/tab_name\").fromParent(text(\"我的\"))").click();
    }
    @Test
    // 实现滚动查找元素
    public void crollTest(){
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) this.driver;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"雪球路演\").instance(0))").click();
    }

}
