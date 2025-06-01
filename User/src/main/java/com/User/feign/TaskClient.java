package com.User.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.User.dto.TaskDTO;

@FeignClient(name = "Task")
public interface TaskClient {
   
   /**
   * Obtiene las tareas asociadas a un usuario por su ID.
   */
   @GetMapping("/api/v1/tasks/by-user/{userId}")
   List<TaskDTO> getTasksByUserId(@PathVariable("userId") Long userId);
}
