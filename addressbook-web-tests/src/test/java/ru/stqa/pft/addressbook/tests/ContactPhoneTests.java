package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().StartPage();
    if (app.db().contacts().size()==0) {
      if (app.db().groups().size()==0) {
        app.goTo().groupPage();
        app.group().create(new GroupDate().withName("test2"));
      }
      Groups groups = app.db().groups();
      app.goTo().AddContactPage();
      app.contact().create(new ContactDate()
              .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
              .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
              .withAddress("Moskwa, 6").withHomePhone("2222222").withMobilePhone("3333333").withWorkPhone("4444444")
              .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
              .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
              .withAday("12").withAmonth("September").withAyear("2004")
              .inGroup(groups.iterator().next())
//              .withNewGroup("test1")
              .withAddress2("Kiev").withPhone2(null).withNotes("kak dela?"), true);
      app.goTo().StartPage();
    }

  }


  @Test

  public void testContactData(){
    ContactDate contact =app.contact().all().iterator().next();
    ContactDate contactInfoFromEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }


  private String mergePhones(ContactDate contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
    .stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactDate contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
           // .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

}
