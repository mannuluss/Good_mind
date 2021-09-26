
/**
 * Write a description of class Mensaje here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
