package HospitalManagementSystem.model;

/**
 * 科室类型
 */
public class Department {
    /**
     * 科室id
     */
    private int id;

    /**
     * 科室名称
     */
    private String departmentName;

    /**
     * 系主任名称
     */
    private String deanName;

    /**
     * 构造函数
     * @param id:科室id
     * @param departmentName:名称
     * @param deanName:系主任
     */
    public Department(int id, String departmentName, String deanName) {
        this.id = id;
        this.departmentName = departmentName;
        this.deanName = deanName;
    }

    /**
     * 获取科室id
     * @return :科室id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置科室id
     * @param id:科室id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取科室名称
     * @return :科室名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 获取系主任姓名
     * @return :系主任姓名
     */
    public String getDeanName() {
        return deanName;
    }

    /**
     * 设置科室名称
     * @param departmentName:科室名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 设置系主任姓名
     * @param deanName:系主任姓名
     */
    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }
}
