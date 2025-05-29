package gui;

import controller.Controller;

import javax.swing.*;

public class HomePageAmministratore {
    private JPanel panel1;
    private static JFrame frame;

    private JFrame frameChiamante;
    private Controller controller;

    public HomePageAmministratore(JFrame frameChiamante, Controller controller){
        frame = new JFrame("HomePageAmministratore");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
