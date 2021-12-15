package com.xivs.weblab44.service;

import com.xivs.weblab44.model.User;

import java.util.List;

public interface IUserService {
    boolean register(User user);
    List<User> getAll();
    User findByLogin(String login);
    User findById(Long id);
}
