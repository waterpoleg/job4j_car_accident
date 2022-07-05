package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

/*
    @Repository
*/
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(Function<Session, T> function) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            T toReturn = function.apply(session);
            session.getTransaction().commit();
            return toReturn;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident create(Accident accident) {
        tx(session -> session.save(accident));
        return accident;
    }

    public void save(Accident accident) {
        tx(session -> session.merge(accident));
    }

    public List<Accident> getAccidents() {
        return tx(session -> session
                .createQuery("select distinct a from Accident a "
                        + "left join fetch a.rules", Accident.class)
                .getResultList());
    }

    public Accident getByID(int id) {
        return tx(session -> session
                .createQuery("select distinct a from Accident a "
                        + "left join fetch a.rules where a.id = :id", Accident.class)
                .setParameter("id", id)
                .getSingleResult()
        );
    }

    public List<Rule> getRules() {
        return tx(session -> session
                .createQuery("select r from Rule r", Rule.class)
                .getResultList());
    }

    public List<AccidentType> getAccidentTypes() {
        return tx(session -> session
                .createQuery("select t from AccidentType t", AccidentType.class)
                .getResultList());
    }

    public AccidentType getAccidentTypeById(int id) {
        return tx(session -> session.get(AccidentType.class, id));
    }

    public Rule getRuleByID(int id) {
        return tx(session -> session.get(Rule.class, id));
    }
}
