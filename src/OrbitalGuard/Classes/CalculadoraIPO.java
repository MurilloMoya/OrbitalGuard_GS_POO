package OrbitalGuard.Classes;

public class CalculadoraIPO {

    public static double calcularIPO(double riscoMedio, int quantidade, int eventosCriticos) {
        double ipo,riscoNormalizado;

        riscoNormalizado = (riscoMedio / 200.0) * 100;

        ipo = 100 - (quantidade * 0.3 + riscoNormalizado * 0.5 + eventosCriticos * 0.2);

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
        }
        else if (ipo >= 30) {
            return "Amarelo";
        }
        else {
            return "Vermelho";
        }
    }
}