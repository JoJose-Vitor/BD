package DAO;

import Model.receptor;
import java.util.*;
import java.sql.*;

public class receptorDAO {

public static ArrayList<receptor> MinhaLista = new ArrayList<receptor>();

public receptorDAO() {
}

public int maiorID() throws SQLException {
int maiorID = 0;
try {
    Statement stmt = this.getConexao().createStatement();
    ResultSet res = stmt.executeQuery("SELECT MAX(receptorID) receptorID FROM doarbd_receptor");
    res.next();
    maiorID = res.getInt("receptorID");
    
    stmt.close();
    
} catch (SQLException ex) {
    }
    
return maiorID;
}

public Connection getConexao() {
        
Connection connection = null;
try {
String driver = "com.mysql.cj.jdbc.Driver";
Class.forName(driver);
String server = "localhost";
String database = "doardb";
String url = "jdbc:mysql://" + server + ":3306/" + database
+ "?useTimezone=true&serverTimezone=UTC";
String user = "root";
String password = "rootpass";
connection = DriverManager.getConnection(url, user, password);
if (connection != null) {
System.out.println("Status: Conectado!");
} else {
System.out.println("Status: NÃO CONECTADO!");
}
return connection;
} catch (ClassNotFoundException e) {
System.out.println("O driver nao foi encontrado.");
return null;
} catch (SQLException e) {
System.out.println("Nao foi possivel conectar...");
return null;
}
}

public ArrayList getMinhaLista() {
MinhaLista.clear();
try {
Statement stmt = this.getConexao().createStatement();
ResultSet res = stmt.executeQuery("SELECT * FROM doardb_receptor");
while (res.next()) {
String receptor = res.getString("receptor");
        
int receptorID = res.getInt("receptorID");
String nome = res.getString("nome");
String email = res.getString("email");
String cnpj = res.getString("cnpj");
String telefone = res.getString("telefone");
String endereco = res.getString("endereco");
        
receptor objeto = new receptor(receptorID, nome, email, cnpj, telefone, endereco);
MinhaLista.add(objeto);
}
stmt.close();
} catch (SQLException ex) {
}
return MinhaLista;
}

public boolean Insertreceptor(receptor objeto) {
String sql = "INSERT INTO receptor(receptorID, nome, email, cnpj, telefone, endereco, banco) VALUES(?,?,?,?,?,?)";
try {
PreparedStatement stmt = this.getConexao().prepareStatement(sql);
stmt.setInt(1, objeto.getreceptorID());
stmt.setString(2, objeto.getnome());
stmt.setString(3, objeto.getemail());
stmt.setString(4, objeto.getcnpj());
stmt.setString(5, objeto.gettelefone());
stmt.setString(6, objeto.getendereco());
    
stmt.execute();
stmt.close();
return true;
} catch (SQLException erro) {
throw new RuntimeException(erro);
}
}

public boolean Deletereceptor(receptor objeto) {
    
    try {
        
    Statement stmt = this.getConexao().createStatement();
    ResultSet res = stmt.executeQuery("DELETE FROM doardb_receptor WHERE receptorID = " + receptorID);
    stmt.close();
    
    } catch (SQLException erro) {
    }
    return true;
}

public boolean Updatereceptor(receptor objeto) {
String sql = "UPDATE doardb_receptor set nome = ?, email = ?, cnpj = ?, telefone = ?, endereco = ? WHERE receptorID = ?";
try {
PreparedStatement stmt = this.getConexao().prepareStatement(sql);

stmt.setInt(1, objeto.getreceptorID());
stmt.setString(2, objeto.getnome());
stmt.setString(3, objeto.getemail());
stmt.setString(4, objeto.getcnpj());
stmt.setString(5, objeto.gettelefone());
stmt.setString(6, objeto.getendereco());
        
stmt.execute();
stmt.close();
return true;
} catch (SQLException erro) {
throw new RuntimeException(erro);
}
}

public receptor Carregareceptor(int receptorID) {
    
    receptor objeto = new receptor();
    objeto.setreceptorID(receptorID);
    
try {
Statement stmt = this.getConexao().createStatement();
ResultSet res = stmt.executeQuery("SELECT * FROM doardb_receptor WHERE receptorID = " + receptorID);
res.next();

objeto.setInt(1, res.getreceptorID());
objeto.setString(2, res.getnome());
objeto.setString(3, res.getemail());
objeto.setString(4, res.getcnpj());
objeto.setString(5, res.gettelefone());
objeto.setString(6, res.getendereco());
       
stmt.close();

} catch (SQLException erro) {
}
return objeto;
}
}
