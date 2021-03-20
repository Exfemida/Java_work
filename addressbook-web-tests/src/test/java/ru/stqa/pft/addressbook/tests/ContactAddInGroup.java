package ru.stqa.pft.addressbook.tests;

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

 //   int newGroupId = 0;
 //   String newGroupName=null;
    GroupDate newGroupForAdd=new GroupDate();

    //если группы из базы нет в списке групп контакта, то будем добавлять контакт в эту группу
    while (newGroupForAdd.getName()== null) {
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
          newGroupForAdd.withId(group.getId());
          newGroupForAdd.withName(group.getName());
        }
      }
      // если свободной группы нет, создаем ее
      if (newGroupForAdd.getName()== null) {
        app.goTo().groupPage();
        newGroupForAdd.withName("nametest 1").withHeader("headertest 1").withFooter("footertest 1");
        app.group().create(newGroupForAdd);
      }
    }

    app.goTo().StartPage();
    app.contact().addToGroup(modifyContact,newGroupForAdd);
  //  newGroupForAdd.getId()=0;


  }
}

