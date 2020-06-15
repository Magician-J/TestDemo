package testweb.class9;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author jiaoyl
 * @date 2020/5/26 22:02
 */
public class AiCeTest {
    public static WebDriver webdriver;
    public static WebDriverWait wait;
    @BeforeAll
    public static void initData(){
        webdriver = new ChromeDriver();
        // 加入隐式等待
        webdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // 按住ctrl 放在WebDriverWait上，可以看到括号中需要传递的参数。
        wait = new WebDriverWait(webdriver,5);

    }
    @Test
    public void login(){
        webdriver.get("https://home.testing-studio.com");
        //语法示例：//input[contains(@name,'na')] 查找name属性中包含na关键字的页面元素
        webdriver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        webdriver.findElement(By.id("login-account-name")).clear();
        webdriver.findElement(By.id("login-account-name")).sendKeys("jyl1360@163.com");
        webdriver.findElement(By.id("login-account-password")).clear();
        webdriver.findElement(By.id("login-account-password")).sendKeys("jyl1360@JYL87319");
        webdriver.findElement(By.id("login-button")).click();
    }

    @Test
    public void timeSleepTest(){
        webdriver.get("https://home.testing-studio.com");
        try {
            // 直接等待
            Thread.sleep(5000);
            webdriver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //===========================
    //显示等待waitTest
    @Test
    public void waitTest(){
        webdriver.get("https://home.testing-studio.com");
//        webdriver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

        //等待方法1（官网提供）
        //------------------------------------------------------------
//        WebElement loginEle = wait.until(new ExpectedCondition<WebElement>() {
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
//            }
//        });
//        loginEle.click();
        //-------------------------------------------------------------
        //等待方法2（自带方法）
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
        element.click();
    }

    @AfterAll
    public static void tearDown(){
        webdriver.quit();
    }


}
