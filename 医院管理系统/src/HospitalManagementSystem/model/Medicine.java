package HospitalManagementSystem.model;

public class Medicine {
    /**
     * 药品id
     */
    private int id;

    /**
     * 药品名称
     */
    private String name;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 使用说明
     */
    private String introduction;

    /**
     * 参考价格
     */
    private int price;

    /**
     * 产品类别
     */
    private String type;

    /**
     * 缺省构造函数
     */
    public Medicine() {
    }

    /**
     * 构造函数
     * @param id:药品ID
     * @param name:名称
     * @param dosageForm:剂型
     * @param specifications:规格
     * @param introduction:使用说明
     * @param price:参考价格
     * @param type:类型
     */
    public Medicine(int id, String name, String dosageForm, String specifications, String introduction, int price, String type) {
        this.id = id;
        this.name = name;
        this.dosageForm = dosageForm;
        this.specifications = specifications;
        this.introduction = introduction;
        this.price = price;
        this.type = type;
    }

    /**
     * 获取产品id
     * @return :药品id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置产品id
     * @param id:药品id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取产品名称
     * @return :药品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名称
     * @param name:药品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取产品剂型
     * @return :药品剂型
     */
    public String getDosageForm() {
        return dosageForm;
    }

    /**
     * 设置产品剂型
     * @param dosageForm:药品剂型
     */
    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    /**
     * 获取产品规格
     * @return :药品规格
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * 设置产品规格
     * @param specifications:药品规格
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * 获取使用说明
     * @return :使用说明
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置使用说明
     * @param introduction:使用说明
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取参考价格
     * @return :参考价格
     */
    public int getPrice() {
        return price;
    }

    /**
     * 设置参考价格
     * @param price:参考价格
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * 获取产品类别
     * @return :药品类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置产品类别
     * @param type:药品类别
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", specifications='" + specifications + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
