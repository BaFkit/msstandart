package ru.msstandart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.msstandart.entities.Options;

@Repository
public interface OptionsRepository extends JpaRepository<Options, String> {
}
