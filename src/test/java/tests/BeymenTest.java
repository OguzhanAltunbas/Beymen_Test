package tests;

import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.CommonPage;
import utils.ConfigReader;
import utils.ScreenShotUtils;

import java.io.IOException;

public class BeymenTest extends CommonTest{

    @Test
    public void beymenShoppingTest() throws IOException, InterruptedException {
        commonPage.checkUrl(ConfigReader.getProperty("baseURL"));
        commonPage.seeAndClickElement(commonPage.acceptCookiesBtn);
        commonPage.seeAndClickElement(commonPage.closePopupBtn);

        commonPage.elementVisible(homePage.beymenLogo, CommonPage.DEFAULT_WAIT);
        commonPage.elementVisible(homePage.productSearchBox,CommonPage.DEFAULT_WAIT);
        homePage.searchAndWriteProductFromExcel("./product.xlsx",  1, 1);
        ScreenShotUtils.TakeScreenshot("searchSort");

        commonPage.clearInputField(homePage.productSearchBox);
        homePage.searchAndWriteProductFromExcel("./product.xlsx",  1, 2);
        ScreenShotUtils.TakeScreenshot("searchShirt");

        commonPage.pressKey(Keys.ENTER);
        searchPage.checkElementIsDisplayed(searchPage.productListTitle);
        searchPage.selectAndSetRandomProduct();
        searchPage.saveProductInfoAndPrice();
        searchPage.findAndClickRandomProduct();

        commonPage.seeAndClickElement(searchPage.productVariation);
        commonPage.seeAndClickElement(searchPage.addToBasketButton);

        commonPage.elementVisible(homePage.addedToBasketConfirmationText,CommonPage.DEFAULT_WAIT);
        ScreenShotUtils.TakeScreenshot("productSuccessfullyAddedToTheCart");
        commonPage.waitElementInvisible(homePage.addedToBasketConfirmationText,CommonPage.DEFAULT_WAIT);

        searchPage.ScrollAndClickElementCenter(homePage.myCartButton);
        commonPage.elementVisible(basketPage.shoppingCartTitle,CommonPage.DEFAULT_WAIT);

        basketPage.checkProductTextAndPriceExpected();
        commonPage.seeAndClickElement(basketPage.itemQuantitySelector);
        commonPage.seeAndClickElement(basketPage.twoProductQuantityChoice);
        basketPage.compareProductPrices();
        ScreenShotUtils.TakeScreenshot("comparisonProcessSuccessful");

        basketPage.myShoppingCartContentControl();

        commonPage.seeAndClickElement(basketPage.removeProductBtn);
        commonPage.elementVisible(basketPage.removedProductSuccessfullyMsg,CommonPage.DEFAULT_WAIT);
        ScreenShotUtils.TakeScreenshot("deletedProcessSuccessful");

        commonPage.elementVisible(basketPage.emptyBasketText,5);
        ScreenShotUtils.TakeScreenshot("basketIsEmpty");
    }

}
