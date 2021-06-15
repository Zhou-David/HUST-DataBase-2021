package HospitalManagementSystem.function;

import HospitalManagementSystem.model.Doctor;
import HospitalManagementSystem.model.Medicine;
import HospitalManagementSystem.model.Ward;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 医院系统函数
 */
public class Func {
    /**
     * JDBC驱动
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 所连接的数据库
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/hospital?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    /**
     * 数据库用户名
     */
    static final String USER = "root";

    /**
     * 数据库密码
     */
    static final String PASS = "fengyunxiaowei";

    /**
     * 数据库连接
     */
    static Connection connection = null;

    /**
     * 数据库操作语句
     */
    public static Statement statement = null;

    /**
     * 连接数据库
     */
    public static void ConnectToDataBase(){
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("成功连接到数据库！");
    }

    /**
     * 关闭连接
     */
    public static void CloseConnection() {
        // 关闭资源
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * 计算所给日期之间至今过了多少年
     * @param date:所计算日期
     * @return :时隔年限
     */
    public static int getYear(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(date);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int differenceYear = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    differenceYear--;//当前日期在生日之前，年龄减一
                }
            }
            else{
                differenceYear--;//当前月份在生日之前，年龄减一
            }
        }
        return differenceYear;
    }

    /**
     * 检测账户密码的正确性
     * @param account:输入账户
     * @param password:输入密码
     * @return :如果账户密码正确返回true，否则返回false
     */
    public static int Check(String account,String password) {
        String sql;
        sql = "SELECT id,用户名,密码 FROM 用户";
        try {
            ResultSet rs = statement.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int userId = rs.getInt("id");
                String userAccount = rs.getString("用户名");
                String userPasswd = rs.getString("密码");

                //比较账户密码
                if (userAccount.equals(account) && userPasswd.equals(password)) {
                    return userId;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 确定药品数据导入的列
     * @param colMedicineName:名称
     * @param colMedicineDosage:剂型
     * @param colMedicineSpecifications:规格
     * @param colMedicineIntroduction:使用说明
     * @param colMedicinePrice:参考价格
     */
    public static void setMedicineData(TableColumn<Medicine, String> colMedicineName, TableColumn<Medicine, String> colMedicineDosage, TableColumn<Medicine, String> colMedicineSpecifications, TableColumn<Medicine, String> colMedicineIntroduction, TableColumn<Medicine, String> colMedicinePrice) {
        colMedicineName.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(medicineStringCellDataFeatures.getValue().getName())
        );
        colMedicineDosage.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(medicineStringCellDataFeatures.getValue().getDosageForm())
        );
        colMedicineSpecifications.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(medicineStringCellDataFeatures.getValue().getSpecifications())
        );
        colMedicineIntroduction.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(medicineStringCellDataFeatures.getValue().getIntroduction())
        );
        colMedicinePrice.setCellValueFactory(
                medicineStringCellDataFeatures -> new SimpleStringProperty(""+medicineStringCellDataFeatures.getValue().getPrice())
        );
    }

    /**
     * 确定病房数据导入的列
     * @param colWardRoomNum:病房号
     * @param colWardCapacity:容量
     * @param colWardType:类型
     * @param colWardUsed:入住人数
     * @param colWardRemarks:备注
     */
    public static void setWardData(TableColumn<Ward,String> colWardRoomNum,TableColumn<Ward,String> colWardCapacity,TableColumn<Ward,String> colWardType,TableColumn<Ward,String> colWardUsed,TableColumn<Ward,String> colWardRemarks){
        colWardRoomNum.setCellValueFactory(
                wardStringCellDataFeatures -> new SimpleStringProperty(wardStringCellDataFeatures.getValue().getNumber())
        );
        colWardCapacity.setCellValueFactory(
                wardIntegerCellDataFeatures -> new SimpleStringProperty(""+wardIntegerCellDataFeatures.getValue().getCapacity())
        );
        colWardType.setCellValueFactory(
                wardStringCellDataFeatures -> new SimpleStringProperty(wardStringCellDataFeatures.getValue().getType())
        );
        colWardUsed.setCellValueFactory(
                wardIntegerCellDataFeatures -> new SimpleStringProperty(""+wardIntegerCellDataFeatures.getValue().getUsed())
        );
        colWardRemarks.setCellValueFactory(
                wardStringCellDataFeatures -> new SimpleStringProperty(wardStringCellDataFeatures.getValue().getRemarks())
        );
    }

    /**
     * 设置医师信息
     * @param resultSet:查询结果
     * @return :在数据库中查询到的医师信息所建立的医师类实体
     * @throws SQLException:异常捕获
     */
    public static Doctor setDoctorInfo(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new Doctor(
                resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getDate(4), resultSet.getDate(5),
                resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8) == 1, resultSet.getString(9), resultSet.getString(10),
                resultSet.getInt(11));
    }
}
