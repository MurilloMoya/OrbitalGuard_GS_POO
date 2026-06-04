package OrbitalGuard.Classes;

public class CalculadoraIPO {

    public static double calcularIPO(double riscoMedio, int quantidade, int eventosCriticos) {
        double ipo, riscoNormalizado;

        riscoNormalizado = (riscoMedio / 200.0) * 100;

        ipo = 100 - (quantidade * 3.0 + riscoNormalizado * 1.2 + eventosCriticos * 5.0);

        if (ipo < 0) {
            ipo = 0;
        }
        if (ipo > 100) {
            ipo = 100;
        }

        return ipo;
    }

    public static String classificarIPO(double ipo) {
        if (ipo >= 60) {
            return "Verde";
        } else if (ipo >= 30) {
            return "Amarelo";
        } else {
            return "Vermelho";
        }
    }
}