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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
     * 返回按钮1
     */
    public Button btnReturn1;

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
     * 返回按钮2
     */
    public Button btnReturn2;

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
     * 返回按钮3
     */
    public Button btnReturn3;

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
     * 返回按钮4
     */
    public Button btnReturn4;

    /**
     * 财务报表
     */
    public TableView<Finance> tabFinance;

    /**
     * 财务表-编号
     */
    public TableColumn<Finance,String> colFinanceId;

    /**
     * 财务表-收入来源
     */
    public TableColumn<Finance,String> colFinanceFrom;

    /**
     * 财务表-收入时间
     */
    public TableColumn<Finance,String> colFinanceTime;

    /**
     * 财务表-费用
     */
    public TableColumn<Finance,String> colFee;

    /**
     * 搜索财务来源信息
     */
    public TextField fieldIncomeSearch;

    /**
     * 搜索财务来源按钮
     */
    public Button btnIncomeSearch;

    /**
     * 总收入信息
     */
    public Label labTotalIncome;

    /**
     * 返回按钮5
     */
    public Button btnReturn5;

    /**
     * 药品信息表-药品编号
     */
    public TableColumn<Medicine,String> colMedicineId;

    /**
     * 病房信息表-病房编号
     */
    public TableColumn<Ward,String> colWardId;

    /**
     * 医师信息表-医师编号
     */
    public TableColumn<Doctor,String> colDoctorId;

    /**
     * 删除药品按钮
     */
    public Button btnMedicineDelete;

    /**
     * 删除病房按钮
     */
    public Button btnWardDelete;

    /**
     * 删除医师按钮
     */
    public Button btnDoctorDelete;

    /**
     * 删除科室按钮
     */
    public Button btnDepartmentDelete;

    /**
     * 添加病房-病房号
     */
    public TextField fieldAddWardNum;

    /**
     * 添加病房-病房容量
     */
    public TextField fieldAddWardCapacity;

    /**
     * 添加病房-病房类型
     */
    public TextField fieldAddWardType;

    /**
     * 添加病房-病房备注
     */
    public TextField fieldAddWardRemark;

    /**
     * 添加病房提交按钮
     */
    public Button btnAddWardSubmit;

    /**
     * 返回按钮6
     */
    public Button btnReturn6;

    /**
     * 添加药品-药品名称
     */
    public TextField fieldAddMedicineName;

    /**
     * 添加药品-药品剂型
     */
    public TextField fieldAddMedicineDosage;

    /**
     * 添加药品-药品规格
     */
    public TextField fieldAddMedicineSpecifications;

    /**
     * 添加药品提交按钮
     */
    public Button btnAddMedicineSubmit;

    /**
     * 添加药品-使用说明
     */
    public TextField fieldAddMedicineIntroduction;

    /**
     * 添加药品-参考价格
     */
    public TextField fieldAddMedicinePrice;

    /**
     * 添加药品-药品类型
     */
    public TextField fieldAddMedicineType;

    /**
     * 返回按钮7
     */
    public Button btnReturn7;

    /**
     * 添加医师-姓名
     */
    public TextField fieldAddDoctorName;

    /**
     * 添加医师-性别
     */
    public ChoiceBox<String> cbxAddDoctorSex;

    /**
     * 添加医师-生日
     */
    public DatePicker dateAddDoctorBirthday;

    /**
     * 添加医师-工作日期
     */
    public DatePicker dateAddDoctorWorkingDay;

    /**
     * 添加医师-所属科室
     */
    public ChoiceBox<String> cbxAddDoctorDepartment;

    /**
     * 添加医师-职务
     */
    public TextField fieldAddDoctorJob;

    /**
     * 添加医师-电话号码
     */
    public TextField fieldAddDoctorPhone;

    /**
     * 添加医师-电子邮箱
     */
    public TextField fieldAddDoctorEmail;

    /**
     * 添加医师-挂号费
     */
    public TextField fieldAddDoctorRegisterFee;

    /**
     * 添加医师-是否为专家
     */
    public CheckBox checkAddDoctorExpert;

    /**
     * 添加医师提交按钮
     */
    public Button btnAddDoctorSubmit;

    /**
     * 返回按钮8
     */
    public Button btnReturn8;

    /**
     * 添加科室-科室名称
     */
    public TextField fieldAddDepartmentName;

    /**
     * 添加科室-系主任
     */
    public TextField fieldAddDepartmentDean;

    /**
     * 返回按钮9
     */
    public Button btnReturn9;

    /**
     * 添加科室提交按钮
     */
    public Button btnAddDepartmentSubmit;

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
     * 从数据库导入的药品信息数据
     */
    private final ObservableList<Finance> financeData = FXCollections.observableArrayList();

    /**
     * 管理人员界面App
     */
    private Main managerApp;

    /**
     * 设置管理人员界面
     * @param managerApp:管理人员界面APP
     */
    public void setManagerApp(Main managerApp) {
        this.managerApp = managerApp;

        //在科室管理中导入数据
        try {
            String sql;
            sql = "SELECT 科室信息.id, 科室名称, 姓名 FROM 科室信息 LEFT JOIN 医生信息 医 on 医.id = 科室信息.系主任 "+
                    "ORDER BY 科室信息.id";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Department department = new Department(
                        rs.getInt(1), rs.getString(2), rs.getString(3)
                );
                departmentData.add(department);
            }
            setDepartmentData(departmentData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //在个人信息和医师管理中导入数据
        try {
            String sql;
            sql = "SELECT 医.id, 姓名, 性别, 出生日期, 入职日期, 科室名称, 职务, 是否为专家, 电话号码, 电子邮箱,挂号费 " +
                    "FROM 医生信息 医 JOIN 科室信息 科 on 医.所属科室 = 科.id "+
                    "ORDER BY 医.id";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8) == 1, rs.getString(9), rs.getString(10),
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
            sql = "SELECT * FROM 药品信息 ORDER BY id";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7)
                );
                medicineData.add(medicine);
            }
            setMedicineData(medicineData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //在病房管理中导入数据
        try {
            String sql;
            sql = "SELECT * FROM 病房信息 ORDER BY id";
            ResultSet rs = Func.statement.executeQuery(sql);
            while (rs.next()) {
                Ward ward = new Ward(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6)
                );
                wardData.add(ward);
            }
            setWardData(wardData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int totalIncome=0;
        //在财务管理中导入数据
        try {
            int count=1;
            String sql;
            ResultSet rs;

            //获取挂号费信息
            sql="SELECT 挂号时间,挂号费 FROM 挂号信息";
            rs=Func.statement.executeQuery(sql);
            while(rs.next()){
                Finance finance=new Finance(
                        count++,"挂号费",
                        rs.getDate(1)+" "+rs.getTime(1),
                        rs.getInt(2)
                );
                totalIncome+=finance.getFee();
                financeData.add(finance);
            }

            //获取取药费信息
            sql="SELECT 取药时间,费用 FROM 取药单";
            rs=Func.statement.executeQuery(sql);
            while(rs.next()){
                Finance finance=new Finance(
                        count++,"取药费",
                        rs.getDate(1)+" "+rs.getTime(1),
                        rs.getInt(2)
                );
                totalIncome+=finance.getFee();
                financeData.add(finance);
            }

            //获取住院费信息
            sql="SELECT 出院时间,费用 FROM 出院信息";
            rs=Func.statement.executeQuery(sql);
            while(rs.next()){
                Finance finance=new Finance(
                        count++,"住院费",
                        rs.getDate(1)+" "+rs.getTime(1),
                        rs.getInt(2)
                );
                totalIncome+=finance.getFee();
                financeData.add(finance);
            }

            setFinanceData(financeData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //总收入
        labTotalIncome.setText(""+totalIncome);

        //性别选项
        cbxAddDoctorSex.getItems().setAll("男","女");

        //科室选项
        for(Department department:departmentData) {
            cbxAddDoctorDepartment.getItems().add(department.getDepartmentName());
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
     * 财务查询
     */
    public void onClickFinanceSearch(){
        //如果搜索栏没有内容，则显示全部数据
        if(fieldIncomeSearch.getText().equals("")){
            setFinanceData(financeData);
        }

        //找到对应房间号的所有病房
        else {
            ObservableList<Finance> financeMach = FXCollections.observableArrayList();
            //查找到对应的病房
            for (Finance finance : financeData) {
                if (finance.getIncomeSource().equals(fieldIncomeSearch.getText())) {
                    financeMach.add(finance);
                }
            }
            setFinanceData(financeMach);
        }
    }

    /**
     * 医师数据导入
     * @param doctorData:医师数据
     */
    public void setDoctorData(ObservableList<Doctor> doctorData) {
        //确定数据导入的列
        colDoctorId.setCellValueFactory(
                doctorStringCellDataFeatures -> new SimpleStringProperty(""+doctorStringCellDataFeatures.getValue().getId())
        );
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
        colMedicineId.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(""+medicineStringCellDataFeatures.getValue().getId())
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
        colWardId.setCellValueFactory(
                wardStringCellDataFeatures -> new SimpleStringProperty(""+wardStringCellDataFeatures.getValue().getId())
        );

        //向表中导入数据
        tabWardInfo.setItems(wardData);
    }

    /**
     * 科室数据导入
     * @param financeData:科室数据
     */
    public void setFinanceData(ObservableList<Finance> financeData){
        //确定导入数据的列
        colFinanceId.setCellValueFactory(
                financeIntegerCellDataFeatures -> new SimpleStringProperty(""+financeIntegerCellDataFeatures.getValue().getId())
        );
        colFinanceFrom.setCellValueFactory(
                financeStringCellDataFeatures -> new SimpleStringProperty(financeStringCellDataFeatures.getValue().getIncomeSource())
        );
        colFinanceTime.setCellValueFactory(
                financeStringCellDataFeatures -> new SimpleStringProperty(financeStringCellDataFeatures.getValue().getIncomeTime())
        );
        colFee.setCellValueFactory(
                financeStringCellDataFeatures -> new SimpleStringProperty(""+financeStringCellDataFeatures.getValue().getFee())
        );

        //向表中导入数据
        tabFinance.setItems(financeData);
    }

    /**
     * 修改药品信息表
     * @param medicineStringCellEditEvent:修改信息
     */
    public void onEditMedicine(TableColumn.CellEditEvent<Medicine, String> medicineStringCellEditEvent) {
        int row=medicineStringCellEditEvent.getTablePosition().getRow();
        int column=medicineStringCellEditEvent.getTablePosition().getColumn();
        String newValue=medicineStringCellEditEvent.getNewValue();

        //按照修改的行和列，修改对应数据
        switch (column){
            case 1:
                medicineData.get(row).setName(newValue);
                break;
            case 2:
                medicineData.get(row).setDosageForm(newValue);
                break;
            case 3:
                medicineData.get(row).setSpecifications(newValue);
            case 4:
                medicineData.get(row).setIntroduction(newValue);
            case 5:
                try {
                    int price=Integer.parseInt(newValue);
                    medicineData.get(row).setPrice(price);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.INFORMATION, "格式有误，修改失败").showAndWait();
                    return;
                }
                break;
            case 6:
                medicineData.get(row).setType(newValue);
                break;
            default:
                break;
        }

        Medicine medicine=medicineData.get(row);

        //将修改的数据同步到数据库
        try {
            String sql="UPDATE 药品信息 " +
                    "SET 名称='"+medicine.getName()+
                    "',剂型='"+medicine.getDosageForm()+
                    "',规格='"+medicine.getSpecifications()+
                    "',使用说明='"+medicine.getIntroduction()+
                    "',参考价格="+medicine.getPrice()+
                    ",类型='"+medicine.getType()+
                    "' WHERE id="+medicine.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "修改失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 修改病房信息表
     * @param wardStringCellEditEvent:修改信息
     */
    public void onEditWarn(TableColumn.CellEditEvent<Ward, String> wardStringCellEditEvent) {
        int row=wardStringCellEditEvent.getTablePosition().getRow();
        int column=wardStringCellEditEvent.getTablePosition().getColumn();
        String newValue=wardStringCellEditEvent.getNewValue();

        //按照修改的行和列，修改对应数据
        switch (column){
            case 1:
                wardData.get(row).setNumber(newValue);
                break;
            case 2:
                try {
                    int capacity=Integer.parseInt(newValue);
                    wardData.get(row).setCapacity(capacity);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.INFORMATION, "格式有误，修改失败").showAndWait();
                    return;
                }
                break;
            case 3:
                wardData.get(row).setType(newValue);
            case 5:
                wardData.get(row).setRemarks(newValue);
                break;
            default:
                break;
        }

        Ward ward=wardData.get(row);

        //将修改的数据同步到数据库
        try {
            String sql="UPDATE 病房信息 " +
                    "SET 病房号='"+ward.getNumber()+
                    "',病房容量="+ward.getCapacity()+
                    ",房间类型='"+ward.getType()+
                    "',备注='"+ward.getRemarks()+
                    "' WHERE id="+ward.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "修改失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 修改科室信息表
     * @param departmentStringCellEditEvent:修改信息
     */
    public void onEditDepartment(TableColumn.CellEditEvent<Department, String> departmentStringCellEditEvent) {
        int row=departmentStringCellEditEvent.getTablePosition().getRow();
        int column=departmentStringCellEditEvent.getTablePosition().getColumn();
        String newValue=departmentStringCellEditEvent.getNewValue();

        //按照修改的行和列，修改对应数据
        switch (column){
            case 1:
                departmentData.get(row).setDepartmentName(newValue);
                break;
            case 2:
                try {
                    String sql="SELECT 姓名 FROM 医生信息";
                    ResultSet rs=Func.statement.executeQuery(sql);
                    List<String> nameList=new ArrayList<>();
                    while(rs.next()){
                        nameList.add(rs.getString(1));
                    }
                    if(nameList.contains(newValue)){
                        departmentData.get(row).setDeanName(newValue);
                    }
                    else{
                        new Alert(Alert.AlertType.INFORMATION, "医师不存在，修改失败").showAndWait();
                        return;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                break;
        }

        Department department=departmentData.get(row);

        //将修改的数据同步到数据库
        try {
            String sql;
            sql="SELECT id FROM 医生信息 WHERE 姓名='"+department.getDeanName()+"'";
            ResultSet rs=Func.statement.executeQuery(sql);
            rs.next();
            int deanId=rs.getInt(1);
            sql="UPDATE 科室信息 " +
                    "SET 科室名称='"+department.getDepartmentName()+
                    "',系主任="+deanId+
                    " WHERE id="+department.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "修改失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 修改医师信息表
     * @param doctorStringCellEditEvent:修改信息
     */
    public void onEditDoctor(TableColumn.CellEditEvent<Doctor, String> doctorStringCellEditEvent) {
        int row=doctorStringCellEditEvent.getTablePosition().getRow();
        int column=doctorStringCellEditEvent.getTablePosition().getColumn();
        String newValue=doctorStringCellEditEvent.getNewValue();

        // 指定日期格式为四位年-两位月份-两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //按照修改的行和列，修改对应数据
        switch (column){
            case 1:
                doctorData.get(row).setName(newValue);
                break;
            case 2:
                if(!newValue.equals("男")&&!newValue.equals("女")){
                    new Alert(Alert.AlertType.INFORMATION, "性别输入错误，修改失败").showAndWait();
                    return;
                }
                doctorData.get(row).setSex(newValue);
                break;
            case 3:
                try {
                    doctorData.get(row).setBirthday(format.parse(newValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.INFORMATION, "日期格式有误，修改失败").showAndWait();
                    return;
                }
                break;
            case 4:
                try {
                    doctorData.get(row).setWorkingDay(format.parse(newValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.INFORMATION, "日期格式有误，修改失败").showAndWait();
                    return;
                }
                break;
            case 5:
                try {
                    String sql="SELECT 科室名称 FROM 科室信息";
                    ResultSet rs=Func.statement.executeQuery(sql);
                    List<String> nameList=new ArrayList<>();
                    while(rs.next()){
                        nameList.add(rs.getString(1));
                    }
                    if(nameList.contains(newValue)){
                        doctorData.get(row).setName(newValue);
                    }
                    else{
                        new Alert(Alert.AlertType.INFORMATION, "科室不存在，修改失败").showAndWait();
                        return;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 6:
                doctorData.get(row).setJob(newValue);
                break;
            case 7:
                try {
                    int fee=Integer.parseInt(newValue);
                    doctorData.get(row).setRegisterFee(fee);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.INFORMATION, "格式有误，修改失败").showAndWait();
                    return;
                }
                break;
            case 8:
                doctorData.get(row).setPhoneNumber(newValue);
                break;
            case 9:
                doctorData.get(row).setEmail(newValue);
                break;
            case 10:
                doctorData.get(row).setExpert(newValue.equals("专家"));
                break;
            default:
                break;
        }

        Doctor doctor=doctorData.get(row);

        //将修改的数据同步到数据库
        try {
            String sql;
            sql="SELECT id FROM 科室信息 WHERE 科室名称='"+doctor.getDepartment()+"'";
            ResultSet rs=Func.statement.executeQuery(sql);
            rs.next();
            int departmentId=rs.getInt(1);
            int expert=doctor.getIsExpert()?1:0;
            sql="UPDATE 医生信息 " +
                    "SET 姓名='"+doctor.getName()+
                    "',性别='"+doctor.getSex()+
                    "',出生日期='"+format.format(doctor.getBirthday())+
                    "',入职日期='"+format.format(doctor.getWorkingDay())+
                    "',所属科室="+departmentId+
                    ",职务='"+doctor.getJob()+
                    "',是否为专家="+expert+
                    ",电话号码='"+doctor.getPhoneNumber()+
                    "',电子邮箱='"+doctor.getEmail()+
                    "',挂号费="+doctor.getRegisterFee()+
                    " WHERE id="+doctor.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "修改失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 新增药品数据
     */
    public void onClickMedicineAdd() {
        try {
            int id=medicineData.get(medicineData.size()-1).getId()+1;
            String sql="INSERT INTO 药品信息 VALUES("+id+
                    ",'"+fieldAddMedicineName.getText()+
                    "','"+fieldAddMedicineDosage.getText()+
                    "','"+fieldAddMedicineSpecifications.getText()+
                    "','"+fieldAddMedicineIntroduction.getText()+
                    "',"+fieldAddMedicinePrice.getText()+
                    ",'"+fieldAddMedicineType.getText()+"')";
            Medicine medicine=new Medicine(id,fieldAddMedicineName.getText(),fieldAddMedicineDosage.getText(),
                    fieldAddMedicineSpecifications.getText(),fieldAddMedicineIntroduction.getText(),
                    Integer.parseInt(fieldAddMedicinePrice.getText()),fieldAddMedicineType.getText());
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                medicineData.add(medicine);
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "添加失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "格式有误，修改失败").showAndWait();
        }
    }

    /**
     * 删除药品数据
     */
    public void onClickMedicineDelete() {
        //从列表中删除对应数据
        Medicine medicine=tabMedicineInfo.getSelectionModel().getSelectedItem();
        medicineData.remove(medicine);

        //从数据库中删除对应数据
        try {
            String sql="DELETE FROM 药品信息 WHERE id="+medicine.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "删除失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 新增病房数据
     */
    public void onClickWardAdd() {
        try {
            int id=wardData.get(wardData.size()-1).getId()+1;
            String sql="INSERT INTO 病房信息 VALUES("+id+
                    ",'"+fieldAddWardNum.getText()+
                    "',"+fieldAddWardCapacity.getText()+
                    ",'"+fieldAddWardType.getText()+
                    "',"+0+
                    ",'"+fieldAddWardRemark.getText()+"')";
            Ward ward=new Ward(id,fieldAddWardNum.getText(),Integer.parseInt(fieldAddWardCapacity.getText()),
                    fieldAddWardType.getText(),0,fieldAddWardRemark.getText());
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                wardData.add(ward);
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "添加失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "格式有误，修改失败").showAndWait();
        }
    }

    /**
     * 删除病房数据
     */
    public void onClickWardDelete() {
        //从列表中删除对应数据
        Ward ward=tabWardInfo.getSelectionModel().getSelectedItem();
        wardData.remove(ward);

        //从数据库中删除对应数据
        try {
            String sql="DELETE FROM 病房信息 WHERE id="+ward.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "删除失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 新增医师数据
     */
    public void onClickDoctorAdd() {
        try {
            int id=doctorData.get(doctorData.size()-1).getId()+1;
            int expert=checkAddDoctorExpert.isSelected()?1:0;

            String strBirthday=dateAddDoctorBirthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String strWorkingDay=dateAddDoctorWorkingDay.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String sql="SELECT id FROM 科室信息 WHERE 科室名称='"+cbxAddDoctorDepartment.getValue()+"'";
            ResultSet rs=Func.statement.executeQuery(sql);
            rs.next();

            sql="INSERT INTO 医生信息 VALUES("+id+
                    ",'"+fieldAddDoctorName.getText()+
                    "','"+cbxAddDoctorSex.getValue()+
                    "','"+strBirthday+
                    "','"+strWorkingDay+
                    "',"+rs.getInt(1)+
                    ",'"+fieldAddDoctorJob.getText()+
                    "',"+expert+
                    ",'"+fieldAddDoctorPhone.getText()+
                    "','"+fieldAddDoctorEmail.getText()+
                    "',"+fieldAddDoctorRegisterFee.getText()+")";
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                String doctorMessage = "SELECT 医.id, 姓名, 性别, 出生日期, 入职日期, 科室名称, 职务, 是否为专家, 电话号码, 电子邮箱,挂号费 FROM 医生信息 医 JOIN 科室信息 科 on 医.所属科室 = 科.id WHERE 医.id="+id;
                ResultSet resultSet = Func.statement.executeQuery(doctorMessage);
                doctorData.add(Func.setDoctorInfo(resultSet));
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "添加失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "格式有误，修改失败").showAndWait();
        }
    }

    /**
     * 删除医师数据
     */
    public void onClickDoctorDelete() {
        Doctor doctor=tabDoctorInfo.getSelectionModel().getSelectedItem();
        doctorData.remove(doctor);

        try {
            String sql="UPDATE 科室信息 SET 系主任=null WHERE 系主任="+doctor.getId();
            int len=Func.statement.executeUpdate(sql);
            if(len<0){
                new Alert(Alert.AlertType.INFORMATION, "删除失败").showAndWait();
                return;
            }
            sql="DELETE FROM 医生信息 WHERE id="+doctor.getId();
            len=Func.statement.executeUpdate(sql);
            if(len<0){
                new Alert(Alert.AlertType.INFORMATION, "删除失败").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 新增科室数据
     */
    public void onClickDepartmentAdd() {
        try {
            int id=departmentData.get(departmentData.size()-1).getId()+1;

            String sql;
            if(fieldAddDepartmentDean.getText().equals("")){
                sql="INSERT INTO 科室信息 VALUES("+id+
                        ",'"+fieldAddDepartmentName.getText()+
                        "',"+null+")";
            }
            else{
                sql="SELECT id FROM 医生信息 WHERE 姓名='"+fieldAddDepartmentDean.getText()+"'";
                ResultSet rs=Func.statement.executeQuery(sql);
                if(!rs.next()){
                    new Alert(Alert.AlertType.INFORMATION, "请输入正确的医师姓名").showAndWait();
                    return;
                }
                int doctorId=rs.getInt(1);
                sql="INSERT INTO 科室信息 VALUES("+id+
                        ",'"+fieldAddDepartmentName.getText()+
                        "',"+doctorId+")";
            }
            int len=Func.statement.executeUpdate(sql);
            Department department=new Department(id,fieldAddDepartmentName.getText(),fieldAddDepartmentDean.getText());
            if(len>0){
                new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                departmentData.add(department);
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "添加失败").showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 删除科室数据
     */
    public void onClickDepartmentDelete() {
        Department department=tabDepartmentInfo.getSelectionModel().getSelectedItem();
        try {
            String sql="SELECT * FROM 医生信息 WHERE 所属科室="+department.getId();
            ResultSet rs=Func.statement.executeQuery(sql);
            if(rs.next()){
                new Alert(Alert.AlertType.INFORMATION, "该科室下有部分医师信息，拒绝删除").showAndWait();
            }
            else {
                departmentData.remove(department);
                sql="DELETE FROM 科室信息 WHERE id="+department.getId();
                int len=Func.statement.executeUpdate(sql);
                if(len>0){
                    new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION, "删除失败").showAndWait();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
                Arrays.asList(colWardRoomNum,colWardCapacity,colWardType,colWardRemarks)) {
            wardStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }

        //使科室表格中的各行可进行修改
        for (TableColumn<Department, String> departmentStringTableColumn :
                Arrays.asList(colDepartmentName,colDepartmentDean)) {
            departmentStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }
    }
}
