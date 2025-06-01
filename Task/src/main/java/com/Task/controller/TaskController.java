package com.Task.controller;

import com.Task.dto.TaskDTO;
import com.Task.dto.TaskWithUserDTO;
import com.Task.entity.Task;
import com.Task.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TaskController {

   private final TaskService service;

   /**
    * Obtiene todas las tareas.
    *
    * @return Lista de tareas
    */
   @GetMapping
   public ResponseEntity<List<Task>> getAllTasks() {
      return ResponseEntity.ok(service.getAllTasks());
   }

   /**
    * Crea una nueva tarea.
    *
    * @param task Tarea a crear
    * @return Tarea creada
    */
   @PostMapping
   public ResponseEntity<Task> createTask(@RequestBody Task task) {
      return ResponseEntity.ok(service.createTask(task));
   }

   /**
    * Actualiza una tarea existente.
    *
    * @param task Tarea a actualizar
    * @return Tarea actualizada
    */
   @PutMapping
   public ResponseEntity<Task> updateTask(@RequestBody Task task) {
      return ResponseEntity.ok(service.createTask(task));
   }

   /**
    * Elimina una tarea por su ID.
    *
    * @param id ID de la tarea a eliminar
    * @return Respuesta vacía con código 204 No Content
    */
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
      service.deleteTask(id);
      return ResponseEntity.noContent().build();
   }

   /**
    * Obtiene una tarea por su ID.
    *
    * @param id ID de la tarea
    * @return Tarea encontrada
    */
   @GetMapping("/{id}")
   public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
      Task task = service.getTaskById(id);
      return ResponseEntity.ok(task);
   }

   /**
    * Busca tareas por título utilizando una consulta personalizada.
    *
    * @param title Título a buscar
    * @return Lista de tareas que coinciden con el título
    *         /tasks/search/{title}
    */
   @GetMapping("/search/{title}")
   public ResponseEntity<List<Task>> findByTitleCustom(@PathVariable String title) {
      return ResponseEntity.ok(service.findByTitleCustom(title));
   }

   /**
    * Obtiene todas las tareas junto con los usuarios asignados.
    *
    * @return Lista de tareas con información del usuario
    */
   @GetMapping("/with-users")
   public ResponseEntity<List<TaskWithUserDTO>> getAllTasksWithUsers() {
      return ResponseEntity.ok(service.getAllTasksWithUsers());
   }
   
   /**
    * Obtiene las tareas asociadas a un usuario por su ID.
    *
    * @param userId ID del usuario
    * @return Lista de tareas asociadas al usuario
    */
   @GetMapping("/by-user/{userId}")
   public ResponseEntity<List<TaskDTO>> getTasksByUserId(@PathVariable Long userId) {
      return ResponseEntity.ok(service.findByUserId(userId));
   }

}
