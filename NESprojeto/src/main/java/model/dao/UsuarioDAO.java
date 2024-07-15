

package model.dao;

import Connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class UsuarioDAO {
    
    public boolean CheckLogin(String login, String senha) throws SQLException{
      
      Connection con = Conexao.getConnection();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      boolean check = false;

      
     
      try {
          stmt = con.prepareStatement("SELECT * FROM dadossenhas WHERE login = ? and senha = ?");
          stmt.setString(1, login); 
          stmt.setString(2, senha);
          
          rs = stmt.executeQuery();
          
          if (rs.next()) {
              
                check = true;
              
              
          }
            
          
          
          
      } catch (SQLException ex) {
          Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
//      finally{
//          Conexao.closeConnection(con, stmt, rs);
//      }
      
      return check;
  }
    
}
