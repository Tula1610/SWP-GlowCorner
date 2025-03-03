package com.glowcorner.backend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleDTO {

    @Id
    private String roleID;

    private String roleName;

    private String roleDescription;
}
