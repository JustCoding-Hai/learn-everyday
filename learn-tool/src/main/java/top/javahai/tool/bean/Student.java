package top.javahai.tool.bean;

import top.javahai.tool.annotation.Sensitive;


/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2022/7/19 - 0:14
 **/
public class Student {

    private String name;

    @Sensitive(value = Sensitive.Type.ID_CARD)
    private String idCard;

    public Student() {
    }

    public Student(String name, String idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
