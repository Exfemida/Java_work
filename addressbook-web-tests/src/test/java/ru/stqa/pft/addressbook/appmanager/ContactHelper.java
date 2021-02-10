package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
      super(wd);
  }

  public void submitContactCreation() {
   click(By.xpath("(//input[@name='submit'])[2]"));
  }


  public void fillContactForm(Contacts contacts) {
    type(By.name("firstname"),contacts.getFirstname());
    type(By.name("middlename"),contacts.getMiddlename());
    type(By.name("lastname"),contacts.getLastname());
    type(By.name("nickname"),contacts.getNickname());
    type(By.name("title"),contacts.getTitle());
    type(By.name("company"),contacts.getCompany());
    type(By.name("address"),contacts.getAddress());
    type(By.name("home"),contacts.getHome());
    type(By.name("mobile"),contacts.getMobile());
    type(By.name("work"),contacts.getWork());
    type(By.name("fax"),contacts.getFax());
    type(By.name("email"),contacts.getEmail());
    type(By.name("email2"),contacts.getEmail2());
    type(By.name("email3"),contacts.getEmail3());
    type(By.name("homepage"),contacts.getHomepage());
    select(By.name("bday"), contacts.getBday());
    select(By.name("bmonth"), contacts.getBmonth());
    type(By.name("byear"),contacts.getByear());
    select(By.name("aday"), contacts.getAday());
    select(By.name("amonth"), contacts.getAmonth());
    type(By.name("ayear"),contacts.getAyear());
    select(By.name("new_group"), contacts.getNewGroup());
    type(By.name("address2"),contacts.getAddress2());
    type(By.name("phone2"),contacts.getPhone2());
    type(By.name("notes"),contacts.getNotes());
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public void editContact() {
    click(By.xpath("(//img[@alt='Edit'])[3]"));     //click(By.xpath("(//img[@alt='Edit'])["+numStr+"]"));
  }

  public void submitContactUpdate() {  //String numStr
    click(By.name("update"));
  }
  public void editContactForm(Contacts contacts) {
    type(By.name("firstname"),contacts.getFirstname());
    type(By.name("middlename"),contacts.getMiddlename());
    type(By.name("lastname"),contacts.getLastname());
    type(By.name("nickname"),contacts.getNickname());
    type(By.name("title"),contacts.getTitle());
    type(By.name("company"),contacts.getCompany());
    type(By.name("address"),contacts.getAddress());
    type(By.name("home"),contacts.getHome());
    type(By.name("mobile"),contacts.getMobile());
    type(By.name("work"),contacts.getWork());
    type(By.name("fax"),contacts.getFax());
    type(By.name("email"),contacts.getEmail());
    type(By.name("email2"),contacts.getEmail2());
    type(By.name("email3"),contacts.getEmail3());
    type(By.name("homepage"),contacts.getHomepage());
    select(By.name("bday"), contacts.getBday());
    select(By.name("bmonth"), contacts.getBmonth());
    type(By.name("byear"),contacts.getByear());
    select(By.name("aday"), contacts.getAday());
    select(By.name("amonth"), contacts.getAmonth());
    type(By.name("ayear"),contacts.getAyear());

    type(By.name("address2"),contacts.getAddress2());
    type(By.name("phone2"),contacts.getPhone2());
    type(By.name("notes"),contacts.getNotes());
  }

}
