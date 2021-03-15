package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size()==0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupDate().withName("test2"));
      }
      File photo = new File("src/test/resources/stru.png");
      Groups groups =app.db().groups();
      app.goTo().AddContactPage();
      app.contact().create(new ContactDate()
              .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
              .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
              .withAddress("Moskwa, 6").withHomePhone("2222222").withMobilePhone("3333333").withWorkPhone("4444444")
              .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
              .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
              .withAday("12").withAmonth("September").withAyear("2004")
              .withAddress2("Kiev").withPhone2("34").withNotes("kak dela?")
              .withPhoto(photo)
              .withNewGroup(groups.stream().map((g) ->g.getName()).findAny().get()), true);
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {

    app.goTo().StartPage();
    Contacts before = app.db().contacts();
    ContactDate modifyContact = before.iterator().next();
    ContactDate oldContact = modifyContact;
    app.contact().initEditContactById(modifyContact.getId());
    modifyContact = new ContactDate().withId(modifyContact.getId()).withFirstname("Maria").withMiddlename("Ivanovna").withLastname("Kochina")
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

    assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(
            before.without(oldContact).withAdded(modifyContact)));

  }
}
