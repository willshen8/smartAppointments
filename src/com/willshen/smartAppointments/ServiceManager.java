package com.willshen.smartAppointments;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class ServiceManager {
    static Configuration configuration = new Configuration();
    static {
        configuration.configure();
    }
    static SessionFactory sessionFactory = configuration.buildSessionFactory();

    /**
     * This function gets a service object and saves into the database through hibernate
     * @param service Service object contains the service to be saved into the database
     * @return Returns true if saved successfully and false otherwise
     */
    public boolean createService(Service service){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(service);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * A staff can perform multiple services/tasks/responsibilities. This function will add a created service under that staff.
     * @param serviceId Service id uniquely identifies a particular service (primary id).
     * @param staffEmail Staff email uniquely identifies a staff (primary id).
     * @return Returns true if service is added to the joining table.
     */
    //Add a staff to an existing service
    public boolean addUserToExistingService(long serviceId, String staffEmail){
        try{
            Session session = sessionFactory.openSession();
            ServiceManager serviceManager = new ServiceManager();
            Service service = serviceManager.getService(serviceId);

            UserManager userManager = new UserManager();
            User user = userManager.getUser(staffEmail);
            Set<User> staffIdsInSet = service.getStaffId();
            staffIdsInSet.add(user);
            service.setStaffId(staffIdsInSet);
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(service);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This function retrieves the service from the database and return it as an object.
     * @param serviceId Service id is the primary key to the service and uniquely identifies the service.
     * @return Returns the fetched service object if successful and null otherwise.
     */
    public Service getService(long serviceId) {
        try {
            Session session = sessionFactory.openSession();
            Service service = session.get(Service.class, serviceId);
            session.close();
            return service;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
