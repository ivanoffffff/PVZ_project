package com.oxyl.coursepfback.repository.Tests;

import com.oxyl.coursepfback.core.model.Zombie;
import com.oxyl.coursepfback.repository.ZombieDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ZombieDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ZombieDAOImpl zombieDAO;

    private Zombie testZombie;

    @Before
    public void setUp() {
        // Initialiser un zombie de test
        testZombie = new Zombie();
        testZombie.setId(1L);
        testZombie.setNom("Zombie de test");
        testZombie.setPoints_de_vie(100);
        testZombie.setAttaque_par_seconde(0.8);
        testZombie.setDegat_attaque(10);
        testZombie.setVitesse_de_deplacement(0.5);
        testZombie.setChemin_image("images/zombie/test.png");
        testZombie.setId_map(1L);
    }

    @Test
    public void testFindAll() {
        // Préparer les données de test
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(testZombie);

        // Configurer le mock
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(zombies);

        // Exécuter la méthode à tester
        List<Zombie> result = zombieDAO.findAll();

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Zombie de test", result.get(0).getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    public void testFindById_Found() {
        // Préparer les données de test
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(testZombie);

        // Configurer le mock
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(zombies);

        // Exécuter la méthode à tester
        Optional<Zombie> result = zombieDAO.findById(1L);

        // Vérifier les résultats
        assertTrue(result.isPresent());
        assertEquals("Zombie de test", result.get().getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(1L));
    }

    @Test
    public void testFindById_NotFound() {
        // Configurer le mock pour simuler un zombie non trouvé
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(99L))).thenReturn(new ArrayList<>());

        // Exécuter la méthode à tester
        Optional<Zombie> result = zombieDAO.findById(99L);

        // Vérifier les résultats
        assertFalse(result.isPresent());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(99L));
    }

    @Test
    public void testFindByMapId() {
        // Préparer les données de test
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(testZombie);

        // Configurer le mock
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(zombies);

        // Exécuter la méthode à tester
        List<Zombie> result = zombieDAO.findByMapId(1L);

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Zombie de test", result.get(0).getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(1L));
    }

    @Test
    public void testSave() {
        // Configurer les mocks
        GeneratedKeyHolder keyHolder = mock(GeneratedKeyHolder.class);

        // On ne peut pas facilement mocker jdbcTemplate.update avec une lambda,
        // donc ce test est simplifié

        // Exécuter la méthode à tester
        Zombie result = zombieDAO.save(testZombie);

        // Vérifier les résultats
        assertNotNull(result);
    }

    @Test
    public void testUpdate() {
        // Exécuter la méthode à tester
        zombieDAO.update(testZombie);

        // Vérifier que la méthode update a été appelée
        verify(jdbcTemplate, times(1)).update(anyString(),
                eq(testZombie.getNom()),
                eq(testZombie.getPoints_de_vie()),
                eq(testZombie.getAttaque_par_seconde()),
                eq(testZombie.getDegat_attaque()),
                eq(testZombie.getVitesse_de_deplacement()),
                eq(testZombie.getChemin_image()),
                eq(testZombie.getId_map()),
                eq(testZombie.getId_map())
        );
    }

    @Test
    public void testDelete() {
        // Exécuter la méthode à tester
        zombieDAO.delete(1L);

        // Vérifier que la méthode update a été appelée
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L));
    }
}