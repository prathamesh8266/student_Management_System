package com.student.studentManagement.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIResponse {
    String message;
    boolean success;

    public APIResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }
}
