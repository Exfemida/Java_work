package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
      super(wd);
  }

  public void submitContactCreation() {
   click(By.xpath("(//input[@name='submit'])[2]"));
  }


  public void fillContactForm(ContactDate contacts, boolean creation) {
    type(By.name("firstname"),contacts.getFirstname());
    type(By.name("middlename"),contacts.getMiddlename());
    type(By.name("lastname"),contacts.getLastname());
    type(By.name("nickname"),contacts.getNickname());
    type(By.name("title"),contacts.getTitle());
    type(By.name("company"),contacts.getCompany());
    type(By.name("address"),contacts.getAddress());
    type(By.name("home"),contacts.getHomePhone());
    type(By.name("mobile"),contacts.getMobilePhone());
    type(By.name("work"),contacts.getWorkPhone());
    type(By.name("fax"),contacts.getFax());
    type(By.name("email"),contacts.getEmail());
    type(By.name("email2"),contacts.getEmail2());
    type(By.name("email3"),contacts.getEmail3());
    type(By.name("homepage"),contacts.getHomepage());
    select(By.name("bday"), ""+contacts.getBday());
    select(By.name("bmonth"), contacts.getBmonth());
    type(By.name("byear"),contacts.getByear());
    select(By.name("aday"), (""+contacts.getAday()));
    select(By.name("amonth"), contacts.getAmonth());
    type(By.name("ayear"),contacts.getAyear());

    if (creation){
      if(contacts.getGroups().size()>0){
        Assert.assertTrue(contacts.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contacts.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("address2"),contacts.getAddress2());
    type(By.name("phone2"),contacts.getPhone2());
    type(By.name("notes"),contacts.getNotes());
   // type(By.name("photo"),contacts.getPhoto().getAbsolutePath());
    attach(By.name("photo"),contacts.getPhoto());
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
  }
  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='"+id+"']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void addToGroup() {
    click(By.xpath("//input[@value='Add to']"));
  }

  public void deleteFromGroup() {
    click(By.xpath("//input[@name='remove']"));
  }


  public void closeAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
   // click(By.xpath("//img[@alt='Edit']"));
  }
  public void initEditContactById(int id) {
  // wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List <WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  private void submitAddContact(int id) {
    wd.findElement(By.xpath("//a[@href='./?group=" + id +"']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public void create(ContactDate contact, boolean b) {
    fillContactForm(contact,true);
    submitContactCreation();
  }
  public void delete(ContactDate contact) {
    selectContactById(contact.getId());
    deleteContact();
    closeAlertDelete();
  }

  public void addToGroup(ContactDate contact, GroupDate newGroupId) {
    selectContactById(contact.getId());
    select(By.name("to_group"), ""+newGroupId.getName());
    addToGroup();
    submitAddContact(newGroupId.getId());
  }

  public void deleteFromGroup(ContactDate contact, GroupDate group) {
    select(By.name("group"), ""+group.getName());
    selectContactById(contact.getId());
    deleteFromGroup();

  }

    public List<ContactDate> getContactsList() {
    List<ContactDate> listOfContacts = new ArrayList<ContactDate>();
    List<WebElement> listOfrow = wd.findElements(By.cssSelector("tr"));
    listOfrow.remove(0);
    for (WebElement row : listOfrow) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        int id =Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
        String lastname = cells.get(1).getText();
        String firstname = cells.get(2).getText();
        ContactDate contact = new ContactDate().withId(id).withLastname(lastname).withFirstname(firstname);
        listOfContacts.add(contact);
    }
    return listOfContacts;
  }

  public Contacts all() {
   Contacts contacts=new Contacts();
    List<WebElement> listOfrow = wd.findElements(By.cssSelector("tr"));
    listOfrow.remove(0);
    for (WebElement row : listOfrow) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      contacts.add(new ContactDate().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }

  public ContactDate infoFormEditForm(ContactDate contact) {
    initEditContactById(contact.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String email=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactDate().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).
                    withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }



}


