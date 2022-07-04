package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.*;

/*
* @Repository
* */
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident create(Accident accident) {
        String insertSQL = "insert into accident (name, text, address, type) "
                + "values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSQL, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((int) keyHolder.getKey());
        setRulesToAccident(accident);
        return accident;
    }

    public Accident save(Accident accident) {
        jdbc.update("update accident set name=?, text=?, address=?, type=? where id=?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        setRulesToAccident(accident);
        return accident;
    }

    public List<Accident> getAccidents() {
        return jdbc.query("select id, name, text, address, type from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getAccidentTypeById(rs.getInt("type")));
                    accident.setRules(getRulesByAccidentId(rs.getInt("id")));
                    return accident;
                });
    }

    public Accident getByID(int id) {
        return jdbc.queryForObject(
                "select id, name, text, address, type from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(id);
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getAccidentTypeById(rs.getInt("type")));
                    accident.setRules(getRulesByAccidentId(rs.getInt("id")));
                    return accident;
                }, id);
    }

    public Rule getRuleByID(int id) {
        return jdbc.queryForObject(
                "select id, name from rule where id=?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }

    public Set<Rule> getRulesByAccidentId(int accidentId) {
        return new HashSet<>(
                jdbc.query("select * from rule r "
                                + "join accident_rule ar on r.id = ar.rule_id"
                                + " where ar.accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, accidentId)
        );
    }

    public void setRulesToAccident(Accident accident) {
        var rules = accident.getRules();
        for (var rule: rules) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
    }

    public AccidentType getAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "select id, name from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(id);
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id);
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Collection<AccidentType> getAccidentTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }
}
