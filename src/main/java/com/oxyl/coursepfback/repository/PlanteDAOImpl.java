package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.core.model.Plante;
import com.oxyl.coursepfback.core.model.Effet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PlanteDAOImpl implements PlanteDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Plante> planteRowMapper = (rs, rowNum) -> {
        Plante plante = new Plante();
        plante.setIdPlante(rs.getLong("id_plante"));
        plante.setNom(rs.getString("nom"));
        plante.setPointDeVie(rs.getInt("point_de_vie"));
        plante.setAttaqueParSeconde(rs.getDouble("attaque_par_seconde"));
        plante.setDegatAttaque(rs.getInt("degat_attaque"));
        plante.setCout(rs.getInt("cout"));
        plante.setSoleilParSeconde(rs.getDouble("soleil_par_seconde"));

        // Conversion de la chaîne en enum Effet
        String effetStr = rs.getString("effet");
        plante.setEffet(mapStringToEffet(effetStr));

        plante.setCheminImage(rs.getString("chemin_image"));
        return plante;
    };

    @Autowired
    public PlanteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Plante> findAll() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, planteRowMapper);
    }

    @Override
    public Optional<Plante> findById(Long id) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        List<Plante> plantes = jdbcTemplate.query(sql, planteRowMapper, id);
        return plantes.isEmpty() ? Optional.empty() : Optional.of(plantes.get(0));
    }

    @Override
    public List<Plante> findByEffet(String effet) {
        String sql = "SELECT * FROM plante WHERE effet = ?";
        return jdbcTemplate.query(sql, planteRowMapper, effet);
    }

    @Override
    public Plante save(Plante plante) {
        String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plante.getNom());
            ps.setInt(2, plante.getPointDeVie());
            ps.setDouble(3, plante.getAttaqueParSeconde());
            ps.setInt(4, plante.getDegatAttaque());
            ps.setInt(5, plante.getCout());
            ps.setDouble(6, plante.getSoleilParSeconde());
            ps.setString(7, mapEffetToString(plante.getEffet()));
            ps.setString(8, plante.getCheminImage());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            plante.setIdPlante(key.longValue());
        }

        return plante;
    }

    @Override
    public void update(Plante plante) {
        String sql = "UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?";
        jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPointDeVie(),
                plante.getAttaqueParSeconde(),
                plante.getDegatAttaque(),
                plante.getCout(),
                plante.getSoleilParSeconde(),
                mapEffetToString(plante.getEffet()),
                plante.getCheminImage(),
                plante.getIdPlante()
        );
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id);
    }

    // Méthode utilitaire pour convertir une chaîne de caractères de la base de données en enum Effet
    private Effet mapStringToEffet(String effetStr) {
        if (effetStr == null) {
            return Effet.NORMAL; // Valeur par défaut
        }

        switch (effetStr.toUpperCase().replace(" ", "_")) {
            case "SLOW_LOW":
                return Effet.SLOW_LOW;
            case "SLOW_MEDIUM":
                return Effet.SLOW_MEDIUM;
            case "SLOW_STOP":
                return Effet.SLOW_STOP;
            default:
                return Effet.NORMAL;
        }
    }

    // Méthode utilitaire pour convertir un enum Effet en chaîne de caractères pour la base de données
    private String mapEffetToString(Effet effet) {
        if (effet == null) {
            return "normal"; // Valeur par défaut
        }

        switch (effet) {
            case SLOW_LOW:
                return "slow low";
            case SLOW_MEDIUM:
                return "slow medium";
            case SLOW_STOP:
                return "slow stop";
            default:
                return "normal";
        }
    }
}