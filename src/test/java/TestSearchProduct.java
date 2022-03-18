import org.junit.jupiter.api.*;
import paginas.HomePage;
import paginas.ListProducts;
import utiles.ConfigProperties;
import utiles.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.valueOf;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSearchProduct {

    ConfigProperties auxiliar = new ConfigProperties();
    Properties dataText = auxiliar.readConfig();
    HomePage home = new HomePage();
    ListProducts listProducts = new ListProducts();

    @BeforeAll
    public void visitPage(){
       home.openBrowser();

    }
    @Test
    public void getProductSearch() throws IOException {
        Assertions.assertEquals(dataText.getProperty("tittle"), home.getTitlePage(), dataText.getProperty("wrongPageMessage"));

        home.closeCookiesMessage();
        home.searchProduct(dataText.getProperty("textArticle"));
        Assertions.assertFalse(listProducts.emptyList(), dataText.getProperty("notResultMessage"));

        listProducts.getProductsToTxt(dataText.getProperty("txtName"));
    }

    @AfterAll
    public void closeBrowser(){
       home.closePage();
    }

}
