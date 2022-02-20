package Classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Conexao;

public class Aluno {

	private int codAluno;
	private String nomAluno;
	private String nomMae;
	private String nomPai;
	private Date dataNasc;
	private double medAluno;
	private String sitAluno;

	public int getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(int codAluno) {
		this.codAluno = codAluno;
	}

	public String getNomAluno() {
		return nomAluno;
	}

	public void setNomAluno(String nomAluno) {
		this.nomAluno = nomAluno;
	}

	public String getNomMae() {
		return nomMae;
	}

	public void setNomMae(String nomMae) {
		this.nomMae = nomMae;
	}

	public String getNomPai() {
		return nomPai;
	}

	public void setNomPai(String nomPai) {
		this.nomPai = nomPai;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public double getMedAluno() {
		return medAluno;
	}

	public void setMedAluno(double medAluno) {
		this.medAluno = medAluno;
	}

	public String getSitAluno() {
		return sitAluno;
	}

	public void setSitAluno(String sitAluno) {
		this.sitAluno = sitAluno;
	}

	public boolean incluirAluno() {

		Connection con = Conexao.conectar();

		String sql = "insert into aluno(nomeAluno, nomeMae,";
			   sql += " nomePai, dataNasc, medAluno, sitAluno)";
			   sql += " values(?,?,?,?,?,?)";
		boolean status = true;

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, this.nomAluno);
			stm.setString(2, this.nomMae);
			stm.setString(3, this.nomPai);
			stm.setDate(4, this.dataNasc);
			stm.setDouble(5, this.medAluno);
			stm.setString(6, this.sitAluno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean alterarAluno(int id) {
		boolean status = true;
		Connection con = Conexao.conectar();

		String sql = "update aluno set nomeAluno = ?, nomePai = ?, nomeMae = ?, dataNasc = ? ,"
				+ " medAluno = ?, sitAluno = ? where codAluno = " + id;
		      

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, this.nomAluno);
			stm.setString(2, this.nomPai);
			stm.setString(3, this.nomMae);
			stm.setDate(4, this.dataNasc);
			stm.setDouble(5, this.medAluno);
			stm.setString(6, this.sitAluno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public boolean excluirAluno(int codAluno) {
		boolean status = true;
		String sql = "delete from aluno where codAluno = ?";

		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			stm.setInt(1,codAluno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;

	}

	public Aluno consultarAluno(int codAluno) {
		Aluno alu = new Aluno();
		String sql = "select * from aluno where codAluno = " + codAluno;
		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				alu.setCodAluno(rs.getInt("codAluno"));
				alu.setNomAluno(rs.getString("nomeAluno"));
				alu.setDataNasc(rs.getDate("DataNasc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alu;
	
	}

	public List<Aluno> listarAlunos() {
		List<Aluno> alu = new ArrayList<>();
		String sql = "select * from aluno";
		try {
			PreparedStatement stm = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setCodAluno(rs.getInt("codAluno"));
				aluno.setNomAluno(rs.getString("nomeAluno"));
				aluno.setDataNasc(rs.getDate("DataNasc"));
				alu.add(aluno);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alu;
	}

}