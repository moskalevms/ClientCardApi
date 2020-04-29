package ru.sberbank.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  //  Role findOneByName(String name);
}
