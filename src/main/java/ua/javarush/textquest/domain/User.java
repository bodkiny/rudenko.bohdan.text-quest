package ua.javarush.textquest.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private static int counter = 0;
    private int id;
    private String name;
    private String password;

    public User(String name, String password) {
        this.id = counter;
        counter++;
        this.name = name;
        this.password = password;
    }
}
