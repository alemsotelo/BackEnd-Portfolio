/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.soteloale.Dto;

import javax.validation.constraints.NotBlank;


public class DtoEducacion {
    @NotBlank
    private String nombreEdu;
    @NotBlank
    private String descripcionEdu;

    public DtoEducacion() {
    }
    
    

    public DtoEducacion(String nombreEdu, String descripcionEdu) {
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
    }

    public String getNombreEdu() {
        return nombreEdu;
    }

    public void setNombreEdu(String nombreEdu) {
        this.nombreEdu = nombreEdu;
    }

    public String getDescripcionEdu() {
        return descripcionEdu;
    }

    public void setDescripcionEdu(String descripcionEdu) {
        this.descripcionEdu = descripcionEdu;
    }

    
   
    

    
}
