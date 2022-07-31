package top.javahai.datasource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Ethan
 * @since 2020-11-19
 */
@TableName(value = "t_user_info")
public class TUserinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user_name
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 所在学院
     */
    @TableField("areaObj")
    private String areaObj;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 学生照片
     */
    private String userPhoto;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 家庭地址
     */
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAreaObj() {
        return areaObj;
    }

    public void setAreaObj(String areaObj) {
        this.areaObj = areaObj;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TUserinfo{" +
        "userName=" + userName +
        ", password=" + password +
        ", areaObj=" + areaObj +
        ", name=" + name +
        ", sex=" + sex +
        ", userPhoto=" + userPhoto +
        ", birthday=" + birthday +
        ", telephone=" + telephone +
        ", address=" + address +
        "}";
    }
}
