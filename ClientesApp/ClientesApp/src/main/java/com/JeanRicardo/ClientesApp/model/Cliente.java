/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.model;

import com.JeanRicardo.ClientesApp.ferramentas.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Jean
 */
public class Cliente {

    private Connection c;
    private PreparedStatement stmt;
    private String sql;

    public void cadastrarNovo(String nome, String documento,
            ArrayList<String> telefones, ArrayList<String> enderecos) throws Exception {

        c = new Conexao().criarConexao();

       

        try {
            sql = "INSERT INTO clientes (nome,documento) VALUES (?,?);";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, documento);
            stmt.executeUpdate();

            int id = 0;

            stmt = c.prepareStatement("SELECT id FROM clientes WHERE clientes.documento = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, documento);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.first()) {
                id = resultado.getInt("id");
            }

            String numero;
            for (int a = 0; a < telefones.size(); a++) {
                numero = telefones.get(a);
                sql = "INSERT INTO telefone (idCliente, telefone) VALUES (?,?);";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, numero);
                stmt.executeUpdate();
            }
            

            String endereco;
            boolean isPrincipal = false;
            for (int a = 0; a < enderecos.size(); a++) {
                if ( a == 0) {
                    isPrincipal = true;
                } else {
                    isPrincipal = false;
                }

                endereco = enderecos.get(a);
                sql = "INSERT INTO endereco (idCliente, endereco, principal) VALUES (?,?,?);";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, endereco);
                stmt.setBoolean(3, isPrincipal);
                stmt.executeUpdate();
            }
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
