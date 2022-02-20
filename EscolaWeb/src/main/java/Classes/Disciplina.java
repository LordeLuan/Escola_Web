package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Conexao;

/**
 * @author +DEVS2BLU
 */
public class Disciplina {
	private int codDisciplina;
	private String nomDisciplina;
	private String nomProfessor;
	private int qtdAvaliacoes;

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNomDisciplina() {
		return nomDisciplina;
	}

	public void setNomDisciplina(String nomDisciplina) {
		this.nomDisciplina = nomDisciplina;
	}

	public String getNomProfessor() {
		return nomProfessor;
	}

	public void setNomProfessor(String nomProfessor) {
		this.nomProfessor = nomProfessor;
	}

	public int getQtdAvaliacoes() {
		return qtdAvaliacoes;
	}

	public void setQtdAvaliacoes(int qtdAvaliacoes) {
		this.qtdAvaliacoes = qtdAvaliacoes;
	}

	public boolean incluirDisciplina() {
		Connection con = Conexao.conectar();

		String sql = "insert into disciplina(nomeDisciplina, nomeProfessor, qtdAvaliacoes)";
		sql += " values(?,?,?)";
		boolean status = true;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, this.nomDisciplina);
			stm.setString(2, this.nomProfessor);
			stm.setInt(3, this.qtdAvaliacoes);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean alterarDisciplina(int id) {
		boolean status = true;
		Connection con = Conexao.conectar();

		String sql = "update disciplina set codDisciplina = ?, nomeDisciplina = ?, nomeProfessor = ?, qtdAvaliacoes = ?"
				+ " where codDisciplina = " + id;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, this.codDisciplina);
			stm.setString(2, this.nomDisciplina);
			stm.setString(3, this.nomProfessor);
			stm.setInt(4, this.qtdAvaliacoes);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean excluirDisciplina(int codDisciplina) {
		boolean status = true;
		String sql = "delete from disciplina where codDisciplina = ?";

		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			stm.setInt(1, codDisciplina);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;

	}

	public Disciplina consultarDisciplina(int codDisciplina) {
		Disciplina disc = new Disciplina();
		String sql = "select * from disciplina where codDisciplina = " + codDisciplina;
		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				disc.setCodDisciplina(rs.getInt("codDisciplina"));
				disc.setNomDisciplina(rs.getString("nomeDisciplina"));
				disc.setNomProfessor(rs.getString("nomeProfessor"));
				disc.setQtdAvaliacoes(rs.getInt("qtdAvaliacoes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return disc;
	}

	public List<Disciplina> listarDisciplina() {
		List<Disciplina> disc = new ArrayList<>();
		String sql = "select * from disciplina";
		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Disciplina disci = new Disciplina();
				disci.setCodDisciplina(rs.getInt("codDisciplina"));
				disci.setNomDisciplina(rs.getString("nomeDisciplina"));
				disci.setNomProfessor(rs.getString("nomeProfessor"));
				disci.setQtdAvaliacoes(rs.getInt("qtdAvaliacoes"));
				disc.add(disci);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disc;
	}
}
