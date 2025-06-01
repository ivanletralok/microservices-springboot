package com.User.dto;

import lombok.*;

@Data
public class TaskDTO {
   private Long id;
   private String title;
   private String description;
   private Boolean completed;
   private Long userId;
}
