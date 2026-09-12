package seleniumgitjenkin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SleniumJenkinsFlow {

	public static void main(String[] args) {
		
	ChromeOptions options= new ChromeOptions();
	options.addArguments("--headless");
	options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");
    
    //Initialize WebDriver with the options
    
    WebDriver driver= new ChromeDriver(options);
    
    WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    
    try {
    	driver.get("https://www.saucedemo.com/");
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        addToCartBtn.click();

        // 4. Verify cart item count
        WebElement cartBadge = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );
        if ("1".equals(cartBadge.getText())) {
            System.out.println("Test PASSED: Item successfully added to cart.");
        } else {
            System.out.println("Test FAILED: Cart count mismatch.");
        }

    } catch (Exception e) {
        System.out.println("Test FAILED with exception: " + e.getMessage());
    } finally {
        // 5. Close browser session
        driver.quit();
    }
}
    
	}


