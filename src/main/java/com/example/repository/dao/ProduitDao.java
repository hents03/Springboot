package com.example.repository.dao.;


import com.example.model.produit;
import com.example.repository.inter.produitInterface;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProduitDao implements produitInterface {
    private Connection connection;

public ProduitDao (Connection connection) {
    this.connection = connection ;
}
    @Override
    public void insertProduit(produit produit) {
        String query = "INSERT INTO produits (id, nom_du_produit,quantite_en_stock,prix) values (?, ?, ?, ?,)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, produit.getId());
            statement.setString(2, produit.getNom_produit());

            statement.setInt(3, produit.getQuantite_en_stock());
            statement.setDouble(4, produit.getPrix());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<produit> findAllProduit() throws SQLException {
        List<produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produit";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
           mapResultSetToProduit(produit,resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;

    }


    @Override
    public produit getById(int id_produit) throws SQLException {
        String sql = "SELECT * FROM produits WHERE id_produit = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_produit);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new produit(
                        result.getInt("id"),
                        result.getString("nom_du_produit"),
                        result.getInt("quantite_en_stock"),
                        result.getDouble("prix")

                );
            }
        }
        return null;
    }


    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM produits WHERE id_produit = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }




    @Override
    public produit update(int id, String nom_produit, int quantite_en_stock, double prix ) throws SQLException {
        produit updatedProduit = new produit(id, nom_produit, quantite_en_stock,prix);
        String sql = "UPDATE produits SET nom_produit = ?, quantite_en_stock = ?, prix = ?, WHERE id_produit = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nom_produit);
            preparedStatement.setInt(3, quantite_en_stock);
            preparedStatement.setDouble(4, prix);
            preparedStatement.executeUpdate();
        }
        return updatedProduit;
    }



    private List<produit> mapResultSetToProduit( List<produit> produits ,ResultSet resultSet) throws SQLException {
        produits.add(
                new produit((
                        resultSet.getInt("id_produit"),
                        resultSet.getString("nom_produit"),
                        resultSet.getString("description_produit"),
                        resultSet.getInt("prix_produit"),
                        resultSet.getInt("quatité_produit")
        ));

        return produits;
    }
}
