package ImplementazioneDAO;

import dao.AmministratoreDAO;
import db.ConnessioneDatabase;
import model.Amministratore;
import model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                    "INSERT INTO \"Amministratori\" " +
                            "(\"Id\", \"Login\", \"Email\", \"Password\") " +
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
}
