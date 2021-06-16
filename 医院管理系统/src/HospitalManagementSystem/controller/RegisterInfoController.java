package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Main;
import HospitalManagementSystem.model.Patient;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * 挂号信息界面控制器
 */
public class RegisterInfoController implements Initializable {
    /**
     * 病人姓名
     */
    public TextField fieldPatientName;

    /**
     * 病人性别
     */
    public TextField fieldPatientSex;

    /**
     * 病人年龄
     */
    public TextField fieldPatientAge;

    /**
     * 病人电话号码
     */
    public TextField fieldPatientPhone;

    /**
     * 医师姓名
     */
    public TextField fieldDoctorName;

    /**
     * 医师所属科室
     */
    public TextField fieldDoctorDepartment;

    /**
     * 医师职务
     */
    public TextField fieldDoctorJob;

    /**
     * 医师挂号费
     */
    public TextField fieldRegisterFee;

    /**
     * 返回按钮
     */
    public Button btnReturn;

    /**
     * 挂号单号信息
     */
    public Label labRegisterNum;

    /**
     * 挂号时间信息
     */
    public Label labRegisterTime;

    /**
     * 是否为专家号信息
     */
    public Label labIsExpert;

    /**
     * 挂号信息界面App
     */
    private Main registerInfoApp;

    /**
     * 设置挂号信息界面
     * @param registerInfoApp:挂号信息界面App
     * @param registerNum:挂号信息单号
     */
    public void setRegisterInfoApp(Main registerInfoApp,int registerNum) {
        this.registerInfoApp = registerInfoApp;

        try {
            String sql;
            ResultSet rs;

            //找到对应的挂号单
            sql ="SELECT * FROM 挂号信息 WHERE id="+registerNum;
            rs = Func.statement.executeQuery(sql);
            rs.next();
            int patientNum=rs.getInt("就诊病人");
            int doctorNum=rs.getInt("医生");
            Date date=rs.getDate("挂号时间");
            Date time=rs.getTime("挂号时间");

            //获取病人信息
            sql = "SELECT * FROM 病人信息 WHERE id="+patientNum;
            rs = Func.statement.executeQuery(sql);
            rs.next();
            Patient patient=new Patient(rs.getInt(1), rs.getString(3),
                    rs.getString(4),rs.getDate(5),rs.getString(6));
            fieldPatientName.setText(patient.getName());
            fieldPatientSex.setText(patient.getSex());
            fieldPatientAge.setText(""+patient.getAge());
            fieldPatientPhone.setText(patient.getPhone());

            //获取医师信息
            sql="SELECT 医.id, 姓名, 性别, 出生日期, 入职日期, 科室名称, 职务, 是否为专家, 电话号码, 电子邮箱,挂号费 " +
                    "FROM 医生信息 医 JOIN 科室信息 科 on 医.所属科室 = 科.id "+
                    "WHERE 医.id="+doctorNum;
            rs = Func.statement.executeQuery(sql);
            Doctor doctor=Func.setDoctorInfo(rs);
            fieldDoctorName.setText(doctor.getName());
            fieldDoctorDepartment.setText(doctor.getDepartment());
            fieldDoctorJob.setText(doctor.getJob());
            fieldRegisterFee.setText(""+doctor.getRegisterFee());

            //填写挂号单其他信息
            labRegisterNum.setText(""+registerNum);
            labRegisterTime.setText(date.toString()+"  "+time.toString());
            labIsExpert.setText(doctor.getIsExpert()?"是":"否");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 返回上一级
     */
    public void onClickReturn(){
        this.registerInfoApp.gotoService();
    }

    /**
     * 初始化
     * @param url:
     * @param resourceBundle:
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
