package com.willshen.smartAppointments;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Service {
    @Id
    @Column(name="serviceId", updatable=false, nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name="serviceName", nullable=false, length=30)
    private String serviceName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="email")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<User> staffId;
    private String description;

    public Service(){}

    public Service(String serviceName, String description) {
        this.serviceName = serviceName;
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public Set<User> getStaffId() {
        return staffId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStaffId(Set<User> staffIds) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"serviceName\":\"" + serviceName + '\"' +
                ", \"staffId\":\"" + staffId + '\"' +
                ", \"description\":\"" + description + '\"' +
                '}';
    }
}