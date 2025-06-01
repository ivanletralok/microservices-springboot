package com.User.repository;

import com.User.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Repositorio para la entidad User.
 * Proporciona métodos para realizar operaciones CRUD y consultas
 * personalizadas.
 */
public interface UserRepository extends JpaRepository<User, Long> {
   /*
    * Busca un usuario por su nombre.
    */
   @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")

   List<User> findByName(@Param("name") String name);

}