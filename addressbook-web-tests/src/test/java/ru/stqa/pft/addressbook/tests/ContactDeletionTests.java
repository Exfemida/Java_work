package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    String numStr="15";                       //номер строки для удаления
    app.getContactHelper().selectContact(numStr);
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlertDelete();
    app.getNavigationHelper().gotoStartPage();
  }
}
