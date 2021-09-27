import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *La informacion de un usuario en la plataforma
 * 
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
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
    public List<Conversacion> conversaciones;
    /**
     * notificacion de que el usuario requiere ayuda especializada
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
     * Devuelve conversación si existe, de lo contrario crea una
     *
     * @param username nombre de usuario
     * @return conversación
     */
    public Conversacion InitChat(String username){
        for (Conversacion cv : conversaciones) {
            if (cv.ChatUser.equals(username))
                return cv;//ya existe una conversacion
        }
        var newcv = new Conversacion(username);
        this.conversaciones.add(newcv);
        return newcv;
    }

    /**
     * Devuelve la conversacione con un usuario
     *
     * @param name_user nombre del usuario
     * @return conversacións
     */
    public Conversacion GetConversacion(String name_user){
        for (Conversacion ch : conversaciones) {
            if (ch.ChatUser.equals(name_user)){
                return ch;
            }
        }
        return null;
    }

    /**
     * buscar aletoriamente un usuario de tipo profesional
     * @return profesional, nulo en caso de no haber ninguno disponible
     */
    public static Usuario RamdomProfesional(ArrayList<Usuario> allusers){
        ArrayList<Usuario> profesionales = new ArrayList<Usuario>();
        for (Usuario u : allusers) {
            if (u.role == Role.profesional && u.state)
                profesionales.add(u);
        }
        int max = profesionales.size();
        if (max == 0)
            return null;
        int index = (int) Math.random() * (max - 1);
        return profesionales.get(index);
    }

}
