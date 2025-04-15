package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.core.model.Map;
import java.util.List;
import java.util.Optional;

public interface MapDAO {
    List<Map> findAll();
    Optional<Map> findById(Long id);
    Map save(Map map);
    void update(Map map);
    void delete(Long id);
}