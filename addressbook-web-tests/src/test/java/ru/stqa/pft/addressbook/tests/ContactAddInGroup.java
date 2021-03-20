package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddInGroup extends TestBase {

  @Test
  public void testAddContactInGroup() throws Exception {
    Contacts contact = app.db().contacts();  //получили все контакты из базы
    ContactDate modifyContact = contact.iterator().next(); //выбрали контакт который будем добавлять в группу
    ContactDate oldcontact = modifyContact; //контакт до добавления группы
    System.out.println(modifyContact.getGroups());

     GroupDate newGroupForAdd = new GroupDate();
    //если группы из базы нет в списке групп контакта, то будем добавлять контакт в эту группу
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
      }
    }

    //переименовываем группу для уникальности имени (использ при выборе)
    Groups temp = app.db().groups();
    int max = 0;
    for (GroupDate g : temp) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    newGroupForAdd.withId(max).withName("nametest " + max).withHeader("headertest " + max).withFooter("footertest " + max);
    app.group().modify(newGroupForAdd);

    //добавляем контакт в группу
    app.goTo().StartPage();
    app.contact().addToGroup(modifyContact, newGroupForAdd);

  }
}

