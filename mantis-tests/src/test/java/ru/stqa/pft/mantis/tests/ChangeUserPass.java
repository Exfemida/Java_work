package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UsersDate;
import org.hibernate.SessionFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class ChangeUserPass extends TestBase {

  private SessionFactory sessionFactory;

//  @BeforeMethod
//  public void setUp() throws Exception {
//    // A SessionFactory is set up once for an application!
//    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//            .configure() // configures settings from hibernate.cfg.xml
//            .build();
//    try {
//      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//    } catch (Exception e) {
//      e.printStackTrace();
//      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//      // so destroy it manually.
//      StandardServiceRegistryBuilder.destroy(registry);
//    }
//  }


  @Test
  public void ChangeUserPassword() throws SQLException, IOException, MessagingException {
//    List<UsersDate> result = users();

//    Integer userId = 1;
//    for (UsersDate user : result) {
//      if (!(user.getUsername().equals("administrator"))) {
//        userId = user.getId();
//      }
//    }
//    System.out.println(userId + "");

    String userName = "administrator";
    String password = "root";
    app.changePass().start(userName, password);


  }

  private List<UsersDate> users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UsersDate> result = session.createQuery("from UsersDate").list();
    session.getTransaction().commit();
    session.close();
    return result;
  }

}

