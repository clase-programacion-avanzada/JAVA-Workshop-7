package com.javeriana;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando la aplicación...");
        System.out.println("Creando servicios");
        /*
        PlaylistService playlistService = new PlaylistService();
        SongService songService = new SongService();
        ArtistService artistService = new ArtistService();
        CustomerAdminService customerAdminService = new CustomerAdminService();

        System.out.println("Creando controladores");
        //Create Controller
        AdminController adminController = new AdminController(artistService, customerAdminService, songService, playlistService);

        System.out.println("Creando vistas");
        //Create Views
        Scanner scanner = new Scanner(System.in);
        AdminView adminView = new AdminView(adminController, scanner);
        */
        Scanner scanner = new Scanner(System.in);
        //Show Menu
        int option = 0;

        do {
            System.out.println("""
                    =Menú principal=
                    
                    Seleccione qué vista desea utilizar:
                    
                    1. Administrador
                    0. Salir
                    """);
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> System.out.println("Implemente la funcionalidad");//adminView.showMenu();

                case 0 -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);

        scanner.close();

    }
}