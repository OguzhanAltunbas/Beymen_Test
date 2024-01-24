package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BasketPage extends CommonPage {

    @FindBy(how = How.XPATH, using = "//*[@class='m-basket__header']")
    public WebElement shoppingCartTitle;

    @FindBy(how = How.XPATH, using = "//span[@class='m-basket__productInfoName']")
    public WebElement basketProductName;

    @FindBy(how = How.XPATH, using = "//span[@class='m-productPrice__salePrice']")
    public WebElement basketProductPrice;

    @FindBy(how = How.XPATH, using = "//select[@id='quantitySelect0-key-0']")
    public WebElement itemQuantitySelector;

    @FindBy(how = How.XPATH, using = "//option[@value='2']")
    public WebElement twoProductQuantityChoice;

    @FindBy(how = How.XPATH, using = "(//button[@id='removeCartItemBtn0-key-0'])[1]")
    public WebElement removeProductBtn;

    @FindBy(how = How.XPATH, using = "//*[text()='Sepetinizden ürün başarılı bir şekilde silinmiştir.']")
    public WebElement removedProductSuccessfullyMsg;

    @FindBy(how = How.XPATH, using = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement emptyBasketText;
    @FindBy(how = How.XPATH, using = "//*[@class='m-orderSummary__header']")
    public WebElement orderSummaryHeader;
    @FindBy(how = How.XPATH, using = "//*[@class='m-orderSummary__bottom']//button[text()='SATIN AL']")
    public WebElement orderSummaryBuyButton;
    @FindBy(how = How.ID, using = "addCouponCodeBtn")
    public WebElement addCouponCodeButton;




    public void checkProductTextAndPriceExpected() {
        verifyProductText(basketProductName, product.getProductName());
        verifyPriceForBasket(basketProductPrice, product.getPrice());
    }

    public void compareProductPrices() throws InterruptedException {
        verifyAndCompareProductPrices(product.getPrice(), basketProductPrice);
    }

    private void verifyProductText(WebElement element, String expectedText) {
        String elementText = element.getText();
        LoggerUtil.assertEquals(elementText, expectedText);
    }

    private void verifyPriceForBasket(WebElement productPrice, String expectedPrice) {
        int firstPrice = parsePriceString(expectedPrice);

        String[] priceParts = productPrice.getText().split(",");
        int basketPrice = parsePriceString(priceParts[0]);

        Assert.assertEquals("The initial price of the product and the price added to the cart are not equal.", firstPrice, basketPrice);
    }

    private void verifyAndCompareProductPrices(String oneProductPrice, WebElement twoProductPriceElement) throws InterruptedException {
        Thread.sleep(2000);

        int firstPrice = parsePriceString(oneProductPrice);

        String[] priceParts = twoProductPriceElement.getText().split(",");
        int twoProductPrice = parsePriceString(priceParts[0]);

        // Doubling and controlling prices
        Assert.assertEquals("The initial price of the product is not equal to twice the price of the product added to the cart.", firstPrice * 2, twoProductPrice);
    }

    public void myShoppingCartContentControl() {
        List<String> faultyElements = new ArrayList<>();
        Map<String, WebElement> controlElement = new LinkedHashMap<>();

        controlElement.put("Order Summary Header",orderSummaryHeader);
        controlElement.put("Order Summary Buy Button",orderSummaryBuyButton);
        controlElement.put("Add Coupon Code Button",addCouponCodeButton);

        controlElement.forEach((k, v) -> {
            if (!checkElementIsDisplayed(v)) {
                faultyElements.add(k);
            }
        });

        faultyElementsList(faultyElements);

    }

    private int parsePriceString(String priceString) {
        return Integer.parseInt(priceString.replaceAll(" ", "").replace("T", "")
                .replace("L", "").replace(".", ""));
    }
}
