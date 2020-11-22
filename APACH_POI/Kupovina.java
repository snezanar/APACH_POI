package SeleniumAPACH_POI;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Kupovina {
    /* Koristeci svoju tabelu sa potrebnim vrednostima, automatizovati kupovinu na sajtu https://www.saucedemo.com. Proci kroz sledece stavke:
     a) Ulogovati se sa ispravnim kredencijalima(na sajtu su ispisani ispravni kredencijali, probati sa 2 username-a)
     b) Izabrati da redosled proizvoda bude od najjeftinijeg do najskupljeg
     c) Dodati najskuplji proizvod u cart
     d) Preci na korak za placanje i popuniti neophodna polja
     e) Uspesno zavrsiti kupovinu

   */ //Dodatno: Izvuci informaciju (mozete i rucno) o iznosu takse na kupovinu, nazivu proizvoda i payment information (#nekbrojevi) te iste informacije ubaciti u tabelu a
    //zatim prilikom dolaska na tu stranicu kroz test, proveriti da li se ta dva parametra poklapaju sa onim sto imate u vasoj tabeli.
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Misa i Sneza\\Downloads\\!IT BOOTCAMP\\7. SELENIJUM\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        for (int i = 1; i <= 2; i++) {

            HSSFWorkbook wb;
            FileInputStream fi;

            try {
                fi = new FileInputStream("C:\\Users\\Misa i Sneza\\Downloads\\!IT BOOTCAMP\\7. SELENIJUM\\Domaci\\saucedemo.xls");
                wb = new HSSFWorkbook(fi);

                Sheet sheet = wb.getSheetAt(0);
                Row row = sheet.getRow(i);

                Cell cell = row.getCell(0);
                WebElement username = driver.findElement(By.xpath(" //input[@id='user-name']"));
                username.sendKeys(cell.toString());
                Thread.sleep(1000);

                cell = row.getCell(1);
                WebElement password = driver.findElement(By.xpath(" //input[@id='password']"));
                password.sendKeys(cell.toString());
                Thread.sleep(1000);

                WebElement login = driver.findElement(By.id("login-button"));
                login.click();
                Thread.sleep(1000);

                Select continent = new Select(driver.findElement(By.className("product_sort_container")));
                continent.selectByVisibleText("Price (low to high)");
                Thread.sleep(1000);

                WebElement addToCart = driver.findElement(By.xpath(" //body[@class='main-body']//div[@id='inventory_container']//div[@id='inventory_container']//div[2]//div[3]//button[1]"));
                addToCart.click();
                Thread.sleep(1000);

                WebElement goToCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link fa-layers fa-fw']//*[local-name()='svg']"));
                goToCart.click();
                Thread.sleep(1000);

                WebElement checkOut = driver.findElement(By.xpath(" //a[@class='btn_action checkout_button']"));
                checkOut.click();
                Thread.sleep(1000);

                cell = row.getCell(2);
                WebElement firstName = driver.findElement(By.id("first-name"));
                firstName.sendKeys(cell.toString());
                Thread.sleep(1000);

                cell = row.getCell(3);
                WebElement lastName = driver.findElement(By.id("last-name"));
                lastName.sendKeys(cell.toString());
                Thread.sleep(1000);

                cell = row.getCell(4);
                WebElement zipCode = driver.findElement(By.id("postal-code"));
                zipCode.sendKeys(cell.toString());
                Thread.sleep(1000);

                WebElement confirmPurchase = driver.findElement(By.xpath(" //input[@class='btn_primary cart_button']"));//continue
                confirmPurchase.click();
                Thread.sleep(1000);

                WebElement finish = driver.findElement(By.xpath("//a[@class='btn_action cart_button']"));
                finish.click();
                Thread.sleep(1000);

                WebElement menu = driver.findElement(By.xpath("//button[contains(text(),'Open Menu')]"));
                menu.click();
                Thread.sleep(1000);

                WebElement logOut=driver.findElement(By.id(" logout_sidebar_link"));
                logOut.click();
                Thread.sleep(1000);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            driver.close();
        }
    }
}

