package com.willshen.smartAppointments;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class AppointmentManager {
    static Configuration configuration = new Configuration();
    static {
        configuration.configure();
    }
    static SessionFactory sessionFactory;

    static{
        try{
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function adds a new appointment object into the database table.
     * @param appointment This is the appointment object to be added into database.
     * @return It returns true if successfully added and false otherwise.
     */
    public boolean addAppointment(Appointment appointment){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(appointment);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function returns the appointment object given an appointment id.
     * @param appointmentId The appointment id is the search key for the appointment object.
     * @return Return the appointment object, and if not found it will return null.
     */
    public Appointment getAppointment(long appointmentId) {
        try {
            Session session = sessionFactory.openSession();
            Appointment appointment = session.get(Appointment.class, appointmentId);
            session.close();
            return appointment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function updates the appointment based an appointment id.
     * @param appointmentId
     * @param date
     * @param staff
     * @return
     */

    public boolean updateAppointment(long appointmentId, LocalDate date, User staff, String notes){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Appointment appointmentToBeUpdated = session.get(Appointment.class, appointmentId);
            appointmentToBeUpdated.setBookingDate(date);
            appointmentToBeUpdated.setStaff(staff);
            appointmentToBeUpdated.setNote(notes);
            session.update(appointmentToBeUpdated);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function update the appointment date given an appointment id
     * @param appointmentId Appointment id of the appointment to be updated
     * @param date Date in YYYY-MM-DD format.
     * @return Return true if database record is updated, and false otherwise.
     */
    public boolean updateAppointmentDate(long appointmentId, LocalDate date){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Appointment appointmentToBeUpdated = session.get(Appointment.class, appointmentId);
            appointmentToBeUpdated.setBookingDate(date);
            session.update(appointmentToBeUpdated);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function will take the appointment id of the appointment and allows user to change the staff of the appointment
     * @param appointmentId Appointment id of the appointment.
     * @param staff Takes the Staff object associated with that appointment.
     * @return Returns true if updating the appointment is successful, and false otherwise.
     */
    public boolean updateAppointmentStaff(long appointmentId, User staff){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Appointment appointmentToBeUpdated = session.get(Appointment.class, appointmentId);
            appointmentToBeUpdated.setStaff(staff);
            session.update(appointmentToBeUpdated);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function takes an appointment id as argument and delete that appointment from the database
     * @param appointmentId Appointment id of that particular appointment (primary id).
     * @return Return true if that appointment is deleted successfully, and false otherwise.
     */
    public boolean deleteAppointment(long appointmentId){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Appointment appointmentToBeDeleted = session.get(Appointment.class, appointmentId);
            session.remove(appointmentToBeDeleted);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function takes the user's email and outputs all the appointments made by that user.
     * @param email Email of the user (primary id).
     * @return Returns a list of all appointments made by that user.
     */
    public List getAppointmentByUserEmail(String email) {
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("from Appointment where userId = :email");
            query.setParameter("email", email);
            List list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function takes the staff email as arguments and returns a list of all services provided by that staff.
     * @param email Email is the primary ID of the staff used to find all services of that staff.
     * @return Returns a list of all services names for that staff.
     */
    public List getAllServicesByStaff(String email) {
        try{
            Session session = sessionFactory.openSession();

            Query SQLquery = session.createSQLQuery("SELECT distinct serviceName FROM user, service, Service_User as su " +
                    "WHERE su.staffId_email=user.email AND su.staffId_email = :email");
            SQLquery.setParameter("email", email);
            List<String> list = SQLquery.list();
            return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function takes a List as argument and print out the contents line by line.
     * @param listToBePrinted This is the list passed in and to be printed.
     */
    public void printResultListForAppointments(List listToBePrinted){
        System.out.println("Service provided by this user are:");
//        listToBePrinted.forEach(s -> System.out.println(s));
        for(int i=0; i<listToBePrinted.size(); i++){
            int j = i+1;
            System.out.println(j+": " + listToBePrinted.get(i));
        }
    }
}
