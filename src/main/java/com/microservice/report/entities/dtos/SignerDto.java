package com.microservice.report.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignerDto {
    private String name;

    private String lastName;

    private String dni;
}
