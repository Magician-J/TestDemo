package testapp.class11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import testapp.BaseTest;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

/**
 * @author jiaoyl
 * @date 2020/6/15 21:57
 * 参数化用例测试对应录播11-1
 */
public class SearchPage extends BaseTest {

    @ParameterizedTest
    @MethodSource("byNameGetPrice")//方法来源
//    @Test
    //获取价格
    public void searchByNmae(String name , String code ,double price){
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys(name);
        //细节：注意code前后单引号
        driver.findElement(By.xpath("//*[@text='"+code+"']")).click();
        //获取股票价格
        String realprice = driver.findElement(By.xpath("//*[@text='"+code+"']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();
        System.out.println(realprice);
        //点击取消（此处坑：去过取消在断言后，如果断言失败将不执行取消，影响后续case）
        driver.findElement(By.id("com.xueqiu.android:id/action_close")).click();
        //添加断言（hamcrest断言）
        assertThat("股票实际价格和期望价格比较",Double.parseDouble(realprice),greaterThan(price));

    }
    private static Stream<Arguments> byNameGetPrice(){
        return Stream.of(Arguments.of("alibaba","BABA",210d),
                Arguments.of("wangyi","NTES",250d),
                Arguments.of("baidu","BIDU",180d)
                );
    }
}
