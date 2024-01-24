package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.LoggerUtil;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SearchPage extends CommonPage {

    private final By productPrice = By.xpath("//*[@id='productList']/div//span[@class='m-productCard__newPrice']");
    private final By productCart = By.xpath("//div[@class='m-productCard__stockCartIcon']");

    @FindBy(how = How.ID, using = "addBasket")
    public WebElement addToBasketButton;
    @FindBy(how = How.XPATH, using = "//*[@id='productListTitle']")
    public WebElement productListTitle;
    @FindBy(how = How.XPATH, using = "//*[@id='productList']/div//span[@class='m-productCard__desc']")
    public List<WebElement> productName;
    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'m-variation__item') and not(contains(@class,'-disabled'))])[1]")
    public WebElement productVariation;


    public void selectAndSetRandomProduct() {
        List<WebElement> productPriceList = super.presenceOfAllWait(productPrice, DEFAULT_WAIT);
        int size = ThreadLocalRandom.current().nextInt(1, productPriceList.size());
        number.setRandomNumber(size);
    }

    public void saveProductInfoAndPrice() throws IOException {
        List<WebElement> productPriceList = super.presenceOfAllWait(productPrice, DEFAULT_WAIT);
        setProductNameAndPrice();
        saveProductInfoInTxt(productName.get(number.getRandomNumber()), productPriceList.get(number.getRandomNumber()));
    }

    public void findAndClickRandomProduct() {
        List<WebElement> productCartList = super.presenceOfAllWait(productCart, DEFAULT_WAIT);
        WebElement webElement = productCartList.get(number.getRandomNumber());
        ScrollAndClickElementCenter(webElement);
    }


    private void setProductNameAndPrice() {
        List<WebElement> productPriceList = super.presenceOfAllWait(productPrice, DEFAULT_WAIT);
        WebElement productNameElement = productName.get(number.getRandomNumber());
        WebElement productPriceElement = productPriceList.get(number.getRandomNumber());

        if (productNameElement != null && productPriceElement != null) {
            product.setProductName(productNameElement.getText());
            product.setPrice(productPriceElement.getText());
        } else {
            LoggerUtil.assertfailWithLogging("productNameElement or productPriceElement is null.");
        }
    }
}
