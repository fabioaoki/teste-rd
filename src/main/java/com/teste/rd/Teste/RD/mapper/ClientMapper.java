package com.teste.rd.Teste.RD.mapper;

import com.teste.rd.Teste.RD.entity.Client;
import com.teste.rd.Teste.RD.model.ClientModel;
import com.teste.rd.Teste.RD.request.ClientRequestDto;
import com.teste.rd.Teste.RD.response.ClientResponseDto;
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

    Client toEntity(ClientModel clientModel);

    ClientResponseDto toDto(Client client);
}
