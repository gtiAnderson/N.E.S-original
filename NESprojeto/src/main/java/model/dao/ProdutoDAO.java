package model.dao;

import Connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

public class ProdutoDAO {
  
  public void create(Produto p) throws SQLException {
    Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("INSERT INTO produtos (descricao, preco, numero_contato) VALUES (?, ?, ?)");
            
            stmt.setString(1, p.getDescricaoProduto());
            stmt.setDouble(2, p.getPreco());
            stmt.setString(3, p.getNumeroContato()); // Utilizando o número de telefone como string
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    private boolean validarNumeroTelefone(String numero) {
        return numero.matches("\\d{10,11}"); // Validar número de telefone (10 ou 11 dígitos)
    }

    private String limparNumeroTelefone(String numero) {
        return numero.replaceAll("[^0-9]", ""); // Limpar qualquer caractere não numérico
    }

    private String formatarNumeroTelefone(String numero) {
        // Implementar formatação se necessário (por exemplo, adicionar DDD)
        return numero; // Implementar conforme necessário
    }

    public String converterNumeroContato(String numero) throws NumberFormatException {
        if (!validarNumeroTelefone(numero)) {
            throw new NumberFormatException("Formato de número de telefone inválido");
        }

        return limparNumeroTelefone(numero);
    
}

 public List<Produto> read() throws SQLException {
    Connection con = Conexao.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Produto> produtos = new ArrayList<>();

    try {
        stmt = con.prepareStatement("SELECT * FROM produtos");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setDescricaoProduto(rs.getString("descricao"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setNumeroContato(rs.getString("numero_contato"));
            produtos.add(produto);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        Conexao.closeConnection(con, stmt, rs);
    }

    return produtos;
}

  public void update(Produto p) throws SQLException {
    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = Conexao.getConnection();
        stmt = con.prepareStatement("UPDATE produtos SET descricao = ?, preco = ?, numero_contato = ? WHERE id = ?");
        
        stmt.setString(1, p.getDescricaoProduto());
        stmt.setDouble(2, p.getPreco());
        stmt.setString(3, p.getNumeroContato()); // Utilizando o número de telefone como string
        stmt.setInt(4, p.getId());
        
        stmt.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
    } finally {
        Conexao.closeConnection(con, stmt);
    }
}
      
  public void delete(Produto p) throws SQLException {
    Connection con = Conexao.getConnection();
    PreparedStatement stmt = null;
    
    try { 
        stmt = con.prepareStatement("DELETE FROM produtos WHERE id = ?");
        stmt.setInt(1, p.getId());
        
        stmt.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage());
    } finally {
        Conexao.closeConnection(con, stmt);
    }
}
}