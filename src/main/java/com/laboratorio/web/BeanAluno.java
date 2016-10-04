package com.laboratorio.web;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.laboratorio.model.Aluno;
import com.laboratorio.util.ConnectionFactory;
@ManagedBean (name="bAluno")
public class BeanAluno implements Serializable{

	private static final long serialVersionUID = 1L;
	private Aluno aluno = new Aluno();
	
	public String actionUpdate(){
		Connection con = null;
		PreparedStatement psUp = null;
		String sql = "update aluno set nome = ?, idade = ?, sexo = ?, "
				+ "peso = ?, altura = ? where matricula = ?";
		try{
			Aluno aluno = new Aluno();
			con = ConnectionFactory.getConnection();
			psUp = con.prepareStatement(sql);
			psUp.setString(1, aluno.getNome());
			psUp.setInt(2, aluno.getIdade());
			psUp.setString(3, aluno.getSexo());
			psUp.setFloat(4, aluno.getPeso());
			psUp.setFloat(5, aluno.getAltura());
			psUp.setInt(6, aluno.getMatricula());
			psUp.executeUpdate();
			
		}catch(Exception e){
			System.err.println("---------------------");
			System.err.println("Erro na atualização");
			e.printStackTrace();
		}
		return "index";
	}
	public String actionInsert(){
		Connection con = null;
		PreparedStatement psIn = null;
		String sql = "insert into aluno(nome, sexo, idade, peso, altura)"
				+ "values (?, ?, ?, ?, ?)";
		try{
			con = ConnectionFactory.getConnection();
			psIn = con.prepareStatement(sql);
			psIn.setString(1, aluno.getNome());
			psIn.setString(2, aluno.getSexo());
			psIn.setInt(3, aluno.getIdade());
			psIn.setFloat(4, aluno.getPeso());
			psIn.setFloat(5, aluno.getAltura());
			psIn.executeUpdate();
			aluno = new Aluno();
		}catch(Exception e){
			System.err.println("---------------------");
			System.err.println("Erro na atualização");
			e.printStackTrace();
		}
		return "index";
	}
	
	public String actionDelete(){
		Connection con = null;
		PreparedStatement psDel = null;
		String sql = "delete from aluno where matricula = ?";
		
		try{
			con = ConnectionFactory.getConnection();
			psDel = con.prepareStatement(sql);
			psDel.setInt(1, aluno.getMatricula());
			psDel.executeUpdate();
			aluno = new Aluno();
		}catch(Exception e){
			System.err.println("---------------------");
			System.err.println("Erro na atualização");
			e.printStackTrace();
		}
		return "index";
	}
	public String actionSelectMatricula(){
		Connection con = null;
		PreparedStatement psSel = null;
		ResultSet rs = null;
		String sql = "select * from aluno where matricula = ?";
		
		try{
			con = ConnectionFactory.getConnection();
			psSel = con.prepareStatement(sql);
			psSel.setInt(1, aluno.getMatricula());
			rs = psSel.executeQuery();
			if(rs.next()){
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setIdade(rs.getInt("idade"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setPeso(rs.getFloat("peso"));
				aluno.setAltura(rs.getFloat("altura"));
				aluno = new Aluno();
			}
		}catch(Exception e){
			System.err.println("---------------------");
			System.err.println("Erro na atualização");
			e.printStackTrace();
		}
		return null;
	}
	public List<Aluno> getListagemAlunos(){
		List<Aluno> lsAlunos = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from aluno order by nome";
		try{
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			lsAlunos = new ArrayList<Aluno>();
			
			while(rs.next()){
				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setIdade(rs.getInt("idade"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setPeso(rs.getFloat("peso"));
				aluno.setAltura(rs.getFloat("altura"));
				lsAlunos.add(aluno);				
			}
		}catch(Exception e){
			System.err.println("------------------");
			System.err.println("Erro no select");
			e.printStackTrace();
		}
		return lsAlunos;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
