package com.avaliacao.rd.client.RD;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.avaliacao.rd.client.RD.entity.Client;
import com.avaliacao.rd.client.RD.exception.ClientException;
import com.avaliacao.rd.client.RD.exception.ErrorCode;
import com.avaliacao.rd.client.RD.model.ClientModel;
import com.avaliacao.rd.client.RD.repository.ClientRepositoryImpl;
import com.avaliacao.rd.client.RD.service.ClientService;
import com.avaliacao.rd.client.RD.service.mapper.ClientMapper;
import com.avaliacao.rd.client.RD.service.request.ClientRequestDto;
import com.avaliacao.rd.client.RD.service.response.ClientResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepositoryImpl repository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    private ClientRequestDto clientRequestDto;
    private ClientModel clientModel;
    private Client clientEntity;
    private ClientResponseDto clientResponseDto;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        LocalDateTime now = LocalDateTime.now();
//        clientRequestDto = new ClientRequestDto("John", "Doe");
//        clientModel = new ClientModel(1L, "John", "Doe", LocalDateTime.now(), null, true);
//        clientEntity = new Client(1L, "John", "Doe", LocalDateTime.now(), null, true);
//        clientResponseDto = new ClientResponseDto(1L, "John", "Doe", LocalDateTime.now(), null, true);
//
//        // Setup mock behavior
//        when(clientMapper.toModel(any(ClientRequestDto.class))).thenReturn(clientModel);
//        when(repository.save(any(Client.class))).thenReturn(clientEntity);
//        when(clientMapper.toDto(any(Client.class))).thenReturn(clientResponseDto);
    }

    @Test
    void save_shouldSaveClient_whenClientIsValid() {

        // Preparação dos dados
        ClientRequestDto dto = new ClientRequestDto("John", "Doe");
        ClientModel model = new ClientModel(1L, "John", "Doe", null, null, true);
        Client entity = new Client(1L, "John", "Doe", null, null, true);
        ClientResponseDto responseDto = new ClientResponseDto(1L, "John", "Doe", LocalDateTime.now(), null, true);

        when(clientMapper.toModel(any(ClientRequestDto.class))).thenReturn(model);
        when(repository.save(any(Client.class))).thenReturn(entity);
        when(clientMapper.toDto(any(Client.class))).thenReturn(responseDto);

        ClientResponseDto savedDto = clientService.save(dto);

        verify(repository, times(1)).save(any(Client.class));
        assertEquals("John", savedDto.getFirstName());
        assertEquals("Doe", savedDto.getLastName());
        assertTrue(savedDto.isActive());
    }

    @Test
    void save_shouldThrowException_whenFirstNameIsNull() {
        ClientRequestDto dto = new ClientRequestDto(null, "Doe");

        assertThrows(ClientException.class, () -> clientService.save(dto));
    }

    @Test
    void save_shouldThrowException_whenFirstNameIsEmpty() {
        ClientRequestDto dto = new ClientRequestDto(" ", "Doe");

        assertThrows(ClientException.class, () -> clientService.save(dto));
    }

    @Test
    void save_shouldThrowException_whenLastNameIsNull() {
        ClientRequestDto dto = new ClientRequestDto("John", null);

        assertThrows(ClientException.class, () -> clientService.save(dto));
    }

    @Test
    void save_shouldThrowException_whenLastNameIsEmpty() {
        ClientRequestDto dto = new ClientRequestDto("John", " ");

        assertThrows(ClientException.class, () -> clientService.save(dto));
    }

    @Test
    void save_shouldThrowException_whenFirstNameHasInvalidCharacters() {
        ClientRequestDto dto = new ClientRequestDto("John123", "Doe");

        assertThrows(ClientException.class, () -> clientService.save(dto));
    }

    @Test
    void save_shouldThrowException_whenLastNameHasInvalidCharacters() {
        ClientRequestDto dto = new ClientRequestDto("John", "Doe@!");

        assertThrows(ClientException.class, () -> clientService.save(dto));
    }

    @Test
    void findById_ShouldReturnClient_WhenClientExists() {
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", LocalDateTime.now(), null, true);
        ClientResponseDto expectedDto = new ClientResponseDto(clientId, "John", "Doe", LocalDateTime.now(), null, true);

        when(repository.findById(clientId)).thenReturn(Optional.of(client));

        ClientResponseDto resultDto = clientService.findById(clientId);

        assertNotNull(resultDto);
        assertEquals(expectedDto.getId(), resultDto.getId());
        assertEquals(expectedDto.getFirstName(), resultDto.getFirstName());
        assertEquals(expectedDto.getLastName(), resultDto.getLastName());
        assertEquals(expectedDto.isActive(), resultDto.isActive());
    }

    @Test
    void findById_ShouldThrowException_WhenClientNotFound() {
        Long clientId = 999L;
        when(repository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ClientException.class, () -> clientService.findById(clientId));
    }

    @Test
    void findAll_ShouldReturnClients_WhenClientsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Client> clients = List.of(new Client(1L, "John", "Doe", LocalDateTime.now(), null, true));
        Page<Client> page = new PageImpl<>(clients);

        when(repository.findAll(pageable)).thenReturn(page);

        Page<ClientResponseDto> result = clientService.findAll(pageable);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getContent().size());
        assertEquals("John", result.getContent().get(0).getFirstName());
    }

    @Test
    void findAll_ShouldReturnEmpty_WhenNoClientsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Client> page = Page.empty();

        when(repository.findAll(pageable)).thenReturn(page);

        Page<ClientResponseDto> result = clientService.findAll(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void changeStatus_ShouldNotUpdate_WhenStatusIsSame() {
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", LocalDateTime.now(), null, true);
        when(repository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.changeStatus(clientId, true);

        verify(repository, never()).save(any(Client.class));
    }


}