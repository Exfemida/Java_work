package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
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
    List<Contacts> before = app.getContactHelper().getContactsList();
    app.getContactHelper().editContact(before.size() - 1);
    String firstname = "Maria";
    String lastname = "Ivanova";
    app.getContactHelper().fillContactForm(new Contacts(firstname, "Sergeevna", lastname, "Mashka", "do not know", "Rosneft", "Moskwa, 6", "2222222", "3333333", "4444444", "5555555", "email_1", "email_2", "email_3", "mashka.ru", "8", "May", "1982", "12", "September", "2004", null, "Kiev22", "3422", "kak dela?22"), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().gotoStartPage();
    List<Contacts> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size());
    Contacts cont = new Contacts(before.get(before.size() - 1).getId(), lastname, firstname);
    before.remove(before.size() - 1);
    before.add(cont);

    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
