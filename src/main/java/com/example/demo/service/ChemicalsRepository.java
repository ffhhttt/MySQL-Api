package com.example.demo.service;

import com.example.demo.entity.Chemicals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChemicalsRepository extends JpaRepository<Chemicals,Integer> {

    List<Chemicals> findByName(String name);
    List<Chemicals> findByPlace(String place);

    @Query(value = "select * from chemicals where name like %?1%", nativeQuery = true)
    List<Chemicals> findByNameLike(String name);
    @Query(value = "select * from chemicals where place like %?1%", nativeQuery = true)
    List<Chemicals> findByPlaceLike(String place);

    @Query(value = "select max(id) + 1 from chemicals;", nativeQuery = true)
    Integer getMaxID();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `chemicals` VALUES (?1, ?2, ?3, ?4, ?5);", nativeQuery = true)
    void rewriteSave(Integer maxId,String name,String place,Integer stock,Integer type);
}
