package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersDate;

@Test
public class ChangeUserPass extends TestBase{
  Users users=app.db().users();

}
