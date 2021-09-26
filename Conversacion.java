import java.util.ArrayList;
import java.io.Serializable;

/**
 * Write a description of class Conversacion here.
 * 
 * @author (your name)
 * @version (a version number or a date)
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
