package com.example.repository.inter;

import com.example.model.produit;

import java.sql.SQLException;
import java.util.List;

public interface produitInterface {
    void insertProduit(produit produit);
    List<produit> findAllProduit() throws SQLException;

    produit getById(int id_produit) throws SQLException;

    void delete(int id_produit) throws SQLException;
    produit update( int id_produit, String nom_produit,String description_produit,int prix_produit,int quatité_produit) throws SQLException;
}
