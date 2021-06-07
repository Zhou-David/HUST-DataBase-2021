package HospitalManagementSystem.function;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
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
}
