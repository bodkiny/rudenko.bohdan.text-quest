package ua.javarush.textquest.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Answer {
    private int id;
    private int nextQuestionId;
    private String text;
}
