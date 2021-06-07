/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.ferramentas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Jean
 */
public class Conexao {
     private Connection c;
    private PreparedStatement stm;
    private String sql;

    public Connection criarConexao() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");

            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cadastro",
                            "postgres", "adm");

            return c;
        } catch (Exception ex) {
            throw new Exception("Problema ao se conectar base de dados");
        }
    }
}
