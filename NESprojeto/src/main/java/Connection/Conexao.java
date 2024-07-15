package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/cadastro";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        
    Connection conexao = null;
    try {
        conexao = DriverManager.getConnection(URL, USER, PASS);
    } catch (SQLException ex) {
        System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
    }
    return conexao;
}

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar statement: " + ex.getMessage());
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar result set: " + ex.getMessage());
            }
        }
    }
}

