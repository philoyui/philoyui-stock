package io.philoyui.media.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

//@Entity
public class FunEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * OpenId
     */
    private String openId;

    /**
     * 所属公众号
     */
    private Long accountId;

    /**
     * 订阅时间
     */
    private Date subscribeTime;

    /**
     * 最后联系时间
     */
    private Date lastContactTime;

    /**
     * 所在省
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getLastContactTime() {
        return lastContactTime;
    }

    public void setLastContactTime(Date lastContactTime) {
        this.lastContactTime = lastContactTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
