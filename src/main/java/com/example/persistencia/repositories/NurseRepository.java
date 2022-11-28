package com.example.persistencia.repositories;


import com.example.persistencia.domain.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, String> {
}
