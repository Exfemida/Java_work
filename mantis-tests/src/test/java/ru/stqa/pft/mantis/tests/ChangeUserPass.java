package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UsersDate;
import org.hibernate.SessionFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.reverseOrder;
import static org.testng.AssertJUnit.assertTrue;


public class ChangeUserPass extends TestBase {

  @Test
  public void ChangeUserPassword() throws SQLException, IOException, MessagingException {
    //зайти под администраторм
    String userName = "administrator";
    String password = "root";
    app.changePass().start(userName, password);

    //получить из базы список пользователей
    List<UsersDate> result = app.changePass().getUsersData();

    //выбрать любого исключая администратора
    Integer managerId = 1;
    String managerName = null;
    String managerEmail=null;
    for (UsersDate user : result) {
      if (!(user.getUsername().equals("administrator"))) {
        managerId = user.getId();
        managerName=user.getUsername();
        managerEmail=user.getEmail();
      }
    }
    System.out.println(managerId + "" + managerName);


   //
   // app.james().deleteUser(managerName);
   // app.james().createUser(managerName, "password");
   Integer boxSize=(app.james().waitForMail(managerName,"password", 60000, 0)).size();

    //перейти на сайте в список пользователей, выбрать жертву и сбросить у нее пароль
    app.changePass().goToManagerUsers();
    app.changePass().selectManager(managerId);
    app.changePass().managerResetPassword(managerEmail);

    //на сервере получить письмо со ссылкой
//    app.james().getAllMail(managerName, "password");

    List<MailMessage> mailMessages=app.james().waitForMail(managerName,"password", 60000, boxSize);
    MailMessage mailMessage= mailMessages.get(mailMessages.size() - 1);


    String confirmationLink = findConfirmationLink(mailMessage, managerEmail);
    app.changePass().finish(confirmationLink, "newpassword");



  }
  private String findConfirmationLink(MailMessage mailMessage, String email) {
 //   MailMessage mailMessage=mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
    VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}

