package ru.i_novus.number.generator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Number {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String number;

    public Number(){
    }

    public Number(String number){
        this.number = number;
    }

    public Long getId(){
        return this.Id;
    }

    public String getNumber(){
        return this.number;
    }

    public void setId(Long Id){
        this.Id = Id;
    }

    public void setNumber(String number){
        this.number = number;
    }

}
