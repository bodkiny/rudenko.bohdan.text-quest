package ua.javarush.textquest.repository;

import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
public class InMemoryUserRepository implements Repository<User> {
    private final Map<Integer, User> idToUsers;

    public InMemoryUserRepository() {
        idToUsers = new HashMap<>();
    }
    @Override
    public User create(User user) {
        return idToUsers.put(user.getId(), user);
    }

    @Override
    public Optional<User> read(int id) {
        return Optional.ofNullable(idToUsers.get(id));
    }

    @Override
    public User update(User user) {
        if(idToUsers.containsKey(user.getId())){
            return idToUsers.put(user.getId(), user);
        } else {
            LOGGER.warn("User with id {} was not found to update", user.getId());
            return null;
        }
    }

    @Override
    public void delete(int id) {
        idToUsers.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(idToUsers.values());
    }

    @Override
    public Optional<User> findBy(String name) {
        return idToUsers.values().stream().
                filter(user -> user.getName().equals(name))
                .findFirst();
    }
}
