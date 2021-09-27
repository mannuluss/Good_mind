import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;

/**
 * Clase con metodos utilizados para leer o escribir archivos json en este caso
 * obtener la informacion de la aplicacion (usuarios registrados)
 * 
 * @author Valentina Escobar, Daniel Gonzalez y Felipe Rojas 
 * 
 */
public class Server {
    /**
     * obtiene la lista de usuarios registrados en la plataforma
     * 
     * (lee un archivo .json)
     * 
     * @param path ubicacion del archivo
     * @return lista de usuarios
     */
    public static ArrayList<Usuario> GetAllUsers(String path) {
        InputStream is = Server.class.getResourceAsStream(path);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + path);
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONArray array = new JSONArray(tokener);
        var Users = new ArrayList<Usuario>();
        for (var i = 0; i < array.length(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            var user = new Usuario(object.getString("nombre"), object.getEnum(Role.class, "role"),
                    object.getBoolean("state"));
            user.id = object.getString("id");
            JSONArray arraychats = object.getJSONArray("conversaciones");
            for (int j = 0; j < arraychats.toList().size(); j++) {
                JSONObject obj = arraychats.getJSONObject(j);
                Conversacion c = new Conversacion();
                c.ChatUser = obj.getString("ChatUser");
                c.activo = obj.getBoolean("activo");

                var arraymsj = obj.getJSONArray("chat");
                for (int k = 0; k < arraymsj.toList().size(); k++) {
                    var objmsj = arraymsj.getJSONObject(k);
                    Mensaje msj = new Mensaje(
                        objmsj.getString("mensaje"), 
                        objmsj.getEnum(TypeMenssage.class, "tipo"),
                        objmsj.getString("id_remitente")
                    );
                    c.chat.add(msj);
                }

                user.conversaciones.add(c);
            }

            user.notificacion = object.getBoolean("notificacion");
            user.registro.positivos = object.getJSONObject("registro").getInt("positivos");
            user.registro.negativos = object.getJSONObject("registro").getInt("negativos");
            user.registro.neutros = object.getJSONObject("registro").getInt("neutros");

            Users.add(user);
        }

        return Users;
    }

    /**
     * Analiza la información de la ubicación del usuario para
     * mostrar los consultorios cercanos
     *
     * @return ubicación de consultorios cercanos
     */
    public static ArrayList<consultorio> GetConsultorios(String ubicacion) {
        // se analiza la ip o el gps para proponer los consultorios cerca
        InputStream is = Server.class.getResourceAsStream("./list_consultorios.json");
        if (is == null) {
            throw new NullPointerException("Cannot find resource file ./list_consultorios.json");
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONArray array = new JSONArray(tokener);
        var locations = new ArrayList<consultorio>();
        for (var i = 0; i < array.length(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            if (object.getString("ubicacion").equals(ubicacion)) {
                var item = new consultorio();
                item.nombre = object.getString("nombre");
                item.descripcion = object.getString("descripcion");
                item.ubicacion = object.getString("ubicacion");
                locations.add(item);
            }
        }
        return locations;
    }

    /**
     * guarda objeto de tipo Usuario en el archivo .json de usuarios
     * 
     * (para guardar nuevos usuarios)
     * 
     * @param user el usuario
     */
    public static void userToJson(Usuario user,String path) {
        try{     
            //Creating stream and writing the object    
            FileOutputStream fout=new FileOutputStream("f.txt");    
            ObjectOutputStream out=new ObjectOutputStream(fout);    
            out.writeObject(user);    
            out.flush();    
            //closing the stream    
            out.close();    
            System.out.println("success");    
        }catch(Exception e){System.out.println(e);}    
    }
}
