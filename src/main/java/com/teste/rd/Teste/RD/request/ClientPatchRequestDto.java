package com.teste.rd.Teste.RD.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ClientPatchRequestDto {
    private String firstName;
    private String lastName;
}
