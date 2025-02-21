package org.chat_app.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.chat_app.entity.User;
import org.chat_app.service.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/register")
    public User register(User user) {
        return userService.register(user);
    }

    @POST
    @Path("/login")
    public User login(@QueryParam("username") String username, @QueryParam("password") String password) {
        return userService.login(username, password);
    }
}