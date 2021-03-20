package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;


public class ContactAddInGroup extends TestBase {

  @Test
  public void testAddContactInGroup() {
    Contacts before = app.db().contacts();  //получили все контакты из базы
    ContactDate modifyContact = before.iterator().next(); //выбрали контакт который будем добавлять в группу
    System.out.println(modifyContact.getGroups());

    int newGroupId = 0;
    //если группы из базы нет в списке групп контакта, то будем добавлять контакт в эту группу
    while (newGroupId == 0) {
      Groups groups = app.db().groups(); //получили все группы из базы
      for (GroupDate group : groups) {
        boolean i = false;
        for (GroupDate groupModifyContact : modifyContact.getGroups()) {
          if (group.getId() == groupModifyContact.getId()) {
            i = true;
          }
        }
        if (i == false) {
          System.out.println(group.getId() + " berem");  //удалить
          newGroupId = group.getId();
        }
      }
      // если свободной группы нет, создаем ее
      if (newGroupId == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupDate().withName("nametest 1").withHeader("headertest 1").withFooter("footertest 1"));
      }
    }
    


  }
}

