package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UsersDate;
import org.hibernate.SessionFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class ChangeUserPass extends TestBase {

  @Test
  public void ChangeUserPassword() throws SQLException, IOException, MessagingException {
    String userName = "administrator";
    String password = "root";
    app.changePass().start(userName, password);

    List<UsersDate> result = app.changePass().getUsersData();

    Integer userId = 1;
    for (UsersDate user : result) {
      if (!(user.getUsername().equals("administrator"))) {
        userId = user.getId();
      }
    }
    System.out.println(userId + "");

    app.changePass().goToManagerUsers();

  }


}

