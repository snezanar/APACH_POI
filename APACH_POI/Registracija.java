package SeleniumAPACH_POI;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Registracija {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Misa i Sneza\\Downloads\\!IT BOOTCAMP\\7. SELENIJUM\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        HSSFWorkbook wb;
        FileInputStream fi;


        try {
            fi = new FileInputStream("C:\\Users\\Misa i Sneza\\Downloads\\!IT BOOTCAMP\\7. SELENIJUM\\registracija.xls");
            wb = new HSSFWorkbook(fi);

            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= 4; i++) {
                Row row = sheet.getRow(i);

                Cell cell = row.getCell(0);
                WebElement firstname = driver.findElement(By.xpath("//input[@id='firstName']"));
                firstname.sendKeys(cell.toString());
                Thread.sleep(1000);

                cell = row.getCell(1);
                WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
                lastname.sendKeys(cell.toString());
                Thread.sleep(1000);

                cell = row.getCell(2);
                WebElement email = driver.findElement(By.xpath(" //input[@id='userEmail']"));
                email.sendKeys(cell.toString());
                Thread.sleep(1000);

                cell = row.getCell(3);
                if (row.getCell(3).getStringCellValue().contains("f")) {
                    WebElement femail = driver.findElement(By.xpath(" //label[contains(text(),'Female')]"));
                    femail.click();
                } else {
                    WebElement male = driver.findElement(By.xpath(" //label[contains(text(),'Male')]"));
                    male.click();
                }

                cell = row.getCell(4);
                WebElement phoneNum = driver.findElement(By.xpath(" //input[@id='userNumber']"));
                phoneNum.sendKeys(cell.toString());
                phoneNum.sendKeys(Keys.PAGE_DOWN);
                Thread.sleep(1000);

                cell = row.getCell(5);
                WebElement currentAdderss = driver.findElement(By.xpath(" //textarea[@id='currentAddress']"));
                currentAdderss.sendKeys(cell.toString());
                Thread.sleep(1000);

                WebElement submit = driver.findElement(By.cssSelector("#submit"));
                submit.click();
                Thread.sleep(1000);

                WebElement close = driver.findElement(By.id("closeLargeModal"));
                close.click();
                Thread.sleep(1000);

            }

            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        driver.close();
    }
}