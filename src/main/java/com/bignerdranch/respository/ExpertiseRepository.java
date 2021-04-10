package com.bignerdranch.respository;

import com.bignerdranch.model.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, Long> {
}
