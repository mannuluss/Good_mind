
/**
 * Esta clase representa los consultorios de los profesionales de la salud;
 * sus variables indican el nombre de este, su ubicación y una pequeña
 * descripción.
 *
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
 * 
 */
public class consultorio
{
    public String nombre;
    public String ubicacion;//como si de una codigo postal se tratase ejm: 801564
    public String descripcion;    

    public consultorio(){

    }
    public consultorio(String nombre,String ubicacion,String description){
        this.nombre =  nombre;
        this.ubicacion = ubicacion;
        this.descripcion = description;
    }
    
}
