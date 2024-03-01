package ua.javarush.textquest.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public final class JsonQuestionParser {

    private JsonQuestionParser() {
    }

    public static Map<Integer, Question> parse(String path) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();

        Map<Integer, Question> idToQuestions = new HashMap<>();

        try (InputStream inputStream = JsonQuestionParser.class.getClassLoader().getResourceAsStream(path);
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            List<Question> questions = gson.fromJson(reader, type);
            questions.forEach(question -> idToQuestions.put(question.getId(), question));
        } catch (NullPointerException e){
            LOGGER.error("Input stream is null. {}", e.getMessage());
        } catch (JsonIOException  | JsonSyntaxException e) {
            LOGGER.warn("Json exception while reading game config file. {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error while trying to read game config file. {}", e.getMessage());
        }

        return idToQuestions;
    }
}