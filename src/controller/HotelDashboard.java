/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Chambre;
import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import services.ChambreService;
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author 123
 */
public class HotelDashboard implements Initializable {

    @FXML
    private StackedBarChart<?, ?> StackBar;
    @FXML
    private PieChart PieChart;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HotelService hs = new HotelService();
        ChambreService cs = new ChambreService();

        List<Hotel> listH = new ArrayList<>();
        List<Chambre> listCh = new ArrayList<>();

        try {
            listH = hs.afficherHotel();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            listCh = cs.afficherChambre();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int nbIb = 0;
        int nbGol = 0;
        int nbTab = 0;
        int nbBr = 0;
        int nbLaB = 0;
        int nbRam = 0;
        int nbRoy = 0;
        int nbF = 0;

        for (Hotel h : listH) {
            if (h.getNomHotel().equals("iberostar")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbIb++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("Goldanyasmin")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbGol++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("TabarcaThalasou")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbTab++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("BravoDjerba")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbBr++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("LaBadira")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbLaB++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("RamadaPlaza")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbRam++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("RoyalThalassa")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbRoy++;
                        }
                    }
                }
            }

            if (h.getNomHotel().equals("forSeasonTes")) {
                for (Chambre ch : listCh) {
                    if (ch.getHotel_id() == h.getId()) {
                        if (ch.getDispo().equals("Non Disponible")) {
                            nbF++;
                        }
                    }
                }
            }

        }
//        System.out.println(nbIb + "" + nbGol + "" + nbTab + "" + nbBr + "" + nbLaB + "" + nbRam + "" + nbRoy + "" + nbF);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Iberostar : ", nbIb),
                new PieChart.Data("Goldan yasmin", nbGol),
                new PieChart.Data("Tabarca Thalasou", nbTab),
                new PieChart.Data("Bravo Djerba", nbBr),
                new PieChart.Data("La Badira", nbLaB),
                new PieChart.Data("Ramada Plaza", nbRam),
                new PieChart.Data("Royal Thalassa", nbRoy),
                new PieChart.Data("for Seasones", nbF)
        );

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Hotels visités");
        PieChart.setData(pieChartData);

        // XY CHART
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Les Hotels les plus visitées ");
        dataSeries1.getData().add(new XYChart.Data("Iberostar : ", nbIb));
        dataSeries1.getData().add(new XYChart.Data("Goldan yasmin", nbGol ));
        dataSeries1.getData().add(new XYChart.Data("Tabarca Thalasou", nbTab));
        dataSeries1.getData().add(new XYChart.Data("Bravo Djerba", nbBr ));
        dataSeries1.getData().add(new XYChart.Data("La Badira", nbLaB ));
        dataSeries1.getData().add(new XYChart.Data("Ramada Plaza", nbRam ));
        dataSeries1.getData().add(new XYChart.Data("Royal Thalassa", nbRoy ));
        dataSeries1.getData().add(new XYChart.Data("for Seasones", nbF ));
        StackBar.getData().add(dataSeries1);
        DecimalFormat df = new DecimalFormat("0.00");
    }

    @FXML
    private void retourVol(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/home.fxml"));
        Parent root = loader.load();
        home.Controller ac = loader.getController();
        retour.getScene().setRoot(root);
    }

}
