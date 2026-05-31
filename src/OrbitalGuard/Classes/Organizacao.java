package OrbitalGuard.Classes;

public class Organizacao {

    private String nome;
    private String pais;

    public Organizacao(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return nome + " - " + pais;
    }
}