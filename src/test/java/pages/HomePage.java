package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.IOException;

public class HomePage extends CommonPage {

    @FindBy(how = How.XPATH, using = "//img[@alt='Beymen']")
    public WebElement beymenLogo;

    @FindBy(how = How.XPATH, using = "(//*[@class='o-header__search--input'])[last()]")
    public WebElement productSearchBox;

    @FindBy(how = How.XPATH, using = "//*[text()='Ürün sepetinize eklenmiştir.']")
    public WebElement addedToBasketConfirmationText;

    @FindBy(how = How.XPATH, using = "//*[@title='Sepetim']")
    public WebElement myCartButton;

    public void searchAndWriteProductFromExcel(String filePath, int row, int column) throws IOException {
        String searchData = getDataFromSheet(filePath, row, column);
        sendKeysLikeHuman(productSearchBox,searchData);
    }
}
