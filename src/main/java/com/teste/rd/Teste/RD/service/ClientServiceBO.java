package com.teste.rd.Teste.RD.service;

import com.teste.rd.Teste.RD.exception.ClientException;
import com.teste.rd.Teste.RD.request.ClientRequestDto;
import com.teste.rd.Teste.RD.response.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientServiceBO {

    ClientResponseDto save(ClientRequestDto dto);

    ClientResponseDto findById(final Long id) throws ClientException;

    Page<ClientResponseDto> findAll(final Pageable pageable);
}
