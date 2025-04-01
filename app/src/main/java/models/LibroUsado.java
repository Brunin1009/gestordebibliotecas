package models;

public class LibroUsado extends Libro {
    private int antiguedadAños;
    private double factorDesgaste;
    private String propietarioAnterior;

    public LibroUsado(String titulo, String codigo, double precioBase, String autor,
                      int añoPublicacion,int numero, int antiguedadAños, double factorDesgaste,
                      String propietarioAnterior) {
        super(titulo, codigo, precioBase, autor, añoPublicacion,numero);
        this.antiguedadAños = antiguedadAños;
        this.factorDesgaste = factorDesgaste;
        this.propietarioAnterior = propietarioAnterior;
    }

    @Override
    public double calcularPrecio() {
        return getPrecioBase() * (7 - (antiguedadAños * factorDesgaste / 10));
    }

    @Override
    public String obtenerInformacion() {
        return super.obtenerInformacion() + "\n" +
                "Tipo: Usado\n" +
                "Antigüedad: " + antiguedadAños + " años\n" +
                "Factor Desgaste: " + factorDesgaste + "\n" +
                "Propietario Anterior: " + propietarioAnterior + "\n" +
                "Precio Final: " + calcularPrecio();
    }

    // Getters y setters específicos
    public int getAntiguedadAños() { return antiguedadAños; }
    public double getFactorDesgaste() { return factorDesgaste; }
    public String getPropietarioAnterior() { return propietarioAnterior; }
    public void setAntiguedadAños(int antiguedadAños) {
        this.antiguedadAños = antiguedadAños;
    }

    public void setFactorDesgaste(double factorDesgaste) {
        this.factorDesgaste = factorDesgaste;
    }

    public void setPropietarioAnterior(String propietarioAnterior) {
        this.propietarioAnterior = propietarioAnterior;
    }
}