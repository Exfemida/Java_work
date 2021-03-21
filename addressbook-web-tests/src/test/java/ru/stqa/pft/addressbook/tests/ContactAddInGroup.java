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


public class ContactAddInGroup extends TestBase {

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
  public void testAddContactInGroup() throws Exception {

    Contacts contact = app.db().contacts();  //получили все контакты из базы
    ContactDate modifyContact = contact.iterator().next(); //выбрали контакт который будем добавлять в группу
    ContactDate oldcontact = modifyContact; //контакт до добавления группы
    System.out.println(modifyContact.getGroups());

    GroupDate newGroupForAdd = new GroupDate();
    //если группы из базы нет в списке групп контакта, то будем добавлять контакт в эту группу
    int flag = 0;
    while (newGroupForAdd.getName() == null) {
      Groups groups = app.db().groups(); //получили все группы из базы
      int i = 0;
      for (GroupDate group : groups) {
        i = 0;
        for (GroupDate groupModifyContact : modifyContact.getGroups()) {
          if (group.getId() == groupModifyContact.getId()) {
            i = 1;
          }
        }
        if (i == 0) {
          newGroupForAdd.withId(group.getId()).withName(group.getName())  //если находим группу к которую не входит контакт, то запоминаем ее
                  .withFooter(group.getFooter()).withHeader(group.getHeader());
        }
      }
      // если свободной группы нет, создаем ее
      if (newGroupForAdd.getName() == null) {
        app.goTo().groupPage();
        newGroupForAdd.withName("nametest 1").withHeader("headertest 1").withFooter("footertest 1");
        app.group().create(newGroupForAdd);
        flag = 1;
      }
    }

    //переименовываем созданную группу для уникальности имени (использ при выборе)
    if (flag==1) {
      Groups temp = app.db().groups();
      int max = 0;
      for (GroupDate g : temp) {
        if (g.getId() > max) {
          max = g.getId();
        }
      }
      newGroupForAdd.withId(max).withName("nametest " + max).withHeader("headertest " + max).withFooter("footertest " + max);
      app.group().modify(newGroupForAdd);
    }

    //добавляем контакт в группу
    app.goTo().StartPage();
    app.contact().addToGroup(modifyContact, newGroupForAdd);

    oldcontact.getGroups();
    Groups old = oldcontact.inGroup(newGroupForAdd).getGroups();

    Contacts temp = app.db().contacts();

//    Groups nev = temp.stream().map((g) ->g.getGroups());


    MatcherAssert.assertThat(oldcontact, CoreMatchers.equalTo(oldcontact.inGroup(newGroupForAdd)));


  }
}

