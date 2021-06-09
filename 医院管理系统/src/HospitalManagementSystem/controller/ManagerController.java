package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    /**
     * 医师信息表
     */
    public TableView<Doctor> tabDoctorInfo;

    /**
     * 医师信息表中的各个列
     */
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

    /**
     * 搜索医师的姓名
     */
    public TextField fieldDoctorSearch;

    /**
     * 搜索医师按钮
     */
    public Button btnDoctorSearch;

    /**
     * 返回按钮6
     */
    public Button btnReturn6;

    /**
     * 药品信息表
     */
    public TableView<Medicine> tabMedicineInfo;

    /**
     * 药品信息的各个列
     */
    public TableColumn<Medicine,String> colMedicineName;
    public TableColumn<Medicine,String> colMedicineDosage;
    public TableColumn<Medicine,String> colMedicineSpecifications;
    public TableColumn<Medicine,String> colMedicineIntroduction;
    public TableColumn<Medicine,Integer> colMedicinePrice;
    public TableColumn<Medicine,String> colMedicineType;

    /**
     * 搜索药品的名称
     */
    public TextField fieldMedicineSearch;

    /**
     * 搜索药品按钮
     */
    public Button btnMedicineSearch;

    /**
     * 返回按钮3
     */
    public Button btnReturn3;

    /**
     * 病房信息表
     */
    public TableView<Ward> tabWardInfo;

    /**
     * 病房信息表的各个列
     */
    public TableColumn<Ward,String> colWardRoomNum;
    public TableColumn<Ward,Integer> colWardCapacity;
    public TableColumn<Ward,String> colWardType;
    public TableColumn<Ward,Integer> colWardUsed;
    public TableColumn<Ward,String> colWardRemarks;

    /**
     * 搜索病房信息的房间号
     */
    public TextField fieldWardSearch;

    /**
     * 搜索病房信息按钮
     */
    public Button btnWardSearch;

    /**
     * 返回按钮4
     */
    public Button btnReturn4;

    /**
     * 科室信息表
     */
    public TableView<Department> tabDepartmentInfo;

    /**
     * 科室信息表的各个列
     */
    public TableColumn<Department,Integer> colDepartmentId;
    public TableColumn<Department,String> colDepartmentName;
    public TableColumn<Department,String> colDepartmentDean;

    /**
     * 搜索科室信息的名称
     */
    public TextField fieldDepartmentSearch;

    /**
     * 搜索科室按钮
     */
    public Button btnDepartmentSearch;

    /**
     * 返回按钮7
     */
    public Button btnReturn7;

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Doctor> doctorData = FXCollections.observableArrayList();

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Department> departmentData = FXCollections.observableArrayList();

    /**
     * 从数据库导入的病房信息数据
     */
    private final ObservableList<Ward> wardData = FXCollections.observableArrayList();

    /**
     * 从数据库导入的药品信息数据
     */
    private final ObservableList<Medicine> medicineData = FXCollections.observableArrayList();

    /**
     * 管理人员界面App
     */
    private Main managerApp;

    /**
     * 设置管理人员界面
     * @param managerApp:管理人员界面APP
     */
    public void setManagerApp(Main managerApp){
        this.managerApp=managerApp;

        //在科室管理中导入数据
        try {
            String sql;
            sql = "SELECT 科室信息.id, 科室名称, 姓名 FROM 科室信息 JOIN 医生信息 医 on 医.id = 科室信息.系主任";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Department department=new Department(
                        rs.getInt(1),rs.getString(2),rs.getString(3)
                );
                departmentData.add(department);
            }
            setDepartmentData(departmentData);
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
                doctorData.add(doctor);
            }
            setDoctorData(doctorData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //在药品管理中导入数据
        try {
            String sql;
            sql = "SELECT * FROM 药品信息";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Medicine medicine=new Medicine(
                        rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getInt(6),rs.getString(7)
                );
                medicineData.add(medicine);
            }
            setMedicineData(medicineData);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //在病房管理中导入数据
        try {
            String sql;
            sql = "SELECT * FROM 病房信息";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Ward ward=new Ward(
                        rs.getInt(1),rs.getString(2),rs.getInt(3),
                        rs.getString(4),rs.getInt(5),rs.getString(6)
                );
                wardData.add(ward);
            }
            setWardData(wardData);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 医师搜索
     */
    public void onClickDoctorSearch() {
    }

    /**
     * 医师数据导入
     * @param doctorData:医师数据
     */
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

    /**
     * 科室数据导入
     * @param departmentData:科室数据
     */
    public void setDepartmentData(ObservableList<Department> departmentData){
        //确定导入数据的列
        colDepartmentId.setCellValueFactory(
                departmentIntegerCellDataFeatures -> new SimpleIntegerProperty(departmentIntegerCellDataFeatures.getValue().getId()).asObject()
        );
        colDepartmentDean.setCellValueFactory(
                departmentStringCellDataFeatures -> new SimpleStringProperty(departmentStringCellDataFeatures.getValue().getDeanName())
        );
        colDepartmentName.setCellValueFactory(
                departmentStringCellDataFeatures -> new SimpleStringProperty(departmentStringCellDataFeatures.getValue().getDepartmentName())
        );

        //向表中导入数据
        tabDepartmentInfo.setItems(departmentData);
    }

    /**
     * 返回上一级
     */
    public void onClickReturn() {
        this.managerApp.gotoMain();
    }

    /**
     * 药品数据导入
     * @param medicineData:药品数据
     */
    private void setMedicineData(ObservableList<Medicine> medicineData) {
        //确定数据导入的列
        Func.setMedicineData(colMedicineName, colMedicineDosage, colMedicineSpecifications, colMedicineIntroduction, colMedicinePrice);
        colMedicineType.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(medicineStringCellDataFeatures.getValue().getType())
        );

        //向表中导入数据
        tabMedicineInfo.setItems(medicineData);
    }


    /**
     * 病房数据导入
     * @param wardData:病房数据
     */
    private void setWardData(ObservableList<Ward> wardData) {
        //确定数据导入的列
        Func.setWardData(colWardRoomNum,colWardCapacity,colWardType,colWardUsed,colWardRemarks);

        //向表中导入数据
        tabWardInfo.setItems(wardData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
