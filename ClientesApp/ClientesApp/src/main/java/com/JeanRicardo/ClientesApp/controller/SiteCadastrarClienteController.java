/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Jean
 */


@Controller
public class SiteCadastrarClienteController {
    /*
    http://localhost:8080/cadastrarcliente
     */
    
    @GetMapping("/cadastrarcliente")
    public String index() throws Exception {
        return "cadastroClientes.html";
    }
    
}
