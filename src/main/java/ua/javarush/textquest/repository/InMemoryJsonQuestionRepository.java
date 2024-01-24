package ua.javarush.textquest.repository;

import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.domain.Question;
import ua.javarush.textquest.util.JsonQuestionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
public class InMemoryJsonQuestionRepository implements Repository<Question> {
    private final Map<Integer, Question> idToQuestions;

    public InMemoryJsonQuestionRepository(String filePath) {
        idToQuestions = JsonQuestionParser.parse(filePath);
    }

    @Override
    public Question create(Question question) {
        return idToQuestions.put(question.getId(), question);
    }

    @Override
    public Optional<Question> read(int id) {
        return Optional.ofNullable(idToQuestions.get(id));
    }

    @Override
    public Question update(Question question) {
        if(idToQuestions.containsKey(question.getId())){
            return idToQuestions.put(question.getId(), question);
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
