package models;

public class LibroNuevo extends Libro {
    private double descuentoPromocional;
    private int garantiaMeses;

    public LibroNuevo(String titulo, String codigo, double precioBase, String autor,
                      int añoPublicacion,int numero, double descuentoPromocional, int garantiaMeses) {
        super(titulo, codigo, precioBase, autor, añoPublicacion,numero);
        this.descuentoPromocional = descuentoPromocional;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double calcularPrecio() {
        return getPrecioBase() - (getPrecioBase() * descuentoPromocional / 100);
    }

    @Override
    public String obtenerInformacion() {
        return super.obtenerInformacion() + "\n" +
                "Tipo: Nuevo\n" +
                "Descuento Promocional: " + descuentoPromocional + "%\n" +
                "Garantía: " + garantiaMeses + " meses\n" +
                "Precio Final: " + calcularPrecio();
    }
    public double getDescuentoPromocional() { return descuentoPromocional; }
    public int getGarantiaMeses() { return garantiaMeses; }
    public void setDescuentoPromocional(double descuentoPromocional) {
        this.descuentoPromocional = descuentoPromocional;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }
}
