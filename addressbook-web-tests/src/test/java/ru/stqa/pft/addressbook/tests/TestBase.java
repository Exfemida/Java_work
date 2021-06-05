package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class TestBase {

  Logger logger= LoggerFactory.getLogger(TestBase.class);
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

 // public ApplicationManager getApp() {
 //   return app;
 // }

//  @BeforeMethod
//  public void logTestStart(Method m, Object[] p) {
//    logger.info("Start test " + m.getName() + "with parametrs " + Arrays.asList(p));
//  }

//  @AfterMethod (alwaysRun=true)
//  public void logTestStop(Method m, Object[] p){
//    logger.info("Stop test " + m.getName() + "with parametrs " + Arrays.asList(p));
//  }

//  public void verifyGroupListInUA() {
//    if (Boolean.getBoolean("verifyUI")) {
//      Groups dbGroups = app.db().groups();
//      Groups uaGroups = app.group().all();
//      MatcherAssert.assertThat(uaGroups, CoreMatchers.equalTo(dbGroups.stream()
//              .map((g) -> new GroupDate().withId(g.getId()).withName(g.getName()))
//              .collect(Collectors.toSet())));
//    }


//  }

}
