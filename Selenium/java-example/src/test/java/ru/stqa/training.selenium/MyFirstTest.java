package ru.stqa.training.selenium;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeTest
  public void start(){
    driver=new ChromeDriver();
    wait=new WebDriverWait(driver,10);
  }

  @Test
  public void myFirstTest() {
    driver.get("http://www.google.com");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnK")).click();
    wait.until(titleIs("webdriver - ����� � Google"));
  }

  @AfterTest

  public  void stop(){
    driver.quit();
    driver=null;
  }
}