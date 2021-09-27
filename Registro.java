/**
 * Esta clase registra el tipo de emoción del usuario.
 * 
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
 * 
 */
public class Registro {
    public float positivos = 0;
    public float negativos = 0;
    public float neutros = 0;

    /**
     * Constructor for objects of class Registro
     */
    public Registro() {

    }
    public boolean registrar(emociones emocion){
        switch (emocion.GetType()){
            case -1:
                negativos++;
            break;
            case 0:
                neutros++;
            break;
            case 1:
                positivos++;
            break;
        }
        return true;
    }
    public float GetTotal() {
        return positivos + negativos + neutros;
    };
}
