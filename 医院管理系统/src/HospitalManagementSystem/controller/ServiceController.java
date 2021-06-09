package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Main;
import HospitalManagementSystem.model.Medicine;
import HospitalManagementSystem.model.Ward;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * 病人服务界面控制器
 */
public class ServiceController implements Initializable {
    /**
     * 病人姓名
     */
    public TextField fieldName;

    /**
     * 病人性别
     */
    public ChoiceBox<String> cbxSex;

    /**
     * 病人身份证号
     */
    public TextField fieldIDCard;

    /**
     * 病人电话
     */
    public TextField fieldPhone;

    /**
     * 选择科室
     */
    public ChoiceBox<String> cbxDepartment;

    /**
     * 选择医师
     */
    public ChoiceBox<String> cbxDoctor;

    /**
     * 提交按钮
     */
    public Button btnSubmit;

    /**
     * 挂号界面的返回按钮
     */
    public Button btnReturn1;

    /**
     * 用于检索医师的信息
     */
    public TextField fieldDoctorSearch;

    /**
     * 医师信息查询界面搜索按钮
     */
    public Button btnDoctorSearch;

    /**
     * 医师信息查询界面返回按钮
     */
    public Button btnReturn2;

    /**
     * 用于检索药品的信息
     */
    public TextField fieldMedicineSearch;

    /**
     * 药品信息查询界面搜索按钮
     */
    public Button btnMedicineSearch;

    /**
     * 药品信息查询界面返回按钮
     */
    public Button btnReturn3;

    /**
     * 用于检索病房的信息
     */
    public TextField fieldWardSearch;

    /**
     * 病房信息查询界面搜索按钮
     */
    public Button btnWardSearch;

    /**
     * 病房信息查询界面返回按钮
     */
    public Button btnReturn4;

    /**
     * 医师信息表-姓名
     */
    public TableColumn<Doctor,String> colDoctorName;

    /**
     * 医师信息表-年龄
     */
    public TableColumn<Doctor,Integer> colDoctorAge;

    /**
     * 医师信息表-工作时间
     */
    public TableColumn<Doctor,Integer> colDoctorWorking;

    /**
     * 医师信息表-科室
     */
    public TableColumn<Doctor,String> colDoctorDepartment;

    /**
     * 医师信息表-等级
     */
    public TableColumn<Doctor,String> colDoctorLevel;

    /**
     * 医师信息表-挂号费
     */
    public TableColumn<Doctor,Integer> colDoctorFee;

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
     * 药品信息表-价格
     */
    public TableColumn<Medicine,String> colMedicinePrice;

    /**
     * 病房信息表-房间号
     */
    public TableColumn<Ward,String> colWardNumber;

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
     * 医师信息表格
     */
    public TableView<Doctor> tabDoctorInfo;

    /**
     * 药品表格信息
     */
    public TableView<Medicine> tabMedicineInfo;

    /**
     * 病房表格信息
     */
    public TableView<Ward> tabWardInfo;

    /**
     * 服务界面APP
     */
    private Main serviceApp;

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Doctor> doctorData = FXCollections.observableArrayList();

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Medicine> medicineData = FXCollections.observableArrayList();

    /**
     * 从数据库导入的医师信息数据
     */
    private final ObservableList<Ward> wardData = FXCollections.observableArrayList();

