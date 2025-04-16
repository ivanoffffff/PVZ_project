package com.oxyl.coursepfback.core.Service.Tests;

import com.oxyl.coursepfback.core.Service.ZombieServiceImpl;
import com.oxyl.coursepfback.repository.MapDAO;
import com.oxyl.coursepfback.repository.ZombieDAO;
import com.oxyl.coursepfback.core.model.Map;
import com.oxyl.coursepfback.core.model.Zombie;
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
public class ZombieServiceTest {

    @Mock
    private ZombieDAO zombieDAO;

    @Mock
    private MapDAO mapDAO;

    @InjectMocks
    private ZombieServiceImpl zombieService;

    private Zombie testZombie;
    private Map testMap;

    @Before
    public void setUp() {
        // Initialiser un zombie de test
        testMap = new Map();
        testMap.setIdMap(1L);
        testMap.setLigne(5);
        testMap.setColonne(9);
        testMap.setCheminImage("images/map/test.png");

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
    public void testGetAllZombies() {
        // Préparer les données de test
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(testZombie);

        // Configurer le mock
        when(zombieDAO.findAll()).thenReturn(zombies);

        // Exécuter la méthode à tester
        List<Zombie> result = zombieService.getAllZombies();

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Zombie de test", result.get(0).getNom());
        verify(zombieDAO, times(1)).findAll();
    }

    @Test
    public void testGetZombieById_Found() {
        // Configurer le mock
        when(zombieDAO.findById(1L)).thenReturn(Optional.of(testZombie));

        // Exécuter la méthode à tester
        Zombie result = zombieService.getZombieById(1L);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Zombie de test", result.getNom());
        verify(zombieDAO, times(1)).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetZombieById_NotFound() {
        // Configurer le mock pour simuler un zombie non trouvé
        when(zombieDAO.findById(99L)).thenReturn(Optional.empty());

        // Cette méthode devrait lancer une exception
        zombieService.getZombieById(99L);
    }

    @Test
    public void testCreateZombie() {
        // Configurer les mocks
        when(mapDAO.findById(1L)).thenReturn(Optional.of(testMap));
        when(zombieDAO.save(any(Zombie.class))).thenReturn(testZombie);

        // Exécuter la méthode à tester
        Zombie result = zombieService.createZombie(testZombie);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals("Zombie de test", result.getNom());
        verify(mapDAO, times(1)).findById(1L);
        verify(zombieDAO, times(1)).save(testZombie);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateZombie_InvalidData() {
        // Créer un zombie invalide (sans nom)
        testZombie.setNom(null);

        // Cette méthode devrait lancer une exception
        zombieService.createZombie(testZombie);
    }

    @Test
    public void testUpdateZombie() {
        // Configurer les mocks
        when(zombieDAO.findById(1L)).thenReturn(Optional.of(testZombie));
        when(mapDAO.findById(1L)).thenReturn(Optional.of(testMap));

        // Modifier le zombie
        testZombie.setNom("Zombie modifié");

        // Exécuter la méthode à tester
        Zombie result = zombieService.updateZombie(testZombie);

        // Vérifier les résultats
        assertEquals("Zombie modifié", result.getNom());
        verify(zombieDAO, times(1)).update(testZombie);
    }

    @Test
    public void testDeleteZombie() {
        // Configurer le mock
        when(zombieDAO.findById(1L)).thenReturn(Optional.of(testZombie));

        // Exécuter la méthode à tester
        zombieService.deleteZombie(1L);

        // Vérifier que la méthode delete a été appelée
        verify(zombieDAO, times(1)).delete(1L);
    }

    @Test
    public void testGetZombiesByMapId() {
        // Préparer les données de test
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(testZombie);

        // Configurer les mocks
        when(zombieDAO.findByMapId(1L)).thenReturn(zombies);

        // Exécuter la méthode à tester
        List<Zombie> result = zombieService.getZombiesByMapId(1L);

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals("Zombie de test", result.get(0).getNom());
        verify(zombieDAO, times(1)).findByMapId(1L);
    }
}