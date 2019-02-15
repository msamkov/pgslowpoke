package ru.multicon.pgslowpoke.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.multicon.pgslowpoke.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM t_user u WHERE u.name = :name", nativeQuery = true)
    User findByName(@Param("name") String name);

}