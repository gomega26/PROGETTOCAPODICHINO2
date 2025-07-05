package ImplementazioneDAO;

import dao.BagaglioDAO;
import db.ConnessioneDatabase;
import model.Bagaglio;
import model.StatoBagaglio;

import java.sql.*;

public class ImplementazioneBagaglioDAO implements BagaglioDAO {

    private Connection connection;
    private static int id = 0;

    public ImplementazioneBagaglioDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
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
                            "JOIN prenotazioni p ON b.prenotazione_id = p.id " +
                            "WHERE p.id_utente = " + idUser + " AND b.id = " + codiceBagaglio + ";"
            );

            if (rs.next()) {
                b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
            }

            connection.close();
        } catch (SQLException e) {
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
                    "SELECT b.* " +
                            "WHERE b.id = " + codiceBagaglio + ";"
            );

            if (rs.next()) {
                b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public void create(int id_prenotazione) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO bagagli (id, prenotazione_id, stato) VALUES (" + id + ", " + id_prenotazione + ", 'Ritirabile');"
            );
            ps.executeUpdate();
            connection.close();
            id++;
        } catch (SQLException e) {
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
                            "JOIN prenotazioni p ON b.prenotazione_id = p.id " +
                            "JOIN voli v ON p.volo_id = v.id " +
                            "JOIN gestione g ON g.id_volo = v.id " +
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
            e.printStackTrace();
        }

        return false;
    }
}