package com.willshen.smartAppointments;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MasterController {
    @RequestMapping(value="/getAppointment")
    public ModelAndView getAppointment(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String parsedAppointmentId = request.getParameter("getAppointmentById");
        int appointmentId = Integer.parseInt(parsedAppointmentId);
        ModelAndView viewToReturn = null;

        AppointmentManager appointmentManager = new AppointmentManager();
        Appointment retrievedAppointment = appointmentManager.getAppointment(appointmentId);

        if(retrievedAppointment!=null){
            viewToReturn = new ModelAndView("appointment", "retrievedAppointment", retrievedAppointment);
            session.setAttribute("retrievedAppointment", retrievedAppointment);
        } else {
            viewToReturn = new ModelAndView("noAppointmentFound", "email", request.getParameter("getAppointmentById"));
        }
        return viewToReturn;
    }

    @RequestMapping(value="/createUser")
    public ResponseEntity<?> createUser(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        LocalDate parsedDob = LocalDate.parse(dob);
        String phone = request.getParameter("phone");

        UserManager usersManager = new UserManager();
        User user = new User(name, parsedDob, email, phone, password, UserType.USER );

        boolean success = usersManager.registerUser(user);
        if(success){
            try{
                response.getWriter().print("Success! You have been registered!");
                response.sendRedirect("http://localhost:8080/smartAppointments/login.html");
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                response.getWriter().print("Opps, we could not register you. Please ensure your details are entered correctly!");
                response.sendRedirect("http://localhost:8080/smartAppointments/registration.html");
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/createStaff")
    public ResponseEntity<?> createStaff(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        LocalDate parsedDob = LocalDate.parse(dob);
        String phone = request.getParameter("phone");

        UserManager usersManager = new UserManager();
        User user = new User(name, parsedDob, email, phone, password, UserType.STAFF );

        boolean success = usersManager.registerUser(user);
        if(success){
            try{
                response.getWriter().print("Success! New staff is created!");
                response.sendRedirect("http://localhost:8080/smartAppointments/login.html");
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                response.getWriter().print("Opps, we could not register you. Please ensure your details are entered correctly!");
                response.sendRedirect("http://localhost:8080/smartAppointments/registration.html");
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/createAdmin")
    public ResponseEntity<?> createAdmin(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        LocalDate parsedDob = LocalDate.parse(dob);
        String phone = request.getParameter("phone");

        UserManager usersManager = new UserManager();
        User newAdmin = new User(name, parsedDob, email, phone, password, UserType.ADMIN);

        boolean success = usersManager.registerUser(newAdmin);
        if(success){
            try{
                response.getWriter().print("Success! New admin is created!");
                response.sendRedirect("http://localhost:8080/smartAppointments/login.html");
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                response.getWriter().print("Opps, we could not register you. Please ensure your details are entered correctly!");
                response.sendRedirect("http://localhost:8080/smartAppointments/createAdmin.html");
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/createService")
    public ResponseEntity<?> createService(HttpServletRequest request, HttpServletResponse response){
        String serviceName = request.getParameter("serviceName");
        String serviceDescription = request.getParameter("serviceDescription");

        ServiceManager serviceManager = new ServiceManager();
        Service newService = new Service(serviceName, serviceDescription);

        boolean success = serviceManager.createService(newService);

        if(success){
            try{
                response.sendRedirect("http://localhost:8080/smartAppointments/index.html");
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                response.getWriter().print("Opps, we not could add the new service.");
                response.sendRedirect("http://localhost:8080/smartAppointments/createService.html");
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/createAppointment")
    public ResponseEntity<?> createAppointment(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object parsedEmail = session.getAttribute("email"); //get current user's email
        if(parsedEmail == null){
        }
        String email = parsedEmail.toString();

        String parsedDate = request.getParameter("date");
        LocalDate date = LocalDate.parse(parsedDate);
        String staffEmail= request.getParameter("staffEmail");
        String notes= request.getParameter("notes");

        UserManager userManager = new UserManager();
        User user = userManager.getUser(email);
        User staff = userManager.getUser(staffEmail);
        AppointmentManager appointmentManager = new AppointmentManager();
        Appointment newAppointment = new Appointment(date, user, staff, notes);
        boolean success = appointmentManager.addAppointment(newAppointment);

        if(success){
            try{
                response.sendRedirect("http://localhost:8080/smartAppointments/index.html");
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                response.getWriter().print("Opps, we not could add the new appointment.");
                response.sendRedirect("http://localhost:8080/smartAppointments/createAppointment.html");
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/updateAppointment")
    public ResponseEntity<?> updateAppointment(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String parsedId = request.getParameter("id");
        Long id = Long.parseLong(parsedId);
        String email = request.getParameter("staffEmail");
        String parsedDate = request.getParameter("date");
        LocalDate date = LocalDate.parse(parsedDate);
        String notes = request.getParameter("notes");
        System.out.println("ID=" + id);

        UserManager userManager = new UserManager();
        User staff = userManager.getUser(email);

        AppointmentManager appointmentManager = new AppointmentManager();

        boolean success = appointmentManager.updateAppointment(id, date, staff, notes);
        if(success){
            try{
                response.getWriter().print("Success! appointment is updated!");
                response.sendRedirect("http://localhost:8080/smartAppointments/index.html");
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                response.getWriter().print("Opps, we could not update the appointment!");
                response.sendRedirect("http://localhost:8080/smartAppointments/error.jsp");
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/login")
    public ModelAndView loginValidate(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ModelAndView viewToReturn = null;

        UserManager usersManager = new UserManager();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        boolean success = usersManager.validateUser(user);

        if(success){
            UserManager userManager = new UserManager();
            User loggedinUser = userManager.getUser(email);
            String name = loggedinUser.getName();
            List<Appointment> appointments = null;
            AppointmentManager appointmentManager = new AppointmentManager();
            appointments = appointmentManager.getAppointmentByUserEmail(email);
            viewToReturn = new ModelAndView("welcome", "appointmentList", appointments);
//            session.setMaxInactiveInterval(5000);
            session.setAttribute("name", name); //stored in the session
            session.setAttribute("email", email);
            session.setAttribute("appointments", appointments);
        } else {
            viewToReturn = new ModelAndView("invalidLogin", "email", request.getParameter("email"));
        }
        return viewToReturn;
    }

    @RequestMapping(value="/retrieveAppointments", produces = "application/json")
    public ResponseEntity<List> retrieveAppointments(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object email = session.getAttribute("email");
        String convertedEmail = email.toString();
        if(email == null){
            return null;
        } else {
            List<Appointment> appointments = null;
            AppointmentManager appointmentManager = new AppointmentManager();
            appointments = appointmentManager.getAppointmentByUserEmail(convertedEmail);
            return new ResponseEntity<List>(appointments, HttpStatus.ACCEPTED);
        }
    }

}
