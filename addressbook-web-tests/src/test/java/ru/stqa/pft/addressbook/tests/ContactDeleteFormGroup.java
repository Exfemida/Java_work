package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.List;

public class ContactDeleteFormGroup extends TestBase {

  @Test
  public void testDeleteContactFromGroup() throws Exception {

    GroupDate modGroup = new GroupDate();
    ContactDate modContact = new ContactDate();
    int modContactId;
    int modGroupId = 0;

    Contacts contact = app.db().contacts();  //получили все контакты из базы
    Groups groups = new Groups();

    for (ContactDate cont : contact) {
      groups = cont.getGroups();
    //  System.out.print(cont.getId());
    //  System.out.println(groups);
      modContact = cont;
    //  modContactId = cont.getId();
      if (cont.getGroups().size() != 0) {
        modGroup = cont.getGroups().iterator().next();
        modGroupId = cont.getGroups().iterator().next().getId();
    //    System.out.print(cont.getId());
    //    System.out.println(groups);
      }
    }
    if (modGroupId == 0) {
      //добавить в последний контакт в любую группу
      app.goTo().StartPage();
      app.contact().addToGroup(modContact, app.db().groups().iterator().next());
    }
  }
}
