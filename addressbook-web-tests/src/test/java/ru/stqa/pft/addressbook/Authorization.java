package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Authorization {
  private WebDriver wd;
  private String username;
  private String password;


  public Authorization(WebDriver wd,String username, String password) {
    this.wd=wd;
    this.username =username;
    this.password =password;
  }

  public void login() {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
}

