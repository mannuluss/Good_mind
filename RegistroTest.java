

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RegistroTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RegistroTest
{
    /**
     * Default constructor for test class RegistroTest
     */
    public RegistroTest()
    {
    }
    
    @Test
    public void total(){
        Registro r= new Registro();
        r.registrar(emociones.tristeza);
        r.registrar(emociones.alegre);
        r.registrar(emociones.ansiedad);
        r.registrar(emociones.tristeza);
        r.registrar(emociones.ira);
        r.registrar(emociones.exaltado);
        r.registrar(emociones.alegre);
        assertEquals(7, r.GetTotal());
        
    }

    @Test
    public void registrar()
    {
        Registro r= new Registro();
        assertEquals(true, r.registrar(emociones.tristeza));
    }
}


