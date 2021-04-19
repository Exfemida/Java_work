package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.mantis.model.UsersDate;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChangePassHelper extends HelperBase{

  private SessionFactory sessionFactory;

  public ChangePassHelper (ApplicationManager app) {
    super(app);
  }

  public void start(String userName, String password) {
    wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
    type(By.name("username"), userName);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void goToManagerUsers() {
    wd.findElement(By.xpath("//a[@href='/mantisbt-1.2.20/manage_overview_page.php']")).click();
    wd.findElement(By.xpath("//a[@href='/mantisbt-1.2.20/manage_user_page.php']")).click();
  }

  public void selectManager(Integer id) {
    wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id +"']")).click();
  }

  public void managerResetPassword(String email) {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }




  public List<UsersDate> getUsersData() {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UsersDate> result = session.createQuery("from UsersDate").list();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}
