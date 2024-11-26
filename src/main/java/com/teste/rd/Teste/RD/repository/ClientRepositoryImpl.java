package com.teste.rd.Teste.RD.repository;

import com.teste.rd.Teste.RD.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientRepositoryImpl {
    Client save(Client entity);

    Optional<Client> findById(Long id);

    Page<Client> findAll(Pageable pageable);
}
