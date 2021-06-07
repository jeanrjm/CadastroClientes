/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.controller;

import com.JeanRicardo.ClientesApp.ferramentas.Ferramentas;
import com.JeanRicardo.ClientesApp.model.Cliente;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

        String nome = "";
        String documento = "";
        ArrayList<String> telefones = new ArrayList<String>();
        ArrayList<String> enderecos = new ArrayList<String>();
        
        HttpHeaders headers= new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                //headers.setContentType(MediaType.TEXT_PLAIN);

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(str);

            nome = jsonObject.get("nome").toString();
            documento = jsonObject.get("documento").toString();

            JSONArray telefonesJSON = (JSONArray) jsonObject.get("telefones");
            Iterator<String> iterator = telefonesJSON.iterator();
            String aux = "";
            while (iterator.hasNext()) {
                aux = iterator.next().toString();
                telefones.add(aux);
            }
            JSONArray enderecosJSON = (JSONArray) jsonObject.get("enderecos");
            iterator = enderecosJSON.iterator();
            while (iterator.hasNext()) {
                aux = iterator.next().toString();
                enderecos.add(aux);
            }

        } catch (Exception e) {
            String erro = new Ferramentas().removerAspasDuplas(e.getMessage());
           String resposta = "{\"Resposta\": \"Erro: "+erro+"\"}";
            return new ResponseEntity<>(resposta, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
            Cliente cliente = new Cliente();
            cliente.cadastrarNovo(nome, documento, telefones, enderecos);
            String resposta = "{\"Resposta\": \"Cadastrado com Sucesso\"}";
            return new ResponseEntity<>(resposta, headers, HttpStatus.OK);
        } catch (Exception e) {
            String erro = new Ferramentas().removerAspasDuplas(e.getMessage());
            String resposta = "{\"Resposta\": \"Erro: "+erro+"\"}";
            return new ResponseEntity<>(resposta, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
