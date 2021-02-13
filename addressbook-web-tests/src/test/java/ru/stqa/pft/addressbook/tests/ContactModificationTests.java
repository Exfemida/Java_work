package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (!app.getContactHelper().isThereAContact()) {          //проверка наличия в базе созданных контактов
      app.getNavigationHelper().gotoGroupPage();                    //проверка наличия в базе созданных групп
      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
      }
      app.getNavigationHelper().gotoAddContactPage();
      app.getContactHelper().createContact(new Contacts("Maria", "Sergeevna", "Ivanova", "Mashka", "do not know", "Rosneft", "Moskwa, 6", "2222222", "3333333", "4444444", "5555555", "email_1", "email_2", "email_3", "mashka.ru", "8", "May", "1982", "12", "September", "2004", "test1", "Kiev", "34", "kak dela?"), true);
    }

    app.getNavigationHelper().gotoStartPage();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new Contacts("Maria", "Sergeevna", "Ivanova", "Mashka", "do not know", "Rosneft", "Moskwa, 6", "2222222", "3333333", "4444444", "5555555", "email_1", "email_2", "email_3", "mashka.ru", "8", "May", "1982", "12", "September", "2004", null, "Kiev22", "3422", "kak dela?22"), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().gotoStartPage();

  }

}
