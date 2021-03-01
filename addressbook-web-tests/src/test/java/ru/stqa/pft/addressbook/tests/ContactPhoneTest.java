package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactPhoneTest extends TestBase {
  public void testContactPhones(){
    app.goTo().StartPage();
    ContactDate contact =app.contact().all().iterator().next();
    ContactDate contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
  }
}
