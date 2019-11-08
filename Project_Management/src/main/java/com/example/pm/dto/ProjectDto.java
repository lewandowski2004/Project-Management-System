package com.example.pm.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProjectDto {

    private Long id;

    private String name;

    private Boolean isDeleted;
}
