package com.willshen.smartAppointments;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @Column(name="appointmentId", updatable=false, nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name="userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name="staffId")
    private User staff;
    private LocalDate bookingDate;
    private String note;

    public Appointment(){}

    public Appointment(LocalDate date, User user, User staff, String note) {
        this.bookingDate = date;
        this.user = user;
        this.staff = staff;
        this.note = note;
    }

    public Long getId()
    {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"user\":\"" + user.getName() + '\"' +
                ", \"staff\":\"" + staff.getName() + '\"' +
                ", \"bookingDate\":\"" + bookingDate + '\"' +
                ", \"note\":\"" + note + '\"' +
                '}';
    }
}
