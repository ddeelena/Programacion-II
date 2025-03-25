package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    // Nuevo método que devuelve solo el ID del usuario
    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Optional<Integer> findIdByUsername(@Param("username") String username);
}
