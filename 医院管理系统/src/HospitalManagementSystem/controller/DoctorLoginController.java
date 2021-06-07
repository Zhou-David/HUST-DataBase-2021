package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorLoginController implements Initializable {
    /**
     * 登陆界面App
     */
    private Main loginApp;

    /**
     * 账户块
     */
    public HBox hbUser;

    /**
     * 账户标签
     */
    public Label labelUser;

    /**
     * 账户输入
     */
    public TextField fieldUser;

    /**
     * 密码块
     */
    public HBox hbPassword;

    /**
     * 密码标签
     */
    public Label labelPassword;

    /**
     * 密码输入
     */
    public PasswordField fieldPassword;

    /**
     * 按钮块
     */
    public HBox hbButton;

    /**
     * 返回按钮
     */
    public Button btnReturn;

    /**
     * 登陆按钮
     */
    public Button btnLogin;

    /**
     * 注册按钮
     */
    public Button btnRegister;

    /**
     * 设置登陆界面APP
     * @param loginApp:传入参数
     */
    public void setLoginApp(Main loginApp){
        this.loginApp=loginApp;
    }

    /**
     * 登录
     * @param event:触发事件
     */
    public void onClickLogin(ActionEvent event) {
        int id = Func.Check(fieldUser.getText(), fieldPassword.getText());
        if (id > 0) {
            this.loginApp.gotoDoctorService();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "账户或密码有误，请检查输入").showAndWait();
        }
    }

    /**
     * 注册
     * @param event:触发事件
     */
    public void onClickRegister(ActionEvent event) {
        if(fieldUser.getText().equals("")||fieldPassword.getText().equals("")){
            new Alert(Alert.AlertType.INFORMATION, "注册失败！请填写完整的用户名和密码").showAndWait();
            return;
        }
        try{
            String sql;
            ResultSet rs;

            //比较用户名是否重复
            sql = "SELECT 用户名 FROM 用户";
            rs = Func.statement.executeQuery(sql);
            while (rs.next()){
                if(rs.getString(1).equals(fieldUser.getText())){
                    new Alert(Alert.AlertType.INFORMATION, "该用户名已存在！").showAndWait();
                    return;
                }
            }

            //找到目前用户数目，以确定添加的用户ID
            sql = "SELECT COUNT(*) AS NUM FROM 用户";
            rs = Func.statement.executeQuery(sql);
            rs.next();
            int userNum = rs.getInt("NUM");

            //添加新的用户
            sql = "INSERT INTO 用户 VALUES("+(userNum+1)+ ",'" +fieldUser.getText()+"','"+fieldPassword.getText()+"',0)";
            int len = Func.statement.executeUpdate(sql);
            new Alert(Alert.AlertType.INFORMATION, len>0?"注册成功":"注册失败").showAndWait();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 返回上一级
     * @param event:触发事件
     */
    public void onClickReturn(ActionEvent event){
        this.loginApp.gotoMain();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
