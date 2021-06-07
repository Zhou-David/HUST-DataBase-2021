package HospitalManagementSystem.model;

public class Department {
    private int id;
    private String departmentName;
    private String deanName;

    public Department(int id, String departmentName, String deanName) {
        this.id = id;
        this.departmentName = departmentName;
        this.deanName = deanName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }
}
