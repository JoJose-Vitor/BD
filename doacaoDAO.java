package DAO;

import Model.doacao;
import java.util.*;
import java.sql.*;

public class doacaoDAO {

public static ArrayList<doacao> MinhaLista = new ArrayList<doacao>();

public doacaoDAO() {
}

public int maiorID() throws SQLException {
int maiorID = 0;
try {
    Statement stmt = this.getConexao().createStatement();
    ResultSet res = stmt.executeQuery("SELECT MAX(doacaoID) doacaoID FROM doarbd_doacao");
    res.next();
    maiorID = res.getInt("doacaoID");
    
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
ResultSet res = stmt.executeQuery("SELECT * FROM doardb_doacao");
while (res.next()) {
String receptor = res.getString("doacao");
        
int doacaoID = res.getInt("doacaoID");
float valor = res.getFloat("valor");
String entrada = res.getString("entrada");
String saida = res.getString("saida");
int doadorID = res.getInt("doadorID");
int receptorID = res.getInt("receptorID");
String comprovante = res.getString("comprovante");
        
doacao objeto = new doacao(doacaoID, valor, entrada, saida, doadorID, receptorID, comprovante);
MinhaLista.add(objeto);
}
stmt.close();
} catch (SQLException ex) {
}
return MinhaLista;
}

public boolean Insertdoacao(doacao objeto) {
String sql = "INSERT INTO doador(doacaoID, valor, entrada, saida, doadorID, receptorID, comprovante) VALUES(?,?,?,?,?,?,?)";
try {
PreparedStatement stmt = this.getConexao().prepareStatement(sql);
stmt.setInt(1, objeto.getdoacaoID());
stmt.setFloat(2, objeto.getvalor());
stmt.setString(3, objeto.getentrada());
stmt.setstring(4, objeto.getsaida());
stmt.setInt(5, objeto.getdoadorID());
stmt.setString(6, objeto.getreceptorID());
stmt.setString(7, objeto.getcomprovante());
        
stmt.execute();
stmt.close();
return true;
} catch (SQLException erro) {
throw new RuntimeException(erro);
}
}

public boolean Deletedoacao(doacao objeto) {
    
    try {
        
    Statement stmt = this.getConexao().createStatement();
    ResultSet res = stmt.executeQuery("DELETE FROM doardb_doacao WHERE doacaoID = " + doacaoID);
    stmt.close();
    
    } catch (SQLException erro) {
    }
    return true;
}

public boolean Updatedoacao(doacao objeto) {
String sql = "UPDATE doardb_doacao set valor = ?, entrada = ?, saida = ?, doadorID = ?, receptorID = ?, comprovante = ? WHERE doacaoID = ?";
try {
PreparedStatement stmt = this.getConexao().prepareStatement(sql);

stmt.setInt(1, objeto.getdoacaoID());
stmt.setFloat(2, objeto.getvalor());
stmt.setString(3, objeto.getentrada());
stmt.setString(4, objeto.getsaida());
stmt.setInt(5, objeto.getdoadorID());
stmt.setInt(6, objeto.getreceptorID());
stmt.setString(7, objeto.getcomprovante());
        
stmt.execute();
stmt.close();
return true;
} catch (SQLException erro) {
throw new RuntimeException(erro);
}
}

public doacao Carregardoacao(int doacaoID) {
    
    doacao objeto = new doacao();
    objeto.setdoacaoID(doacaoID);
    
try {
Statement stmt = this.getConexao().createStatement();
ResultSet res = stmt.executeQuery("SELECT * FROM doardb_doacao WHERE doacaoID = " + doacaoID);
res.next();

objeto.setInt(1, res.getdoacaoID());
objeto.setFloat(2, res.getvalor());
objeto.setString(3, res.getentrada());
objeto.setString(4, res.getsaida());
objeto.setInt(5, res.getdoadorID());
objeto.setInt(6, res.getreceptorID());
objeto.setString(7, res.getreceptorID());
       
stmt.close();

} catch (SQLException erro) {
}
return objeto;
}
}