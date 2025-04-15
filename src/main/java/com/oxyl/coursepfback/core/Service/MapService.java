package com.oxyl.coursepfback.core.Service;

import com.oxyl.coursepfback.core.model.Map;
import java.util.List;

public interface MapService {
    List<Map> getAllMaps();
    Map getMapById(Long id);
    Map createMap(Map map);
    Map updateMap(Map map);
    void deleteMap(Long id);
}