/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.controller;

import com.JeanRicardo.ClientesApp.ferramentas.Ferramentas;
import com.JeanRicardo.ClientesApp.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicoCadastrarClienteController {

    /*
    http://localhost:8080/service/cadastrarcliente
    
    {
        "nome":"Bob",
        "documento":"300988",
        "telefones": ["7899938399", "89803993"],
        "enderecos": ["Rua Borges", "Rua Silva"]
    }
     */
    @PostMapping("/service/cadastrarcliente")
    public ResponseEntity<String> index(@RequestBody String str) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
     
        ObjectMapper objectMapper = new ObjectMapper();

        ClienteDTO dto = objectMapper.readValue(str, ClienteDTO.class);

        try {
            Cliente cliente = new Cliente();
            cliente.cadastrarNovo(dto.getNome(), dto.getDocumento(), dto.getTelefones(), dto.getEnderecos());
            String resposta = "{\"Resposta\": \"Cadastrado com Sucesso\"}";
            return new ResponseEntity<>(resposta, headers, HttpStatus.OK);
        } catch (Exception e) {
            String erro = new Ferramentas().removerAspasDuplas(e.getMessage());
            String resposta = "{\"Resposta\": \"Erro: " + erro + "\"}";
            return new ResponseEntity<>(resposta, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
