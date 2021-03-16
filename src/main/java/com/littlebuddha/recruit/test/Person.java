package com.littlebuddha.recruit.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author ck
 * @date 2020/9/14 15:32
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String id;
    private String name;
    private String sex;
    private List<Car> cars;
    private Map<String,String> position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Map<String, String> getPosition() {
        return position;
    }

    public void setPosition(Map<String, String> position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", cars=" + cars +
                ", position=" + position +
                '}';
    }
}
