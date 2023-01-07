package comparingPrice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class comparingPrice {
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
    public void test() throws InterruptedException {
        driver.get("https://www.mizunotr.com/tenis-409");

        //WebElement copyright = driver.findElement(By.xpath("//*[text()=' ©2020 Mizunotr.com Tüm Hakkı Saklıdır.']"));
        //JavascriptExecutor jsexec = (JavascriptExecutor) driver;
        //jsexec.executeScript("arguments[0].scrollIntoView(true)", copyright);

        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
            int cont=1000;
            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, "+cont+");");
                Thread.sleep(1000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight <= cont) {
                    break;
                }
//                      lastHeight = newHeight;
                cont+=500;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> muzinoProductTitleCount = driver.findElements(By.xpath("//*[@class='col col-12 text-description detailLink']"));
        List<WebElement> muzinoProductPriceCount = driver.findElements(By.xpath("//div[contains(@class, 'currentPrice')]"));

        System.out.println("Mizuno Tenis Ürünleri Sayısı: " + muzinoProductTitleCount.size());
        System.out.println("Mizuno Tenis Ürünleri Fiyatları Sayısı: " + muzinoProductPriceCount.size());

        List<String> muzinoProductTitles = new ArrayList<String>();
        List<String> muzinoProductPrices = new ArrayList<String>();

        for (int i = 0; i < muzinoProductTitleCount.size(); i++)
        {
            muzinoProductTitles.add(muzinoProductTitleCount.get(i).getText());
            muzinoProductPrices.add(muzinoProductPriceCount.get(i).getText().replace(".","").replace("","").replace("TL",""));
            System.out.println(muzinoProductTitles.get(i) + " - " + muzinoProductPrices.get(i));
        }

        for (int i = 0; i < 5; i++)
            System.out.println("");
        System.out.println("***********************************");
        for (int i = 0; i < 5; i++)
            System.out.println("");

        driver.get("https://shop.greengtc.com.tr/Green-ProShop-%C3%9Cr%C3%BCnler?filter=Tenis");
        List<WebElement> greenProductTitleCount = driver.findElements(By.xpath("//*[@class='stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6']"));
        List<WebElement> greenProductPriceCount = driver.findElements(By.xpath("//*[@class='stext-110 cl3']"));
        System.out.println("Green Tenis Ürünleri Sayısı: " + greenProductTitleCount.size());
        System.out.println("Green Tenis Ürünleri Fiyatları Sayısı: " + greenProductPriceCount.size());

        List<String> greenProductTitles = new ArrayList<String>();
        List<String> greenProductPrices = new ArrayList<String>();

        for (int i = 0; i < greenProductTitleCount.size(); i++)
        {
            greenProductTitles.add(greenProductTitleCount.get(i).getText());
            greenProductPrices.add(greenProductPriceCount.get(i).getText());
            System.out.println(greenProductTitles.get(i) + " - " + greenProductPrices.get(i));
        }

        for (int i = 0; i < 5; i++)
            System.out.println("");
        System.out.println("***********************************");
        for (int i = 0; i < 5; i++)
            System.out.println("");

        for(int i=0; i < greenProductTitles.size(); i++)
        {
            for(int j=0; j < muzinoProductTitles.size(); j++)
            {
                if(muzinoProductTitles.get(j).equalsIgnoreCase(greenProductTitles.get(i)))
                {
                    if(!muzinoProductPrices.get(j).contains(greenProductPrices.get(i)))
                    {
                        System.out.println(greenProductTitles.get(i) + " - " + greenProductPrices.get(i) + " -> " + muzinoProductPrices.get(j));
                    }
                }
            }
        }
    }
}
