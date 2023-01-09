package com.example.vote.domain.agenda.issue.repository;

import com.example.vote.domain.agenda.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
