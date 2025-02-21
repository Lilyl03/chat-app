package org.chat_app.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.chat_app.entity.Message;
import org.chat_app.entity.User;
import org.chat_app.repository.UserRepository;
import org.chat_app.service.MessageService;
import org.chat_app.service.UserService;

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
    public Message sendMessage(@QueryParam("senderId") Long senderId, @QueryParam("receiverId") Long receiverId, @QueryParam("text") String text) {
        User sender = userService.findById(senderId);
        if (sender == null) {
            throw new WebApplicationException("Sender not found", 404);
        }

        User receiver = userService.findById(receiverId);
        if (receiver == null) {
            throw new WebApplicationException("Receiver not found", 404);
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(text);
        return messageService.sendMessage(message);
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