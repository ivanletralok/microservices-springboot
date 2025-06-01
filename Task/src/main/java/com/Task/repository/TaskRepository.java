package com.Task.repository;

import com.Task.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   /**
    * Busca tareas por título utilizando una consulta SQL nativa.
    * Esta consulta permite buscar tareas cuyo título contenga una cadena
    * específica.
    *
    * @param title Título a buscar
    * @return Lista de tareas que coinciden con el título
    */
   @Query(value = "SELECT * FROM tasks WHERE title LIKE CONCAT('%', :title, '%')", nativeQuery = true)
   List<Task> findByTitleCustom(@Param("title") String title);
   
   List<Task> findByUserId(Long userId);
}