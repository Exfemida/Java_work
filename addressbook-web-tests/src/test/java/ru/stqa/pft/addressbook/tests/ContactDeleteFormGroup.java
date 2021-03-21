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

    // Groups before=app.db().groups();
    // GroupDate group = before.iterator().next();

    Contacts contact = app.db().contacts();  //получили все контакты из базы
    Groups groups = new Groups();
    GroupDate modGroup= new GroupDate();
    for (ContactDate cont : contact) {
      groups = cont.getGroups();
      System.out.print(cont.getId());
      System.out.println(groups);
      if (cont.getGroups().size() != 0) {
        modGroup = cont.getGroups().iterator().next();
      }

    }

  }
}
