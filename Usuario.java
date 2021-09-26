import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * la informacion de un usuario en la plataforma
 * 
 * @version (1.0)
 */
public class Usuario {
    public String id;
    public String nombre;
    public Role role;
    /**
     * disponibles (true), ocupado (false)
     */
    public boolean state;
    // public String direccion;
    public List<Conversacion> conversaciones;
    /**
     * notificacion de usuario requiere ayuda especializada
     */
    public boolean notificacion;
    public Registro registro;

    public Usuario(String name, Role rol, boolean estado) {
        id = UUID.randomUUID().toString();
        nombre = name;
        role = rol;
        state = estado;
        conversaciones = new ArrayList<Conversacion>();
        registro = new Registro();
    }

    public Usuario(String name, Role rol) {
        this(name, rol, true);
    }

    /**
     * obtiene una lista con los nombres de las usuarios con los que ha iniciados
     * conversaciones previas.
     * 
     * @return
     */
    public ArrayList<String> GetListNameChat(boolean activos) {
        var array = new ArrayList<String>();
        for (var chat : conversaciones) {
            array.add(chat.ChatUser);
        }
        return array;
    }

    public Conversacion GetConversacion(String name_user){
        for (Conversacion ch : conversaciones) {
            if (ch.ChatUser == name_user){
                return ch;
            }
        }
        return null;
    }
}
