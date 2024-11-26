package com.avaliacao.rd.client.RD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientModel {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private boolean active;
}
