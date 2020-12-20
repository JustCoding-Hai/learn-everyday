package top.javahai.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ethan
 * @since 2020-11-21
 */
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    private String cUsername;

    private String cPassword;

    private Integer cGender;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }
    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }
    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }
    public Integer getcGender() {
        return cGender;
    }

    public void setcGender(Integer cGender) {
        this.cGender = cGender;
    }

    @Override
    public String toString() {
        return "TUser{" +
        "cId=" + cId +
        ", cUsername=" + cUsername +
        ", cPassword=" + cPassword +
        ", cGender=" + cGender +
        "}";
    }
}
