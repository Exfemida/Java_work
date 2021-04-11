package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.sql.*;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersDate;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() throws SQLException {
    Connection conn = null;
    try {
      //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
      Statement st = conn.createStatement();
//      ResultSet rs = st.executeQuery("select group_id , group_name, group_header, group_footer from group_list");
      ResultSet rs = st.executeQuery("select id , username, email, password from mantis_user_table");
      //     Groups groups = new Groups();
      Users users = new Users();
      //     while (rs.next()){
      //       groups.add (new GroupDate().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
      //               .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
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
