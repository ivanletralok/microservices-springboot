package com.Task.dto;

import com.Task.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskWithUserDTO {
   private Task task;
   private UserDTO user;

}
