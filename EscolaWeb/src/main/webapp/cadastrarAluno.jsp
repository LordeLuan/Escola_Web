<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Classes.Aluno"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro Aluno</title>
</head>
<body>
	<%
	Aluno alu = new Aluno();
	alu.setNomAluno("Luan Lordello");
	alu.setNomMae("Lia Lordello");
	
	if(alu.incluirAluno()){
		response.sendRedirect("cadastrarAluno.jsp?mensagem=Aluno salvo com sucesso");
	} else{
		response.sendRedirect("cadastrarAluno.jsp?mensagem=Aluno salvo com sucesso");
	}
	
	%>

</body>
</html>