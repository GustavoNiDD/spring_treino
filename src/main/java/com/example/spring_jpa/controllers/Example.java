package com.example.spring_jpa.controllers;

import java.util.ArrayList;
import java.util.List;

public class Example {
    
    public static void main(String[] args) {
        List<String> listaPessoa = new ArrayList<>();
        listaPessoa.add("João");
        listaPessoa.add("Lucca");
        listaPessoa.add("Fernanda");

        for (String pessoa : listaPessoa) {
            
            System.out.println(pessoa);
        }

       
    }
}
