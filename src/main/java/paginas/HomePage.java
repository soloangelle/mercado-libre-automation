package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utiles.ConfigProperties;

import java.util.Properties;


public class HomePage extends BasePage{

    ConfigProperties auxiliar = new ConfigProperties();
    Properties dateText = auxiliar.readConfig();
    String url = dateText.getProperty("url");
    By searchProductId =By.id(dateText.getProperty("searchProductId"));
    By searchButtonClass =By.className(dateText.getProperty("serachButtonClass"));
    By cookiesButton = By.xpath(dateText.getProperty("cookiesButton"));

    public HomePage() {  // El constructor
        super(driver);
    }

    public void openBrowser() {
        openPage(url);

    }
    public String getTitlePage(){
       return driver.getTitle();

    }

    public void searchProduct(String article){
        click(searchProductId);
        typeText(article,searchProductId);
        click(searchButtonClass);
    }

    public void closeCookiesMessage(){
        if(isDisplayed(cookiesButton)) click(cookiesButton);
    }
}
