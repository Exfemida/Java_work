package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UsersDate> {
  private Set<UsersDate> delegate;

  public Users(Users users) {
    this.delegate=new HashSet<UsersDate>(users.delegate);
  }

  public Users() {
    this.delegate=new HashSet<UsersDate>();
  }

  public Users(Collection<UsersDate> users) {
    this.delegate=new HashSet<UsersDate>(users);
  }


  @Override
  protected Set<UsersDate> delegate() {
    return delegate;
  }
}


