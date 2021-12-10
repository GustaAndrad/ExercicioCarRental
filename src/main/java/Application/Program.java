/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalServices;

/**
 *
 * @author gusta
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
        
        System.out.println("Enter rental data");
        System.out.println("Car model: ");
        String carModel = sc.nextLine();
        System.out.println("Pickup (dd/MM/yyyy hh:ss):");
        Date start = sdf.parse(sc.nextLine());
        System.out.println("Return (dd/MM/yyyy hh:ss):");
        Date finish = sdf.parse(sc.nextLine());
        
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
        
        System.out.println("Enter price per hour:");
        double pricePerHour = sc.nextDouble();
        System.out.println("Enta price per day:");
        double pricePerDay = sc.nextDouble();
        
        RentalServices rentalService = new RentalServices(pricePerDay,
                pricePerHour, new BrazilTaxService());
        
        rentalService.processInvoice(cr);
        
        System.out.println("INVOICE:");
        System.out.println("Basic payment" + String.format("%.2f",
                cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f",
                cr.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("%.2f",
                cr.getInvoice().getTotalPayment()));
        
        
        
        
        sc.close();
    }
    
}
