package HospitalManagementSystem.model;

import java.util.Date;

/**
 * 财务类型
 */
public class Finance {
    private final int id;
    private final String incomeSource;
    private final String incomeTime;
    private final int fee;

    public Finance(int id, String incomeSource, String incomeTime, int fee) {
        this.id = id;
        this.incomeSource = incomeSource;
        this.incomeTime = incomeTime;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public String getIncomeTime() {
        return incomeTime;
    }

    public int getFee() {
        return fee;
    }
}
