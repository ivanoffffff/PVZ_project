package com.oxyl.coursepfback.API.controller.Tests;

import com.oxyl.coursepfback.API.DTO.ZombieDTO;
import com.oxyl.coursepfback.API.controller.ZombieController;
import com.oxyl.coursepfback.core.Service.ZombieService;
import com.oxyl.coursepfback.core.model.Zombie;
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
public class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    @InjectMocks
    private ZombieController zombieController;

    private Zombie testZombie;
    private ZombieDTO testZombieDTO;

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

        // Initialiser un DTO de zombie de test
        testZombieDTO = new ZombieDTO(
                1L,
                "Zombie de test",
                100,
                0.8,
                10,
                0.5,
                "images/zombie/test.png",
                1L
        );
    }

    @Test
    public void testGetAllZombies() {
        // Préparer les données de test
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(testZombie);

        // Configurer le mock
        when(zombieService.getAllZombies()).thenReturn(zombies);

        // Exécuter la méthode à tester
        ResponseEntity<List<ZombieDTO>> response = zombieController.getAllZombies();

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Zombie de test", response.getBody().get(0).getNom());
        verify(zombieService, times(1)).getAllZombies();
    }

    @Test
    public void testGetZombieById_Found() {
        // Configurer le mock
        when(zombieService.getZombieById(1L)).thenReturn(testZombie);

        // Exécuter la méthode à tester
        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Zombie de test", response.getBody().getNom());
        verify(zombieService, times(1)).getZombieById(1L);
    }

    @Test
    public void testGetZombieById_NotFound() {
        // Configurer le mock pour simuler une exception
        when(zombieService.getZombieById(99L)).thenThrow(new RuntimeException("Zombie not found"));

        // Exécuter la méthode à tester
        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(99L);

        // Vérifier les résultats
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(zombieService, times(1)).getZombieById(99L);
    }

    @Test
    public void testCreateZombie() {
        // Configurer le mock
        when(zombieService.createZombie(any(Zombie.class))).thenReturn(testZombie);

        // Exécuter la méthode à tester
        ResponseEntity<ZombieDTO> response = zombieController.createZombie(testZombieDTO);

        // Vérifier les résultats
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Zombie de test", response.getBody().getNom());
        verify(zombieService, times(1)).createZombie(any(Zombie.class));
    }

    @Test
    public void testUpdateZombie() {
        // Configurer le mock
        when(zombieService.updateZombie(any(Zombie.class))).thenReturn(testZombie);

        // Exécuter la méthode à tester
        ResponseEntity<ZombieDTO> response = zombieController.updateZombie(1L, testZombieDTO);

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Zombie de test", response.getBody().getNom());
        verify(zombieService, times(1)).updateZombie(any(Zombie.class));
    }

    @Test
    public void testDeleteZombie() {
        // Configurer le mock
        doNothing().when(zombieService).deleteZombie(1L);

        // Exécuter la méthode à tester
        ResponseEntity<Void> response = zombieController.deleteZombie(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zombieService, times(1)).deleteZombie(1L);
    }
}