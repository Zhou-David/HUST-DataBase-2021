package HospitalManagementSystem.model;

import HospitalManagementSystem.function.Func;

import java.util.Date;

/**
 * 病人类型
 */
public class Patient {
    /**
     * 病人人员id
     */
    private final int id;

    /**
     * 证件号
     */
    private final String IDCard;

    /**
     * 病人人员姓名
     */
    private final String name;

    /**
     * 病人人员姓名
     */
    private final String sex;

    /**
     * 病人出生日期
     */
    private final Date birthday;

    /**
     * 联系方式
     */
    private final String phone;

    /**
     * 构造函数
     * @param id:病人id
     * @param IDCard:证件号
     * @param name:姓名
     * @param sex:性别
     * @param birthday:出生日期
     * @param phone:电话号码
     */
    public Patient(int id, String IDCard, String name, String sex, Date birthday, String phone) {
        this.id = id;
        this.IDCard = IDCard;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
    }

    /**
     * 获取人员id
     * @return :病人id
     */
    public int getId() {
        return id;
    }

    /**
     * 获取人员姓名
     * @return :姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 获取人员性别
     * @return :性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 获取人员年龄
     * @return :年龄
     */
    public int getAge(){
        return Func.getYear(this.birthday);
    }

    /**
     * 获取人员电话号码
     * @return :电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 获取病人证件号
     * @return :证件号
     */
    public String getIDCard() {
        return IDCard;
    }
}
