package ImplementazioneDAO;

import dao.VoloDAO;
import db.ConnessioneDatabase;
import model.Volo;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneVoloDAO implements VoloDAO {

    private Connection connection;

    public ImplementazioneVoloDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(ArrayList<Volo> voli) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli;");

            while (rs.next()) {
                Volo v = new Volo();
                v.setCodice(rs.getString("id"));
                v.setCompagniaAerea(rs.getString("compagnia"));
                v.setDataPartenza(rs.getString("origine"));
                v.setDestinazione(rs.getString("destinazione"));
                v.setDataPartenza(rs.getString("data_partenza"));
                v.setOrarioPartenza(rs.getString("ora_partenza"));
                voli.add(v);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Volo getVoloPerId(String codiceVolo) {
        Volo v = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli WHERE id = '" + codiceVolo + "';");

            if (rs.next()) {
                v = new Volo();
                v.setId(rs.getString("id"));
                v.setCompagnia(rs.getString("compagnia"));
                v.setPartenza(rs.getString("partenza"));
                v.setDestinazione(rs.getString("destinazione"));
                v.setDataPartenza(rs.getString("data_partenza"));
                v.setOraPartenza(rs.getString("ora_partenza"));
                v.setPostiDisponibili(rs.getInt("posti_disponibili"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void setPrenotazione(int id_prenotazione) {
        try {
            Statement stmt = connection.createStatement();

            // Riduce di 1 il numero di posti disponibili nel volo associato alla prenotazione
            stmt.executeUpdate(
                    "UPDATE voli SET posti_disponibili = posti_disponibili - 1 " +
                            "WHERE id = (SELECT id_volo FROM prenotazioni WHERE id = " + id_prenotazione + ");"
            );

            connection.close();


