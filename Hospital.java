/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital;

/**
 *
 * @author RODRIGO
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class Hospital {
    private List<Paciente> listaPacientes = new ArrayList<>();
    private List<Medico> listaDoctores = new ArrayList<>();

    public static void main(String[] args) {
       Hospital Hospital = new Hospital();

        // Crear doctores
       Medico doctor1 = new Medico();
       doctor1.setNombre("Dr.Smith");
       doctor1.setEspecialidad("Cardiologia");
       doctor1.setNumeroColegiatura(4);
      
       
       Medico doctor2 = new Medico();
       doctor2.setNombre("Dr.Rodriguez");
       doctor2.setEspecialidad("Neumología");
       doctor2.setNumeroColegiatura(5);
       
        // Crear pacientes
        Paciente paciente1 = new Paciente();
        paciente1.setDNI(123456789);
        paciente1.setNombre("Rodrigo");
        paciente1.setDireccion("Calle Los Mojaves 543");
        paciente1.setPeso(70);
        paciente1.setTemperatura(36.0);
        paciente1.setMedicoAsignado(doctor2);
        
        Paciente paciente2 = new Paciente();
        paciente2.setDNI(987654321);
        paciente2.setNombre("Janet");
        paciente2.setDireccion("Calle Las Tunas 321");
        paciente2.setPeso(60);
        paciente2.setTemperatura(36.5);
        paciente2.setMedicoAsignado(doctor1);

        // Asignar doctores a pacientes
        paciente1.setMedicoAsignado(doctor2);
        paciente2.setMedicoAsignado(doctor1);

        // Agregar doctores y pacientes a las listas
        Hospital.listaDoctores.add(doctor1);
        Hospital.listaDoctores.add(doctor2);
        Hospital.listaPacientes.add(paciente1);
        Hospital.listaPacientes.add(paciente2);

        int opcion;
        Scanner scanner = new Scanner(System.in);

        do {
            mostrarMenu();
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();
            Hospital.ejecutarOperacion(opcion, scanner);
        } while (opcion != 0);

        scanner.close();

    }

    private static void mostrarMenu() {
        System.out.println("\n----- Menú -----");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Eliminar paciente");
        System.out.println("3. Modificar datos de un paciente");
        System.out.println("4. Mostrar el peso que más se repite");
        System.out.println("5. Mostrar cantidad de pacientes con el peso que más se repite");
        System.out.println("6. Mostrar peso mayor y menor");
        System.out.println("7. Dividir rango de pesos y mostrar cantidad de personas en cada rango");
        System.out.println("8. Mostrar lista de pacientes ordenados por Nombres");
        System.out.println("9. Dado un paciente, indicar qué doctor lo atendió");
        System.out.println("10. Buscar doctores por especialidad");
        System.out.println("0. Salir");
    }

    public void ejecutarOperacion(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1 -> registrarPaciente(scanner);
            case 2 -> eliminarPaciente(scanner);
            case 3 -> modificarPaciente(scanner);
            case 4 -> mostrarPesoMasRepetido();
            case 5 -> mostrarCantidadPacientesConPesoMasRepetido();
            case 6 -> mostrarPesoMayorYMenor();
            case 7 -> mostrarCantidadPersonasPorRango();
            case 8 -> mostrarPacientesOrdenadosPorNombres();
            case 9 -> mostrarDoctorQueAtendioPaciente(scanner);
            case 10 -> buscarDoctoresPorEspecialidad(scanner);
            case 0 -> System.out.println("Saliendo del programa. ¡Hasta luego!");
            default -> System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
        }
    }

    private void registrarPaciente(Scanner scanner) {
        Paciente paciente = new Paciente();

        System.out.print("Ingrese el DNI del paciente: ");
        paciente.setDNI(scanner.nextInt());

        System.out.print("Ingrese el nombre del paciente: ");
        scanner.nextLine(); // Consumir el salto de línea pendiente
        paciente.setNombre(scanner.nextLine());

        System.out.print("Ingrese la dirección del paciente: ");
        paciente.setDireccion(scanner.nextLine());

        System.out.print("Ingrese el peso del paciente: ");
        paciente.setPeso(scanner.nextDouble());

        System.out.print("Ingrese la temperatura del paciente: ");
        paciente.setTemperatura(scanner.nextDouble());

        // Asignar médico al azar de la lista de doctores (simulación)
        if (!listaDoctores.isEmpty()) {
            Random random = new Random();
            Medico medicoAsignado = listaDoctores.get(random.nextInt(listaDoctores.size()));
            paciente.setMedicoAsignado(medicoAsignado);
        }

        listaPacientes.add(paciente);
        System.out.println("Paciente registrado correctamente.");
    }

    private void eliminarPaciente(Scanner scanner) {
        if (!listaPacientes.isEmpty()) {
            System.out.print("Ingrese la posición del paciente a eliminar: ");
            int posicionEliminar = scanner.nextInt();
            if (posicionEliminar >= 0 && posicionEliminar < listaPacientes.size()) {
                listaPacientes.remove(posicionEliminar);
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("Posición inválida. No se eliminó ningún paciente.");
            }
        } else {
            System.out.println("No hay pacientes para eliminar.");
        }
    }

    private void modificarPaciente(Scanner scanner) {
        if (!listaPacientes.isEmpty()) {
            System.out.print("Ingrese la posición del paciente a modificar: ");
            int posicionModificar = scanner.nextInt();
            if (posicionModificar >= 0 && posicionModificar < listaPacientes.size()) {
                Paciente pacienteModificar = listaPacientes.get(posicionModificar);
                Paciente nuevoPaciente = crearPaciente(scanner);

                // Mantener el médico asignado del paciente original
                nuevoPaciente.setMedicoAsignado(pacienteModificar.getMedicoAsignado());

                listaPacientes.set(posicionModificar, nuevoPaciente);
                System.out.println("Paciente modificado correctamente.");
            } else {
                System.out.println("Posición inválida. No se modificó ningún paciente.");
            }
        } else {
            System.out.println("No hay pacientes para modificar.");
        }
    }

    private void mostrarPesoMasRepetido() {
        if (!listaPacientes.isEmpty()) {
            double pesoMasRepetido = pesoMasRepetido();
            System.out.println("Peso que más se repite: " + pesoMasRepetido);
        } else {
            System.out.println("No hay pacientes para mostrar el peso que más se repite.");
        }
    }

    private void mostrarCantidadPacientesConPesoMasRepetido() {
        if (!listaPacientes.isEmpty()) {
            int cantidadPacientes = cantidadPacientesConPesoMasRepetido();
            System.out.println("Cantidad de pacientes con el peso que más se repite: " + cantidadPacientes);
        } else {
            System.out.println("No hay pacientes para mostrar la cantidad de pacientes con el peso que más se repite.");
        }
    }

    private void mostrarPesoMayorYMenor() {
        if (!listaPacientes.isEmpty()) {
            System.out.println("Peso mayor y menor:");
            Paciente pacienteMayor = Collections.max(listaPacientes, Comparator.comparing(Paciente::getPeso));
            Paciente pacienteMenor = Collections.min(listaPacientes, Comparator.comparing(Paciente::getPeso));
            System.out.println("Mayor: " + pacienteMayor.getPeso() + " kg - " + pacienteMayor.getNombre());
            System.out.println("Menor: " + pacienteMenor.getPeso() + " kg - " + pacienteMenor.getNombre());
        } else {
            System.out.println("No hay pacientes para mostrar el peso mayor y menor.");
        }
    }

    private void mostrarCantidadPersonasPorRango() {
    if (!listaPacientes.isEmpty()) {
        // Definir rangos de pesos
        double pesoMinimo = 40.0;
        double pesoMaximo = 120.0;
        int cantidadRangos = 4;

        // Calcular el tamaño de cada rango
        double rangoSize = (pesoMaximo - pesoMinimo) / cantidadRangos;

        // Inicializar contadores para cada rango
        int[] cantidadEnRango = new int[cantidadRangos];

        // Contar la cantidad de personas en cada rango
        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();

            for (int i = 0; i < cantidadRangos; i++) {
                double rangoInicio = pesoMinimo + i * rangoSize;
                double rangoFin = pesoMinimo + (i + 1) * rangoSize;

                if (peso >= rangoInicio && peso < rangoFin) {
                    cantidadEnRango[i]++;
                    break;  // Salir del bucle una vez que se ha encontrado el rango
                }
            }
        }

        // Mostrar la cantidad de personas en cada rango
        for (int i = 0; i < cantidadRangos; i++) {
            double rangoInicio = pesoMinimo + i * rangoSize;
            double rangoFin = pesoMinimo + (i + 1) * rangoSize;

            System.out.println("Rango " + (i + 1) + ": " +
                    "De " + rangoInicio + " kg a " + rangoFin + " kg - " +
                    cantidadEnRango[i] + " personas.");
        }
    } else {
        System.out.println("No hay pacientes para mostrar la cantidad de personas por rango de pesos.");
    }
}


    private void mostrarPacientesOrdenadosPorNombres() {
        if (!listaPacientes.isEmpty()) {
            Collections.sort(listaPacientes, Comparator.comparing(Paciente::getNombre));
            System.out.println("Lista de pacientes ordenados por Nombre:");
            for (Paciente paciente : listaPacientes) {
                System.out.println("Nombre: " + paciente.getNombre());
            }
        } else {
            System.out.println("No hay pacientes para mostrar la lista ordenada por Nombres.");
        }
    }

    private void mostrarDoctorQueAtendioPaciente(Scanner scanner) {
        if (!listaPacientes.isEmpty()) {
            System.out.print("Ingrese la posición del paciente: ");
            int posicionBuscarDoctor = scanner.nextInt();
            if (posicionBuscarDoctor >= 0 && posicionBuscarDoctor < listaPacientes.size()) {
                Paciente pacienteBuscarDoctor = listaPacientes.get(posicionBuscarDoctor);
                Medico doctorQueAtendioPaciente = doctorQueAtendioAlPaciente(pacienteBuscarDoctor);
                System.out.println("El paciente fue atendido por el Dr. " + doctorQueAtendioPaciente.getNombre());
            } else {
                System.out.println("Posición inválida. No se buscó ningún doctor.");
            }
        } else {
            System.out.println("No hay pacientes para buscar al doctor que los atendió.");
        }
    }

    private void buscarDoctoresPorEspecialidad(Scanner scanner) {
        if (!listaDoctores.isEmpty()) {
            System.out.print("Ingrese la especialidad a buscar: ");
            scanner.nextLine(); // Consumir el salto de línea pendiente
            String especialidadBuscar = scanner.nextLine();
            List<Medico> doctoresEncontrados = buscarDoctoresPorEspecialidad(especialidadBuscar);
            System.out.println("Doctores en la especialidad de " + especialidadBuscar + ":");
            for (Medico medico : doctoresEncontrados) {
                System.out.println("Número de colegiatura: " + medico.getNumeroColegiatura() +
                        ", Nombre: " + medico.getNombre() + ", Especialidad: " + medico.getEspecialidad());
            }
        } else {
            System.out.println("No hay doctores para buscar por especialidad.");
        }
    }


    private Paciente crearPaciente(Scanner scanner) {
        Paciente paciente = new Paciente();

        System.out.print("Ingrese el DNI del paciente: ");
        paciente.setDNI(scanner.nextInt());

        System.out.print("Ingrese el nombre del paciente: ");
        scanner.nextLine(); // Consumir el salto de línea pendiente
        paciente.setNombre(scanner.nextLine());

        System.out.print("Ingrese la dirección del paciente: ");
        paciente.setDireccion(scanner.nextLine());

        System.out.print("Ingrese el peso del paciente: ");
        paciente.setPeso(scanner.nextDouble());

        System.out.print("Ingrese la temperatura del paciente: ");
        paciente.setTemperatura(scanner.nextDouble());

        
        if (!listaDoctores.isEmpty()) {
            Random random = new Random();
            Medico medicoAsignado = listaDoctores.get(random.nextInt(listaDoctores.size()));
            paciente.setMedicoAsignado(medicoAsignado);
        }

        return paciente;
    }


    private double pesoMasRepetido() {
        if (!listaPacientes.isEmpty()) {
            Map<Double, Long> conteoPesos = listaPacientes.stream()
                    .collect(Collectors.groupingBy(Paciente::getPeso, Collectors.counting()));

            double pesoMasRepetido = conteoPesos.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(0.0);

            return pesoMasRepetido;
        } else {
            return 0.0;
        }
    }

    private int cantidadPacientesConPesoMasRepetido() {
        if (!listaPacientes.isEmpty()) {
            double pesoMasRepetido = pesoMasRepetido();
            long cantidadPacientes = listaPacientes.stream()
                    .filter(paciente -> paciente.getPeso() == pesoMasRepetido)
                    .count();

            return (int) cantidadPacientes;
        } else {
            return 0;
        }
    }

    private List<Medico> buscarDoctoresPorEspecialidad(String especialidad) {
        return listaDoctores.stream()
                .filter(medico -> medico.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }

    private Medico doctorQueAtendioAlPaciente(Paciente paciente) {
        return paciente.getMedicoAsignado();
    }

  
}





   


