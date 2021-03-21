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


public class ContactDeleteFormGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupDate().withName("test2"));
      }
      File photo = new File("src/test/resources/stru.png");
      Groups groups = app.db().groups();
      app.goTo().AddContactPage();
      app.contact().create(new ContactDate()
              .withFirstname("Maria").withMiddlename("Sergeevna").withLastname("Ivanova")
              .withNickname("Mashka").withTitle("do not know").withCompany("Rosneft")
              .withAddress("Moskwa, 6").withHomePhone("2222222").withMobilePhone("3333333").withWorkPhone("4444444")
              .withFax("5555555").withEmail("email_1").withEmail2("email_2").withEmail3("email_3")
              .withHomepage("mashka.ru").withBday("8").withBmonth("May").withByear("1982")
              .withAday("12").withAmonth("September").withAyear("2004")
              .withAddress2("Kiev").withPhone2("34").withNotes("kak dela?")
              .inGroup(groups.iterator().next())
//                      .withNewGroup(groups.stream().map((g) -> g.getName()).findAny().get())
              .withPhoto(photo), true);

    }
    app.goTo().StartPage();
  }

  @Test
  public void testDeleteContactFromGroup() throws Exception {

    Groups groups = new Groups();
    GroupDate modifyGroup = new GroupDate();
    ContactDate modifyContact = new ContactDate();
    ContactDate modContact = new ContactDate();
    int modContactId;
    int modGroupId = 0;

    Contacts contact = app.db().contacts();  //получили все контакты из базы

    // ищем контакт у которого есть группа
    for (ContactDate cont : contact) {
      groups = cont.getGroups();
      modContact = cont;
      if (cont.getGroups().size() != 0) {
        modifyGroup = cont.getGroups().iterator().next();
        modGroupId = cont.getGroups().iterator().next().getId();
        modifyContact = cont;
      }
    }
    if (modGroupId == 0) {
      //если у контактов групп нет, то добавить последний контакт в любую группу
      app.goTo().StartPage();
      modifyContact=modContact;
      modifyGroup=app.db().groups().iterator().next();
      app.contact().addToGroup(modifyContact, modifyGroup);

    }
    ContactDate beforeContact = modifyContact;
    app.contact().deleteFromGroup(modifyContact, modifyGroup);

    //сравнение
    contact = app.db().contacts();
    //здесь нужно получить контакт по Id modifyContact
    //MatcherAssert.assertThat(beforeContact, CoreMatchers.equalTo(тут обновленный контакт inGroup(modifyGroup));


  }
}
