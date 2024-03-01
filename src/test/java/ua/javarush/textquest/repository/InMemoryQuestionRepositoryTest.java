package ua.javarush.textquest.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.javarush.textquest.domain.Answer;
import ua.javarush.textquest.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryQuestionRepositoryTest {
    static final String TEST_GAME_CONFIG_FILE_PATH = "test-game-config.json";

    private Repository<Question> repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryQuestionRepository(TEST_GAME_CONFIG_FILE_PATH);
    }

    @Test
    @DisplayName("Repository should be initialized with correct data in case configuration file is presented and correct")
    void shouldInitializeWithCorrectData() {
        Map<Integer, Question> expectedIdToQuestions = getExpectedIdToQuestions();

        repository.findAll().forEach(question -> assertEquals(expectedIdToQuestions.get(question.getId()), question, "Question with id " + question.getId() + " is not equal to expected"));
    }

    @Test
    @DisplayName("Create should add question to repository")
    void createAddsQuestionToRepository() {
        Question question = new Question(
                List.of(new Answer(45, 56, "Some random answer text")),
                77,
                "Sample text of question 77",
                true,
                false);

        Question result = repository.create(question);

        assertEquals(question, result);
        Optional<Question> createdQuestion = repository.read(question.getId());
        assertTrue(createdQuestion.isPresent());
        assertEquals(question, createdQuestion.get());
    }

    @Test
    @DisplayName("Read should return question if present")
    void readReturnsQuestionIfPresent() {
        Optional<Question> result = repository.read(0);

        assertTrue(result.isPresent());
        assertEquals("Sample text of question 0", result.get().getText());
    }

    @Test
    @DisplayName("Read should return empty if question not present")
    void readReturnsEmptyIfQuestionNotPresent() {
        Optional<Question> result = repository.read(100);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Update should return updated question if present")
    void updateReturnsUpdatedQuestionIfPresent() {
        Question question = new Question(new ArrayList<>(), 0, "", false, false);

        question.setText("Updated text");

        Question result = repository.update(question);

        assertEquals(question, result);
        Optional<Question> updatedQuestion = repository.read(0);
        assertTrue(updatedQuestion.isPresent());
        assertEquals("Updated text", updatedQuestion.get().getText());
    }

    @Test
    @DisplayName("Update should return null if question not present")
    void updateReturnsNullIfQuestionNotPresent() {
        Question question = new Question(null, 100, "", false, false);

        Question result = repository.update(question);

        assertNull(result);
    }

    @Test
    @DisplayName("Delete should remove question from repository")
    void deleteRemovesQuestionFromRepository() {
        repository.delete(0);

        Optional<Question> deletedQuestion = repository.read(0);
        assertFalse(deletedQuestion.isPresent());
    }

    @Test
    @DisplayName("FindAll should return all questions")
    void findAllReturnsAllQuestions() {
        List<Question> questions = repository.findAll();

        assertFalse(questions.isEmpty());
    }

    @Test
    @DisplayName("FindBy should return question if present")
    void findByReturnsQuestionIfPresent() {
        Optional<Question> result = repository.findBy("0");

        assertTrue(result.isPresent());
        assertEquals("Sample text of question 0", result.get().getText());
    }

    @Test
    @DisplayName("FindBy should return empty if question not present")
    void findByReturnsEmptyIfQuestionNotPresent() {
        Optional<Question> result = repository.findBy("100");

        assertFalse(result.isPresent());
    }

    private Map<Integer, Question> getExpectedIdToQuestions() {
        List<Answer> answers = List.of(
                new Answer(1, 1, "Sample answer 1"),
                new Answer(2, 2, "Sample answer 2"),
                new Answer(3, 3, "Sample answer 3")
        );

        HashMap<Integer, Question> idToQuestion = new HashMap<>();

        idToQuestion.put(0, new Question(answers, 0, "Sample text of question 0", false, false));
        idToQuestion.put(1, new Question(new ArrayList<>(), 1, "Sample text of question 1", false, true));
        idToQuestion.put(2, new Question(new ArrayList<>(), 2, "Sample text of question 2", false, true));
        idToQuestion.put(3, new Question(new ArrayList<>(), 3, "Sample text of question 3", true, false));

        return idToQuestion;
    }
}
