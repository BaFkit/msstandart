package ru.msstandart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.msstandart.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
