package com.example.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class produit {
    private int id;
    private String nom_produit;
    private int quantite_en_stock;
    private Double prix;


}
