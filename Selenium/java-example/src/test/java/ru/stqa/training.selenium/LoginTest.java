package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeTest
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void testLoginAdmin() {
    driver.get("http://localhost/litecard/admin");
    String login = "admin";
    String password = "admin";
    driver.findElement(By.name("username")).sendKeys(login);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
    wait.until(titleIs("My Store"));

    driver.findElement(By.linkText("Users_")).click();
    wait.until(titleIs("Users | My Store"));
  }

  @AfterTest
  public void stop() {
    driver.quit();
    driver = null;
  }
}