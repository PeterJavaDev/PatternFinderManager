package org.patternfinder.repository;

import org.patternfinder.model.SearchTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchTaskRepository extends JpaRepository<SearchTask, Long> {
}
