package ru.stqa.pft.mantis.tests;

public class RegistrationTests extends TestBase{

  public void testRegistration(){
    app.registration().start("user1", "user1.localhost.localdomain");

  }
}
