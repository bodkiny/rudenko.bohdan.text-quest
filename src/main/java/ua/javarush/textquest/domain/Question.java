package ua.javarush.textquest.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Question {
    private List<Answer> answers;
    private int id;
    private String text;
    private boolean isFinal;
    private boolean isLoose;
}
