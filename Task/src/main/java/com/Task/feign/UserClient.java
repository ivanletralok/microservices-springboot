package com.Task.feign;

import com.Task.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para interactuar con el servicio de usuarios.
 * Este cliente se utiliza para obtener información de usuarios desde el
 * servicio User.
 */

@FeignClient(name = "User")
public interface UserClient {

    /**
     * Obtiene un usuario por su ID.
     * Este método se utiliza para obtener información de un usuario.
     *
     * @param id ID del usuario
     * @return Usuario encontrado
     */
    @GetMapping("/api/v1/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

}
