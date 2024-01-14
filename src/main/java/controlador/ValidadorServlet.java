package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.LoginDao;
import modelo.Usuario;
import utils.Conexion;

public class ValidadorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ValidadorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario, password, msg = "";

        usuario = request.getParameter("usuario");
        password = request.getParameter("password");

        try {
            if (usuario.isBlank() || password.isBlank()) {
                msg = "Por favor, ingrese ambos campos";
            } else {
                boolean usuarioValido = LoginDao.validar(usuario, password);
                if (usuarioValido) {
                    Usuario usuarioInfo = LoginDao.obtenerInformacionUsuario(usuario, password);
                    if (usuarioInfo != null) {
                        RequestDispatcher despachador = request.getRequestDispatcher("/informacionUsuario.jsp");
                        request.setAttribute("usuario", usuarioInfo);
                        despachador.forward(request, response);
                        return;
                    }
                } else {
                    msg = "Revise Usuario y Contraseña ya que son Incorrectos";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error en la validación del usuario";
        }

        msg = msg.toUpperCase();

        RequestDispatcher despachador = request.getRequestDispatcher("/mensaje.jsp");
        request.setAttribute("mensaje", msg);
        despachador.forward(request, response);
    }
}
