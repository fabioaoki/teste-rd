package com.teste.rd.Teste.RD.repository;

import com.teste.rd.Teste.RD.entity.Client;

import java.util.Optional;

public interface ClientRepositoryImpl {
    Client save(Client entity);

    Optional<Client> findById(Long id);
}
