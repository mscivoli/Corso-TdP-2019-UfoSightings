package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.ufo.model.Sighting;

public class SightingsDAO {
	
	public List<Sighting> getSightings() {
		String sql = "SELECT * FROM sighting" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Sighting> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Sighting(res.getInt("id"),
							res.getTimestamp("datetime").toLocalDateTime(),
							res.getString("city"), 
							res.getString("state"), 
							res.getString("country"),
							res.getString("shape"),
							res.getInt("duration"),
							res.getString("duration_hm"),
							res.getString("comments"),
							res.getDate("date_posted").toLocalDate(),
							res.getDouble("latitude"), 
							res.getDouble("longitude"))) ;
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Intestazione> getIntestazione() {
		String sql ="SELECT Year(s.datetime) AS anno, COUNT(*) AS cnt " + 
				"FROM sighting s " + 
				"WHERE s.country = 'us' " + 
				"GROUP BY anno";
		List<Intestazione> ltemp = new LinkedList<Intestazione>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
						
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				ltemp.add(new Intestazione(res.getInt("anno"), res.getInt("cnt")));
			}
			
			conn.close();
			return ltemp ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

	public List<String> getStati(int anno) {
		String sql = "SELECT DISTINCT s.state " + 
				"FROM sighting s " + 
				"WHERE s.country = 'us' AND YEAR(s.datetime) = ? ";
		
		List<String> ltemp = new LinkedList<String>();
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				ltemp.add(res.getString("s.state"));
			}
			
			conn.close();
			return ltemp ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public boolean esisteArco(String s1, String s2, int anno) {
	
		String sql = "SELECT COUNT(*) AS cnt " + 
				"FROM sighting s1, sighting s2 " + 
				"WHERE s1.state = ? AND s2.state = ? AND YEAR(s1.datetime) = ? AND YEAR(s2.datetime) = ? AND s2.datetime > s1.datetime AND s1.country = 'us' AND s2.country = 'us' " + 
				"GROUP BY s1.state, s2.state";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, s1);
			st.setString(2, s2);
			st.setInt(3, anno);
			st.setInt(4, anno);
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				if(res.getInt("cnt")>0) {
					conn.close();
					return true;
				}
				else {
					conn.close();
					return false;
				}
			}
			else {
				conn.close();
				return false;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
