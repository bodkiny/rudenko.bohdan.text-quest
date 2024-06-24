package ua.javarush.textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.javarush.textquest.domain.User;
import ua.javarush.textquest.exception.BadCredentialsException;
import ua.javarush.textquest.repository.Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private Repository<User> userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void processLogIn_withValidCredentials_returnsUser() {
        User user = new User("username", "password");

        when(userRepository.findBy("username")).thenReturn(Optional.of(user));

        User result = userService.processLogIn("username", "password");

        assertEquals(user, result);
    }

    @Test
    void processLogIn_withInvalidPassword_throwsException() {
        User user = new User("username", "password");

        when(userRepository.findBy("username")).thenReturn(Optional.of(user));

        assertThrows(BadCredentialsException.class, () -> userService.processLogIn("username", "wrongPassword"));
    }

    @Test
    void processLogIn_withNonExistingUsername_throwsException() {
        when(userRepository.findBy("username")).thenReturn(Optional.empty());

        assertThrows(BadCredentialsException.class, () -> userService.processLogIn("username", "password"));
    }
}
