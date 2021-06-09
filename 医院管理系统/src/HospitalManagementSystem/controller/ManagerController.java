package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * 管理人员界面控制器
 */
public class ManagerController implements Initializable {
    /**
     * 医师信息表
     */
    public TableView<Doctor> tabDoctorInfo;

    /**
     * 医师信息表中-姓名
     */
    public TableColumn<Doctor,String> colDoctorName;

    /**
     * 医师信息表-性别
     */
    public TableColumn<Doctor,String> colDoctorSex;

    /**
     * 医师信息表-出生日期
     */
    public TableColumn<Doctor,String> colDoctorBirthday;

    /**
     * 医师信息表-工作日期
     */
    public TableColumn<Doctor,String> colDoctorWorkingDay;

    /**
     * 医师信息表-所属科室
     */
    public TableColumn<Doctor,String> colDoctorDepartment;

    /**
     * 医师信息表-职务
     */
    public TableColumn<Doctor,String> colDoctorJob;

    /**
     * 医师信息表-挂号费
     */
    public TableColumn<Doctor,String> colDoctorFee;

    /**
     * 医师信息表-电话号码
     */
    public TableColumn<Doctor,String> colDoctorPone;

    /**
     * 医师信息表-电子邮箱
     */
    public TableColumn<Doctor,String> colDoctorEmail;

    /**
     * 医师信息表-是否为专家
     */
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
     * 药品信息表-名称
     */
    public TableColumn<Medicine,String> colMedicineName;

    /**
     * 药品信息表-剂型
     */
    public TableColumn<Medicine,String> colMedicineDosage;

    /**
     * 药品信息表-规格
     */
    public TableColumn<Medicine,String> colMedicineSpecifications;

    /**
     * 药品信息表-使用说明
     */
    public TableColumn<Medicine,String> colMedicineIntroduction;

    /**
     * 药品信息表-参考价格
     */
    public TableColumn<Medicine,String> colMedicinePrice;

    /**
     * 药品信息表-药品类型
     */
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

    /**
     * 病房信息表-房间容量
     */
    public TableColumn<Ward,String> colWardCapacity;

    /**
     * 病房信息表-病房类型
     */
    public TableColumn<Ward,String> colWardType;

    /**
     * 病房信息表-入住人数
     */
    public TableColumn<Ward,String> colWardUsed;

    /**
     * 病房信息表-备注
     */
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
     * 科室信息表-科室ID
     */
    public TableColumn<Department,Integer> colDepartmentId;

    /**
     * 科室信息表-科室名称
     */
    public TableColumn<Department,String> colDepartmentName;

    /**
     * 科室信息表-系主任
     */
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
        //如果搜索栏没有内容，则显示全部数据
        if(fieldDoctorSearch.getText().equals("")){
            setDoctorData(this.doctorData);
        }

        //找到对应名字的所有医生
        else {
            ObservableList<Doctor> doctorMach = FXCollections.observableArrayList();
            //查找到对应的医生
            for (Doctor doctor : doctorData) {
                if (doctor.getName().equals(fieldDoctorSearch.getText())) {
                    doctorMach.add(doctor);
                }
            }
            setDoctorData(doctorMach);
        }
    }

    /**
     * 药品信息查询
     */
    public void onClickMedicineSearch() {
        //如果搜索栏没有内容，则显示全部数据
        if(fieldMedicineSearch.getText().equals("")){
            setMedicineData(this.medicineData);
        }

        //找到对应名称的所有药品
        else {
            ObservableList<Medicine> medicineMach = FXCollections.observableArrayList();
            //查找到对应的药品
            for (Medicine medicine : medicineData) {
                if (medicine.getName().equals(fieldMedicineSearch.getText())) {
                    medicineMach.add(medicine);
                }
            }
            setMedicineData(medicineMach);
        }
    }

    /**
     * 病房信息查询
     */
    public void onClickWarnSearch() {
        //如果搜索栏没有内容，则显示全部数据
        if(fieldWardSearch.getText().equals("")){
            setWardData(this.wardData);
        }

        //找到对应房间号的所有病房
        else {
            ObservableList<Ward> wardMach = FXCollections.observableArrayList();
            //查找到对应的病房
            for (Ward ward : wardData) {
                if (ward.getNumber().equals(fieldWardSearch.getText())) {
                    wardMach.add(ward);
                }
            }
            setWardData(wardMach);
        }
    }

    /**
     * 科室信息查询
     */
    public void onClickDepartmentSearch() {
        //如果搜索栏没有内容，则显示全部数据
        if(fieldDepartmentSearch.getText().equals("")){
            setDepartmentData(departmentData);
        }

        //找到对应房间号的所有病房
        else {
            ObservableList<Department> departmentMach = FXCollections.observableArrayList();
            //查找到对应的病房
            for (Department department : departmentData) {
                if (department.getDepartmentName().equals(fieldDepartmentSearch.getText())) {
                    departmentMach.add(department);
                }
            }
            setDepartmentData(departmentMach);
        }
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
                doctorIntegerCellDataFeatures -> new SimpleStringProperty(""+doctorIntegerCellDataFeatures.getValue().getRegisterFee())
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

    /**
     * 修改表格内容后，对数据库内容进行修改
     */
    public void onEditMedicine() {
        new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
    }

    /**
     * 初始化操作
     * @param url:
     * @param resourceBundle:
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //使药品表格中的各个行可进行修改
        for(TableColumn<Medicine,String> medicineStringTableColumn:
                Arrays.asList(colMedicineName,colMedicineDosage,colMedicineSpecifications,
                        colMedicineIntroduction,colMedicinePrice,colMedicineType)){
            medicineStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }

        //使医师表格中的各个行可进行修改
        for (TableColumn<Doctor, String> doctorStringTableColumn :
                Arrays.asList(colDoctorName, colDoctorSex, colDoctorBirthday, colDoctorWorkingDay,
                        colDoctorDepartment, colDoctorJob, colDoctorFee, colDoctorPone, colDoctorEmail,
                        colDoctorExpert)) {
            doctorStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }

        //使病房表格中的各行可进行修改
        for (TableColumn<Ward, String> wardStringTableColumn :
                Arrays.asList(colWardRoomNum,colWardCapacity,colWardType,colWardUsed,colWardRemarks)) {
            wardStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }

        //使科室表格中的各行可进行修改
        for (TableColumn<Department, String> departmentStringTableColumn :
                Arrays.asList(colDepartmentName,colDepartmentDean)) {
            departmentStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }
    }
}
