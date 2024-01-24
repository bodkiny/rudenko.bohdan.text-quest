package ua.javarush.textquest.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.javarush.textquest.domain.Answer;
import ua.javarush.textquest.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryJsonQuestionRepositoryTest {
    static final String TEST_GAME_CONFIG_FILE_PATH = "src/test/resources/test-game-config.json";

    @Test
    @DisplayName("Repository should be initialized with correct data in case configuration file is presented and correct")
    void shouldInitializeWithCorrectData() {
        Map<Integer, Question> expectedIdToQuestions = getExpectedIdToQuestions();

        Repository<Question> repository = new InMemoryJsonQuestionRepository(TEST_GAME_CONFIG_FILE_PATH);

        repository.findAll().forEach(question -> assertEquals(expectedIdToQuestions.get(question.getId()), question, "Question with id " + question.getId() + " is not equal to expected"));
    }

    private Map<Integer, Question> getExpectedIdToQuestions() {
        List<Answer> answers = List.of(
                new Answer(1, 1, "Sample answer 1"),
                new Answer(1, 2, "Sample answer 2"),
                new Answer(1, 2, "Sample answer 3")
        );

        HashMap<Integer, Question> idToQuestion = new HashMap<>();

        idToQuestion.put(0, new Question(answers, 0, "Sample text of question 0", false, false));
        idToQuestion.put(1, new Question(new ArrayList<>(), 1, "Sample text of question 1", false, true));
        idToQuestion.put(2, new Question(new ArrayList<>(), 2, "Sample text of question 2", false, true));
        idToQuestion.put(3, new Question(new ArrayList<>(), 3, "Sample text of question 3", true, false));

        return idToQuestion;
    }
}
