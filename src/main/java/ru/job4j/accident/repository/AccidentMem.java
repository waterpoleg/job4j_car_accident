package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        var newId = id.incrementAndGet();
        accident.setId(newId);
        accidents.put(newId, accident);
    }

    public void save(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident getByID(int id) {
        return accidents.get(id);
    }
}
