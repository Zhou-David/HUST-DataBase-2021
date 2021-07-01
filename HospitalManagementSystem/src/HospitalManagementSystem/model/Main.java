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

/**
 * 主类
 */
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
            MainController mainController = (MainController) replaceSceneContent("/HospitalManagementSystem/resources/Main.fxml");
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
            DoctorLoginController doctorLoginController=(DoctorLoginController) replaceSceneContent("/HospitalManagementSystem/resources/DoctorLogin.fxml");
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
            ServiceController serviceController=(ServiceController) replaceSceneContent("/HospitalManagementSystem/resources/Service.fxml");
            serviceController.setServiceApp(this);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至医师服务界面
     * @param doctorID:医师人员id
     */
    public void gotoDoctorService(int doctorID){
        try {
            DoctorServiceController doctorServiceController=(DoctorServiceController) replaceSceneContent("/HospitalManagementSystem/resources/DoctorService.fxml");
            doctorServiceController.setDoctorServiceApp(this,doctorID);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至管理界面
     */
    public void gotoManager(){
        try {
            MINIMUM_WINDOW_WIDTH=800;
            ManagerController managerController=(ManagerController) replaceSceneContent("/HospitalManagementSystem/resources/Manager.fxml");
            managerController.setManagerApp(this);
            MINIMUM_WINDOW_WIDTH=600;
            MINIMUM_WINDOW_HEIGHT=400;
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    /**
     * 跳转至挂号信息界面
     * @param registerNum:挂号单号
     */
    public void gotoRegister(int registerNum){
        try {
            RegisterInfoController registerInfoController=(RegisterInfoController) replaceSceneContent("/HospitalManagementSystem/resources/RegisterInfo.fxml");
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

    /**
     * 主函数
     * @param args :参数
     */
    public static void main(String[] args) {
        //连接到数据库
        Func.ConnectToDataBase();

        launch(args);

        //关闭数据库连接
        Func.CloseConnection();
    }
}
