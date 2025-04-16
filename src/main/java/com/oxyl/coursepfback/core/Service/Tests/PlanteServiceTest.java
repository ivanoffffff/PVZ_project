package com.oxyl.coursepfback.core.Service.Tests;

import com.oxyl.coursepfback.repository.PlanteDAO;
import com.oxyl.coursepfback.core.Service.PlanteServiceImpl;
import com.oxyl.coursepfback.core.model.Effet;
import com.oxyl.coursepfback.core.model.Plante;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlanteServiceTest {

    @Mock
    private PlanteDAO planteDAO;

    @InjectMocks
    private PlanteServiceImpl planteService;

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
    public void testGetAllPlantes() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer le mock
        when(planteDAO.findAll()).thenReturn(plantes);

        // Exécuter la méthode à tester
        List<Plante> result = planteService.getAllPlantes();

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Tournesol Test", result.get(0).getNom());
        verify(planteDAO, times(1)).findAll();
    }

    @Test
    public void testGetPlanteById_Found() {
        // Configurer le mock
        when(planteDAO.findById(1L)).thenReturn(Optional.of(testPlante));

        // Exécuter la méthode à tester
        Plante result = planteService.getPlanteById(1L);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Tournesol Test", result.getNom());
        verify(planteDAO, times(1)).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetPlanteById_NotFound() {
        // Configurer le mock pour simuler une plante non trouvée
        when(planteDAO.findById(99L)).thenReturn(Optional.empty());

        // Cette méthode devrait lancer une exception
        planteService.getPlanteById(99L);
    }

    @Test
    public void testCreatePlante() {
        // Configurer les mocks
        when(planteDAO.save(any(Plante.class))).thenReturn(testPlante);

        // Exécuter la méthode à tester
        Plante result = planteService.createPlante(testPlante);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Tournesol Test", result.getNom());
        verify(planteDAO, times(1)).save(testPlante);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlante_InvalidData() {
        // Créer une plante invalide (sans nom)
        testPlante.setNom(null);

        // Cette méthode devrait lancer une exception
        planteService.createPlante(testPlante);
    }

    @Test
    public void testUpdatePlante() {
        // Configurer les mocks
        when(planteDAO.findById(1L)).thenReturn(Optional.of(testPlante));

        // Modifier la plante
        testPlante.setNom("Tournesol Modifié");

        // Exécuter la méthode à tester
        Plante result = planteService.updatePlante(testPlante);

        // Vérifier les résultats
        assertEquals("Tournesol Modifié", result.getNom());
        verify(planteDAO, times(1)).update(testPlante);
    }

    @Test
    public void testDeletePlante() {
        // Configurer le mock
        when(planteDAO.findById(1L)).thenReturn(Optional.of(testPlante));

        // Exécuter la méthode à tester
        planteService.deletePlante(1L);

        // Vérifier que la méthode delete a été appelée
        verify(planteDAO, times(1)).delete(1L);
    }

    @Test
    public void testGetPlantesByEffet() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer les mocks
        when(planteDAO.findByEffet("normal")).thenReturn(plantes);

        // Exécuter la méthode à tester
        List<Plante> result = planteService.getPlantesByEffet("normal");

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Tournesol Test", result.get(0).getNom());
        verify(planteDAO, times(1)).findByEffet("normal");
    }
}