package com.example.vote.domain.agenda.repository;

import com.example.vote.domain.agenda.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
