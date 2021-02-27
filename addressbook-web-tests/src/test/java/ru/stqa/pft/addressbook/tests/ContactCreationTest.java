package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test (enabled = false)
  public void testCreateNewContact() throws Exception {

    app.getNavigationHelper().gotoGroupPage();                    //проверка наличия в базе созданных групп
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
    }

    app.getNavigationHelper().gotoStartPage();
    List<Contacts> before = app.getContactHelper().getContactsList();
    app.getNavigationHelper().gotoAddContactPage();
    Contacts cont = new Contacts("Maria", "Sergeevna", "Ivanova", "Mashka", "do not know", "Rosneft", "Moskwa, 6", "2222222", "3333333", "4444444", "5555555", "email_1", "email_2", "email_3", "mashka.ru", "8", "May", "1982", "12", "September", "2004", "test1", "Kiev", "34", "kak dela?");
    app.getContactHelper().createContact(cont, true);
    //  app.getNavigationHelper().gotoAddContactPage();
    app.getNavigationHelper().gotoStartPage();
    List<Contacts> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    //поиск(возврат) элемента cont из списка after с максимальным id
    cont.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(cont);

    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
