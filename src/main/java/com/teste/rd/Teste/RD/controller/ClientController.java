package com.teste.rd.Teste.RD.controller;

import com.teste.rd.Teste.RD.exception.ClientException;
import com.teste.rd.Teste.RD.exception.ErrorResponse;
import com.teste.rd.Teste.RD.request.ClientRequestDto;
import com.teste.rd.Teste.RD.service.ClientServiceBO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServiceBO clientServiceBO;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createClient(
            @RequestBody ClientRequestDto clientRequestDto) throws ClientException {
        try {
            log.info("Creating a new client");
            return ResponseEntity.ok(clientServiceBO.save(clientRequestDto));
        } catch (ClientException e) {
            log.error("ClientException: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(e));
        }
    }

    private static ErrorResponse getErrorResponse(ClientException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(e.getErrorCode());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
