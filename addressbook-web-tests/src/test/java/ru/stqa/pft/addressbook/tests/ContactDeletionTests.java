package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion() {

    if (!app.getContactHelper().isThereAContact()) {          //проверка наличия в базе созданных контактов
      app.goTo().groupPage();                    //проверка наличия в базе созданных групп
      if (!app.group().isThereAGroup()) {
        app.group().create(new GroupDate().withName("test2"));
      }
      app.goTo().gotoAddContactPage();
      app.getContactHelper().createContact(new Contacts("Maria", "Sergeevna", "Ivanova", "Mashka", "do not know", "Rosneft", "Moskwa, 6", "2222222", "3333333", "4444444", "5555555", "email_1", "email_2", "email_3", "mashka.ru", "8", "May", "1982", "12", "September", "2004", "test1", "Kiev", "34", "kak dela?"), true);
    }


    app.goTo().gotoStartPage();
    List<Contacts> before=app.getContactHelper().getContactsList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlertDelete();
    app.goTo().gotoStartPage();
    List<Contacts> after=app.getContactHelper().getContactsList();
    before.remove(before.size()-1);
    Assert.assertEquals(before.size(),after.size());
    Assert.assertEquals(before, after);
  }
}
