package edu.lab1.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidadorEntrada {
    private final Scanner scanner;

    public ValidadorEntrada() {
        this.scanner = new Scanner(System.in);
    }

    public int lerOpcaoMenu() {
        try {
            int opcao = scanner.nextInt();
            if (opcao < 1 || opcao > 7) {
                System.out.println("❌ Opção inválida! Escolha entre 1 e 7.");
                System.out.print("➤ Digite novamente: ");
                return lerOpcaoMenu();
            }
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida! Digite um número.");
            System.out.print("➤ Digite novamente: ");
            scanner.nextLine(); // Limpa o buffer
            return lerOpcaoMenu();
        }
    }

    public double lerValor() {
        System.out.print("💵 Digite o valor a ser convertido: ");
        try {
            double valor = scanner.nextDouble();
            if (valor < 0) {
                System.out.println("❌ O valor deve ser positivo!");
                return -1;
            }
            return valor;
        } catch (InputMismatchException e) {
            System.out.println("❌ Valor inválido! Digite um número.");
            scanner.nextLine(); // Limpa o buffer
            return -1;
        }
    }
}