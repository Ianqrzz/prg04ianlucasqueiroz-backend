package br.com.ifba.client;

import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Log4j2
public class SpringClient {

    public static void main(String[] args){

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/usuario")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


        UsuarioPostRequestDto usuarioPostRequestDto = new UsuarioPostRequestDto();

        usuarioPostRequestDto.setNome("Pedro Edu");
        usuarioPostRequestDto.setSenha("123456");
        usuarioPostRequestDto.setEmail("pedroedu3@gmail.com");
        usuarioPostRequestDto.setEndereco("Rua dos bobos n 5");


        String response = webClient.post()
                .uri("/save")
                .body(Mono.just(usuarioPostRequestDto), UsuarioPostRequestDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Resposta do save: "+ response);

        String responseGet = webClient.get()
                .uri("/findall")
                .retrieve()
                .bodyToMono(String.class)
                .block();

            log.info("Resposta do findall: "+ responseGet);




    }
}
