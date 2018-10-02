package com.blueice.microweartherbasic.bean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "d") //从d元素开始解析
@XmlAccessorType(XmlAccessType.FIELD)
public class City {
    @XmlAttribute(name = "d1")
    private String cityId;

    @XmlAttribute(name = "d2")
    private String cityName;

    @XmlAttribute(name = "d3")
    private String cityNamePY; //城市拼音名

    @XmlAttribute(name = "d4")
    private String province; //城市所属省

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNamePY() {
        return cityNamePY;
    }

    public void setCityNamePY(String cityNamePY) {
        this.cityNamePY = cityNamePY;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
