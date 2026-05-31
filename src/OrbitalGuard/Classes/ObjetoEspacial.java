package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.StatusRelatorio;

public abstract class ObjetoEspacial implements StatusRelatorio {

    protected int idNorad;
    protected String nomeObjeto;
    protected double altitudeKm;
    protected double velocidadeKmS;
    protected Organizacao organizacao;

    public ObjetoEspacial(int idNorad, String nomeObjeto, double altitudeKm, double velocidadeKmS, Organizacao organizacao) {
        this.idNorad = idNorad;
        this.nomeObjeto = nomeObjeto;
        this.altitudeKm = altitudeKm;
        this.velocidadeKmS = velocidadeKmS;
        this.organizacao = organizacao;
    }

    public int getIdNorad() {
        return idNorad;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public double getAltitudeKm() {
        return altitudeKm;
    }

    public double getVelocidadeKmS() {
        return velocidadeKmS;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public abstract double calcularRiscoColisao();

    public abstract String getTipoObjeto();


    public String gerarRelatorioStatus() {
        String aux = "";
        aux += "🛰️ OBJETO ESPACIAL\n";
        aux += "━━━━━━━━━━━━━━━━━━━━\n";
        aux += "Nome: " + nomeObjeto + "\n";
        aux += "NORAD: " + idNorad + "\n";
        aux += "Tipo: " + getTipoObjeto() + "\n";
        aux += "Altitude: " + altitudeKm + " km\n";
        aux += "Velocidade: " + velocidadeKmS + " km/s\n";
        aux += "Risco de colisão: " + calcularRiscoColisao() + "%\n";
        aux += "━━━━━━━━━━━━━━━━━━━━";
        return aux;
    }
}