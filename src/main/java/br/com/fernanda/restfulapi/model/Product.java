package br.com.fernanda.restfulapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Transactional
@XmlRootElement
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 50)
    private String name;
    private int quantity;
    private double value;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
