package com.avaliacao.rd.client.RD.service.mapper;

import com.avaliacao.rd.client.RD.entity.Client;
import com.avaliacao.rd.client.RD.model.ClientModel;
import com.avaliacao.rd.client.RD.service.request.ClientRequestDto;
import com.avaliacao.rd.client.RD.service.response.ClientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper MAPPER = Mappers.getMapper(ClientMapper.class);

    @Named("toModel")
    default ClientModel toModel(ClientRequestDto dto){
        return ClientModel.builder().firstName(dto.getFirstName()).lastName(dto.getLastName()).active(true).build();
    }

    ClientModel toModel(Client client);

    Client toEntity(ClientModel clientModel);

    ClientResponseDto toDto(Client client);
}
