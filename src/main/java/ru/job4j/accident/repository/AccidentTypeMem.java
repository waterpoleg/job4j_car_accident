package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentTypeMem {

    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        accidentTypes.put(1, AccidentType.of(1, "Car & Car"));
        accidentTypes.put(2, AccidentType.of(2, "Car & Man"));
        accidentTypes.put(3, AccidentType.of(3, "Car & Bicycle"));
    }

    public Collection<AccidentType> getAccidentTypes() {
        return accidentTypes.values();
    }

    public AccidentType getByID(int id) {
        return accidentTypes.get(id);
    }
}
