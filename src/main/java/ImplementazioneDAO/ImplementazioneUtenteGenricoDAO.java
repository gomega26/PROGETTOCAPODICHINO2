package ImplementazioneDAO;

import dao.UtenteGenericoDAO;
import db.ConnessioneDatabase;

import model.Utente;
import model.UtenteGenerico;

import java.sql.*;

public class ImplementazioneUtenteGenericoDAO implements UtenteGenericoDAO {

    private Connection connection;
    protected static int id=0;

    public ImplementazioneUtenteGenericoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void signIn(String login, String password, String email) {
        try {
            PreparedStatement saveUtentePS = connection.prepareStatement(
                    "INSERT INTO \"utente\" " +
                            "(\"id\", \"login\", \"email\", \"password\") " +
                            "VALUES (" + id + ", '" +
                            login + "', '" +
                            email + "', '" +
                            password + "');"
            );
            saveUtentePS.executeUpdate();
            connection.close();
            id++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UtenteGenerico logIn(String login, String password){

        UtenteGenerico user=null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM Utenti Generici " +
                            "WHERE \"login\" = '" + login + "' " +
                            "AND \"password\" = '" + password + "';"
            );

            if (rs.next())
                user = new UtenteGenerico(rs.getString("login"), rs.getString("password"), rs.getString("email"));

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
