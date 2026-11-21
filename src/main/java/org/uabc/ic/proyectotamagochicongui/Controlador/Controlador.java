/* package org.uabc.ic.proyectotamagochicongui.Controlador;

import org.uabc.ic.VIsta.VistaConsola;
import org.uabc.ic.modelo.Coleccion;
import org.uabc.ic.modelo.Tamagochi;

import java.util.Random;

public class Controlador {

    private VistaConsola vista;
    private Coleccion modelo;

    public Controlador(VistaConsola vista, Coleccion modelo) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar(){
        int op;
        int tp;
        do{
            op = vista.mostrarMenuPrincipal();

            switch (op){
                case 1:
                    IO.println("Leer la infomacion del tamagochi");
                    tp = vista.mostrarOpcionTamagochi();
                    //String nombre, int energia, int hambre, int felicidad, int salud, int agresividad, int imper
                    String nombre = vista.leerCadenas("Escribe el nombre de tu tamagochi");
                    Random rdm = new Random();


                    switch (tp){
                        case 1: //Sera perro

                            int agresivo = rdm.nextInt(101);
                            int hiper = rdm.nextInt(101);

                            modelo.agregarPerro(nombre, 100, 0, 100, 100, agresivo, hiper);
                            //modelo.agregarPerro(nombre, 0, 100, 40, 0, agresivo, hiper);
                            break;
                        case 2: // sera pez dorado
                            int oxigenacion = rdm.nextInt(101);
                            int calidadAgua = rdm.nextInt(101);
                            int controlTemperatura = rdm.nextInt(101);

                            modelo.agregarPerDorado(nombre, 100, 0, 100, 100, oxigenacion, calidadAgua, controlTemperatura);
                            //modelo.agregarPerDorado(nombre, 40, 50, 40, 0, oxigenacion, calidadAgua, controlTemperatura);
                            break;
                        case 3: // Sera aguila
                            int nivelFuerza = rdm.nextInt(101);
                            int velocidad = rdm.nextInt(101);

                            modelo.agregarAguila(nombre, 100, 0, 100, 100, nivelFuerza, velocidad);
                            //modelo.agregarAguila(nombre, 10, 50, 60, 0, nivelFuerza, velocidad);
                        break;
                        case 4:
                            int tamano = vista.leerNumeros("Escribe el tamaño de tu dragon");

                            modelo.agregarDragon(nombre, 100, 0, 100, 100, tamano, false, 0);
                            //modelo.agregarDragon(nombre, 50, 40, 60, 0, tamaño, false, 0);
                            break;
                    }
                    break;
                case 2 :
                    IO.println("Mostrar lista de tamagochi");
                    vista.mostrarDatos(modelo.listaJuguetes());
                    break;
                case 3:
                    vista.mostrarDatos(modelo.listaNombres());
                    String tamagochi = vista.leerCadenas("Ingresa el nombre de tu tamagochi");
                    Tamagochi encontrado = modelo.buscarTamagochi(tamagochi);

                    if(encontrado != null) {
                        if (encontrado.morir() == true) {
                            IO.println("Tu mascota ha muerto");
                        } else {
                            IO.println("Se ha encontrado tu tamgochi, ingresa que quieres hacer");
                            int opTamagochi = vista.menuInteractuar();
                            switch (opTamagochi) {
                                case 1:
                                    int alimento = vista.leerNumeros(encontrado.getMenuComida());
                                    encontrado.comer(alimento);
                                    break;
                                case 2:
                                    int juego = vista.leerNumeros(encontrado.getMenuJuegos());
                                    encontrado.jugar(juego);
                                    break;
                                case 3:
                                    int opDormir = vista.menuDormirONo();
                                    switch (opDormir){
                                        case 1:
                                            encontrado.dormir();
                                            IO.println(encontrado.getNombre() + " esta durmiendo");
                                            break;
                                        case 2:
                                            encontrado.despertar();
                                            IO.println(encontrado.getNombre() + " esta despierto");
                                            break;
                                    }
                                    break;
                            }
                        }
                        } else{
                            IO.println("No se ha encontrado el nombre de tu tamagochi");
                        }
                    break;
                case 4:
                    vista.mostrarDatos(modelo.listaNombres());
                    String tamagochiBuscar = vista.leerCadenas("Ingresa el nombre de tu tamagochi");
                    Tamagochi encontrar = modelo.buscarTamagochi(tamagochiBuscar);

                    if(encontrar != null) {
                        if (encontrar.morir() == true) {
                            IO.println("Tu mascota ha muerto");
                        } else {
                            IO.println(encontrar.estadoDeAnimo());
                        }
                        } else{
                            IO.println("No se ha encotrado ningun tamagochi con ese nombre");
                        }
                    break;
                case 5:
                    vista.mostrarDatos(modelo.listaNombres());
                    String tamagochiValidar = vista.leerCadenas("Ingresa el nombre de tu tamgochi");
                    Tamagochi buscarTam = modelo.buscarTamagochi(tamagochiValidar);

                    if (buscarTam != null){
                        if (buscarTam.morir() == true) {
                            IO.println("Tu mascota ha muerto");
                        } else {
                            buscarTam.hacerSonido();
                        }
                    } else {
                        IO.println("No se ha encontrado ningun tamagochi con ese nombre");
                    }
                    break;
            }

            if (op >= 1 && op <= 4) {
                modelo.pasarTiempoATodos();
            }

        } while (op != 6);
    }
}
*/
