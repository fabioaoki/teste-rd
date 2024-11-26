package com.avaliacao.rd.client.RD.service;

import com.avaliacao.rd.client.RD.exception.ClientException;
import com.avaliacao.rd.client.RD.service.request.ClientRequestDto;
import com.avaliacao.rd.client.RD.service.response.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientServiceBO {

    ClientResponseDto save(final ClientRequestDto dto);

    ClientResponseDto findById(final Long id) throws ClientException;

    Page<ClientResponseDto> findAll(final Pageable pageable);

    void changeStatus(final Long id, final boolean active);

    ClientResponseDto patch(final ClientRequestDto clientRequestDto, final Long id);
}