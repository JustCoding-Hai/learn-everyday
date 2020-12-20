package top.javahai.learnelasticsearch.bean;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/20 - 13:20
 **/
public class User  {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
