package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePassHelper extends HelperBase{

  public ChangePassHelper (ApplicationManager app) {
    super(app);
  }

  public void start(String userName, String password) {
    wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
    type(By.name("username"), userName);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}
