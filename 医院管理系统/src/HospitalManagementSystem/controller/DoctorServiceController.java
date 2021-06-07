package HospitalManagementSystem.controller;

import HospitalManagementSystem.model.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorServiceController implements Initializable {
    private Main doctorServiceApp;

    public void setDoctorServiceApp(Main doctorServiceApp){
        this.doctorServiceApp=doctorServiceApp;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
