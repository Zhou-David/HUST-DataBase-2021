package HospitalManagementSystem.model;

public class Department {
    /**
     * 科室id
     */
    private int id;

    /**
     * 科室名称
     */
    private final String departmentName;

    /**
     * 系主任名称
     */
    private final String deanName;

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
}
