package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTest extends TestBase {

  @Test
  public void testCreateNewContact() throws Exception {
    app.getNavigationHelper().gotoAddContactPage();
    app.getContactHelper().fillContactForm(new Contacts("Maria", "Sergeevna", "Ivanova", "Mashka", "do not know", "Rosneft", "Moskwa, 6", "2222222", "3333333", "4444444", "5555555", "email_1", "email_2", "email_3", "mashka.ru", "8", "May", "1982", "12", "September", "2004", "test1", "Kiev", "34", "kak dela?"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoAddContactPage();
  }

}
