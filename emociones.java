
/**
 * emociones representa las 6 emociones que el usuario puede presentar
 *
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
 * 
 */
public enum emociones {
    ansiedad(-1), ira(-1), tristeza(-1), indiferente(0), alegre(1), exaltado(1);

    private int tipo;

    private emociones(int tipo) {
        this.tipo = tipo;
    }

    public int GetType() {// -1 negativa,0 nuetra,1 positiva
        return tipo;
    }
}
