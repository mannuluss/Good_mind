/**
 * Write a description of class Registro here.
 * 
 * @author (your name)
 * @version (a version number or a date)
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

    public float PorcentPlus() {
        float total = GetTotal();
        if (total != 0)
            return positivos / total;
        else
            return 0;
    }

    public float Porcentless() {
        float total = GetTotal();
        if (total != 0)
            return negativos / total;
        else
            return 0;
    }

    public float PorcentNeutral() {
        float total = GetTotal();
        if (total != 0)
            return neutros / total;
        else
            return 0;
    }

}
