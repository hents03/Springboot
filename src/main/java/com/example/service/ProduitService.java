package com.example.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProduitService {
    private ProduitDao produitRepository;
    public ProduitService (ProduitDao dao){
        this.produitRepository = dao;
    }

    public void insertProduit(Produit produit) {
        produitRepository.insertProduit(produit);
    }
    public List<Produit> findAllProduit() throws SQLException {
        return produitRepository.findAllProduit();
    }

    public Produit getProduitById(int id_produit) throws SQLException {
        Produit produit = produitRepository.getById(id_produit);
        return produit;
    }

    public Produit updateProduit(int idProduit, String nomProduit, String descriptionProduit, int prixProduit, int quantiteProduit) throws SQLException {
        return produitRepository.update(idProduit, nomProduit, descriptionProduit, prixProduit, quantiteProduit);
    }
    public void deleteProduit(int id_produit) throws SQLException {
        produitRepository.delete(id_produit);
    }
}
