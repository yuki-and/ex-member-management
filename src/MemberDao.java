import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	private static final String TABLE_NAME = "members";

	public Member load(int id) {
		Connection con = DBManager.createConnection();

		String sql = "SELECT id, name, birthDay, gemder FROM " + TABLE_NAME + " WHERE id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setBirthDay(rs.getDate("birthDay").toLocalDate());
				member.setGender(rs.getString("gender"));
				member.setColorId(rs.getInt("colorId"));
				return member;
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQL = " + sql);
			throw new RuntimeException();
		} finally {
			DBManager.closeConnectoin(con);
		}
	}

	public List<Member> findByName(String name) {
		Connection con = DBManager.createConnection();

		String sql = "SELECT id, name, birthDay, gender, colorId FROM " + TABLE_NAME + " WHERE name LIKE ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			List<Member> memberList = new ArrayList<>();

			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setBirthDay(rs.getDate("birthDay").toLocalDate());
				member.setGender(rs.getString("gender"));
				member.setColorId(rs.getInt("colorId"));
				memberList.add(member);
			}
			return memberList;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQL = " + sql);
			throw new RuntimeException();
		} finally {
			DBManager.closeConnectoin(con);
		}
	}

	public List<Member> findAll() {
		Connection con = DBManager.createConnection();
		String sql = "SELECT id, name, birth_day, gender, color_id FROM " + TABLE_NAME + " ORDER BY birth_day DESC";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			List<Member> memberList = new ArrayList<Member>();

			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setBirthDay(rs.getDate("birth_day").toLocalDate());
				member.setGender(rs.getString("gender"));
				member.setColorId(rs.getInt("color_id"));
				memberList.add(member);
			}
			return memberList;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQL = " + sql);
			throw new RuntimeException();
		} finally {
			DBManager.closeConnectoin(con);
		}
	}

	public int insert(Member member) {
		Connection con = DBManager.createConnection();
		String sql = "INSERT INTO " + TABLE_NAME + " (name, birth_day, gender, color_id) VALUES (?, ?, ?, ?);";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			Date birthday = Date.valueOf(member.getBirthDay());
			pstmt.setString(1, member.getName());
			pstmt.setDate(2, birthday);
			pstmt.setString(3, member.getGender());
			pstmt.setInt(4, member.getColorId());

			int affected = pstmt.executeUpdate();
			return affected;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQL = " + sql);
			throw new RuntimeException();
		} finally {
			DBManager.closeConnectoin(con);
		}
	}

	public int update(Member member) {
		Connection con = DBManager.createConnection();
		String sql = "UPDATE " + TABLE_NAME + " SET name = ?, birth_day = ?, gender = ?, color_id = ? ";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setDate(2, Date.valueOf(member.getBirthDay()));
			pstmt.setString(3, member.getGender());
			pstmt.setInt(4, member.getColorId());

			int affected = pstmt.executeUpdate();
			return affected;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQL = " + sql);
			throw new RuntimeException();
		} finally {
			DBManager.closeConnectoin(con);
		}
	}

	public int deleteById(int id) {
		Connection con = DBManager.createConnection();
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);

			int affected = pstmt.executeUpdate();
			return affected;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQL = " + sql);
			throw new RuntimeException();
		} finally {
			DBManager.closeConnectoin(con);
		}
	}
}
