package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C04 {

    static WebDriver driver;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
    }

    @Test
    @Ignore("Çalışmasın")
    public void test1(){
        driver.get("https://www.greengtc.com.tr");
    }

    @Test
    public void test2(){
        driver.get("https://www.greengtc.com.tr");
    }

    @Test
    public void test3(){
        driver.get("https://www.greengtc.com.tr");
    }
}
