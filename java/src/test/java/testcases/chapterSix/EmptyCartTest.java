package testcases.chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EmptyCartTest {


    @Test
    public void emptyCart() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait myWaitVar = new WebDriverWait(driver,20);

        // Open the website
        driver.get("https://techblog.polteq.com/testshop/index.php");
        driver.manage().window().maximize();

        //Check if empty element is visible if so add product to cart
        if (driver.findElement(By.className("ajax_cart_no_product")).isDisplayed()){
            //Click on the iPod Tag
            driver.findElement(By.cssSelector("a[title='More about ipod']")).click();
            //Click on the iPod shuffle
            driver.findElement(By.xpath("//a[contains(text(),'iPod shuffle')]")).click();
            //Add to cart
            driver.findElement(By.id("add_to_cart")).click();
            //Continue shopping
            myWaitVar.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Continue shopping']"))).click();
            //Check if cart number is 1
            Assertions.assertThat(driver.findElement(By.className("ajax_cart_quantity")).getText())
                    .as("Check if number is 1").isEqualTo("1");
        }
        //Overlay is blocking the cart button
        Thread.sleep(2000);

        //Click the cart button
        driver.findElement(By.cssSelector(".shopping_cart>a")).click();

        //Click the trash icon
        driver.findElement(By.className("icon-trash")).click();

        Thread.sleep(2000);

        //Check if the empty message appears
        Assertions.assertThat(driver.findElement(By.cssSelector(".alert.alert-warning")).getText())
                .as("Validate if the empty cart message appears")
                .isEqualTo("Your shopping cart is empty.");


        //Check if empty element is visible
        Assertions.assertThat(driver.findElement(By.className("ajax_cart_no_product")).isDisplayed())
                .as("Check if empty element is visible").isTrue();

        driver.quit();
    }

    private void WebdriverWait(){

    }

}
