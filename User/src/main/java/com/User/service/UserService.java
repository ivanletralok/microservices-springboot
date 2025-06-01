package com.User.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.User.dto.TaskDTO;
import com.User.entity.User;
import com.User.feign.TaskClient;
import com.User.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

   /**
    * Servicio para gestionar usuarios.
    * Proporciona métodos para realizar operaciones CRUD y consultas
    */
   private final UserRepository userRepository;
   private final TaskClient taskClient;

   /**
    * Obtiene todos los usuarios.
    *
    * @return Lista de usuarios
    */
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   /**
    * Obtiene un usuario por su ID.
    *
    * @param id ID del usuario
    * @return Usuario encontrado
    * @throws ResponseStatusException si el usuario no existe
    */
   public User getUserById(long id) {
      return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
   }

   /**
    * Crea un nuevo usuario.
    *
    * @param user Usuario a crear
    * @return Usuario creado
    */
   public User createUser(User user) {
      return userRepository.save(user);
   }

   /**
    * Elimina un usuario por su ID.
    *
    * @param id
    */
   public void deleteUser(long id) {
      List<TaskDTO> tasks = taskClient.getTasksByUserId(id);

      if (tasks != null && !tasks.isEmpty()) {
         throw new IllegalStateException(
               "No se puede eliminar el usuario con ID " + id + " porque tiene tareas asignadas");
      }

      userRepository.deleteById(id);
   }

   /**
    * Busca un usuario por su nombre.
    *
    * @param name Nombre del usuario
    * @return Usuario encontrado
    * @throws RuntimeException si el usuario no existe
    */
   public User getUserByName(String name) {
      List<User> users = userRepository.findByName(name);
      if (users.isEmpty()) {
         throw new RuntimeException("Usuario " + name + " no existe");
      }
      return users.get(0);
   }

}
