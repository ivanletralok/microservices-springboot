package com.User.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.User.entity.User;
import com.User.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;

   /**
    * Obtiene todos los usuarios.
    *
    * @return Lista de usuarios
    */
   @GetMapping
   public ResponseEntity<List<User>> getAllUsers() {
      return ResponseEntity.ok(userService.getAllUsers());
   }

   /**
    * Crea un nuevo usuario.
    *
    * @param user Usuario a crear
    * @return Usuario creado
    */
   @PostMapping
   public ResponseEntity<User> createUser(@RequestBody User user) {
      return ResponseEntity.ok(userService.createUser(user));
   }

   /**
    * Actualiza un usuario existente.
    *
    * @param user Usuario a actualizar
    * @return Usuario actualizado
    */
   @PutMapping
   public ResponseEntity<User> updateUser(@RequestBody User user) {
      return ResponseEntity.ok(userService.createUser(user));
   }

   /**
    * Elimina un usuario por su ID.
    *
    * @param id ID del usuario a eliminar
    * @return Respuesta vacía con código 204 No Content
    */
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteUser(@PathVariable long id) {
      userService.deleteUser(id);
      return ResponseEntity.ok("Usuario eliminado exitosamente");
   }

   /**
    * Obtiene un usuario por su ID.
    *
    * @param id ID del usuario
    * @return Usuario encontrado
    */
   @GetMapping("/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
      return ResponseEntity.ok(userService.getUserById(id));
   }

   /**
    * Busca un usuario por su nombre.
    *
    * @param name Nombre del usuario
    * @return Usuario encontrado
    */
   @GetMapping("/search/{name}")
   public ResponseEntity<User> getUserByName(@PathVariable String name) {
      return ResponseEntity.ok(userService.getUserByName(name));
   }

}
