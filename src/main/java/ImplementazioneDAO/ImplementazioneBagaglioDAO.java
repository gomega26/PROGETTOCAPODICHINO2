package ImplementazioneDAO;

import dao.BagaglioDAO;
import db.ConnessioneDatabase;
import model.Bagaglio;
import model.StatoBagaglio;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneBagaglioDAO implements BagaglioDAO {

    private Connection connection;

    public ImplementazioneBagaglioDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Bagaglio getBagagliPerUtenteGenerico(int idUser, int codiceBagaglio) {
        Bagaglio b = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.* " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.id_prenotazione = p.id " +
                            "WHERE p.id_utente_generico = " + idUser + " AND b.id = " + codiceBagaglio + ";"
            );

            if (rs.next()) {
                b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public Bagaglio getBagagliPerAmministartore(int codiceBagaglio) {
        Bagaglio b = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM bagagli WHERE id = " + codiceBagaglio + ";"
            );

            if (rs.next()) {
                b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public void setStato(int codiceBagaglio, String stato) {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE bagagli SET stato = '" + stato + "' WHERE id = " + codiceBagaglio + ";"
            );
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void create(int id_prenotazione) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO bagagli (id_prenotazione, stato) VALUES (" + id_prenotazione + ", 'Ritirabile');"
            );
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getSmarriti(ArrayList<Bagaglio> bagagliSmarriti) {

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM bagagli WHERE stato = 'SMARRITO';";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Bagaglio b = new Bagaglio();
                b.setCodice(rs.getInt("id"));

                bagagliSmarriti.add(b);
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean segnalaSmarrimento(int idAmministratore, int codice) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.id " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.id_prenotazione = p.id " +
                            "JOIN voli v ON p.codice_volo = v.codice " +
                            "JOIN gestione g ON g.id_volo = v.codice " +
                            "WHERE b.id = " + codice + " AND g.id_amministratore = " + idAmministratore + ";"
            );

            if (rs.next()) {
                stmt.executeUpdate(
                        "UPDATE bagagli SET stato = 'SMARRITO' WHERE id = " + codice + ";"
                );

                return true;
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public void getBagagliPerAmministratore(int idAmministratore, ArrayList<Bagaglio> bagagli) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.* " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.id_prenotazione = p.id " +
                            "JOIN voli v ON p.codice_volo = v.codice " +
                            "JOIN gestione g ON g.id_volo = v.codice " +
                            "WHERE g.id_amministratore = " + idAmministratore + ";"
            );

            while (rs.next()) {
                Bagaglio b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
                bagagli.add(b);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}