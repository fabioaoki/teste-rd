package com.avaliacao.rd.client.RD.command;

import com.avaliacao.rd.client.RD.service.ClientService;
import com.avaliacao.rd.client.RD.service.request.ClientRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Commands {

    private final ClientService clientService;

    @Autowired
    public Commands(ClientService clientService) {
        this.clientService = clientService;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            ClientRequestDto raul = ClientRequestDto.builder().firstName("rau")
                    .lastName("fuzita")
                    .build();
            clientService.save(raul);

            ClientRequestDto userFabio = ClientRequestDto.builder().firstName("fabio")
                    .lastName("aoki")
                    .build();
            clientService.save(userFabio);

            ClientRequestDto userLais = ClientRequestDto.builder().firstName("lAIS")
                    .lastName("aOKI")
                    .build();
            clientService.save(userLais);

//            UserPatchRequestDto raulUpdate = UserPatchRequestDto.builder().firstName("raul").build();
//            userService.updateUser(1L, raulUpdate);

        };
    }
}
