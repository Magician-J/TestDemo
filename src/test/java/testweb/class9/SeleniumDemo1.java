package testweb.class9;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author jiaoyl
 * @date 2020/5/25 21:50
 */
public class SeleniumDemo1 {
    @Test
    void startSelenium(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://home.testing-studio.com/");
        webDriver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
    }

}
