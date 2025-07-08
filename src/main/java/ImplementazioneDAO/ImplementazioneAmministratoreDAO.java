package ImplementazioneDAO;

import dao.AmministratoreDAO;
import db.ConnessioneDatabase;
import model.Amministratore;
import model.Bagaglio;
import model.StatoBagaglio;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneAmministratoreDAO implements AmministratoreDAO {

    private Connection connection;

    public ImplementazioneAmministratoreDAO() {
        try {
            connection=ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void signIn(String login, String password, String email) {

        try {
            PreparedStatement saveAmministratorePS = connection.prepareStatement(
                    "INSERT INTO amministratori " +
                            "(\"login\", \"email\", \"password\") " +
                            "VALUES ('" +
                            login + "', '" +
                            email + "', '" +
                            password + "');"
            );
            saveAmministratorePS.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Amministratore logIn(String login, String password){

        Amministratore user=null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM amministratori " +
                            "WHERE \"login\" = '" + login + "' " +
                            "AND \"password\" = '" + password + "';"
            );

            if (rs.next())
                user = new Amministratore(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("email"));


            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void inserisciVolo(int idUser, String idVolo) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO gestione " +
                            "(\"id_amministratore\", \"id_volo\") " +
                            "VALUES (" + idUser + ", '" +
                            idVolo + "');"
            );
            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
