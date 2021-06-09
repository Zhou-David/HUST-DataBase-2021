package HospitalManagementSystem.model;

/**
 * 病房类型
 */
public class Ward {
    /**
     * 房间编号
     */
    private int id;

    /**
     * 房间门牌号
     */
    private final String number;

    /**
     * 房间容量
     */
    private final int capacity;

    /**
     * 房间类型
     */
    private final String type;

    /**
     * 入住人数
     */
    private final int used;

    /**
     * 备注
     */
    private final String remarks;

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

    /**
     * 获取病房id
     * @return :病房id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置病房id
     * @param id:病房id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取门牌号
     * @return :门牌号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 获取病房容量
     * @return :病房容量
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * 获取病房类型
     * @return :病房类型
     */
    public String getType() {
        return type;
    }

    /**
     * 获取入住人数
     * @return :入住人数
     */
    public int getUsed() {
        return used;
    }

    /**
     * 获取备注
     * @return :备注
     */
    public String getRemarks() {
        return remarks;
    }
}
