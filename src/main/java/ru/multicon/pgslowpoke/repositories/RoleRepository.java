package ru.multicon.pgslowpoke.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.multicon.pgslowpoke.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}


