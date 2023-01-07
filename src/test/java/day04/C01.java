package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class C01 {
    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        /*driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Samsung headphones", Keys.ENTER);
        WebElement result = driver.findElement(By.xpath("(//*[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
        System.out.println(result.getText());
        WebElement firstItem = driver.findElement(By.xpath("(//*[@class='s-image'])[1]"));
        firstItem.click();*/

        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addBtn = driver.findElement(By.xpath("//*[text()='Add Element']"));
        for (int i = 0; i < 10; i++) {
            addBtn.click();
        }
        List<WebElement> deleteBtnList = driver.findElements(By.xpath("//*[text()='Delete']"));
        int counter = 0;
        for (int i = 0; i < deleteBtnList.size(); i++) {
            counter ++;
        }
        if (counter == 10)
            System.out.println("Delete Count:" + counter);

    }


}
