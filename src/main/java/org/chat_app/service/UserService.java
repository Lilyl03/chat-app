package org.chat_app.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.chat_app.entity.User;
import org.chat_app.repository.UserRepository;

import java.time.Year;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public User register(User user) {
        userRepository.save(user);
        return user;
    }

    public User login(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username,password);
        return user.orElse(null);
    }

    public User findById(long userId){
        Optional<User> user = userRepository.findByUserId(userId);
        return user.
                orElse(null);
    }
}