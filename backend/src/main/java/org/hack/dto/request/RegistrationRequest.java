package org.hack.dto.request;

public record RegistrationRequest (String username,
                                   String email,
                                   String password,
                                   String confirmPassword) {}
