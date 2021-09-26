import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

/**
 * Write a description of class main here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Goodmind {

    private static Usuario user;
    /**
     * arreglo que contiene a todos los usuarios registrados en la plataforma
     */
    private static ArrayList<Usuario> Allusers = new ArrayList<Usuario>();

    /**
     * Constructor for objects of class main
     */
    private Goodmind() {

    }

    public static void Init() {
        Allusers.addAll(Server.GetAllUsers("./users.json"));
    }

    public static void main(String[] args) {
        Init();
        Scanner sc = new Scanner(System.in);
        System.out.println("========= GOOD-MIND ===========");
        while (true) {
            if (user != null) {
                System.out.println("iniciando sesion como: " + user.nombre);
                if (user.role == Role.usuario)
                    SeccionUser();
                else
                    return;
            } else {
                System.out.println("=======================");
                System.out.println("Pantalla de Inicio");
                System.out.println("> 1. iniciar sesion");
                System.out.println("> 2. crear cuenta");
                System.out.println("> 3. salir");
                System.out.print("Respuesta: ");
                String opt = sc.next();
                if (opt.equals("1")) {
                    Login();
                }
                if (opt.equals("2")) {
                    SectionCreateUser();
                }
            }
        }
    }

    private static void SectionCreateUser() {
        Scanner sc = new Scanner(System.in);
        boolean wait = true;
        do {
            System.out.println("Creando Usuario");
            System.out.print("profesional o usuario (p/u): ");
            Role RoleUser = sc.next().compareTo("p") == 0 ? Role.profesional : Role.usuario;
            System.out.print("nombre: ");
            String name = sc.next();
            System.out.print("contraseña: ");
            String password = sc.next();
            Usuario newuser = VerifyCredencials(name, password, RoleUser);
            if (newuser == null) {
                wait = false;
                user = CreateAcount(name, password, RoleUser);
                System.out.println("Usuario Creado Exitosamente");
                sc.close();
                return;
            } else {
                System.out.println("Usuario ya registrado...");
            }
            System.out.println("1. regresar a la pantalla principal");
            System.out.println("2. intentarlo de nuevo");
            System.out.print("Respuesta: ");
            if (sc.next().equals("1")) {
                wait = false;
                sc.close();
                return;
            }
        } while (wait);
        sc.close();
    }

    /**
     * seccion del usuario
     */
    private static void SeccionUser() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        // new ProcessBuilder("cmd", "/c", "cls");
        boolean exit = false;
        do {
            System.out.println("========= " + user.nombre + " =============");
            Scanner sc = new Scanner(System.in);
            System.out.println("> 1. Conversar");
            System.out.println("> 2. Estadisticas");
            System.out.println("> 3. Cerrar sesion");
            System.out.print("Ingrese un numero de opcion : ");
            var opt = sc.next();
            if (opt.compareTo("1") == 0)
                Conversar();
            if (opt.compareTo("2") == 0)
                Estadisticas();
            if (opt.compareTo("3") == 0) {
                exit = true;
                user = null;
                sc.close();
                return;
            }
            sc.close();
        } while (!exit);
    }

    private static void Estadisticas() {
        Scanner sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("Total emociones registradas: " + user.registro.GetTotal());
        System.out.print("positivas: ");
        PrintBar(user.registro.PorcentPlus() * 10);
        System.out.print("negativos: ");
        PrintBar(user.registro.Porcentless() * 10);
        System.out.print("neutros: ");
        PrintBar(user.registro.PorcentNeutral() * 10);
        var exit = false;
        System.out.println("");
        System.out.println("> 1. regresar");
        if (user.notificacion)
            System.out.println("> 2. Ha sido notificado como usuario en condiccion de riesgo, para mayor infomacion");
        System.out.print("Respuesta: ");
        do {
            String res = sc.next();
            if (res.equals("1"))
                return;
            if (res.equals("2") && user.notificacion) {
                exit = true;
                Notify();
            }
        } while (!exit);
        sc.close();
    }

    private static void Notify() {
        System.out.println("Consultorios Cerca de tu zona:\n");
        // revisa por ip y solicitar activar GPS del dispositivo para buscar
        // consultorios cerca
        System.out.println("Tu ubicacion actual es: " + "080001");

        var array = Server.GetConsultorios("080001");
        for (var item : array) {
            System.out.println("====================================");
            System.out.println(item.nombre);
            System.out.println(item.descripcion);
            System.out.println("====================================");
        }
        if (array.size() == 0)
            System.out.println("no se encontro consultorio cerca de tu zona");
        // System.out.println("presione cualquier boton para volver ...");
    }

    private static void PrintBar(float value) {
        System.out.print("[");
        var temp = 0;
        for (int index = 0; index < 10; index++) {
            if (temp < value) {
                System.out.print("◘");
                temp++;
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("]  " + value + "%");

    }

    /**
     * Cuando un usuario o profesional desea conversar con alguien
     * 
     * en caso de ser el profesional obtiene una lista de usuarios con los que ha
     * hablado. en caso de ser un usuario obtiene de manera aleatorio un profesional
     * con el cual hablar.
     */
    private static void Conversar() {
        Scanner sc = new Scanner(System.in);
        if (user.role == Role.usuario) {
            // imprime una lista de los profesionales disponibles
            ArrayList<Usuario> profesionales = new ArrayList<Usuario>();
            for (Usuario u : Allusers) {
                if (u.role == Role.profesional && u.state)
                    profesionales.add(u);
            }
            int max = profesionales.size();
            int index = (int) Math.random() * (max - 0 + 1) + 0;
            System.out.print("Iniciando conversacion con: ");
            System.out.println(profesionales.get(index).nombre);
        } else {
            var allchats = user.GetListNameChat(false);
            System.out.println(allchats);
            int opt = sc.nextInt();
            if (opt < allchats.size())
                SeccionChat(user.conversaciones.get(opt).ChatUser);
        }

    }

    public static void SeccionChat(String p_name) {
        var exit = false;
        Scanner sc = new Scanner(System.in);
        Conversacion Chat = user.GetConversacion(p_name);
        for (Mensaje msj : Chat.chat) {
            if (msj.id_remitente == user.id) {
                System.out.format("%10.3f%n", msj.mensaje);
            } else {
                System.out.println(msj.mensaje);
            }
        }
        do {
            System.out.println("> 1.texto");
            System.out.println("> 2.voz");
            System.out.println("> 3.emocion");
            System.out.println("> 4.back");
            switch (sc.next()) {
                case "1":
                    System.out.println("Ingrese Texto: ");
                    sc.next();
                    break;
                case "2":
                    System.out.println("Ingrese Texto: ");
                    sc.next();
                    break;
                case "3":
                    System.out.println("Emociones: ");
                    for (emociones m : emociones.values()) {
                        System.out.println(m.GetType() + ". " + m.name());
                    }
                    System.out.println("Ingrese: ");
                    int opt = sc.nextInt();
                    user.registro.registrar(emociones.values()[opt]);
                    break;
                case "4":
                    exit = true;
                    return;

            }
        } while (!exit);
    }

    /**
     * Metodo que permite el login de un usuario en la aplicacion
     */
    private static void Login() {
        System.out.println("Inicie sesion");
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        do {
            System.out.print("Rol (profesional/usuario) (p/u): ");
            Role RoleUser = sc.next().compareTo("p") == 0 ? Role.profesional : Role.usuario;
            System.out.println(RoleUser);
            System.out.print("nombre de usuario: ");
            String nameuser = sc.next();
            System.out.print("contraseña: ");
            String password = sc.next();
            user = VerifyCredencials(nameuser, password, RoleUser);
            if (user != null) {
                exit = true;
                break;
            } else
                System.out.println("Usuario no registrado");

            System.out.println("1. regresar a la pantalla principal");
            System.out.println("2. intentarlo de nuevo");
            System.out.print("Respuesta: ");
            if (sc.next().equals("1")) {
                exit = true;
                sc.close();
                return;
            }
        } while (!exit);
        System.out.println("Login Exitoso");
        sc.close();
    }

    /**
     * metodo que compara la
     * 
     * @param nameuser
     * @param password
     * @param rol
     * @return null o el usuario en caso de encontrarlo
     */
    public static Usuario VerifyCredencials(String nameuser, String password, Role rol) {
        for (Usuario u : Allusers) {
            // System.out.println(u.nombre);
            if (u.nombre.compareTo(nameuser) == 0 && u.role == rol) {
                // System.out.println("OK");
                return u;
            }
        }
        return null;
    }

    public static Usuario CreateAcount(String username, String password, Role rol) {
        for (Usuario usuario : Allusers) {
            if (usuario.nombre.equals(username) && usuario.role == rol)
                return null;
        }
        // envia informacion al servidor para registrar el usuario
        Usuario newUser = new Usuario(username, rol);
        Allusers.add(newUser);
        return newUser;
    }
}
