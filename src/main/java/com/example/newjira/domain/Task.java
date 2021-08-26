package com.example.newjira.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="tsk")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = true)
    private User user;

    //private Integer user_id;
    private String title;
    private String description;
    private Date date1;
    private Date date2;

    @ManyToOne
    @JoinColumn(name="status_id",referencedColumnName = "id",nullable = false)
    private Status status;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    //private Integer status_id;

    public Task(){

    }

    @Override
    public String toString(){
        return "id:"+id+"; user_id:"+(user==null?"null":user.getId())+"; title:"+title+"; desc:"+description+"; date1:"+date1+"; date2:"+date2+"; status_id:"+(status==null?"null":status.getId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user=user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date create_date) {
        this.date1 = create_date;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date due_date) {
        this.date2 = due_date;
    }

    public String getDate1String(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date1);
    }

    public String getDate2String(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date2);
    }
}
