package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utiles.ConfigProperties;
import utiles.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.valueOf;

public class ListProducts extends BasePage{
    ConfigProperties auxiliar = new ConfigProperties();
    Properties dateText = auxiliar.readConfig();
    By noResult = By.xpath(dateText.getProperty("notResult"));
    By nameProductsLocator =By.xpath(dateText.getProperty("nameProducts"));
    By priceProductsLocator =By.xpath(dateText.getProperty("priceProducts"));
    By linkProductsLocator = By.xpath(dateText.getProperty("linkProducts"));
    By nextButtonLocator = By.cssSelector(dateText.getProperty("nextButton"));
    private final List<Product> productos = new ArrayList<>();

    public ListProducts() {
        super(driver);
    }

    public void listProducts(){
        List<WebElement> productNamesElements=findElements(nameProductsLocator);
        List<WebElement> productPricesElements=findElements(priceProductsLocator);
        List<WebElement> productLinksElements=findElements(linkProductsLocator);

        for (int i = 0; i < productNamesElements.size(); i++) {
            Product aux = new Product();
            aux.setName(productNamesElements.get(i).getText());
            aux.setPrice(productPricesElements.get(i).getText());
            aux.setLink(productLinksElements.get(i).getAttribute("href"));
            productos.add(aux);
        }
    }

    public void getInfoProductsOfPages(int numberOfPages){
        for (int i = 0; i < numberOfPages; i++) {
            listProducts();

            if(i+1<numberOfPages && isDisplayed(nextButtonLocator)){
                click(nextButtonLocator);
            }
        }
    }
    public void getProductsToTxt(String fileName) throws IOException {
        getInfoProductsOfPages(Integer.parseInt(dateText.getProperty("numberOfPages")));
        FileWriter pw;
        try {
            pw = new FileWriter("output/"+fileName, true);
            int cont = 1;
            for (Product producto : productos) {
                pw.write("Articulo " + cont + ":" + "\n");
                pw.write(producto.getName() + "\n");
                pw.write("$" + producto.getPrice() + "\n");
                pw.write(producto.getLink() + "\n" + "\n");
                cont++;
            }
            pw.close();
        } catch (IOException e) {
            throw new IOException("No se pudo generar el archivo.", e);
        }
    }

    public boolean emptyList(){
        return isDisplayed(noResult);
    }
}