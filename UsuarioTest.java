

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class UsuarioTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UsuarioTest
{
    /**
     * Default constructor for test class UsuarioTest
     */
    public UsuarioTest()
    {
        
    }

    @Test
    public void chat(){
        
        Usuario u1= new Usuario("Daniel", Role.usuario,true);
        Conversacion cv= new Conversacion();
        cv.ChatUser= "Valentina";
        u1.conversaciones.add(cv);
        assertEquals(cv, u1.InitChat("Valentina"));
    }
    
    @Test
    public void getConversationTrue(){
        
        Usuario u1= new Usuario("Daniel", Role.usuario,true);
        Conversacion cv= new Conversacion();
        cv.ChatUser= "Valentina";
        u1.conversaciones.add(cv);
        assertEquals(cv, u1.GetConversacion("Valentina"));
    }
    
    @Test
    public void getConversationFalse(){
        
        Usuario u1= new Usuario("Daniel", Role.usuario,true);
        Conversacion cv= new Conversacion();
        cv.ChatUser= "Valentina";
        u1.conversaciones.add(cv);
        assertEquals(null, u1.GetConversacion("Felipe"));
    }
    
    @Test
    public void randomProfessional(){
        
        ArrayList<Usuario> allUsers= new ArrayList();
        Usuario u= new Usuario("Daniel",Role.usuario,true);
        allUsers.add(u);
        assertEquals(null, u.RamdomProfesional(allUsers));
    }
}