    /**
     * 设置服务界面APP
     * @param serviceApp:服务界面APP
     */
    public void setServiceApp(Main serviceApp){
        this.serviceApp=serviceApp;

        //添加医师数据
        try {
            String sql;
            sql="SELECT 医.id, 姓名, 性别, 出生日期, 入职日期, 科室名称, 职务, 是否为专家, 电话号码, 电子邮箱,挂号费 FROM 医生信息 医 JOIN 科室信息 科 on 医.所属科室 = 科.id";
            ResultSet rs= Func.statement.executeQuery(sql);
            while (rs.next()){
                doctorData.add(new Doctor(
                        rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8)==1,rs.getString(9),rs.getString(10),
                        rs.getInt(11)));
            }
            setDoctorData(doctorData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //添加药品数据
        try {
            String sql;
            sql="SELECT * FROM 药品信息";
            ResultSet rs= Func.statement.executeQuery(sql);
            while (rs.next()){
                medicineData.add(new Medicine(
                        rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getInt(6),rs.getString(7)));
            }
            setMedicineData(medicineData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //添加病房数据
        try {
            String sql;
            sql="SELECT * FROM 病房信息";
            ResultSet rs= Func.statement.executeQuery(sql);
            while (rs.next()){
                wardData.add(new Ward(
                        rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),
                        rs.getInt(5),rs.getString(6)));
            }
            setWardData(wardData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择性别栏
     */
    public void onPressedSex() {
        //只用添加一次，因此当内容数量大于0时返回
        if(cbxSex.getItems().size()>0){
            return;
        }

        //添加性别选项
        cbxSex.getItems().addAll("男","女");
    }

    /**
     * 选择科室栏
     */
    public void onPressedDepartment() {
        //每次选择科室时都要清空医师选项
        cbxDoctor.getItems().clear();

        //只用添加一次，因此当内容数量大于0时返回
        if(cbxDepartment.getItems().size()>0){
            return;
        }

        //添加科室选项
        try {
            String sql;
            sql="SELECT 科室名称 FROM 科室信息";
            ResultSet rs;
            rs = Func.statement.executeQuery(sql);

            //添加全部科室选项，用于非限定科室
            cbxDepartment.getItems().add("全部");

            while (rs.next()){
                cbxDepartment.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 选择性别栏
     */
    public void onPressedDoctor() {
        String department=cbxDepartment.getValue();

        //如果已经选择了科室，则只添加对应科室的医师
        if(department!=null&&!department.equals("全部")){
            //当列表中已有数据，则说明不需要进行添加
            if(cbxDoctor.getItems().size()>0){
                return;
            }

            for(Doctor doctor:doctorData){
                if(doctor.getDepartment().equals(department)){
                    cbxDoctor.getItems().add(doctor.getName());
                }
            }
        }

        //没有选择科室，则加入全部医师
        else {
            //只当没有显示全部医师时，进行加入
            if(cbxDoctor.getItems().size()<doctorData.size()){
                for(Doctor doctor:doctorData){
                    cbxDoctor.getItems().add(doctor.getName());
                }
            }
        }
    }

    /**
     * 返回上一级
     */
    public void onClickReturn(){
        this.serviceApp.gotoMain();
    }

    /**
     * 挂号
     */
    public void onClickSubmit() {
        String patientName=fieldName.getText();
        String patientSex=cbxSex.getValue();
        String patientIDCard=fieldIDCard.getText();
        String patientPhone=fieldPhone.getText();
        String doctorName=cbxDoctor.getValue();

        //当输入信息不完整或者有误时，弹出错误消息
        if(patientName==null|| patientSex==null|| patientIDCard==null|| patientPhone==null||
        doctorName==null|| patientIDCard.length()!=18|| patientPhone.length()!=11){
            new Alert(Alert.AlertType.INFORMATION, "请填写正确的个人信息！").showAndWait();
        }

        else{
            try {
                boolean isRegister=false; //病人信息是否已经录入系统
                int patientNum; //病人编号
                String sql;
                ResultSet rs;

                //保证证件号唯一性
                sql ="SELECT 证件号 FROM 病人信息";
                rs = Func.statement.executeQuery(sql);
                while(rs.next()){
                    if(rs.getString(1).equals(patientIDCard)){
                        isRegister=true;
                    }
                }

                //如果病人信息未录入系统，则添加病人信息
                if(!isRegister) {
                    //找到目前病人数目，以确定添加的病人ID
                    sql = "SELECT COUNT(*) AS NUM FROM 病人信息";
                    rs = Func.statement.executeQuery(sql);
                    rs.next();
                    patientNum = rs.getInt("NUM") + 1;

                    //添加新的病人
                    sql = "INSERT INTO 病人信息 VALUES(" + patientNum + ",'" + patientIDCard + "','" + patientName + "','" + patientSex
                            + "','" + patientIDCard.substring(6, 10) + "-" + patientIDCard.substring(10, 12) + "-" + patientIDCard.substring(12, 14)
                            + "','" + patientPhone + "')";
                    int len = Func.statement.executeUpdate(sql);
                    if (len <= 0) {
                        new Alert(Alert.AlertType.INFORMATION, "添加病人信息失败！").showAndWait();
                        return;
                    }
                }

                //在数据库中找到已录入信息的病人，确定其编号
                else {
                    sql = "SELECT id AS NUM FROM 病人信息 WHERE 证件号='"+patientIDCard+"'";
                    rs = Func.statement.executeQuery(sql);
                    rs.next();
                    patientNum = rs.getInt(1);
                }

                //找到目前挂号单数目，以确定添加的挂号单ID
                sql = "SELECT COUNT(*) AS NUM FROM 挂号信息";
                rs = Func.statement.executeQuery(sql);
                rs.next();
                int registerNum = rs.getInt("NUM")+1;

                //找到病人挂号对应的医师
                sql = "SELECT id FROM 医生信息 WHERE 姓名="+"'"+doctorName+"'";
                rs = Func.statement.executeQuery(sql);
                rs.next();
                int doctorNum = rs.getInt(1);

                //设置时间格式
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //获取对应医生的信息
                Doctor doctor=new Doctor();
                for(Doctor doctor1:doctorData){
                    if(doctor1.getId()==doctorNum){
                        doctor=doctor1;
                        break;
                    }
                }

                //添加新的挂号单
                sql="INSERT INTO 挂号信息 VALUES("+registerNum+","+patientNum+","+doctorNum+
                ",'"+df.format(new Date())+"',"+doctor.getRegisterFee()+","+(doctor.getIsExpert()?1:0)+")";
                int len = Func.statement.executeUpdate(sql);
                if(len<=0){
                    new Alert(Alert.AlertType.INFORMATION, "添加挂号信息失败！").showAndWait();
                    return;
                }
                new Alert(Alert.AlertType.INFORMATION, "挂号成功！").showAndWait();

                //跳转到挂号信息界面
                this.serviceApp.gotoRegister(registerNum);
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 医生信息查询
     */
    public void onClickDoctorSearch() {
        //如果搜索栏没有内容，则显示全部数据
        if(fieldDoctorSearch.getText().equals("")){
            setDoctorData(doctorData);
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
            setMedicineData(medicineData);
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
            setWardData(wardData);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * 医师数据导入
     * @param doctorData:医师数据
     */
    private void setDoctorData(ObservableList<Doctor> doctorData){
        //确定数据导入的列
        colDoctorName.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getName())
        );
        colDoctorAge.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleIntegerProperty(doctorIntegerCellDataFeatures.getValue().getAge()).asObject()
        );
        colDoctorWorking.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleIntegerProperty(doctorIntegerCellDataFeatures.getValue().getWorkingYear()).asObject()
        );
        colDoctorDepartment.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getDepartment())
        );
        colDoctorLevel.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(doctorStringCellDataFeatures.getValue().getIsExpert()?"专家":"普通")
        );
        colDoctorFee.setCellValueFactory(
                doctorIntegerCellDataFeatures -> new SimpleIntegerProperty(doctorIntegerCellDataFeatures.getValue().getRegisterFee()).asObject()
        );

        //向表中导入数据
        tabDoctorInfo.setItems(doctorData);
    }

    /**
     * 药品数据导入
     * @param medicineData:药品数据
     */
    private void setMedicineData(ObservableList<Medicine> medicineData) {
        //确定数据导入的列
        Func.setMedicineData(colMedicineName, colMedicineDosage, colMedicineSpecifications, colMedicineIntroduction, colMedicinePrice);

        //向表中导入数据
        tabMedicineInfo.setItems(medicineData);
    }

    /**
     * 病房数据导入
     * @param wardData:病房数据
     */
    private void setWardData(ObservableList<Ward> wardData){
        //确定数据导入的列
        Func.setWardData(colWardNumber,colWardCapacity,colWardType,colWardUsed,colWardRemarks);

        //向表中导入数据
        tabWardInfo.setItems(wardData);
    }
}
