package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactCreationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size()==0) {
      app.group().create(new GroupDate().withName("test1"));
    }
  }

  @Test //(enabled = false)
  public void testCreateNewContact() throws Exception {
    app.goTo().StartPage();
    Contacts before = app.contact().all();
    app.goTo().AddContactPage();
    ContactDate cont = new ContactDate()
            .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
            .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
            .withAddress("Moskwa, 6").withHome("2222222").withMobile("3333333").withWork("4444444")
            .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
            .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
            .withAday("12").withAmonth("September").withAyear("2004")
            .withNewGroup("test1").withAddress2("Kiev").withPhone2("34").withNotes("kak dela?");

    app.contact().create(cont, true);
    app.goTo().StartPage();
    Contacts after = app.contact().all();

    MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()+1));
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(
            before.withAdded(cont.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }
}
