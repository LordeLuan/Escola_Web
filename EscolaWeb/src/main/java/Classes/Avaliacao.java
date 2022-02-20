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
public class Avaliacao {
	private int codAluno;
	private int codDisciplina;
	private int nrAvaliacao;
	private double vlrNota;

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

	public int getNrAvaliacao() {
		return nrAvaliacao;
	}

	public void setNrAvaliacao(int nrAvaliacao) {
		this.nrAvaliacao = nrAvaliacao;
	}

	public double getVlrNota() {
		return vlrNota;
	}

	public void setVlrNota(double vlrNota) {
		this.vlrNota = vlrNota;
	}

	public boolean incluirAvaliacao() {
		Connection con = Conexao.conectar();

		String sql = "insert into avaliacao(codAluno, codDisciplina, nrAvaliacao, vlrNota)";
		sql += " values(?,?,?,?)";
		boolean status = true;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, this.codAluno);
			stm.setInt(2, this.codDisciplina);
			stm.setInt(3, this.nrAvaliacao);
			stm.setDouble(4, this.vlrNota);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean alterarAvaliacao(int id) {
		boolean status = true;
		Connection con = Conexao.conectar();

		String sql = "update avaliacao set nrAvaliacao = ?, vlrNota = ? " + "where codAluno = " + id;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, this.nrAvaliacao);
			stm.setDouble(2, this.vlrNota);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean excluirAvaliacao(int codAluno) {
		boolean status = true;
		String sql = "delete from avaliacao where codAluno = ?";

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

	public Avaliacao consultarAvaliacao(int codAluno) {
		Avaliacao ava = new Avaliacao();
		String sql = "select * from avaliacao where codAluno = " + codAluno;
		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				ava.setCodAluno(rs.getInt("codAluno"));
				ava.setCodDisciplina(rs.getInt("codDisciplina"));
				ava.setNrAvaliacao(rs.getInt("nrAvaliacao"));
				ava.setVlrNota(rs.getDouble("vlrNota"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ava;
	}

	public List<Avaliacao> listarAvaliacoes() {
		List<Avaliacao> ava = new ArrayList<>();
		String sql = "select * from avaliacao";
		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Avaliacao aval = new Avaliacao();
				aval.setCodAluno(rs.getInt("codAluno"));
				aval.setCodDisciplina(rs.getInt("codDisciplina"));
				aval.setNrAvaliacao(rs.getInt("nrAvaliacao"));
				aval.setVlrNota(rs.getDouble("vlrNota"));
				ava.add(aval);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ava;
	}
}
