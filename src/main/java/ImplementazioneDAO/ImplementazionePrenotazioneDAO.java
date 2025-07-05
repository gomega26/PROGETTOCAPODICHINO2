package ImplementazioneDAO;

import dao.PrenotazioneDAO;
import db.ConnessioneDatabase;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazionePrenotazioneDAO implements PrenotazioneDAO {

    private Connection connection;
    private static int id = 0;

    public ImplementazionePrenotazioneDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPrenotazioniPerUtenteGenerico(int idUser, ArrayList<Prenotazione> prenotazioni) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_utente = " + idUser + ";"
            );

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe"),
                        rs.getString("stato")
                );
                prenotazioni.add(p);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(ArrayList<Prenotazione> prenotazioni) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prenotazioni;");

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe"),
                        rs.getString("stato")
                );
                prenotazioni.add(p);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int create(String posto, String classe, int id_passeggero, String id_volo, int idUser) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO prenotazioni " +
                            "(\"id\", \"posto\", \"classe\", \"id_passeggero\", \"id_volo\", \"id_utente\", \"stato\", \"num_bagagli\") " +
                            "VALUES (" + id + ", '" + posto + "', '" + classe + "', " +
                            id_passeggero + ", '" + id_volo + "', " + idUser + ", 'IN ATTESA', 0);"
            );
            ps.executeUpdate();
            connection.close();
            id++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Prenotazione getPerIdUtenteGenerico(int idUser, int codicePrenotazione) {
        Prenotazione p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_utente = " + idUser +
                            " AND id = " + codicePrenotazione + ";"
            );

            if (rs.next()) {
                p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe"),
                        rs.getString("stato")
                );
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public int getIdVolo(int idPrenotazione) {
        int idVolo = 0;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_volo FROM prenotazioni WHERE id = " + idPrenotazione + ";"
            );

            if (rs.next())
                idVolo = Integer.parseInt(rs.getString("id_volo"));

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idVolo;
    }

    @Override
    public void checkIn(int idPrenotazione, int numBiglietto) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE prenotazioni SET stato = 'CONFERMATA', numBiglietto = " + numBiglietto +
                            " WHERE id = " + idPrenotazione + ";"
            );
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifica(int codicePrenotazione, String posto, String classeVolo) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE prenotazioni SET posto = '" + posto + "', classe = '" + classeVolo + "', num_bagagli = 0 " +
                            "WHERE id = " + codicePrenotazione + ";"
            );
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
