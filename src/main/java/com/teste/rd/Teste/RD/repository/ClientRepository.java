package com.teste.rd.Teste.RD.repository;

import com.teste.rd.Teste.RD.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
