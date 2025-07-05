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
    protected static int id=0;

    public ImplementazioneAmministratoreDAO() {
        try {
            connection=ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void signIn(String login, String password, String email) {

        try {
            PreparedStatement saveAmministratorePS = connection.prepareStatement(
                    "INSERT INTO amministratori " +
                            "(\"id\", \"login\", \"email\", \"password\") " +
                            "VALUES (" + id + ", '" +
                            login + "', '" +
                            email + "', '" +
                            password + "');"
            );
            saveAmministratorePS.executeUpdate();
            connection.close();
            id++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Amministratore logIn(String login, String password){

        Amministratore user=null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM Amministratori " +
                            "WHERE \"login\" = '" + login + "' " +
                            "AND \"password\" = '" + password + "';"
            );

            if (rs.next())
                user = new Amministratore(rs.getString("login"), rs.getString("password"), rs.getString("email"));

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void getBagagli(int id, ArrayList<Bagaglio> bagagli) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.* " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.prenotazione_id = p.id " +
                            "JOIN voli v ON p.volo_id = v.id " +
                            "JOIN gestione g ON g.id_volo = v.id " +
                            "WHERE g.id_amministratore = " + id + ";"
            );

            while (rs.next()) {
                Bagaglio b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
                bagagli.add(b);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
