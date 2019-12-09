package com.formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.formation.model.Client;
import java.sql.PreparedStatement;

public class Dao implements InterfaceDao {

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
PreparedStatement st =null;
Connection cn = null;
		String nom = client.getNom();
		String prenom = client.getPrenom();
		int age = client.getAge();
		try {
						cn = connecter();
			
			String sql = "INSERT INTO client (nom, prenom, age) VALUES (?,?,?)";
			st = cn.prepareStatement(sql);
			// Ecrire une requête
			st.setString(1, nom);
			st.setString(2, prenom);
			st.setInt(3, age);
			// Execution de la requête
			st.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		Connection cn = null; 
		PreparedStatement st = null;
		ResultSet rs = null;
		Client client = new Client();	
		
		try {
			cn = connecter();
			String sql = "SELECT * FROM client WHERE id = ?";
			st = cn.prepareStatement(sql);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				//System.out.println(rs.getString("personne"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAge(rs.getInt("age"));
				System.out.println(rs.getString("nom")+ " " + rs.getString("prenom"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection connecter() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// INformation d'accès à la base de données
				String url = "jdbc:mysql://localhost/vente";
				String login = "root";
				String passwd = "";
				
				
							
					// Etape 1 : Chargement du driver
					Class.forName("com.mysql.jdbc.Driver");
					// Etape 2 : récupération de la connexion
					Connection cn = DriverManager.getConnection(url, login, passwd);
		return cn;
	}
public static void main(String[] args) {
	
	// Client client = new Client("Gervinet", "Fred", 42);
	// System.out.println(client);
	// Client client2 = new Client ();
	// client2.setNom("toto");
	
	InterfaceDao dao = new Dao();
	// dao.save(client);
	dao.findById(1);
	
	
	
}
}
