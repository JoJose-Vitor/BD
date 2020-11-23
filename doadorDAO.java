package DAO;

import Model.doador;
import java.util.*;
import java.sql.*;

public class doadorDAO {

public static ArrayList<doador> MinhaLista = new ArrayList<doador>();

public doadorDAO() {
}

public int maiorID() throws SQLException {
int maiorID = 0;
try {
    Statement stmt = this.getConexao().createStatement();
    ResultSet res = stmt.executeQuery("SELECT MAX(doadorID) doadorID FROM doarbd_doador");
    res.next();
    maiorID = res.getInt("doadorID");
    
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
ResultSet res = stmt.executeQuery("SELECT * FROM doardb_doador");
while (res.next()) {
String doador = res.getString("doador");
        
int doadorID = res.getInt("doadorID");
String login = res.getString("login");
String senha = res.getString("senha");
String email = res.getString("email");
String nome = res.getString("nome");
String rg = res.getString("rg");
String cpf = res.getString("cpf");
String telefone = res.getString("telefone");
String endereco = res.getString("endereco");

        
doador objeto = new doador(doadorID, login, senha, email, nome, rg, cpf, telefone, endereco);
MinhaLista.add(objeto);
}
stmt.close();
} catch (SQLException ex) {
}
return MinhaLista;
}

public boolean Insertdoador(doador objeto) {
String sql = "INSERT INTO receptor(doadorID, login, senha, email, nome, rg, cpf, telefone, endereco) VALUES(?,?,?,?,?,?,?,?,?)";
try {
PreparedStatement stmt = this.getConexao().prepareStatement(sql);
stmt.setInt(1, objeto.getdoadorID());
stmt.setString(2, objeto.getlogin());
stmt.setString(3, objeto.getsenha());
stmt.setString(4, objeto.getemail());
stmt.setString(5, objeto.getnome());
stmt.setString(6, objeto.getrg());
stmt.setString(7, objeto.getcpf());
stmt.setString(8, objeto.gettelefone());
stmt.setString(9, objeto.getendereco());
        
stmt.execute();
stmt.close();
return true;
} catch (SQLException erro) {
throw new RuntimeException(erro);
}
}

public boolean Deletedoador(doador objeto) {
    
    try {
        
    Statement stmt = this.getConexao().createStatement();
    ResultSet res = stmt.executeQuery("DELETE FROM doardb_doador WHERE doadorID = " + doadorID);
    stmt.close();
    
    } catch (SQLException erro) {
    }
    return true;
}

public boolean Updatedoador(doador objeto) {
String sql = "UPDATE doardb_doador set login = ?, senha = ?, email = ?, nome = ?, rg = ?, cpf = ?, telefone = ?, endereco = ? WHERE doadorID = ?";
try {
PreparedStatement stmt = this.getConexao().prepareStatement(sql);

stmt.setInt(1, objeto.getdoadorID());
stmt.setString(2, objeto.getlogin());
stmt.setString(3, objeto.getsenha());
stmt.setString(4, objeto.getemail());
stmt.setString(5, objeto.getnome());
stmt.setString(6, objeto.getrg());
stmt.setString(7, objeto.getcpf());
stmt.setString(8, objeto.gettelefone());
stmt.setString(9, objeto.getendereco());
        
stmt.execute();
stmt.close();
return true;
} catch (SQLException erro) {
throw new RuntimeException(erro);
}
}

public doador Carregardoador(int doadorID) {
    
    doador objeto = new doador();
    objeto.setdoadorID(doadorID);
    
try {
Statement stmt = this.getConexao().createStatement();
ResultSet res = stmt.executeQuery("SELECT * FROM doardb_doador WHERE doadorID = " + doadorID);
res.next();

objeto.setInt(1, res.getdoadorID());
objeto.setString(2, res.getlogin());
objeto.setString(3, res.getsenha());
objeto.setString(4, res.getemail());
objeto.setString(5, res.getnome());
objeto.setString(6, res.getrg());
objeto.setString(7, res.getcpf());
objeto.setString(8, res.gettelefone());
objeto.setString(9, res.getendereco());
       
stmt.close();

} catch (SQLException erro) {
}
return objeto;
}
}