package tech.project.agregadordeinvestimentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.project.agregadordeinvestimentos.entities.User;

import java.util.UUID;

@Repository
public interface UserRespository extends JpaRepository<User, UUID> {
}
