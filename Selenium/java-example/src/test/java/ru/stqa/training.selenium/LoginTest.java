package ru.stqa.training.selenium;


import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeTest
  public void start() {
  FirefoxOptions options = new FirefoxOptions().setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");
  options.setCapability("unexpectedAlertBehaviour", "dismiss");

//  или
//  FirefoxOptions options = new FirefoxOptions()
//            .setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe")
//            .setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

  driver = new FirefoxDriver(options);
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

    driver.findElement(By.linkText("Users")).click();
    wait.until(titleIs("Users | My Store"));


  }
  @AfterTest
  public void stop() {
    driver.quit();
    driver = null;
  }
}