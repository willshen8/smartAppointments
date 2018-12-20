package com.willshen.smartAppointments;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class UserRegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String dob = req.getParameter("dob");
        LocalDate parsedDob = LocalDate.parse(dob);
        String phone = req.getParameter("phone");

        UserManager usersManager = new UserManager();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setDob(parsedDob);
        user.setPassword(password);
        user.setPhone(phone);
        boolean success = usersManager.registerUser(user);
        if(success){
            try{
                resp.getWriter().print("Success! You have been registered!");
                resp.sendRedirect("http://localhost:8080/smartAppointments/login.html");
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                resp.getWriter().print("Opps, we could not register you. Please ensure your details are entered correctly!");
                resp.sendRedirect("http://localhost:8080/smartAppointments/login.html");  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
