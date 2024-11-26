package com.teste.rd.Teste.RD.service;

import com.teste.rd.Teste.RD.entity.Client;
import com.teste.rd.Teste.RD.exception.ClientException;
import com.teste.rd.Teste.RD.exception.ErrorCode;
import com.teste.rd.Teste.RD.mapper.ClientMapper;
import com.teste.rd.Teste.RD.model.ClientModel;
import com.teste.rd.Teste.RD.repository.ClientRepositoryImpl;
import com.teste.rd.Teste.RD.request.ClientRequestDto;
import com.teste.rd.Teste.RD.response.ClientResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Log4j2
@Service
public class ClientService implements ClientServiceBO {

    private static final Logger logger = LogManager.getLogger(ClientService.class);

    private final ClientRepositoryImpl repository;

    @Override
    public ClientResponseDto save(ClientRequestDto dto) {
        logger.info("Received save request for user with initial data: {}", dto);

        try {
            logger.info("Validating request fields");
            validField(dto);

            ClientModel userModel = ClientMapper.MAPPER.toModel(dto);
            logger.debug("Converted UserRequestDto to UserModel: {}", userModel);

            logger.info("Validating names: {}, {}", userModel.getFirstName(), userModel.getLastName());
            isValidName(userModel.getFirstName(), userModel.getLastName());

            userModel.setFirstName(formatName(userModel.getFirstName()));
            userModel.setLastName(formatName(userModel.getLastName()));
            logger.info("Formatted names to: {} {}", userModel.getFirstName(), userModel.getLastName());

            logger.info("Mapping UserModel to User entity");
            Client entity = ClientMapper.MAPPER.toEntity(userModel);
            logger.info("Saving new user to the database: {} {}", entity.getFirstName(), entity.getLastName());
            Client savedEntity = repository.save(entity);
            logger.info("User saved successfully with ID: {}", savedEntity.getId());

            ClientResponseDto responseDto = ClientMapper.MAPPER.toDto(savedEntity);
            logger.info("Returning response DTO for user with ID: {}", responseDto.getId());
            return responseDto;
        } catch (ClientException e) {
            logger.error("Unexpected error during client saving operation: ", e);
            throw new ClientException(ErrorCode.UNEXPECTED_ERROR, "Unexpected error occurred: " + e.getMessage());
        }
    }

    public String formatName(String name) {
        name = name.replaceAll("\\s+", "");
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }


    private static void isValidName(String firstName, String lastName) throws ClientException {
        if (containsInvalidCharacters(firstName) || containsInvalidCharacters(lastName)) {
            throw new ClientException(ErrorCode.INVALID_FIELD, "Names must not contain spaces, numbers, or special characters.");
        }
    }

    private void validField(ClientRequestDto dto) throws ClientException {
        if (dto.getFirstName() == null || dto.getFirstName().trim().isEmpty()) {
            throw new ClientException(ErrorCode.INVALID_FIELD, "The 'firstName' field is mandatory and cannot be empty.");
        }
        if (dto.getLastName() == null || dto.getLastName().trim().isEmpty()) {
            throw new ClientException(ErrorCode.INVALID_FIELD, "The 'lastName' field is mandatory and cannot be empty.");
        }
    }

    private static boolean containsInvalidCharacters(String name) {
        return !name.matches("^[a-zA-Z]+$");
    }

    @Override
    public ClientResponseDto findById(Long id) throws ClientException {
        return null;
    }

    @Override
    public Page<ClientResponseDto> findAll(Pageable pageable) {
        return null;
    }

}
