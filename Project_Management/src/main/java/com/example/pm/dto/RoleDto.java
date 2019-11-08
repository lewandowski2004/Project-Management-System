package com.example.pm.dto;

import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RoleDto {
    private Integer id;
    private String role;
    private String value;
}
