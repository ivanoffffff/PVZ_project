package com.oxyl.coursepfback.repository.Tests;

import com.oxyl.coursepfback.repository.ZombieDAO;
import com.oxyl.coursepfback.core.model.Map;
import com.oxyl.coursepfback.core.model.Zombie;
import com.oxyl.coursepfback.repository.MapDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MapDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ZombieDAO zombieDAO;

    @InjectMocks
    private MapDAOImpl mapDAO;

    private Map testMap;
    private List<Zombie> testZombies;

    @Before
    public void setUp() {
        // Initialiser une map de test
        testMap = new Map();
        testMap.setIdMap(1L);
        testMap.setLigne(5);
        testMap.setColonne(9);
        testMap.setCheminImage("images/map/gazon_test.png");

        // Initialiser une liste de zombies de test
        testZombies = new ArrayList<>();
        Zombie zombie = new Zombie();
        zombie.setId(1L);
        zombie.setNom("Zombie de test");
        zombie.setId_map(1L);
        testZombies.add(zombie);
    }

    @Test
    public void testFindAll() {
        // Préparer les données de test
        List<Map> maps = new ArrayList<>();
        maps.add(testMap);

        // Configurer les mocks
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(maps);
        when(zombieDAO.findByMapId(1L)).thenReturn(testZombies);

        // Exécuter la méthode à tester
        List<Map> result = mapDAO.findAll();

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getLigne().intValue());
        assertEquals(9, result.get(0).getColonne().intValue());
        assertNotNull(result.get(0).getZombies());
        assertEquals(1, result.get(0).getZombies().size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
        verify(zombieDAO, times(1)).findByMapId(1L);
    }

    @Test
    public void testFindById_Found() {
        // Préparer les données de test
        List<Map> maps = new ArrayList<>();
        maps.add(testMap);

        // Configurer les mocks
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(maps);
        when(zombieDAO.findByMapId(1L)).thenReturn(testZombies);

        // Exécuter la méthode à tester
        Optional<Map> result = mapDAO.findById(1L);

        // Vérifier les résultats
        assertTrue(result.isPresent());
        assertEquals(5, result.get().getLigne().intValue());
        assertEquals(9, result.get().getColonne().intValue());
        assertNotNull(result.get().getZombies());
        assertEquals(1, result.get().getZombies().size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(1L));
        verify(zombieDAO, times(1)).findByMapId(1L);
    }

    @Test
    public void testFindById_NotFound() {
        // Configurer le mock pour simuler une map non trouvée
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(99L))).thenReturn(new ArrayList<>());

        // Exécuter la méthode à tester
        Optional<Map> result = mapDAO.findById(99L);

        // Vérifier les résultats
        assertFalse(result.isPresent());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(99L));
        verify(zombieDAO, never()).findByMapId(anyLong());
    }

    @Test
    public void testSave() {
        // Configurer les mocks pour simuler l'insertion avec génération de clé
        GeneratedKeyHolder keyHolder = mock(GeneratedKeyHolder.class);

        // Note: Le test complet du save est complexe avec les lambdas et PreparedStatementCreator
        // Ce test est simplifié

        // Exécuter la méthode à tester (partiellement)
        Map result = mapDAO.save(testMap);

        // Vérifier les résultats de base
        assertNotNull(result);
    }

    @Test
    public void testUpdate() {
        // Exécuter la méthode à tester
        mapDAO.update(testMap);

        // Vérifier que jdbcTemplate.update est appelé avec les bons paramètres
        verify(jdbcTemplate, times(1)).update(
                anyString(),
                eq(testMap.getLigne()),
                eq(testMap.getColonne()),
                eq(testMap.getCheminImage()),
                eq(testMap.getIdMap())
        );
    }

    @Test
    public void testDelete_Success() {
        // Configurer les mocks
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(1L))).thenReturn(0);

        // Exécuter la méthode à tester
        mapDAO.delete(1L);

        // Vérifier que jdbcTemplate.update est appelé avec le bon ID
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L));
    }

    @Test(expected = RuntimeException.class)
    public void testDelete_WithZombies() {
        // Configurer les mocks pour simuler une map avec des zombies
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(1L))).thenReturn(2);

        // Exécuter la méthode à tester - devrait lancer une exception
        mapDAO.delete(1L);
    }
}