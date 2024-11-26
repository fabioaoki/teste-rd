package com.teste.rd.Teste.RD.repository;

import com.teste.rd.Teste.RD.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ClientRepositoryJpa implements ClientRepositoryImpl {
    private final ClientRepository repository;

    @Autowired
    public ClientRepositoryJpa(ClientRepository repository) {
        this.repository = repository;
    }
    @Override
    public Client save(Client entity) {
        if (entity.getCreateDate() == null) {
            entity.setCreateDate(LocalDateTime.now());
        } else {
            entity.setLastUpdate(LocalDateTime.now());
        }
        return repository.save(entity);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }
}
