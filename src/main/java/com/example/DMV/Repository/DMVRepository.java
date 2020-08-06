package com.example.DMV.Repository;

import com.example.DMV.Model.DMV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DMVRepository extends JpaRepository<DMV,Integer> {
}
