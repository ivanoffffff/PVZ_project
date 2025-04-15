package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.core.model.Map;
import com.oxyl.coursepfback.core.model.Zombie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MapDAOImpl implements MapDAO {

    private final JdbcTemplate jdbcTemplate;
    private final ZombieDAO zombieDAO; // Pour récupérer les zombies associés à la map

    private final RowMapper<Map> mapRowMapper = (rs, rowNum) -> {
        Map map = new Map();
        map.setIdMap(rs.getLong("id_map"));
        map.setLigne(rs.getInt("ligne"));
        map.setColonne(rs.getInt("colonne"));
        map.setCheminImage(rs.getString("chemin_image"));
        return map;
    };

    @Autowired
    public MapDAOImpl(JdbcTemplate jdbcTemplate, ZombieDAO zombieDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.zombieDAO = zombieDAO;
    }

    @Override
    public List<Map> findAll() {
        String sql = "SELECT * FROM map";
        List<Map> maps = jdbcTemplate.query(sql, mapRowMapper);

        // Pour chaque map, récupérer ses zombies associés
        for (Map map : maps) {
            map.setZombies(zombieDAO.findByMapId(map.getIdMap()));
        }

        return maps;
    }

    @Override
    public Optional<Map> findById(Long id) {
        String sql = "SELECT * FROM map WHERE id_map = ?";
        List<Map> maps = jdbcTemplate.query(sql, mapRowMapper, id);

        if (maps.isEmpty()) {
            return Optional.empty();
        }

        Map map = maps.get(0);
        // Récupérer les zombies associés à cette map
        map.setZombies(zombieDAO.findByMapId(map.getIdMap()));

        return Optional.of(map);
    }

    @Override
    public Map save(Map map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, map.getLigne());
            ps.setInt(2, map.getColonne());
            ps.setString(3, map.getCheminImage());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            map.setIdMap(key.longValue());
        }

        return map;
    }

    @Override
    public void update(Map map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql,
                map.getLigne(),
                map.getColonne(),
                map.getCheminImage(),
                map.getIdMap()
        );
    }

    @Override
    public void delete(Long id) {
        // Vérifiez d'abord qu'aucun zombie n'est associé à cette map
        String countSql = "SELECT COUNT(*) FROM zombie WHERE id_map = ?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, id);

        if (count != null && count > 0) {
            throw new RuntimeException("Impossible de supprimer la map car elle est liée à " + count + " zombies");
        }

        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }
}
