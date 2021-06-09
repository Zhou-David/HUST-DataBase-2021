package HospitalManagementSystem.controller;

import HospitalManagementSystem.model.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    /**
     * 医师登陆块
     */
    public HBox hbLogin;

    /**
     * 看病服务块
     */
    public HBox hbService;

    /**
     * 医师登陆按钮
     */
    public Button btnLogin;

    /**
     * 看病服务按钮
     */
    public Button btnService;

    /**
     * 主界面APP
     */
    private Main myApp;

    /**
     * 设置注解面APP
     * @param myApp:传入参数
     */
    public void setMyApp(Main myApp) {
        this.myApp = myApp;
    }

    /**
     * 点击医师登陆后进行的操作
     */
    public void onClickLogin(){
        this.myApp.gotoDoctorLogin();
    }

    /**
     * 点击看病服务后进行的操作
     */
    public void onClickService() {
        this.myApp.gotoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
