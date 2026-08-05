/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetohotel;

/**
 *
 * @author jotap
 */
public class Reserva extends ProjetoHotel {
    private Cliente cliente;
    private int numeroQuarto;
    private String status; // "Reservado", "Check-in", "Check-out"
    private Integer avaliacao;
    public Reserva(Cliente cliente, int numeroQuarto) {
        this.cliente = cliente;
        this.numeroQuarto = numeroQuarto;
        this.status = "Reservado";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public String getStatus() {
        return status;
    }
    

    public void checkIn() {
        this.status = "Check-in";
    }

    public void checkOut() {
        this.status = "Check-out";
    }

    public String toString() {
        return cliente + " | Quarto: " + numeroQuarto + " | Status: " + status;
    }
     
   
}
