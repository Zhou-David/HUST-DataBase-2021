package HospitalManagementSystem.controller;

import HospitalManagementSystem.function.Func;
import HospitalManagementSystem.model.Department;
import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Main;
import HospitalManagementSystem.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
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
     * 正在等待病人的数量
     */
    public Label labWaitingPatient;

    /**
     * 正在处理的挂号单ID
     */
    public Label labHandlingPatient;

    /**
     * 药品选项
     */
    public ChoiceBox<String> cbxMedicine;

    /**
     * 添加选择的药品至取药单
     */
    public Button btnAddMedicine;

    /**
     * 取药单信息
     */
    public ListView<String> listMedicine;

    /**
     * 病房选项
     */
    public ChoiceBox<String> cbxWard;

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
     * 诊断提交按钮
     */
    public Button btnDiagnosis;

    /**
     * 返回按钮2
     */
    public Button btnReturn2;

    /**
     * 从取药单中移除药品
     */
    public Button btnDeleteMedicine;

    /**
     * 办理出院病人姓名
     */
    public TextField fieldDischargedPatientName;

    /**
     * 办理出院病人证件号
     */
    public TextField fieldDischargedPatientIDNum;

    /**
     * 所在病房号
     */
    public TextField fieldDischargedWardNum;

    /**
     * 已用病房天数
     */
    public TextField fieldDischargedDays;

    /**
     * 需要缴纳的费用
     */
    public TextField fieldDischargedFee;

    /**
     * 查询病人所在病房信息
     */
    public Button btnDischargedSearch;

    /**
     * 出院提交按钮
     */
    public Button btnDischargedSubmit;

    /**
     * 返回按钮3
     */
    public Button btnReturn3;

    /**
     * 医师服务界面APP
     */
    private Main doctorServiceApp;

    /**
     * 此医师的识别编号
     */
    private int doctorID;

    /**
     * 病人信息
     */
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();

    /**
     * 待处理病人ID
     */
    private final Queue<Integer> waitingPatientId=new ArrayDeque<>();

    /**
     * 待处理挂号单ID
     */
    private final Queue<String> waitingListId= new ArrayDeque<>();

    /**
     * 正在处理的病人ID
     */
    private int patientId;

    /**
     * 正在处理的挂号单ID
     */
    private String registerId;

    /**
     * 查询出院病人ID
     */
    private int searchPatientId;

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

        //待处理病人数目
        try {
            String sql="SELECT * FROM 病人信息 ORDER BY id";
            ResultSet rs=Func.statement.executeQuery(sql);
            while(rs.next()){
                Patient patient=new Patient(rs.getInt(1),rs.getString(3),
                        rs.getString(4),rs.getDate(5),rs.getString(6));
                patients.add(patient);
            }

            sql="SELECT id,就诊病人 FROM 挂号信息 WHERE 医生="+doctorID+" AND 状态=0 ORDER BY id";
            rs=Func.statement.executeQuery(sql);
            while(rs.next()){
                waitingListId.offer(rs.getString(1));
                waitingPatientId.offer(rs.getInt(2));
            }
            labWaitingPatient.setText(""+waitingPatientId.size()+"人");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //正在处理病人信息
        nextPatient();

        //导入病房选项
        cbxWard.getItems().add("无选择");
        try {
            String sql="SELECT 病房号 FROM 病房信息 WHERE 病房信息.入住人数<病房信息.病房容量";
            ResultSet rs=Func.statement.executeQuery(sql);
            while (rs.next()){
                cbxWard.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //导入药品选项
        try {
            String sql="SELECT 名称 FROM 药品信息";
            ResultSet rs=Func.statement.executeQuery(sql);
            while (rs.next()){
                cbxMedicine.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

    /**
     * 添加药品至取药单
     */
    public void onClickAddMedicine() {
        if(!cbxMedicine.getValue().equals("")){
            listMedicine.getItems().add(cbxMedicine.getValue());
        }
    }

    /**
     * 从取药单中移除所选药品
     */
    public void onClickDeleteMedicine() {
        String remove=listMedicine.getSelectionModel().getSelectedItem();
        listMedicine.getItems().remove(remove);
    }

    /**
     * 提交诊断信息
     */
    public void onClickDiagnosis() {
        //提交对此病人的诊断
        try {
            //关闭自动提交
            Func.connection.setAutoCommit(false);

            //设置时间格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sql;
            ResultSet rs;
            String prescriptionId=null;
            String admissionId=null;

            //当有选择开药，则添加取药单
            if(listMedicine.getItems().size()>0){
                double totalPrice=0;

                //获取取药单ID
                sql="SELECT COUNT(*) AS NUM FROM 取药单";
                rs=Func.statement.executeQuery(sql);
                rs.next();
                int medicineListId=rs.getInt(1)+1;
                prescriptionId=""+medicineListId;

                //添加新的取药单
                sql="INSERT INTO 取药单 VALUES("+medicineListId+
                        ",'"+df.format(new Date())+"',null)";
                int len=Func.statement.executeUpdate(sql);
                if(len<=0){
                    new Alert(Alert.AlertType.INFORMATION, "提交失败").showAndWait();

                    //回滚
                    Func.connection.rollback();
                    return;
                }

                //获取药物清单ID
                sql="SELECT COUNT(*) AS NUM FROM 药物清单";
                rs=Func.statement.executeQuery(sql);
                rs.next();
                int subMedicineListId=rs.getInt(1)+1;

                //添加新的药物清单
                for(String medicineName:listMedicine.getItems()){
                    sql="SELECT id,参考价格 FROM 药品信息 WHERE 名称='"+medicineName+"'";
                    rs=Func.statement.executeQuery(sql);
                    rs.next();
                    totalPrice+=rs.getDouble(2);

                    sql="INSERT INTO 药物清单 VALUES("+subMedicineListId+
                            ","+medicineListId+","+rs.getInt(1)+
                            ","+rs.getDouble(2)+")";
                    len=Func.statement.executeUpdate(sql);
                    if(len<0){
                        new Alert(Alert.AlertType.INFORMATION, "提交失败").showAndWait();
                        Func.connection.rollback();
                        return;
                    }

                    subMedicineListId++;
                }

                //计算总价格
                sql="UPDATE 取药单 SET 费用="+totalPrice+" WHERE id="+medicineListId;
                len=Func.statement.executeUpdate(sql);
                if(len<=0){
                    new Alert(Alert.AlertType.INFORMATION, "提交失败").showAndWait();
                    Func.connection.rollback();
                    return;
                }
            }

            //当有选择病房时，则添加入院信息
            if(cbxWard.getValue()!=null&&!cbxWard.getValue().equals("")&&!cbxWard.getValue().equals("无选择")){
                sql="SELECT id FROM 病房信息 WHERE 病房号='"+cbxWard.getValue()+"'";
                rs=Func.statement.executeQuery(sql);
                rs.next();
                int wardId=rs.getInt(1)+1;

                sql="SELECT COUNT(*) AS NUM FROM 入院信息";
                rs=Func.statement.executeQuery(sql);
                rs.next();
                int admitId=rs.getInt(1)+1;
                admissionId=""+admitId;

                sql="INSERT INTO 入院信息 VALUES("+admitId+
                        ",'"+df.format(new Date())+"',"+patientId+","+doctorID+","+wardId+")";
                int len=Func.statement.executeUpdate(sql);
                if(len>0){
                    sql="UPDATE 病房信息 SET 入住人数=入住人数-1 WHERE id="+wardId;
                    if(Func.statement.executeUpdate(sql)<=0){
                        new Alert(Alert.AlertType.INFORMATION,"提交失败").showAndWait();
                        Func.connection.rollback();
                        return;
                    }
                }
                else{
                    new Alert(Alert.AlertType.INFORMATION, "提交失败").showAndWait();
                    Func.connection.rollback();
                    return;
                }
            }

            //添加诊断信息
            sql="SELECT COUNT(*) AS NUM FROM 诊断信息";
            rs=Func.statement.executeQuery(sql);
            rs.next();
            int diagnosisId=rs.getInt(1)+1;

            sql="INSERT INTO 诊断信息 VALUES("+diagnosisId+","+patientId+","+doctorID+",'"+df.format(new Date())+
                    "',"+prescriptionId+","+admissionId+")";
            int len=Func.statement.executeUpdate(sql);
            if(len>0){
                sql="UPDATE 挂号信息 SET 状态=1 WHERE id="+registerId;
                if(Func.statement.executeUpdate(sql)>0){
                    //提交sql信息
                    Func.connection.commit();

                    new Alert(Alert.AlertType.INFORMATION, "提交成功").showAndWait();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION, "提交失败").showAndWait();
                    Func.connection.rollback();
                }
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "提交失败").showAndWait();
                Func.connection.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                Func.connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //处理下一位病人
        labWaitingPatient.setText(""+waitingPatientId.size()+"人");
        nextPatient();
        cbxMedicine.setValue(null);
        cbxWard.setValue(null);
        listMedicine.getItems().clear();
    }

    /**
     * 显示下一位病人的信息
     */
    private void nextPatient() {
        if(waitingPatientId.size()>0) {
            patientId = waitingPatientId.poll();

            Patient patient = patients.get(patientId - 1);
            fieldPatientName.setText(patient.getName());
            fieldPatientAge.setText("" + patient.getAge());
            fieldPatientSex.setText(patient.getSex());

            registerId = waitingListId.poll();
            labHandlingPatient.setText("" + registerId + "号");
        }
    }

    /**
     * 病人所在病房信息查询
     */
    public void onClickDischargedSearch() {
        try {
            String sql;
            if(fieldDischargedPatientIDNum.getText().equals("")&&fieldDischargedPatientName.getText().equals("")) {
                return;
            }
            else if(fieldDischargedPatientName.getText().equals("")){
                sql = "SELECT id FROM 病人信息 WHERE 证件号='" + fieldDischargedPatientIDNum.getText()+"'";
            }
            else {
                sql = "SELECT id FROM 病人信息 WHERE 姓名='" + fieldDischargedPatientName.getText()+"'";
            }
            ResultSet rs=Func.statement.executeQuery(sql);
            rs.next();
            searchPatientId=rs.getInt(1);

            sql="SELECT 入住时间,病房 FROM 入院信息 WHERE 病人="+searchPatientId;
            rs=Func.statement.executeQuery(sql);
            rs.next();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date admissionTime= sdf.parse(rs.getString(1));
            long daysBetween=((new Date()).getTime()-admissionTime.getTime()+1000000)/(60*60*24*1000);

            fieldDischargedWardNum.setText(rs.getString(2));
            fieldDischargedDays.setText(""+daysBetween);
        } catch (SQLException |ParseException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 提交出院
     */
    public void onClickDischargedSubmit() {
        if(fieldDischargedFee.getText().equals("")){
            new Alert(Alert.AlertType.INFORMATION, "请输入费用！").showAndWait();
            return;
        }
        try {
            String sql="SELECT COUNT(*) AS NUM FROM 出院信息";
            ResultSet rs=Func.statement.executeQuery(sql);
            rs.next();
            int dischargedId=rs.getInt(1)+1;

            //设置时间格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            sql="INSERT INTO 出院信息 VALUES("+dischargedId+","+searchPatientId+","+doctorID+",'"+df.format(new Date())+
                    "',"+Integer.parseInt(fieldDischargedFee.getText())+")";
            int len=Func.statement.executeUpdate(sql);
            new Alert(Alert.AlertType.INFORMATION, len>0?"提交成功":"提交失败").showAndWait();
        }catch (SQLException | NumberFormatException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
