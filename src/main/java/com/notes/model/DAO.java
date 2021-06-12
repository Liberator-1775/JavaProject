package com.notes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/notes","root","liberty1775");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		
		PreparedStatement stmt;
		try {
			stmt = connection.
					prepareStatement("SELECT * FROM User");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				users.add(user);
			}
			rs.close();
			stmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void addUser(String name, String passwd) {

		PreparedStatement stmt;
		
		String query = "INSERT INTO User (name, passwd) VALUES (?,?);";
		
		try {
			stmt = connection.
					prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, passwd);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Note> getNotes(Integer user_id, Integer imp, String search){
		List<Note> notes = new ArrayList<Note>();

		PreparedStatement stmt;
		String query = null;

		if(search==null || search.isBlank() || search.isEmpty()) {
			if (imp == 0) {
				
				query = "SELECT * FROM Note "
						+ "WHERE user_id=?";
				
				try {
					stmt = connection.
							prepareStatement(query);
					stmt.setInt(1, user_id);
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						Note note = new Note();
						note.setId(rs.getInt("id"));
						note.setNote(rs.getString("note"));
						note.setUser_id(rs.getInt("user_id"));
						note.setImportance(rs.getInt("importance"));
						notes.add(note);
					}
					rs.close();
					stmt.close();
				} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				}
	
			} else {
				if (imp==2) {
					query = "SELECT * FROM Note "
							+ "WHERE user_id=? "
							+ "ORDER BY importance DESC;";
				} else {
					query = "SELECT * FROM Note "
							+ "WHERE user_id=? "
							+ "ORDER BY importance ASC;";
				}
			
				try {
					stmt = connection.
							prepareStatement(query);
					stmt.setInt(1, user_id);
	
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						Note note = new Note();
						note.setId(rs.getInt("id"));
						note.setNote(rs.getString("note"));
						note.setUser_id(rs.getInt("user_id"));
						note.setImportance(rs.getInt("importance"));
						notes.add(note);
					}
					rs.close();
					stmt.close();
				}catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				}
			}
		} else {
			if (imp == 0) {
				
				query = "SELECT * FROM Note "
						+ "WHERE (note LIKE ? && user_id=?) ";
				
				try {
					stmt = connection.
							prepareStatement(query);
					stmt.setInt(2, user_id);
					stmt.setString(1, "%" + search + "%");
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						Note note = new Note();
						note.setId(rs.getInt("id"));
						note.setNote(rs.getString("note"));
						note.setUser_id(rs.getInt("user_id"));
						note.setImportance(rs.getInt("importance"));
						notes.add(note);
					}
					rs.close();
					stmt.close();
				}catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				}
	
			}else {
				if (imp==2) {
					query = "SELECT * FROM Note "
							+ "WHERE (note LIKE ? && user_id=?) "
							+ "ORDER BY importance DESC;";
				}else {
					query = "SELECT * FROM Note "
							+ "WHERE (note LIKE ? && user_id=?) "
							+ "ORDER BY importance ASC;";
				}
			
				try {
					stmt = connection.
							prepareStatement(query);
					stmt.setInt(2, user_id);
					stmt.setString(1, "%" + search + "%");
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						Note note = new Note();
						note.setId(rs.getInt("id"));
						note.setNote(rs.getString("note"));
						note.setUser_id(rs.getInt("user_id"));
						note.setImportance(rs.getInt("importance"));
						notes.add(note);
					}
					rs.close();
					stmt.close();
				}catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				}
			}
		}
		return notes;

	}
	
	public void addNote(String note, Integer user_id, Integer importance) {
		PreparedStatement stmt;
		
		String query = "INSERT INTO Note (note, user_id, importance) "
								+ "VALUES (?,?,?);";
		
		try {
			stmt = connection.
					prepareStatement(query);
			stmt.setString(1, note);
			stmt.setInt(2, user_id);
			stmt.setInt(3, importance);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delNote(Integer noteId) {
		PreparedStatement stmt;
		
		String query = "DELETE FROM Note WHERE id=?;";
		
		try {
			stmt = connection.
					prepareStatement(query);
			stmt.setInt(1, noteId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editNote(Note note) {
		PreparedStatement stmt;

		String newNote = note.getNote();
		Integer newImp = note.getImportance();
		Integer noteId = note.getId();
		
		String query = "UPDATE Note SET note=?, importance=? WHERE id=?;";
		
		try {
			stmt = connection.
					prepareStatement(query);
			stmt.setString(1, newNote);
			stmt.setInt(2, newImp);
			stmt.setInt(3, noteId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Note> buscaNota(String text, int user_id) {
		PreparedStatement stmt;
		
		List<Note> notes = new ArrayList<Note>();
		
		String query = "SELECT * FROM Note WHERE note LIKE ? && user_id=?;";
		
		try {
			stmt = connection.
					prepareStatement(query);
			stmt.setString(1, "'%" + text + "%'");
			stmt.setInt(2, user_id);
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id"));
				note.setNote(rs.getString("note"));
				note.setUser_id(rs.getInt("user_id"));
				note.setImportance(rs.getInt("importance"));
				notes.add(note);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notes;
	}
}

