package com.oxyl.coursepfback.API.controller.Tests;

import com.oxyl.coursepfback.API.DTO.MapDTO;
import com.oxyl.coursepfback.API.DTO.ZombieDTO;
import com.oxyl.coursepfback.API.controller.MapController;
import com.oxyl.coursepfback.core.Service.MapService;
import com.oxyl.coursepfback.core.model.Map;
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
public class MapControllerTest {

    @Mock
    private MapService mapService;

    @InjectMocks
    private MapController mapController;

    private Map testMap;
    private MapDTO testMapDTO;
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
        zombie.setPoints_de_vie(100);
        zombie.setAttaque_par_seconde(0.8);
        zombie.setDegat_attaque(10);
        zombie.setVitesse_de_deplacement(0.5);
        zombie.setChemin_image("images/zombie/test.png");
        zombie.setId_map(1L);
        testZombies.add(zombie);

        testMap.setZombies(testZombies);

        // Initialiser un DTO de map de test
        testMapDTO = new MapDTO(
                1L,
                5,
                9,
                "images/map/gazon_test.png"
        );

        List<ZombieDTO> zombieDTOs = new ArrayList<>();
        ZombieDTO zombieDTO = new ZombieDTO(
                1L,
                "Zombie de test",
                100,
                0.8,
                10,
                0.5,
                "images/zombie/test.png",
                1L
        );
        zombieDTOs.add(zombieDTO);
        testMapDTO.setZombies(zombieDTOs);
    }

    @Test
    public void testGetAllMaps() {
        // Préparer les données de test
        List<Map> maps = new ArrayList<>();
        maps.add(testMap);

        // Configurer le mock
        when(mapService.getAllMaps()).thenReturn(maps);

        // Exécuter la méthode à tester
        ResponseEntity<List<MapDTO>> response = mapController.getAllMaps();

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(5, response.getBody().get(0).getLigne().intValue());
        assertEquals(9, response.getBody().get(0).getColonne().intValue());
        verify(mapService, times(1)).getAllMaps();
    }

    @Test
    public void testGetMapById_Found() {
        // Configurer le mock
        when(mapService.getMapById(1L)).thenReturn(testMap);

        // Exécuter la méthode à tester
        ResponseEntity<MapDTO> response = mapController.getMapById(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getLigne().intValue());
        assertEquals(9, response.getBody().getColonne().intValue());
        verify(mapService, times(1)).getMapById(1L);
    }

    @Test
    public void testGetMapById_NotFound() {
        // Configurer le mock pour simuler une exception
        when(mapService.getMapById(99L)).thenThrow(new RuntimeException("Map not found"));

        // Exécuter la méthode à tester
        ResponseEntity<MapDTO> response = mapController.getMapById(99L);

        // Vérifier les résultats
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(mapService, times(1)).getMapById(99L);
    }

    @Test
    public void testCreateMap() {
        // Configurer le mock
        when(mapService.createMap(any(Map.class))).thenReturn(testMap);

        // Exécuter la méthode à tester
        ResponseEntity<MapDTO> response = mapController.createMap(testMapDTO);

        // Vérifier les résultats
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getLigne().intValue());
        assertEquals(9, response.getBody().getColonne().intValue());
        verify(mapService, times(1)).createMap(any(Map.class));
    }

    @Test
    public void testUpdateMap() {
        // Configurer le mock
        when(mapService.updateMap(any(Map.class))).thenReturn(testMap);

        // Exécuter la méthode à tester
        ResponseEntity<MapDTO> response = mapController.updateMap(1L, testMapDTO);

        // Vérifier les résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getLigne().intValue());
        assertEquals(9, response.getBody().getColonne().intValue());
        verify(mapService, times(1)).updateMap(any(Map.class));
    }

    @Test
    public void testDeleteMap_Success() {
        // Configurer le mock
        doNothing().when(mapService).deleteMap(1L);

        // Exécuter la méthode à tester
        ResponseEntity<String> response = mapController.deleteMap(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(mapService, times(1)).deleteMap(1L);
    }

    @Test
    public void testDeleteMap_Error() {
        // Configurer le mock pour simuler une exception
        doThrow(new RuntimeException("Cannot delete map with zombies")).when(mapService).deleteMap(1L);

        // Exécuter la méthode à tester
        ResponseEntity<String> response = mapController.deleteMap(1L);

        // Vérifier les résultats
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Cannot delete map with zombies", response.getBody());
        verify(mapService, times(1)).deleteMap(1L);
    }
}