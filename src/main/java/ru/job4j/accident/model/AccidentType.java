package ru.job4j.accident.model;

import lombok.Data;

@Data
public class AccidentType {
    private int id;
    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }
}
