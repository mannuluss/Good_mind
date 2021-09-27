import java.util.ArrayList;
import java.io.Serializable;

/**
 * Clase que representa la conversación
 * 
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
 * 
 */
public class Conversacion implements Serializable {
    public ArrayList<Mensaje> chat = new ArrayList<>();
    public boolean activo = true;
    public String ChatUser;

    /**
     * Constructor for objects of class Conversacion
     */
    public Conversacion() {

    }
    public Conversacion(String touser) {
        ChatUser = touser;
    }
    public void AddMsj(String msj,TypeMenssage typemsj,String id_remitente){
        chat.add(new Mensaje(msj, typemsj, id_remitente));
    }
}
