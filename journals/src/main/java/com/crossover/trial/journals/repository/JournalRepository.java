package com.crossover.trial.journals.repository;

import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface JournalRepository extends CrudRepository<Journal, Long> {

    Collection<Journal> findByPublisher(Publisher publisher);

    List<Journal> findByCategoryIdIn(List<Long> ids);

    // TIP: Write queries for continuous periods as explicit range condition. Do this even for a single day.    
    @Transactional(readOnly = true)
    @Query(value = "SELECT p.name FROM journal p where p.publish_date >= CURDATE() and p.publish_date < CURDATE() + 1", nativeQuery = true)
    String[] fetchTodayJournals();
}
