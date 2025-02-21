package org.chat_app.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.chat_app.entity.User;
import org.chat_app.entity.User$;

import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepository {
    @Inject
    JPAStreamer jpaStreamer;
    @Inject
    EntityManager entityManager;

    public Optional<User> findByUsernameAndPassword(String username, String password){
        return jpaStreamer.stream(User.class)
                .filter(User$.username.equal(username))
                .filter(User$.password.equal(password))
                .findFirst();
    }

    public Optional<User> findByUserId(long id) {
        return jpaStreamer.stream(User.class)
                .filter(User$.userid.equal(id))
                .findFirst();
    }
        @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
}
