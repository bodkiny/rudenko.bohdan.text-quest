package ua.javarush.textquest.service;

import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.domain.User;
import ua.javarush.textquest.exception.BadCredentialsException;
import ua.javarush.textquest.repository.Repository;

@Log4j2
public class UserService {
    Repository<User> userRepository;

    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @SuppressWarnings({"java:S3655", "OptionalGetWithoutIsPresent"})
    public User processLogIn(String userName, String password) {
        if(userIsPresent(userName)){
            User user = userRepository.findBy(userName).get();
            if(passwordIsValid(password, user)){
                return user;
            } else {
                LOGGER.warn("Attempt to log in with a wrong password into account with name {}", userName);
                throw new BadCredentialsException("Bad credentials or there's no such user");
            }
        } else {
            LOGGER.warn("Attempt to log in with a non-existing account name {}", userName);
            throw new BadCredentialsException("Bad credentials or there's no such user");
        }
    }

    private static boolean passwordIsValid(String password, User user) {
        return user.getPassword().equals(password);
    }

    private boolean userIsPresent(String userName) {
        return userRepository.findBy(userName).isPresent();
    }
}
