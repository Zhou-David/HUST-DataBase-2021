package HospitalManagementSystem.model;

import HospitalManagementSystem.function.Func;

import static javafx.application.Application.launch;

public class APP {
    public static void main(String[] args) {
        //连接到数据库
        Func.ConnectToDataBase();

        launch(Main.class);

        //关闭数据库连接
        Func.CloseConnection();
    }
}
