/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetohotel;

/**
 *
 * @author jotap
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProjetoHotel {
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String ARQUIVO_RESERVAS = "cadastro";

    public static void main(String[] args) {
        carregarReservas();
      
        
        
        
        /* menu principal */
        int opcao;
        do {
            System.out.println("\n=== Menu do Hotel ===");
            System.out.println("1. Cadastrar cliente e reservar quarto");
            System.out.println("2. Fazer check-in");
            System.out.println("3. Fazer check-out");
            System.out.println("4. Listar reservas");
            System.out.println("5. salvar reservas");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarReserva();
                case 2 -> realizarCheckIn();
                case 3 -> realizarCheckOut();
                case 4 -> listarReservas();
                case 5-> salvarReservas();
                case 0 -> salvarReservas();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    
    private static void cadastrarReserva() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Número do quarto: ");
        int numero = Integer.parseInt(scanner.nextLine());
        
    // condicional do quarto 
    Reserva existente = null;
    for (Reserva r : reservas) {
        if (r.getNumeroQuarto() == numero && !r.getStatus().equals("Check-out")) {
            existente = r;
            break;
        }
    }

    if (existente != null) {
        System.out.println("O quarto " + numero + " já está ocupado por " + existente.getCliente().getNome());
        System.out.print("Você é acompanhante desse cliente? (s/n): ");
        String resposta = scanner.nextLine();
        if (!resposta.equalsIgnoreCase("s")) {
            System.out.println("Reserva não permitida. Quarto já ocupado.");
            return;
        }
    }
        

        Cliente cliente = new Cliente(nome, cpf);
        Reserva reserva = new Reserva(cliente, numero);
        reservas.add(reserva);
        System.out.println("Reserva criada com sucesso!");
    }

    private static void realizarCheckIn() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        for (Reserva r : reservas) {
            if (r.getCliente().getCpf().equals(cpf)) {
                r.checkIn();
                System.out.println("Check-in realizado com sucesso!");
                return;
            }
        }
        System.out.println("Reserva não encontrada.");
    }

    private static void realizarCheckOut() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        for (Reserva r : reservas) {
            if (r.getCliente().getCpf().equals(cpf)) {
                r.checkOut();
                System.out.println("Check-out realizado com sucesso!");
                return;
            }
        }
        System.out.println("Reserva não encontrada.");
    }

    private static void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

    private static void salvarReservas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RESERVAS))) {
            for (Reserva r : reservas) {
                writer.write(r.getCliente().getNome() + ";" + r.getCliente().getCpf() + ";" +
                        r.getNumeroQuarto() + ";" + r.getStatus());
                writer.newLine();
            }
            System.out.println("Reservas salvas no arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
    
    

    private static void carregarReservas() {
        File file = new File(ARQUIVO_RESERVAS);
        
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RESERVAS))) {
            String linha;
            
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Cliente cliente = new Cliente(dados[0], dados[1]);
                Reserva reserva = new Reserva(cliente, Integer.parseInt(dados[2]));

                if (dados[3].equals("Check-in")) reserva.checkIn();
                else if (dados[3].equals("Check-out")) reserva.checkOut();

                reservas.add(reserva);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }
   
    
    
}