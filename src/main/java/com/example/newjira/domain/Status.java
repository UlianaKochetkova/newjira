package com.example.newjira.domain;

import javax.persistence.*;


@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Status(){

    }
    public Status(String name){
        this.name=name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String status_name) {
        this.name = status_name;
    }
}
