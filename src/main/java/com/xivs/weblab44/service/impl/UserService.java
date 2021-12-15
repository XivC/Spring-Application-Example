package com.xivs.weblab44.service.impl;

import com.xivs.weblab44.model.User;
import com.xivs.weblab44.repository.UserRepository;
import com.xivs.weblab44.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    public UserService(){

        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean register(User user) {
        if(user.getLogin() == null) return false;
        if(userRepository.findByLogin(user.getLogin()) != null) return false;

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;

    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public User findByLogin(String login) {

        return userRepository.findByLogin(login);
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }
}
