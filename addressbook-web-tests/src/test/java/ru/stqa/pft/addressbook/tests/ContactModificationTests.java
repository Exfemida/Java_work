package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {          //проверка наличия в базе созданных контактов
      app.goTo().groupPage();                    //проверка наличия в базе созданных групп
      if (app.group().all().size() == 0) {
        app.group().create(new GroupDate().withName("test2"));
      }
      app.goTo().AddContactPage();
      app.contact().create(new ContactDate()
              .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
              .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
              .withAddress("Moskwa, 6").withHomePhone("2222222").withMobilePhone("3333333").withWorkPhone("4444444")
              .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
              .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
              .withAday("12").withAmonth("September").withAyear("2004")
              .withNewGroup("test1").withAddress2("Kiev").withPhone2("34").withNotes("kak dela?"), true);
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {

    app.goTo().StartPage();
    Contacts before = app.db().contacts();
    ContactDate modifyContact = before.iterator().next();
    ContactDate oldContact = modifyContact;
    app.contact().initEditContactById(modifyContact.getId());
    modifyContact = new ContactDate().withId(modifyContact.getId()).withFirstname("Maria").withLastname("Kochina")
            .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
            .withAddress("Moskwa, 6").withHomePhone("2222222").withMobilePhone("3333333").withWorkPhone("4444444")
            .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
            .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
            .withAday("12").withAmonth("September").withAyear("2004")
            .withNewGroup("test1").withAddress2("Kiev").withPhone2("34").withNotes("kak dela?");
    File photo = new File("src/test/resources/stru.png");
    modifyContact.withPhoto(photo);

    app.contact().fillContactForm(modifyContact, false);
    app.contact().submitContactUpdate();
    Contacts after = app.db().contacts();
    ContactDate newContact = new ContactDate()
            .withId(modifyContact.getId())
            .withFirstname(modifyContact.getFirstname())
            .withLastname(modifyContact.getLastname());

    assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(
            before.without(oldContact).withAdded(newContact)));

  }
}
