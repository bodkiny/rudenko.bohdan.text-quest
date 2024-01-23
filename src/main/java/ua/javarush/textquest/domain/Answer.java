package ua.javarush.textquest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private int id;
    private int nextQuestionId;
    private String text;
}
