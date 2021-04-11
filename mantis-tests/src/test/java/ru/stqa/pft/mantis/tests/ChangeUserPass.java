package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersDate;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ChangeUserPass extends TestBase {


  private SessionFactory sessionFactory;

  @BeforeClass
  public void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }


  @Test
  public void ChangeUserPassword() throws SQLException {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UsersDate> result = session.createQuery("from UsersDate").list();
    session.getTransaction().commit();
    session.close();

    for (UsersDate user : result) {
      System.out.println(user.toString());
      System.out.println(user.getId());
    }
  }
}
