package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;

public class TestBase {

  Logger logger= LoggerFactory.getLogger(TestBase.class);
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.EDGE));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
//    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
//    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) {
    boolean result = false; //по умолчанию не статус не close
    //которая должна через Remote API получать из баг-трекера информацию о баг-репорте с заданным идентификатором, и возвращать значение false или true в зависимости от того, помечен он как исправленный или нет
  return result;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
