package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

@Repository
public interface IssueJpaRepository extends AbstractJpaRepository<Issue> {
}
