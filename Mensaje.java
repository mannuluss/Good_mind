/**
 * Esta clase representa cada mensaje en conversación
 * 
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
 *
 */
public class Mensaje
{
    public TypeMenssage tipo;
    public String mensaje;
    public String id_remitente;
    
    public Mensaje(String msj,TypeMenssage typemsj,String remitente)
    {
        mensaje = msj;
        tipo = typemsj;
        this.id_remitente = remitente;
    }

}
