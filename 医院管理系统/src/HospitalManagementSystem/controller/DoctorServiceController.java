package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.Department;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Main;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class DoctorServiceController implements Initializable {
    public Button btnReturn;
    public Button btnReturn1;
    public CheckBox checkExpert;
    public TextField fieldName;
    public ChoiceBox<String> cbxSex;
    public DatePicker dateBirthday;
    public DatePicker dateWorkingDay;
    public ChoiceBox<String> cbxDepartment;
    public TextField fieldJob;
    public TextField fieldPhone;
    public TextField fieldEmail;
    public TextField fieldRegisterFee;
    public Button btnSubmit;
    public TableView<Doctor> tabDoctorInfo;
    public TableColumn<Doctor,String> colDoctorName;
    public TableColumn<Doctor,String> colDoctorSex;
    public TableColumn<Doctor,String> colDoctorBirthday;
    public TableColumn<Doctor,String> colDoctorWorkingDay;
    public TableColumn<Doctor,String> colDoctorDepartment;
    public TableColumn<Doctor,String> colDoctorJob;
    public TableColumn<Doctor,Integer> colDoctorFee;
    public TableColumn<Doctor,String> colDoctorPone;
    public TableColumn<Doctor,String> colDoctorEmail;
    public TableColumn<Doctor,String> colDoctorExpert;
    public TextField fieldDoctorSearch;
    public Button btnDoctorSearch;
    public Button btnReturn6;
    private Main doctorServiceApp;
    private int doctorID;

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Doctor> doctorData = FXCollections.observableArrayList();

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Department> departmentData = FXCollections.observableArrayList();

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
                departmentData.add(department);
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

                    Date date = new Date(doctor.getBirthday().getTime());
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateBirthday.setValue(localDate);

                    date = new Date(doctor.getWorkingDay().getTime());
                    localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateWorkingDay.setValue(localDate);
                }
                doctorData.add(doctor);
            }
            setDoctorData(doctorData);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClickReturn(ActionEvent event) {
        this.doctorServiceApp.gotoMain();
    }

    public void onClickSubmit(ActionEvent event) {
    }

    public void onClickDoctorSearch(ActionEvent event) {
    }

    public void setDoctorData(ObservableList<Doctor> doctorData) {
        //确定数据导入的列
        colDoctorName.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getName())
        );
        colDoctorSex.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleStringProperty(doctorIntegerCellDataFeatures.getValue().getSex())
        );
        colDoctorBirthday.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleStringProperty(doctorIntegerCellDataFeatures.getValue().getBirthday().toString())
        );
        colDoctorWorkingDay.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getWorkingDay().toString())
        );
        colDoctorDepartment.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getDepartment())
        );
        colDoctorJob.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleStringProperty(doctorIntegerCellDataFeatures.getValue().getJob())
        );
        colDoctorExpert.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getIsExpert()?"专家":"普通")
        );
        colDoctorPone.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getPhoneNumber())
        );
        colDoctorEmail.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleStringProperty(doctorIntegerCellDataFeatures.getValue().getEmail())
        );
        colDoctorFee.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleIntegerProperty(doctorIntegerCellDataFeatures.getValue().getRegisterFee()).asObject()
        );

        //向表中导入数据
        tabDoctorInfo.setItems(doctorData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
