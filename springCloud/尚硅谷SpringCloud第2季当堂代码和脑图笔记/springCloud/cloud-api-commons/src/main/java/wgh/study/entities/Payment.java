package wgh.study.entities;

/**
 * Author:   wang
 * Date:     2022/5/4 21:49
 * function: payment 表的 dao
 */

import java.io.Serializable;

/**
 * Author:   wang
 * Date:     2022/5/4 16:38
 * function: payment 表 entity
 * @author wang
 */
public class Payment implements Serializable {

    private static final long serialVersionUID = -2083485294938123403L;
    /**
     * id 字段
     */
    private Long id;

    /**
     * serial 字段
     */
    private String serial;

    /**
     * 两个参数构造方法
     * @param id
     * @param serial
     */
    public Payment(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    /**
     * 0 参数构造方法
     */
    public Payment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                '}';
    }
}