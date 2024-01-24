package ua.javarush.textquest.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.domain.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public final class JsonQuestionParser {

    private JsonQuestionParser() {
    }

    public static Map<Integer, Question> parse(String path) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<Integer, Question>>() {
        }.getType();

        Map<Integer, Question> idToQuestions = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            idToQuestions = gson.fromJson(reader, type);
        } catch (JsonIOException  | JsonSyntaxException e) {
            LOGGER.warn("Json exception while reading game config file. {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error while trying to read game config file. {}", e.getMessage());
        }

        return idToQuestions;
    }
}