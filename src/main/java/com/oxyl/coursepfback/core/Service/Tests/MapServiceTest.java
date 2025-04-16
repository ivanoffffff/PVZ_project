package com.oxyl.coursepfback.core.Service.Tests;

import com.oxyl.coursepfback.repository.MapDAO;
import com.oxyl.coursepfback.core.Service.MapServiceImpl;
import com.oxyl.coursepfback.core.model.Map;
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
public class MapServiceTest {

    @Mock
    private MapDAO mapDAO;

    @InjectMocks
    private MapServiceImpl mapService;

    private Map testMap;

    @Before
    public void setUp() {
        // Initialiser une map de test
        testMap = new Map();
        testMap.setIdMap(1L);
        testMap.setLigne(5);
        testMap.setColonne(9);
        testMap.setCheminImage("images/map/gazon_test.png");
    }

    @Test
    public void testGetAllMaps() {
        // Préparer les données de test
        List<Map> maps = new ArrayList<>();
        maps.add(testMap);

        // Configurer le mock
        when(mapDAO.findAll()).thenReturn(maps);

        // Exécuter la méthode à tester
        List<Map> result = mapService.getAllMaps();

        // Vérifier les résultats
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getLigne().intValue());
        assertEquals(9, result.get(0).getColonne().intValue());
        verify(mapDAO, times(1)).findAll();
    }

    @Test
    public void testGetMapById_Found() {
        // Configurer le mock
        when(mapDAO.findById(1L)).thenReturn(Optional.of(testMap));

        // Exécuter la méthode à tester
        Map result = mapService.getMapById(1L);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals(5, result.getLigne().intValue());
        assertEquals(9, result.getColonne().intValue());
        verify(mapDAO, times(1)).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMapById_NotFound() {
        // Configurer le mock pour simuler une map non trouvée
        when(mapDAO.findById(99L)).thenReturn(Optional.empty());

        // Cette méthode devrait lancer une exception
        mapService.getMapById(99L);
    }

    @Test
    public void testCreateMap() {
        // Configurer les mocks
        when(mapDAO.save(any(Map.class))).thenReturn(testMap);

        // Exécuter la méthode à tester
        Map result = mapService.createMap(testMap);

        // Vérifier les résultats
        assertNotNull(result);
        assertEquals(5, result.getLigne().intValue());
        assertEquals(9, result.getColonne().intValue());
        verify(mapDAO, times(1)).save(testMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMap_InvalidData() {
        // Créer une map invalide (sans ligne)
        testMap.setLigne(null);

        // Cette méthode devrait lancer une exception
        mapService.createMap(testMap);
    }

    @Test
    public void testUpdateMap() {
        // Configurer les mocks
        when(mapDAO.findById(1L)).thenReturn(Optional.of(testMap));

        // Modifier la map
        testMap.setLigne(6);
        testMap.setColonne(10);

        // Exécuter la méthode à tester
        Map result = mapService.updateMap(testMap);

        // Vérifier les résultats
        assertEquals(6, result.getLigne().intValue());
        assertEquals(10, result.getColonne().intValue());
        verify(mapDAO, times(1)).update(testMap);
    }

    @Test
    public void testDeleteMap() {
        // Configurer le mock
        when(mapDAO.findById(1L)).thenReturn(Optional.of(testMap));

        // Exécuter la méthode à tester
        mapService.deleteMap(1L);

        // Vérifier que la méthode delete a été appelée
        verify(mapDAO, times(1)).delete(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteMap_Exception() {
        // Configurer les mocks
        when(mapDAO.findById(1L)).thenReturn(Optional.of(testMap));
        doThrow(new RuntimeException("Cannot delete map with zombies")).when(mapDAO).delete(1L);

        // Exécuter la méthode à tester - devrait lancer une exception
        mapService.deleteMap(1L);
    }
}