package com.bignerdranch.respository;

import com.bignerdranch.model.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, Long> {
    Optional<Expertise> findByName(String name);
}
