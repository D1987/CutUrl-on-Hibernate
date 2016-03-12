<%@ page import="classes.Mail" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Перенаправление на почту</title>
  <link href="css/fEn.css" type="text/css" rel="stylesheet"/>
  <script src="js/jquery.js"></script>
  <script  charset="windows-1251" src="js/index.js"></script>
</head>
<body>
  <div>
    <%
      String url = null;
      String name = null;
      Map<String,String> map = new HashMap<String, String>();
      Mail par = new Mail();
      map = par.selectDomain(request.getParameter("mail"));
      for (Map.Entry<String,String> entry:map.entrySet()){
        name = entry.getKey();
        url = entry.getValue();
      }
    %>
    <p>Для завершения регистрации перейдите по ссылке отправленной вам на почту</p>
    <a href="<%=url%>" class="buttEnter">Войти в <%=name%></a>
  </div>
</body>
</html>
