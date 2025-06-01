package com.Task.dto;

import com.Task.entity.Task;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaskDTO {
   private Long id;
   private String title;
   private String description;
   private Long userId; // referencia al usuario asignado
   private boolean completed;

   public TaskDTO(Task task) {
       this.id = task.getId();
       this.title = task.getTitle();
       this.description = task.getDescription();
       this.userId = task.getUserId();
       this.completed = task.getCompleted();
   }

}
