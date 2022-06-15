package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.Collection;

@ThreadSafe
@Service
public class AccidentTypeService {

    private final AccidentTypeMem accidentTypeStore;

    public AccidentTypeService(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeStore = accidentTypeMem;
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return accidentTypeStore.getAccidentTypes();
    }

    public AccidentType getByID(int id) {
        return accidentTypeStore.getByID(id);
    }
}
