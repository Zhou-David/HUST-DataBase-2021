package HospitalManagementSystem.model;

import HospitalManagementSystem.function.Func;

import java.util.Date;

public class Patient {
    /**
     * 病人人员id
     */
    private int id;

    /**
     * 证件号
     */
    private String IDCard;

    /**
     * 病人人员姓名
     */
    private String name;

    /**
     * 病人人员姓名
     */
    private String sex;

    /**
     * 病人出生日期
     */
    private Date birthday;

    /**
     * 联系方式
     */
    private String phone;

    public Patient(int id, String IDCard, String name, String sex, Date birthday, String phone) {
        this.id = id;
        this.IDCard = IDCard;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getIDCard() {
        return IDCard;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getAge(){
        return Func.getYear(this.birthday);
    }

    public String getPhone() {
        return phone;
    }
}
