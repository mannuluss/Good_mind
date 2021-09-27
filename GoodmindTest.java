

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class GoodmindTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GoodmindTest
{
    /**
     * Default constructor for test class GoodmindTest
     */
    public GoodmindTest()
    {
    }

    @Test
    public void verifyCredentials(){
        
        ArrayList<Usuario> Allusers= new ArrayList();
        Usuario u= new Usuario("Felipe", Role.profesional, true);
        Allusers.add(u);
        assertEquals(null, Goodmind.VerifyCredencials("Felipe", "1224526", Role.profesional));
    }
    
    @Test
    public void createAccount(){
        
        Goodmind.Allusers= new ArrayList();
        Usuario usuario = new Usuario("Valentina", Role.profesional, true);
        Goodmind.Allusers.add(usuario);
        assertEquals(null, Goodmind.CreateAcount("Valentina", "124687", Role.profesional));
    }
    
    @Test
    public void GetUsuario(){
        
        ArrayList<Usuario> Allusers= new ArrayList();
        Usuario usuario = new Usuario("Daniel", Role.usuario, true);
        Allusers.add(usuario);
        assertEquals(null, Goodmind.GetUser("Valentina", Role.usuario));
    }
}
