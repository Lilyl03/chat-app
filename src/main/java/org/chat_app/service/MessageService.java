package org.chat_app.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.chat_app.entity.Message;
import org.chat_app.entity.User;
import org.chat_app.repository.MessageRepository;

import java.util.List;

@ApplicationScoped
public class MessageService {

    @Inject
    MessageRepository messageRepository;

    @Transactional
    public Message sendMessage(Message message) {

        return messageRepository.save(message);
    }

    public List<Message> getMessagesForUser(User user) {
        return messageRepository.findByReceiver(user);
    }
}