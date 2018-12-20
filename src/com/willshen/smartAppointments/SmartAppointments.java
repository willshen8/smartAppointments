package com.willshen.smartAppointments;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SmartAppointments {
    public static void MainMenu(){
        UserManager usersManager = new UserManager();
        ServiceManager serviceManager = new ServiceManager();
        AppointmentManager appointmentManager = new AppointmentManager();
        System.out.println("-----------------------------------");
        System.out.println("Welcome to to SmartAppointment App!");
        System.out.println("-----------------------------------");
        Scanner scanner = new Scanner(System.in);
        int optionSelected = -1;

        while(optionSelected!=0){
            System.out.println("Please select from the following menu to begin:");
            System.out.println("[1]: Create a new user with address.");
            System.out.println("[2]: Create a new user without address.");
            System.out.println("[3]: Create a new staff.");
            System.out.println("[4]: Create a new service.");
            System.out.println("[5]: Create a new appointment.");
            System.out.println("[6]: Edit an existing appointment.");
            System.out.println("[7]: Delete an existing appointment");
            System.out.println("[8]: Show all appointments by user.");
            System.out.println("[9]: Show all services provided by a particular staff.");
            System.out.println("[10]: Add an existing staff to a service provided");
            System.out.println("[0]: Exit the app.");

            optionSelected = Integer.parseInt(scanner.nextLine());
            switch(optionSelected){
                case 1:
                    System.out.println("Please enter the name of the User.");
                    String name = scanner.nextLine();
                    System.out.println("Please enter the date of birth in YYYY-MM-DD format.");
                    String parsedDob = scanner.nextLine();
                    LocalDate dob = LocalDate.parse(parsedDob);
                    System.out.println("Please enter an email address.");
                    String email = scanner.nextLine();
                    System.out.println("Please enter a password.");
                    String password = scanner.nextLine();
                    System.out.println("Please enter a phone number.");
                    String phone = scanner.nextLine();
                    System.out.println("Please enter a house number.");
                    String houseNumber = scanner.nextLine();
                    System.out.println("Please enter a street name.");
                    String streetName = scanner.nextLine();
                    System.out.println("Please enter a street type.");
                    String streetType = scanner.nextLine();
                    System.out.println("Please enter a suburb name.");
                    String suburb = scanner.nextLine();
                    System.out.println("Please enter a postcode.");
                    String postcodeInStringFormat = scanner.nextLine();
                    int postcode = Integer.parseInt(postcodeInStringFormat);
                    System.out.println("Please enter a state");
                    String state = scanner.nextLine();
                    System.out.println("Please enter a country");
                    String country = scanner.nextLine();


                    User newUser = new User(name, dob, email, phone, password, UserType.USER, houseNumber, streetName, streetType,
                            suburb, postcode, state, country);
                    boolean resultOfRegisteringNewUser = usersManager.registerUser(newUser);
                    if(resultOfRegisteringNewUser){
                        System.out.println("New User added:");
                        System.out.println(newUser.toString());
                    } else {
                        System.out.println("Something went wrong, please try to add new user again!");
                    }
                    break;
                case 2:
                    System.out.println("Please enter the name of the User.");
                    String name2 = scanner.nextLine();
                    System.out.println("Please enter the date of birth in YYYY-MM-DD format.");
                    String parsedDob2 = scanner.nextLine();
                    LocalDate dob2 = LocalDate.parse(parsedDob2);
                    System.out.println("Please enter an email address.");
                    String email2 = scanner.nextLine();
                    System.out.println("Please enter a password.");
                    String password2 = scanner.nextLine();
                    System.out.println("Please enter a phone number.");
                    String phone2 = scanner.nextLine();
                    User newUser2 = new User(name2, dob2, email2, phone2, password2, UserType.USER);
                    boolean resultOfRegisteringNewUser2 = usersManager.registerUser(newUser2);
                    if(resultOfRegisteringNewUser2){
                        System.out.println("New User added:");
                        System.out.println(newUser2.toString());
                    } else {
                        System.out.println("Something went wrong, please try to add new user again!");
                    }
                    break;
                case 3:
                    System.out.println("Please enter the name of the Staff.");
                    String staffName = scanner.nextLine();
                    System.out.println("Please enter the date of birth in YYYY-MM-DD format.");
                    String parsedStaffDob = scanner.nextLine();
                    LocalDate staffDob = LocalDate.parse(parsedStaffDob);
                    System.out.println("Please enter an email address.");
                    String staffEmail = scanner.nextLine();
                    System.out.println("Please enter a password.");
                    String staffPassword = scanner.nextLine();
                    System.out.println("Please enter a phone number.");
                    String staffPhone = scanner.nextLine();
                    User newStaff = new User(staffName, staffDob, staffEmail, staffPhone, staffPassword, UserType.STAFF);
                    boolean resultOfRegisteringNewStaff = usersManager.registerUser(newStaff);
                    if(resultOfRegisteringNewStaff){
                        System.out.println("New User added:");
                        System.out.println(newStaff.toString());
                    } else {
                        System.out.println("Something went wrong, please try to add new user again!");
                    }
                    break;
                case 4:
                    System.out.println("Please enter a new service name.");
                    String serviceName = scanner.nextLine();
                    System.out.println("Please enter a description for the service if required.");
                    String description = scanner.nextLine();
                    Service service = new Service(serviceName, description);
                    ServiceManager sm = new ServiceManager();
                    boolean resultOfAddNewService = sm.createService(service);
                    if(resultOfAddNewService){
                        System.out.println("Success, new service added!");
                        System.out.println(sm.toString());
                    } else {
                        System.out.println("Something went wrong, please try to add new service again!");
                    }
                    break;
                case 5:
                    System.out.println("Please enter your email address.");
                    String emailForUserAppointment = scanner.nextLine();
                    User user = usersManager.getUser(emailForUserAppointment);
                    System.out.println("Please enter the date for your appointment in YYYY-MM-DD format.");
                    String parsedDate = scanner.nextLine();
                    LocalDate appointmentDate = LocalDate.parse(parsedDate);
                    System.out.println("Please enter the email of the staff");
                    String emailForStaffAppointment = scanner.nextLine();
                    User staff = usersManager.getUser(emailForStaffAppointment);
                    System.out.println("Please enter any additional notes if required");
                    String notes = scanner.nextLine();
                    if(user!=null && staff!=null){
                        Appointment appointment =  new Appointment(appointmentDate, user, staff, notes);
                        boolean resultOfAddNewAppointment = appointmentManager.addAppointment(appointment);
                        if(resultOfAddNewAppointment){
                            System.out.println("Your appointment has been successfully scheduled.");
                        } else {
                            System.out.println("Something went wrong, please make the appointment again.");
                        }
                    } else{
                        System.out.println("Sorry, we can't find your or staff's details, and appointment can not be created");
                    }
                    break;
                case 6:
                    System.out.println("To edit your existing appointment, please follow the instructions below:");
                    System.out.println("Please enter the appointment ID to start.");
                    String parsedAppointmentId = scanner.nextLine();
                    int appointmentId = Integer.parseInt(parsedAppointmentId);
                    System.out.println("Please enter a new date if you'd like to change the date in YYYY-MM--DD format.");
                    String parsedDateAmendAppointment = scanner.nextLine();
                    LocalDate date = null;
                    if(!parsedDateAmendAppointment.isEmpty()){
                        date = LocalDate.parse(parsedDateAmendAppointment);
                    }
                    System.out.println("Enter the staff's email to change the staff.");
                    String emailStaff = scanner.nextLine();
                    System.out.println("Enter the notes to be changed.");
                    String updateNotes = scanner.nextLine();
                    Appointment retrievedAppointment = appointmentManager.getAppointment(appointmentId);
                    if(date!=null && emailStaff!=null && !emailStaff.isEmpty()){
                        retrievedAppointment.setBookingDate(date);
                        User staffToBeUpdated = usersManager.getUser(emailStaff);
                        retrievedAppointment.setStaff(staffToBeUpdated);
                        appointmentManager.updateAppointment(appointmentId, date, staffToBeUpdated, updateNotes);
                    } else if(date!=null && (emailStaff==null || emailStaff.isEmpty())){
                        retrievedAppointment.setBookingDate(date);
                        appointmentManager.updateAppointmentDate(appointmentId, date);
                    } else if(date==null && !emailStaff.isEmpty() && emailStaff!=null){
                        User staffToBeUpdated = usersManager.getUser(emailStaff);
                        retrievedAppointment.setStaff(staffToBeUpdated);
                        appointmentManager.updateAppointment(appointmentId, date, staffToBeUpdated, updateNotes);
                    }
                    break;
                case 7:
                    System.out.println("Please enter the appointment ID to be deleted.");
                    String parsedAppointmentIdForDeletion = scanner.nextLine();
                    int appointmentIdToBeDeleted = Integer.parseInt(parsedAppointmentIdForDeletion);
                    AppointmentManager am2 = new AppointmentManager();
                    am2.deleteAppointment(appointmentIdToBeDeleted);
                    break;
                case 8:
                    System.out.println("Please enter your email to get all the appointments:");
                    String searchAllAppointmentsEmail = scanner.nextLine();
                    List result = appointmentManager.getAppointmentByUserEmail(searchAllAppointmentsEmail);
                    for(int i=0; i<result.size(); i++){
                        System.out.println(result.get(i));
                    }
                    break;
                case 9:
                    System.out.println("Please enter the email to get all the service provided by this user.");
                    String searchAllServicesByEmail = scanner.nextLine();
                    List serviceResults = appointmentManager.getAllServicesByStaff(searchAllServicesByEmail);
                    appointmentManager.printResultListForAppointments(serviceResults);
                    break;
                case 10:
                    System.out.println("Hi, we'll now add an existing staff to service we provide.");
                    System.out.println("Please enter the Service ID.");
                    String scannedServiceId = scanner.nextLine();
                    long serviceId = Integer.parseInt(scannedServiceId);
                    System.out.println("Please enter the staff email.");
                    String staffEmailForAddingToAService = scanner.nextLine();
                    boolean option10Result =serviceManager.addUserToExistingService(serviceId, staffEmailForAddingToAService);
                    if(option10Result){
                        System.out.println("Success! " + staffEmailForAddingToAService + " has been added to " + serviceId);
                    } else {
                        System.out.println("Unsuccessful in adding staff to this service");
                    }
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Please select a valid option from 0-8.");
                    break;
            }
        }
    }
}
