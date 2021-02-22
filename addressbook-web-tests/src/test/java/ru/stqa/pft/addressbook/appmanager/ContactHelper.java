package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
      super(wd);
  }

  public void submitContactCreation() {
   click(By.xpath("(//input[@name='submit'])[2]"));
  }


  public void fillContactForm(Contacts contacts, boolean creation) {
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

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contacts.getNewGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("address2"),contacts.getAddress2());
    type(By.name("phone2"),contacts.getPhone2());
    type(By.name("notes"),contacts.getNotes());
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
   // click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public void createContact(Contacts contact, boolean b) {
    fillContactForm(contact,true);
    submitContactCreation();
  }

  public List<Contacts> getContactsList() {
    List<Contacts> listOfContacts = new ArrayList<Contacts>();
    List<WebElement> listOfrow = wd.findElements(By.cssSelector("tr"));
    listOfrow.remove(0);
    for (WebElement row : listOfrow) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        int id =Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
        String lastname = cells.get(1).getText();
        String firstname = cells.get(2).getText();
        Contacts contact = new Contacts(id, lastname, firstname);
        listOfContacts.add(contact);
    }
    return listOfContacts;
  }
}
