package HospitalManagementSystem.model;

import HospitalManagementSystem.function.Func;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Doctor {
    /**
     * 医生人员编号
     */
    private int id;

    /**
     * 医生人员姓名
     */
    private String name;

    /**
     * 医生人员性别
     */
    private String sex;

    /**
     * 医生人员出生日期
     */
    private Date birthday;

    /**
     * 医生人员从业日期
     */
    private Date workingDay;

    /**
     * 所属科室
     */
    private String department;

    /**
     * 职务
     */
    private String job;

    /**
     * 是否是专家
     */
    private boolean isExpert;

    /**
     * 联系方式-手机号
     */
    private String phoneNumber;

    /**
     * 联系方式-电子邮件
     */
    private String email;

    /**
     * 挂号费
     */
    private int registerFee;

    /**
     * 缺省构造函数
     */
    public Doctor() {
    }

    /**
     * 构造函数
     * @param id:人员id
     * @param name:姓名
     * @param sex:性别
     * @param birthday:出生日期
     * @param workingDay:工作日期
     * @param department:科室
     * @param job:职务
     * @param isExpert:是否为专家
     * @param phoneNumber:电话号码
     * @param email:电子邮箱
     */
    public Doctor(int id, String name, String sex, Date birthday, Date workingDay, String department, String job, boolean isExpert, String phoneNumber, String email,int registerFee) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.workingDay = workingDay;
        this.department = department;
        this.job = job;
        this.isExpert = isExpert;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registerFee=registerFee;
    }

    /**
     * 获取人员id
     * @return :医师人员id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置人员id
     * @param id：医师人员id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取人员姓名
     * @return :医师人员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置人员姓名
     * @param name:医师人员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取人员性别
     * @return :医师人员性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置人员性别
     * @param sex:医师人员性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取人员出生日期
     * @return :医师人员出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置人员出生日期
     * @param birthday:医师人员出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取人员年龄
     * @return :医师人员年龄
     */
    public int getAge(){
        return Func.getYear(this.birthday);
    }

    /**
     * 获取人员从业日期
     * @return :医师人员从业日期
     */
    public Date getWorkingDay() {
        return workingDay;
    }

    /**
     * 设置人员从业日期
     * @param workingDay:医师人员从业日期
     */
    public void setWorkingDay(Date workingDay) {
        this.workingDay = workingDay;
    }

    /**
     * 获取人员从业年限
     */
    public int getWorkingYear(){
        return Func.getYear(this.workingDay);
    }

    /**
     * 获取人员所属科室
     * @return :医师人员所属科室
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置人员所属科室
     * @param department:医师人员所属科室
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 获取人员职务
     * @return :获取医师人员职务
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置人员职务
     * @param job:设置医师人员职务
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取是否为专家
     * @return ：为专家返回1，否则返回0
     */
    public boolean getIsExpert() {
        return isExpert;
    }

    /**
     * 设置是否为专家
     * @param isExpert:1表示为专家，0表示不是专家
     */
    public void setIsExpert(boolean isExpert) {
        this.isExpert = isExpert;
    }

    /**
     * 获取人员电话号码
     * @return :医师人员电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置人员电话号码
     * @param phoneNumber:医师人员电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取人员电子邮箱
     * @return :医师人员电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置人员电子邮箱
     * @param email:医师人员电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取挂号费
     * @return :挂号费
     */
    public int getRegisterFee() {
        return registerFee;
    }

    /**
     * 设置挂号费
     * @param registerFee:挂号费
     */
    public void setRegisterFee(int registerFee) {
        this.registerFee = registerFee;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", workingDay=" + workingDay +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
