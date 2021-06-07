/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeanRicardo.ClientesApp.ferramentas;

/**
 *
 * @author Jean
 */
public class Ferramentas {
    
     public String removerAspasDuplas(String str){
        String nova ="";
        for (int i=0; i<str.length(); i++){
            if(str.charAt(i)!='\"'){
                nova+=str.charAt(i);
            }
        }
        
        return nova;
        
    }
    
}
