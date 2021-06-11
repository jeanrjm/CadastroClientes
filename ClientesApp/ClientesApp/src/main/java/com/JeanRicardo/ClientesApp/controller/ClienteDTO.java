/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.controller;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ClienteDTO {

    @JsonProperty("nome")
    private String nome;
    @JsonProperty("documento")
    private String documento;
    @JsonProperty("telefones")
    private List<String> telefones = null;
    @JsonProperty("enderecos")
    private List<String> enderecos = null;

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("documento")
    public String getDocumento() {
        return documento;
    }

    @JsonProperty("documento")
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @JsonProperty("telefones")
    public List<String> getTelefones() {
        return telefones;
    }

    @JsonProperty("telefones")
    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    @JsonProperty("enderecos")
    public List<String> getEnderecos() {
        return enderecos;
    }

    @JsonProperty("enderecos")
    public void setEnderecos(List<String> enderecos) {
        this.enderecos = enderecos;
    }

}
