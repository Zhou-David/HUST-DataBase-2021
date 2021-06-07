package HospitalManagementSystem.model;

public class Ward {
    /**
     * 房间编号
     */
    private int id;

    /**
     * 房间门牌号
     */
    private String number;

    /**
     * 房间容量
     */
    private int capacity;

    /**
     * 房间类型
     */
    private String type;

    /**
     * 入住人数
     */
    private int used;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 缺省构造函数
     */
    public Ward(){
    }

    /**
     * 构造函数
     * @param id:房间ID
     * @param number:房间号
     * @param capacity:容量
     * @param type:类型
     * @param used:已住人数
     * @param remarks:备注
     */
    public Ward(int id, String number, int capacity, String type, int used, String remarks) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.type = type;
        this.used = used;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
