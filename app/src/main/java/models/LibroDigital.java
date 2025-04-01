package models;

import java.util.List;

public class LibroDigital extends Libro {
    private String formato;
    private double tamañoMB;
    private List<String> restriccionesDispositivos;

    public LibroDigital(String titulo, String codigo, double precioBase, String autor,
                        int añoPublicacion, int numero,String formato, double tamañoMB,
                        List<String> restriccionesDispositivos) {
        super(titulo, codigo, precioBase, autor, añoPublicacion,numero);
        this.formato = formato;
        this.tamañoMB = tamañoMB;
        this.restriccionesDispositivos = restriccionesDispositivos;
    }

    @Override
    public double calcularPrecio() {
        return getPrecioBase() * 0.7 - (tamañoMB * 0.01) + (restriccionesDispositivos.size() * 0.5);
    }

    @Override
    public String obtenerInformacion() {
        return super.obtenerInformacion() + "\n" +
                "Tipo: Digital\n" +
                "Formato: " + formato + "\n" +
                "Tamaño: " + tamañoMB + " MB\n" +
                "Restricciones: " + String.join(", ", restriccionesDispositivos) + "\n" +
                "Precio Final: " + calcularPrecio();
    }

    public String getFormato() { return formato; }
    public double getTamañoMB() { return tamañoMB; }
    public List<String> getRestriccionesDispositivos() { return restriccionesDispositivos; }
}
