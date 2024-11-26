package com.teste.rd.Teste.RD.controller;

import com.teste.rd.Teste.RD.exception.ClientException;
import com.teste.rd.Teste.RD.exception.ErrorResponse;
import com.teste.rd.Teste.RD.request.ClientRequestDto;
import com.teste.rd.Teste.RD.response.ClientResponseDto;
import com.teste.rd.Teste.RD.service.ClientServiceBO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/client")
@Validated
public class ClientController {

    @Autowired
    private ClientServiceBO clientServiceBO;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequestDto clientRequestDto) throws ClientException {
        try {
            log.info("Creating a new client");
            return ResponseEntity.ok(clientServiceBO.save(clientRequestDto));
        } catch (ClientException e) {
            log.error("ClientException: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            log.info("Fetching client with id: {}", id);
            return ResponseEntity.ok(clientServiceBO.findById(id));
        } catch (ClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(e));
        }
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(clientServiceBO.findAll(PageRequest.of(page, size)));
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> clientInactive(@PathVariable Long id) {
        try {
            log.info("Inactive Client with id: {}", id);
            clientServiceBO.changeStatus(id, false);
            return ResponseEntity.ok().build();
        } catch (ClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(e));
        }
    }
    @PutMapping("/active/{id}")
    public ResponseEntity<?> clientActive(@PathVariable Long id) {
        try {
            log.info("Active Client with id: {}", id);
            clientServiceBO.changeStatus(id, true);
            return ResponseEntity.ok().build();
        } catch (ClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(e));
        }
    }

    private static ErrorResponse getErrorResponse(ClientException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(e.getErrorCode());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
