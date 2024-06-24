package ua.javarush.textquest.injector;

import ua.javarush.textquest.controller.GameController;
import ua.javarush.textquest.dispatcher.MethodMap;
import ua.javarush.textquest.dispatcher.RegisterForControllers;
import ua.javarush.textquest.domain.Question;
import ua.javarush.textquest.domain.User;
import ua.javarush.textquest.repository.InMemoryQuestionRepository;
import ua.javarush.textquest.repository.InMemoryUserRepository;
import ua.javarush.textquest.repository.Repository;
import ua.javarush.textquest.service.UserService;

import java.util.Map;

public class ApplicationContext {
    private static final Repository<User> USER_REPOSITORY = new InMemoryUserRepository();
    private static final UserService USER_SERVICE = new UserService(USER_REPOSITORY);
    private static final String GAME_CONFIG_PATH = "game.json";
    private static final Repository<Question> QUESTION_REPOSITORY = new InMemoryQuestionRepository(GAME_CONFIG_PATH);

    private static final GameController GAME_CONTROLLER = new GameController(QUESTION_REPOSITORY);

    public static Map<String, MethodMap> URL_TO_METHOD() {
        return new RegisterForControllers().register(GAME_CONTROLLER);
    }

    private ApplicationContext() {
    }
}
