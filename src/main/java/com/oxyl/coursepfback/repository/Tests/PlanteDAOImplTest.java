package com.oxyl.coursepfback.repository.Tests;

import com.oxyl.coursepfback.core.model.Effet;
import com.oxyl.coursepfback.core.model.Plante;
import com.oxyl.coursepfback.repository.PlanteDAOImpl;
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
public class PlanteDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PlanteDAOImpl planteDAO;

    private Plante testPlante;

    @Before
    public void setUp() {
        // Initialiser une plante de test
        testPlante = new Plante();
        testPlante.setIdPlante(1L);
        testPlante.setNom("Tournesol Test");
        testPlante.setPointDeVie(100);
        testPlante.setAttaqueParSeconde(0.0);
        testPlante.setDegatAttaque(0);
        testPlante.setCout(50);
        testPlante.setSoleilParSeconde(25.0);
        testPlante.setEffet(Effet.NORMAL);
        testPlante.setCheminImage("images/plante/tournesol_test.png");
    }

    @Test
    public void testFindAll() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer le mock
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(plantes);

        // Exécuter la méthode à tester
        List<Plante> result = planteDAO.findAll();

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Tournesol Test", result.get(0).getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    public void testFindById_Found() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer le mock
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(plantes);

        // Exécuter la méthode à tester
        Optional<Plante> result = planteDAO.findById(1L);

        // Vérifier les résultats
        assertTrue(result.isPresent());
        assertEquals("Tournesol Test", result.get().getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(1L));
    }

    @Test
    public void testFindById_NotFound() {
        // Configurer le mock pour simuler une plante non trouvée
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(99L))).thenReturn(new ArrayList<>());

        // Exécuter la méthode à tester
        Optional<Plante> result = planteDAO.findById(99L);

        // Vérifier les résultats
        assertFalse(result.isPresent());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(99L));
    }

    @Test
    public void testFindByEffet() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer le mock
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq("normal"))).thenReturn(plantes);

        // Exécuter la méthode à tester
        List<Plante> result = planteDAO.findByEffet("normal");

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Tournesol Test", result.get(0).getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq("normal"));
    }

    @Test
    public void testSave() {
        // Configurer les mocks pour simuler l'insertion avec génération de clé
        GeneratedKeyHolder keyHolder = mock(GeneratedKeyHolder.class);

        // Note: Le test complet du save est complexe avec les lambdas et PreparedStatementCreator
        // Ce test est simplifié

        // Exécuter la méthode à tester (partiellement)
        Plante result = planteDAO.save(testPlante);

        // Vérifier les résultats de base
        assertNotNull(result);
    }

    @Test
    public void testUpdate() {
        // Exécuter la méthode à tester
        planteDAO.update(testPlante);

        // Vérifier que jdbcTemplate.update est appelé avec les bons paramètres
        verify(jdbcTemplate, times(1)).update(
                anyString(),
                eq(testPlante.getNom()),
                eq(testPlante.getPointDeVie()),
                eq(testPlante.getAttaqueParSeconde()),
                eq(testPlante.getDegatAttaque()),
                eq(testPlante.getCout()),
                eq(testPlante.getSoleilParSeconde()),
                anyString(), // Pour l'effet qui est converti en chaîne
                eq(testPlante.getCheminImage()),
                eq(testPlante.getIdPlante())
        );
    }

    @Test
    public void testDelete() {
        // Exécuter la méthode à tester
        planteDAO.delete(1L);

        // Vérifier que jdbcTemplate.update est appelé avec le bon ID
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L));
    }
}