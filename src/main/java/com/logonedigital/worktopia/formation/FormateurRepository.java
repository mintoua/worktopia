package com.logonedigital.worktopia.formation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {
    Formateur findByNomFormateur(String nomFormateur);
}
