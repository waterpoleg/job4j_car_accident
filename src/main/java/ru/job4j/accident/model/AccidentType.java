package ru.job4j.accident.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accident_type")
public class AccidentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }
}
