package com.Task.service;

import com.Task.dto.TaskDTO;
import com.Task.dto.TaskWithUserDTO;
import com.Task.dto.UserDTO;
import com.Task.entity.Task;
import com.Task.feign.UserClient;
import com.Task.repository.TaskRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

   private final TaskRepository repository;
   private final UserClient userClient;

   /**
    * Obtiene todas las tareas.
    *
    * @return Lista de tareas
    */
   public List<Task> getAllTasks() {
      return repository.findAll();
   }

   /**
    * Obtiene una tarea por su ID.
    *
    * @param id ID de la tarea
    * @return Tarea encontrada
    * @throws RuntimeException si la tarea no existe
    */
   public Task getTaskById(Long id) {
      return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("La tarea con ID " + id + " no existe"));
   }

   /**
    * Crea una nueva tarea.
    * Verifica que el usuario asociado a la tarea exista antes de crearla.
    *
    * @param task Tarea a crear
    * @return Tarea creada
    * @throws RuntimeException si el usuario no existe
    */
   public Task createTask(Task task) {
      try {
         userClient.getUserById(task.getUserId()); // Si no existe, lanzará excepción
      } catch (FeignException.NotFound e) {
         throw new RuntimeException("El usuario con ID " + task.getUserId() + " no existe");
      }

      return repository.save(task);
   }

   /**
    * Actualiza una tarea existente.
    * Verifica que el usuario asociado a la tarea exista antes de actualizarla.
    *
    * @param task Tarea a actualizar
    * @return Tarea actualizada
    * @throws RuntimeException si el usuario no existe
    */
   public void deleteTask(Long id) {
      repository.deleteById(id);
   }

   /**
    * Busca tareas por título utilizando un método personalizado.
    * Este método utiliza una consulta personalizada definida en el repositorio.
    *
    * @param title Título a buscar
    * @return Lista de tareas que coinciden con el título
    *
    */
   public List<Task> findByTitleCustom(String title) {
      return repository.findByTitleCustom(title);
   }

   /**
    * Obtiene todas las tareas junto con la información del usuario asociado.
    * Utiliza el cliente Feign para obtener los datos del usuario.
    *
    * @return Lista de tareas con información del usuario
    */

   public List<TaskWithUserDTO> getAllTasksWithUsers() {
      List<Task> tasks = repository.findAll();
      return tasks.stream().map(task -> {
         UserDTO user = userClient.getUserById(task.getUserId());
         return new TaskWithUserDTO(task, user);
      }).toList();
   }

   /**
    * Busca tareas por ID de usuario.
    *
    * @param userId ID del usuario
    * @return Lista de tareas asociadas al usuario
    */
   public List<TaskDTO> findByUserId(Long userId) {
      List<Task> tasks = repository.findByUserId(userId);

      return tasks.stream()
            .map(task -> new TaskDTO(task))
            .toList();
   }
}
