package OrbitalGuard.Classes;

public class EstagioFogueteAbandonado extends ObjetoEspacial {

    private double massaKg;
    private int anosAbandonado;

    public EstagioFogueteAbandonado(int idNorad, String nomeObjeto, double altitudeKm, double velocidadeKmS, Organizacao organizacao, int anosAbandonado, double massaKg) {
        super(idNorad, nomeObjeto, altitudeKm, velocidadeKmS, organizacao);
        this.anosAbandonado = anosAbandonado;
        this.massaKg = massaKg;
    }

    public double calcularRiscoReentrada() {
        return massaKg / 10 + anosAbandonado * 2;
    }


    public double calcularRiscoColisao() {
        return (massaKg / 10) + (anosAbandonado * 4) + 30;
    }


    public String getTipoObjeto() {
        return "Estágio de Foguete";
    }
}
