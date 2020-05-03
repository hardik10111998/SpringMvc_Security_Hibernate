package com.hardik.dao;

import com.hardik.model.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
