package com.oxyl.coursepfback.API.controller.Tests;

import com.oxyl.coursepfback.API.DTO.PlanteDTO;
import com.oxyl.coursepfback.API.controller.PlanteController;
import com.oxyl.coursepfback.core.Service.PlanteService;
import com.oxyl.coursepfback.core.model.Effet;
import com.oxyl.coursepfback.core.model.Plante;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    @InjectMocks
    private PlanteController planteController;

    private Plante testPlante;
    private PlanteDTO testPlanteDTO;

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

        // Initialiser un DTO de plante de test
        testPlanteDTO = new PlanteDTO(
                1L,
                "Tournesol Test",
                100,
                0.0,
                0,
                50,
                25.0,
                "normal",
                "images/plante/tournesol_test.png"
        );
    }

    @Test
    public void testGetAllPlantes() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer le mock
        when(planteService.getAllPlantes()).thenReturn(plantes);

        // Exécuter la méthode à tester
        ResponseEntity<List<PlanteDTO>> response = planteController.getAllPlantes();

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Tournesol Test", response.getBody().get(0).getNom());
        verify(planteService, times(1)).getAllPlantes();
    }

    @Test
    public void testGetPlanteById_Found() {
        // Configurer le mock
        when(planteService.getPlanteById(1L)).thenReturn(testPlante);

        // Exécuter la méthode à tester
        ResponseEntity<PlanteDTO> response = planteController.getPlanteById(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Tournesol Test", response.getBody().getNom());
        verify(planteService, times(1)).getPlanteById(1L);
    }

    @Test
    public void testGetPlanteById_NotFound() {
        // Configurer le mock pour simuler une exception
        when(planteService.getPlanteById(99L)).thenThrow(new RuntimeException("Plante not found"));

        // Exécuter la méthode à tester
        ResponseEntity<PlanteDTO> response = planteController.getPlanteById(99L);

        // Vérifier les résultats
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(planteService, times(1)).getPlanteById(99L);
    }

    @Test
    public void testCreatePlante() {
        // Configurer le mock
        when(planteService.createPlante(any(Plante.class))).thenReturn(testPlante);

        // Exécuter la méthode à tester
        ResponseEntity<PlanteDTO> response = planteController.createPlante(testPlanteDTO);

        // Vérifier les résultats
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Tournesol Test", response.getBody().getNom());
        verify(planteService, times(1)).createPlante(any(Plante.class));
    }

    @Test
    public void testUpdatePlante() {
        // Configurer le mock
        when(planteService.updatePlante(any(Plante.class))).thenReturn(testPlante);

        // Exécuter la méthode à tester
        ResponseEntity<PlanteDTO> response = planteController.updatePlante(1L, testPlanteDTO);

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Tournesol Test", response.getBody().getNom());
        verify(planteService, times(1)).updatePlante(any(Plante.class));
    }

    @Test
    public void testDeletePlante() {
        // Configurer le mock
        doNothing().when(planteService).deletePlante(1L);

        // Exécuter la méthode à tester
        ResponseEntity<Void> response = planteController.deletePlante(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(planteService, times(1)).deletePlante(1L);
    }

    @Test
    public void testGetPlantesByEffet() {
        // Préparer les données de test
        List<Plante> plantes = new ArrayList<>();
        plantes.add(testPlante);

        // Configurer le mock
        when(planteService.getPlantesByEffet("normal")).thenReturn(plantes);

        // Exécuter la méthode à tester
        ResponseEntity<List<PlanteDTO>> response = planteController.getPlantesByEffet("normal");

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Tournesol Test", response.getBody().get(0).getNom());
        verify(planteService, times(1)).getPlantesByEffet("normal");
    }
}