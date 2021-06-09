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

public class RegisterInfoController implements Initializable {
    public TextField fieldPatientName;
    public TextField fieldPatientSex;
    public TextField fieldPatientAge;
    public TextField fieldPatientPhone;
    public TextField fieldDoctorName;
    public TextField fieldDoctorDepartment;
    public TextField fieldDoctorJob;
    public TextField fieldRegisterFee;
    public Button btnReturn;
    public Label labRegisterNum;
    public Label labRegisterTime;
    public Label labIsExpert;
    private Main registerInfoApp;

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
            Patient patient=new Patient(rs.getInt(1),rs.getString(2), rs.getString(3),
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
            rs.next();
            Doctor doctor=new Doctor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),
                    rs.getDate(5),rs.getString(6),rs.getString(7),rs.getInt(8)==1,rs.getString(9),
                    rs.getString(10),rs.getInt(11));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
