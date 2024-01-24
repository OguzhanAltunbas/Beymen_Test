package pages;

import models.Number;
import models.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.LoggerUtil;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static builders.DriverBuilds.getDriver;


public class CommonPage {
    public CommonPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public static final int DEFAULT_WAIT = Integer.parseInt(ConfigReader.getProperty("defaultWait"));

    Number number = new Number();
    Product product = new Product();

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    public WebElement acceptCookiesBtn;

    @FindBy(how = How.XPATH, using = "//*[@class='o-modal__closeButton bwi-close']")
    public WebElement closePopupBtn;

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT));

    public void checkUrl(String expectedUrl) {
        LoggerUtil.info("Expected Link: " + expectedUrl + "\nActual Link:" + getDriver().getCurrentUrl());
        Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedUrl));
    }


    public void elementVisible(WebElement element,int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            LoggerUtil.info(element + "is visiable.");

        } catch (Exception ex) {
            LoggerUtil.assertfailWithLogging(element + " is not visiable");
        }
    }

    public void waitElementInvisible(WebElement element,int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Throwable var3) {
            LoggerUtil.assertfailWithLogging(element + " is still visible");
        }

    }

    public void seeAndClickElement(WebElement element) {
        try {
            elementVisible(element,DEFAULT_WAIT);
            element.click();
            LoggerUtil.info(element + "is click successful.");
        } catch (Exception ex) {
            LoggerUtil.assertfailWithLogging(element + " clicking on the element could not be performed.");
        }
    }

    public void sendKeysLikeHuman(WebElement element, String text) {
        Random r = new Random();
        try {
            for (int i = 0; i < text.length(); i++) {
                Thread.sleep((int) (r.nextGaussian() * 15 + 300));
                String s = new StringBuilder().append(text.charAt(i)).toString();
                element.sendKeys(s);
                LoggerUtil.info(element + "is successful.");
            }
        } catch (Exception e) {
            LoggerUtil.assertfailWithLogging(element + " sending keys on the element could not be performed");
        }
    }

    public boolean checkElementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Throwable var3) {
            return false;
        }
    }

    public void ScrollAndClickElementCenter(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) getDriver()).executeScript(scrollElementIntoMiddle, webElement);
            TimeUnit.SECONDS.sleep(1);
            webElement.click();
        } catch (Exception e) {
            LoggerUtil.assertfailWithLogging(webElement + "There was a problem scrolling.");
        }
    }

    public static String getDataFromSheet(String xlsxPath, int rowNum, int colNum) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(xlsxPath));
        Workbook workbook = new XSSFWorkbook(excelFile);

        try {
            Sheet datatypeSheet = workbook.getSheetAt(0);


            if (rowNum < 1 || colNum < 1) {
                return "Invalid row or column number.";
            }

            Row currentRow = datatypeSheet.getRow(rowNum - 1);

            if (currentRow == null) {
                return "Specified row is not available.";
            }

            Cell currentCell = currentRow.getCell(colNum - 1);

            if (currentCell == null) {
                return "Specified column not available.";
            }

            return currentCell.getStringCellValue();
        } finally {
            workbook.close();
            excelFile.close();
        }
    }

    public void clearInputField(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            Thread.sleep(1000);
            element.clear();
            Thread.sleep(1000);
            LoggerUtil.info(element + " has been successfully clear.");
        } catch (Exception ex) {
            LoggerUtil.assertfailWithLogging(element + " could not be clear.");
        }
    }


    public void pressKey(Keys key){
        Actions actions = new Actions(getDriver());
        actions.sendKeys(key).build().perform();
    }

    public void saveProductInfoInTxt(WebElement product, WebElement price) throws IOException {
        String productText = product.getText();
        String priceText = price.getText();

        File file = new File("productInfo.txt");

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file, false))) {
            bWriter.write(productText + ": " + priceText);
        }
    }

    protected List<WebElement> presenceOfAllWait(By locator, int seconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(500L)).ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    protected void faultyElementsList(List<String> faultyElements) {
        try {
            if (faultyElements.size() > 0) {
                LoggerUtil.assertfailWithLogging("faulty elements : " + faultyElements);
            }
        } catch (Exception e) {
            LoggerUtil.assertfailWithLogging("faultyElementsList encountered a problem" );
        }
    }

}
