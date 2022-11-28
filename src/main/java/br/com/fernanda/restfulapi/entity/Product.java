package br.com.fernanda.restfulapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "quantity is required")
    private int quantity;

    @NotBlank(message = "message is required")
    private double value;


    public Product() {
        //default constructor
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
