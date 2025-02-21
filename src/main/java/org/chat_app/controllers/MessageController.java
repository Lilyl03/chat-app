package org.chat_app.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.chat_app.entity.Message;
import org.chat_app.entity.User;
import org.chat_app.repository.UserRepository;
import org.chat_app.service.MessageService;
import org.chat_app.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {

    @Inject
    MessageService messageService;

    @Inject
    UserService userService;


    @POST
    @Path("/send")
    public List<Message> sendMessage(@QueryParam("senderId") Long senderId, @QueryParam("receiverIds") List<Long> receiverIds, @QueryParam("text") String text) {
        User sender = userService.findById(senderId);
        if (sender == null) {
            throw new WebApplicationException("Sender not found", 404);
        }

        List<Message> sentMessages = new ArrayList<>();

        for (Long receiverId : receiverIds) {
            User receiver = userService.findById(receiverId);
            if (receiver == null) {
                continue; // Skip if receiver not found
            }

            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setContent(text);
            sentMessages.add(messageService.sendMessage(message));
        }
        return sentMessages;
    }
    @GET
    @Path("/{userId}")
    public List<Message> getMessages(@PathParam("userId") Long userId) {
        User user =userService.findById(userId);
        if (user == null) {
            throw new WebApplicationException("User not found", 404);
        }
        return messageService.getMessagesForUser(user);
    }
}