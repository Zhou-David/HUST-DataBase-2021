package HospitalManagementSystem.model;

import HospitalManagementSystem.controller.*;
import HospitalManagementSystem.function.Func;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    /**
     * 界面
     */
    private Stage stage;

    /**
     * 界面宽度
     */
    private double MINIMUM_WINDOW_WIDTH = 600.0;

    /**
     * 界面高度
     */
    private double MINIMUM_WINDOW_HEIGHT = 400.0;

    @Override
    public void start(Stage primaryStage) {
        stage=primaryStage;
        gotoMain();
        stage.show();
    }

    /**
     * 跳转至主界面
     */
    public void gotoMain(){
        try {
            MainController mainController = (MainController) replaceSceneContent("../resources/Main.fxml");
            mainController.setMyApp(this);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至医师登陆界面
     */
    public void gotoDoctorLogin(){
        try {
            DoctorLoginController doctorLoginController=(DoctorLoginController) replaceSceneContent("../resources/DoctorLogin.fxml");
            doctorLoginController.setLoginApp(this);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至看病服务界面
     */
    public void gotoService(){
        try {
            ServiceController serviceController=(ServiceController) replaceSceneContent("../resources/Service.fxml");
            serviceController.setServiceApp(this);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至医师服务界面
     */
    public void gotoDoctorService(int doctorID){
        try {
            MINIMUM_WINDOW_WIDTH=800;
            DoctorServiceController doctorServiceController=(DoctorServiceController) replaceSceneContent("../resources/DoctorService.fxml");
            doctorServiceController.setDoctorServiceApp(this,doctorID);
            MINIMUM_WINDOW_WIDTH=600;
            MINIMUM_WINDOW_HEIGHT=400;
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至挂号信息界面
     */
    public void gotoRegister(int registerNum){
        try {
            RegisterInfoController registerInfoController=(RegisterInfoController) replaceSceneContent("../resources/RegisterInfo.fxml");
            registerInfoController.setRegisterInfoApp(this,registerNum);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 界面替换
     * @param fxml:需要替换成的界面
     * @return :
     * @throws Exception:
     */
    private Initializable replaceSceneContent(String fxml) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);

        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));

        Pane pane;
        pane = loader.load(in);
        assert in != null;
        in.close();

        Scene scene = new Scene(pane,MINIMUM_WINDOW_WIDTH,MINIMUM_WINDOW_HEIGHT);
        stage.setScene(scene);
        return loader.getController();
    }


    public static void main(String[] args) {
        //连接到数据库
        Func.ConnectToDataBase();

        launch(args);

        //关闭数据库连接
        Func.CloseConnection();
    }
}
