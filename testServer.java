
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class test.
 */
public class testServer {
    /**
     * Default constructor for test class test
     */
    public testServer() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void CountConsultorios() {
        assertEquals(2, Server.GetConsultorios("080001").size());
        assertEquals(1, Server.GetConsultorios("080002").size());
    }

    @Test
    public void ConversacionesSaveTwoUsers() {
        assertEquals(true, testConversacion());
    }

    public boolean testConversacion() {
        Usuario from = new Usuario("miguel", Role.profesional);
        Goodmind.user = from;
        Usuario to = new Usuario("miguel", Role.usuario);
        var conversation = from.InitChat(to.nombre);
        var prochat = to.InitChat(from.nombre);
        String data = "1\nalgun texto\n2\nvoice\n3\n0\n4";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Goodmind.sc = new Scanner(System.in);
        } finally {
            System.setIn(stdin);
        }
        Goodmind.SeccionChat(conversation, prochat);
        boolean firtsid = prochat.chat.get(0).id_remitente.equals(from.id);
        boolean secondtype = prochat.chat.get(0).tipo == TypeMenssage.text;
        boolean thirtemotion = prochat.chat.get(1).tipo == TypeMenssage.voice;
        boolean fin = prochat.chat.size() == 2;//.mensaje.equals(emociones.ansiedad.name());
        if (conversation.chat.size() == prochat.chat.size() && firtsid && secondtype && thirtemotion && fin)
            return true;
        else
            return false;
    }

    @Test
    public void ServerGetAlluser() {
        assertEquals(4, Server.GetAllUsers("./users.json").size());
    }
}
