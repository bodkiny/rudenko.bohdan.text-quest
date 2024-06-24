package ua.javarush.textquest.controller;

import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.dispatcher.RequestMapping;
import ua.javarush.textquest.domain.Question;
import ua.javarush.textquest.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class GameController {
    private final Repository<Question> questionRepository;

    private static final String START_QUESTION_ID = "0";

    public GameController(Repository<Question> questionRepository) {
        this.questionRepository = questionRepository;
    }

    @RequestMapping(url = "/question")
    public void processGame(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nextQuestionId = request.getParameter("nextQuestionId");
            if (!parameterIsPresent(nextQuestionId)) {
                nextQuestionId = START_QUESTION_ID;
            }

            int id = Integer.parseInt(nextQuestionId);
            Optional<Question> questionOptional = questionRepository.read(id);
            if (questionOptional.isPresent()) {
                Question question = questionOptional.get();
                request.setAttribute("question", question);
                if (question.isFinal()) {
                    request.getRequestDispatcher("/final.jsp").forward(request, response);
                } else if (question.isLoose()) {
                    request.getRequestDispatcher("/loose.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/question.jsp").forward(request, response);
                }
            }
        } catch (ServletException e) {
            LOGGER.error("ServletException occurred while processing game. Exception: {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException occurred while processing game. Exception: {}", e.getMessage());
        }
    }

    private static boolean parameterIsPresent(String answerId) {
        return answerId != null && !answerId.isBlank();
    }
}
