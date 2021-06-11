package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.Department;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * 医师服务界面控制器
 */
public class DoctorServiceController implements Initializable {
    /**
     * 返回按钮1
     */
    public Button btnReturn1;

    /**
     * 是否为专家选择框
     */
    public CheckBox checkExpert;

    /**
     * 个人信息-姓名
     */
    public TextField fieldName;

    /**
     * 个人信息-性别
     */
    public ChoiceBox<String> cbxSex;

    /**
     * 个人信息-出生日期
     */
    public DatePicker dateBirthday;

    /**
     * 个人信息-工作日期
     */
    public DatePicker dateWorkingDay;

    /**
     * 个人信息-所属科室
     */
    public ChoiceBox<String> cbxDepartment;

    /**
     * 个人信息-职务
     */
    public TextField fieldJob;

    /**
     * 个人信息-电话号码
     */
    public TextField fieldPhone;

    /**
     * 个人信息-电子邮件
     */
    public TextField fieldEmail;

    /**
     * 个人信息-挂号费
     */
    public TextField fieldRegisterFee;

    /**
     * 个人信息提交按钮
     */
    public Button btnSubmit;

    /**
     * 医师服务界面APP
     */
    private Main doctorServiceApp;

    /**
     * 此医师的识别编号
     */
    private int doctorID;

    /**
     * 设置医师服务界面
     * @param doctorServiceApp:医师服务界面APP
     * @param doctorID:该医师的识别编号
     */
    public void setDoctorServiceApp(Main doctorServiceApp,int doctorID){
        this.doctorServiceApp=doctorServiceApp;
        this.doctorID=doctorID;

        //插入性别选项
        cbxSex.getItems().addAll("男","女");

        //在科室管理中导入数据
        try {
            String sql;
            sql = "SELECT 科室信息.id, 科室名称, 姓名 FROM 科室信息 JOIN 医生信息 医 on 医.id = 科室信息.系主任";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Department department=new Department(
                        rs.getInt(1),rs.getString(2),rs.getString(3)
                );
                cbxDepartment.getItems().add(department.getDepartmentName());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //在个人信息和医师管理中导入数据
        try {
            String sql;
            sql="SELECT 医.id, 姓名, 性别, 出生日期, 入职日期, 科室名称, 职务, 是否为专家, 电话号码, 电子邮箱,挂号费 FROM 医生信息 医 JOIN 科室信息 科 on 医.所属科室 = 科.id";
            ResultSet rs= Func.statement.executeQuery(sql);
            while (rs.next()){
                Doctor doctor=new Doctor(
                        rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8)==1,rs.getString(9),rs.getString(10),
                        rs.getInt(11));

                //导入医师个人信息
                if(doctorID==doctor.getId()){
                    fieldName.setText(doctor.getName());
                    fieldEmail.setText(doctor.getEmail());
                    fieldJob.setText(doctor.getJob());
                    fieldRegisterFee.setText(""+doctor.getRegisterFee());
                    fieldPhone.setText(doctor.getPhoneNumber());
                    cbxDepartment.setValue(doctor.getDepartment());
                    cbxSex.setValue(doctor.getSex());
                    checkExpert.setSelected(doctor.getIsExpert());

                    Date date = new Date(doctor.getBirthday().getTime());
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateBirthday.setValue(localDate);

                    date = new Date(doctor.getWorkingDay().getTime());
                    localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateWorkingDay.setValue(localDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回上一级
     */
    public void onClickReturn() {
        this.doctorServiceApp.gotoMain();
    }

    /**
     * 信息修改提交
     */
    public void onClickSubmit() {
        try {
            String sql;
            ResultSet rs;
            sql="SELECT id FROM 科室信息 WHERE 科室名称='"+cbxDepartment.getValue()+"'";
            rs=Func.statement.executeQuery(sql);
            rs.next();
            int expert=checkExpert.isSelected()?1:0;
            sql="UPDATE 医生信息 " +
                    "SET 姓名='"+fieldName.getText()+
                    "',性别='"+cbxSex.getValue()+
                    "',出生日期='"+dateBirthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+
                    "',入职日期='"+dateWorkingDay.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+
                    "',所属科室="+rs.getInt(1)+
                    ",职务='"+fieldJob.getText()+
                    "',是否为专家="+expert+
                    ",电话号码='"+fieldPhone.getText()+
                    "',电子邮箱='"+fieldEmail.getText()+
                    "',挂号费="+fieldRegisterFee.getText()+
                    " WHERE id="+doctorID;
            int len=Func.statement.executeUpdate(sql);
            new Alert(Alert.AlertType.INFORMATION, len>0?"修改成功":"修改失败").showAndWait();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
