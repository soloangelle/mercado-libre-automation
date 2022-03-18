package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    //Estable la conexión con el navegador Chrome
    public WebDriver chromeDriverConnection(){
           System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
           BasePage.driver = new ChromeDriver(chromeOptions);
           return driver;
    }

    public WebDriverWait setWait(int seconds){
        return new WebDriverWait(driver, seconds);
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator){   //Llamando a los metodos de la Api de Selenium
        return driver.findElements(locator);
    }

    public String getText(WebElement element){  //Devolver texto del elemento
        return element.getText();
    }

    public String getText(By locator){   //Devuelve el texto del elemento a traves del localizador
        return driver.findElement(locator).getText();
    }

    public void typeText(String keyword, By locator ){  // Ingresar text en la página en un localizador
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(keyword);
    }

    /*public void enter(By locator ){  //
        driver.findElement(locator).clear();
        driver.findElement(locator).submit();
    }*/

    public void click(By locator){
        findElement(locator).click();
    }

    public Boolean isDisplayed(By locator){     // Verifica si un elemento existe
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e){
            return false;
        }
    }

    public void openPage(String url){
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void closePage() {
        driver.close();
    }
}
