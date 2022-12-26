package com.student.studentManagement.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifiedUserResponse {
    private String token;
    private Integer id;
    private String userName;
}
