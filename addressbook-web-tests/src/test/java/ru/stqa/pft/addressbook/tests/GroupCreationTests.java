package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupDate> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupDate("test1", null, null));
    List<GroupDate> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()+1);
  }
}
