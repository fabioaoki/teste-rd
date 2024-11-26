package com.avaliacao.rd.client.RD.repository;

import com.avaliacao.rd.client.RD.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
