package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

   public RegistrationHelper (ApplicationManager app) {
    super(app);
  }

  public void start(String userName, String email) {
    wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
    type(By.name("username"), userName);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
   // click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
  }

  public void finish(String confirmationLink, String password) {
     wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));

  }
}
