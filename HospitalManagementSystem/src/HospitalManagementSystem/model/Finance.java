package HospitalManagementSystem.model;

/**
 * 财务类型
 */
public class Finance {
    /**
     * 财务编号
     */
    private final int id;

    /**
     * 财务来源
     */
    private final String incomeSource;

    /**
     * 收入时间
     */
    private final String incomeTime;

    /**
     * 费用
     */
    private final int fee;

    /**
     * 构造函数
     * @param id:编号
     * @param incomeSource:来源
     * @param incomeTime:时间
     * @param fee:费用
     */
    public Finance(int id, String incomeSource, String incomeTime, int fee) {
        this.id = id;
        this.incomeSource = incomeSource;
        this.incomeTime = incomeTime;
        this.fee = fee;
    }

    /**
     * 获取财务单号
     * @return :编号
     */
    public int getId() {
        return id;
    }

    /**
     * 获取财务来源
     * @return :财务来源
     */
    public String getIncomeSource() {
        return incomeSource;
    }

    /**
     * 获取财务收入时间
     * @return :收入时间
     */
    public String getIncomeTime() {
        return incomeTime;
    }

    /**
     * 获取费用
     * @return :费用
     */
    public int getFee() {
        return fee;
    }
}
