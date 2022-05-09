/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import services.VolService;

/**
 * FXML Controller class
 *
 * @author 123
 */
public class Dashboard implements Initializable {

    @FXML
    private StackedBarChart<?, ?> StackBar;
    @FXML
    private PieChart PieChart;

    ControlVol vc = new ControlVol();
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            VolService vc = new VolService();
            double totale = vc.getNbrVolTotal();
            double totParis = vc.getNbrVolParis();
            double totEgypt = vc.getNbrVolEgypte();
            double totEspagne = vc.getNbrVolEspagne();
            double totDubai = vc.getNbrVolDubai();
            double totChine = vc.getNbrVolChine();
            double totItalie = vc.getNbrVolItalie();
            double totTurquie = vc.getNbrVolTurquie();
            double totAlmagne = vc.getNbrVolAllemagne();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Paris", totParis),
                    new PieChart.Data("Dubai", totDubai),
                    new PieChart.Data("Turquie", totTurquie),
                    new PieChart.Data("Italie", totItalie),
                    new PieChart.Data("Chine", totChine),
                    new PieChart.Data("Allemagne", totAlmagne),
                    new PieChart.Data("Egypte", totEgypt),
                    new PieChart.Data("Espagne", totEspagne)
            );

            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Depot");
            PieChart.setData(pieChartData);

            // XY CHART
            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName("Les Villes les plus visitées ");
            dataSeries1.getData().add(new XYChart.Data("Paris", (totParis / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Dubai", (totDubai / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Egypt", (totEgypt / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Espagne", (totEspagne / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Turquie", (totTurquie / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Italie", (totItalie / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Allemagne", (totAlmagne / totale) * 100));
            dataSeries1.getData().add(new XYChart.Data("Chine ", (totChine / totale) * 100));

            StackBar.getData().add(dataSeries1);
            DecimalFormat df = new DecimalFormat("0.00");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void retourVol(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/home.fxml"));
        Parent root = loader.load();
        home.Controller ac = loader.getController();
        retour.getScene().setRoot(root);
    }
}
