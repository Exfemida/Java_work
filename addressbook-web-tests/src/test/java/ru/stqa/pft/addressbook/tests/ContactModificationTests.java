package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {

    if (!app.contact().isThereAContact()) {          //проверка наличия в базе созданных контактов
      app.goTo().groupPage();                    //проверка наличия в базе созданных групп
      if (!app.group().isThereAGroup()) {
        app.group().create(new GroupDate().withName("test2"));
      }
      app.goTo().AddContactPage();
      app.contact().create(new ContactDate()
              .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
              .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
              .withAddress("Moskwa, 6").withHome("2222222").withMobile("3333333").withWork("4444444")
              .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
              .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
              .withAday("12").withAmonth("September").withAyear("2004")
              .withNewGroup("test1").withAddress2("Kiev").withPhone2("34").withNotes("kak dela?"), true);
    }

    app.goTo().StartPage();
    List<ContactDate> before = app.contact().getContactsList();
    app.contact().editContact(before.size() - 1);
    String firstname = "Maria";
    String lastname = "Ivanova";
    app.contact().fillContactForm(new ContactDate()
            .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
            .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
            .withAddress("Moskwa, 6").withHome("2222222").withMobile("3333333").withWork("4444444")
            .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
            .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
            .withAday("12").withAmonth("September").withAyear("2004")
            .withNewGroup("test1").withAddress2("Kiev").withPhone2("34").withNotes("kak dela?"), false);


            app.contact().submitContactUpdate();
    app.goTo().StartPage();
    List<ContactDate> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size());
 //   ContactDate cont = new ContactDate(before.get(before.size() - 1).getId(), lastname, firstname);
    ContactDate cont = new ContactDate().withId(before.size() - 1).withLastname(lastname).withFirstname(firstname);
    before.remove(before.size() - 1);
    before.add(cont);

    Comparator<? super ContactDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
