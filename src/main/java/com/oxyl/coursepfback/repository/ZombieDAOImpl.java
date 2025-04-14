package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.core.model.Zombie;
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
public class ZombieDAOImpl implements ZombieDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Zombie> zombieRowMapper = (rs, rowNum) -> {
        Zombie zombie = new Zombie();
        zombie.setId(rs.getLong("id_zombie"));
        zombie.setNom(rs.getString("nom"));
        zombie.setPoints_de_vie(rs.getInt("point_de_vie"));
        zombie.setAttaque_par_seconde(rs.getDouble("attaque_par_seconde"));
        zombie.setDegat_attaque(rs.getInt("degat_attaque"));
        zombie.setVitesse_de_deplacement(rs.getDouble("vitesse_de_deplacement"));
        zombie.setChemin_image(rs.getString("chemin_image"));
        zombie.setId_map(rs.getLong("id_map"));
        return zombie;
    };

    @Autowired
    public ZombieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Zombie> findAll() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }

    @Override
    public Optional<Zombie> findById(Long id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        List<Zombie> zombies = jdbcTemplate.query(sql, zombieRowMapper, id);
        return zombies.isEmpty() ? Optional.empty() : Optional.of(zombies.get(0));
    }

    @Override
    public List<Zombie> findByMapId(Long mapId) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, zombieRowMapper, mapId);
    }

    @Override
    public Zombie save(Zombie zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, zombie.getNom());
            ps.setInt(2, zombie.getPoints_de_vie());
            ps.setDouble(3, zombie.getAttaque_par_seconde());
            ps.setInt(4, zombie.getDegat_attaque());
            ps.setDouble(5, zombie.getVitesse_de_deplacement());
            ps.setString(6, zombie.getChemin_image());
            ps.setLong(7, zombie.getId_map());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            zombie.setId(key.longValue());
        }

        return zombie;
    }

    @Override
    public void update(Zombie zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPoints_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_map(),
                zombie.getId()
        );
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }
}