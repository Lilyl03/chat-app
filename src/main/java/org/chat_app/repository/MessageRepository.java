package org.chat_app.repository;

import jakarta.inject.Inject;
import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.persistence.EntityManager;
import org.chat_app.entity.Message;
import org.chat_app.entity.Message$;
import org.chat_app.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MessageRepository {

    @Inject
    EntityManager em;

    @Inject
    UserRepository userRepository;

    @Inject
    JPAStreamer jpaStreamer;

    public Message save(Message message) {
        em.persist(message);
        return message;
    }

    public List<Message> findByReceiver(User receiver) {
        return jpaStreamer.stream(Message.class)
                .filter(m -> m.getReceiver().getUserid() == receiver.getUserid())
                .toList();
    }


}