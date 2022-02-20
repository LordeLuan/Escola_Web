package Classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Conexao;

/**
 * @author +DEVS2BLU
 */
public class Matricula {
	private int codAluno;
	private int codDisciplina;
	private Date dtMatricula;
	private char statusMatricula;

	public int getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(int codAluno) {
		this.codAluno = codAluno;
	}

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public Date getDtMatricula() {
		return dtMatricula;
	}

	public void setDtMatricula(Date dtMatricula) {
		this.dtMatricula = dtMatricula;
	}

	public char getStatusMatricula() {
		return statusMatricula;
	}

	public void setStatusMatricula(char statusMatricula) {
		this.statusMatricula = statusMatricula;
	}

	/**
	 * 
	 */
	public boolean incluirMatricula() {
		Connection con = Conexao.conectar();

		String sql = "insert into matricula(codAluno, codDisciplina, dataMatricula, statusMatricula)";
		sql += " values(?,?,?,?)";
		boolean status = true;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, this.codAluno);
			stm.setInt(2, this.codDisciplina);
			stm.setDate(3, this.dtMatricula);
			stm.setString(4, Character.toString(this.statusMatricula));
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean alterarMatricula(int id) {
		boolean status = true;
		Connection con = Conexao.conectar();

		String sql = "update matricula set dataMatricula = ?, statusMatricula = ? " + " where codAluno = " + id;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			// stm.setInt(1, this.codAluno);
			// stm.setInt(2, this.codDisciplina);
			stm.setDate(1, this.dtMatricula);
			stm.setString(2, Character.toString(this.statusMatricula));
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	/**
	 * 
	 */

	public boolean excluirMatricula(int codAluno) {
		boolean status = true;
		String sql = "delete from matricula where codAluno = ?";

		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			stm.setInt(1, codAluno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;

	}

	/**
	 * 
	 */
	
		public Matricula consultarMatricula(int codAluno) {
			Matricula matr = new Matricula();
			String sql = "select * from matricula where codAluno = " + codAluno;
			try {
				PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					matr.setCodAluno(rs.getInt("codAluno"));
					matr.setCodDisciplina(rs.getInt("codDisciplina"));
					matr.setDtMatricula(rs.getDate("dataMatricula"));
					matr.setStatusMatricula(rs.getString("statusMatricula").charAt(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return matr;
	}

	/**
	 * 
	 */


		public List<Matricula> listarMatriculas() {
			List<Matricula> matr = new ArrayList<>();
			String sql = "select * from matricula";
			try {
				PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					Matricula matri = new Matricula();
					matri.setCodAluno(rs.getInt("codAluno"));
					matri.setCodDisciplina(rs.getInt("codDisciplina"));
					matri.setDtMatricula(rs.getDate("dataMatricula"));
					matri.setStatusMatricula(rs.getString("statusMatricula").charAt(0));
					matr.add(matri);
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return matr;
		
	}

}