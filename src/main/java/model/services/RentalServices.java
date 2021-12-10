/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

/**
 *
 * @author gusta
 */
public class RentalServices {
    
    private Double pricePerDay;
    private Double pricePerHour;
    
    private TaxService taxService;

    public RentalServices(Double pricePerDay, Double pricePerHour, TaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }
    
    public void processInvoice(CarRental carRental){
        long t1 = carRental.getStart().getTime();
        long t2 = carRental.getFinish().getTime();
        double hours = (double) (t2 - t1) / 1000 / 60 / 60;
        
        double basicPayment;
        if(hours <= 12.0) {
            basicPayment = Math.ceil(hours) * pricePerHour;
        }
        else{
            basicPayment = Math.ceil(hours/24) * pricePerDay;
        }
        
        double tax = taxService.tax(basicPayment);
        
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
    
}
