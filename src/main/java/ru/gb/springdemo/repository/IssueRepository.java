package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.*;

@Repository
public class IssueRepository extends AbstractRepository<Issue> {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    @Override
    public Issue getById(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<Issue> first = issues.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
        if (first.isPresent()) {
            issues.remove(first.get());
            return true;
        } else return false;
    }

    public Issue save(Issue issue) {
        // insert into ....
        issues.add(issue);
        return issue;
    }

    @Override
    public List<Issue> getAll() {
        return issues;
    }

}
