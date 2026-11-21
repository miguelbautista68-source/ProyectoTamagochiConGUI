package org.uabc.ic.proyectotamagochicongui.Modelo;

public interface TamagochiActions {
    public void comer(int alimento);
    public int  jugar(int tipoDeJuego);
    //Retornar un valor para que se quite la energia.
    public int dormir(int horasDeSueno);

    // En Tamagochi.java (clase padre)
    public abstract String getMenuComida();
    public abstract String getMenuJuegos();

    public abstract void hacerSonido();


}
