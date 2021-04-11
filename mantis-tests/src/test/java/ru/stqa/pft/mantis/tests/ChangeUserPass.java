package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersDate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ChangeUserPass extends TestBase {

  @Test
  public void ChangeUserPassword() throws SQLException {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id , username, email, password from mantis_user_table");
      Users users = new Users();
      while (rs.next()) {
        users.add(new UsersDate().withId(rs.getInt("id")).withUsername(rs.getString("username"))
                .withEmail(rs.getString("email")).withPassword(rs.getString("password")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(users);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

}
