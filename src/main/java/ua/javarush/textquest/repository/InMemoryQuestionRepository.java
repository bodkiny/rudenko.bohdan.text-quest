package ua.javarush.textquest.repository;

import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.domain.Question;
import ua.javarush.textquest.util.JsonQuestionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
public class InMemoryQuestionRepository implements Repository<Question> {
    private final Map<Integer, Question> idToQuestions;

    public InMemoryQuestionRepository(String filePath) {
        idToQuestions = JsonQuestionParser.parse(filePath);
    }

    @Override
    public Question create(Question question) {
        idToQuestions.put(question.getId(), question);
        return question;
    }

    @Override
    public Optional<Question> read(int id) {
        return Optional.ofNullable(idToQuestions.get(id));
    }

    @Override
    public Question update(Question question) {
        if(idToQuestions.containsKey(question.getId())){
            idToQuestions.put(question.getId(), question);
            return question;
        } else {
            LOGGER.warn("Question with id {} was not found to update", question.getId());
            return null;
        }
    }

    @Override
    public void delete(int id) {
        idToQuestions.remove(id);
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(idToQuestions.values());
    }

    @Override
    public Optional<Question> findBy(String id) {
        return read(Integer.parseInt(id));
    }
}
