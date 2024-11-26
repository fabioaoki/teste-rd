package com.teste.rd.Teste.RD.mapper;

import com.teste.rd.Teste.RD.entity.Client;
import com.teste.rd.Teste.RD.model.ClientModel;
import com.teste.rd.Teste.RD.request.ClientRequestDto;
import com.teste.rd.Teste.RD.response.ClientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper MAPPER = Mappers.getMapper(ClientMapper.class);

    ClientModel toModel(ClientRequestDto dto);

    Client toEntity(ClientModel clientModel);

    ClientResponseDto toDto(Client client);
}
