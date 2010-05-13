<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
  <title>Test</title>
</head>

<body>
      <form action="<c:url value="/oauth/authorize"/>" method="post">
          <input name="requestToken" value="<c:out value="${oauth_token}"/>"/>
          <c:if test="${!empty oauth_callback}">
          <input name="callbackURL" value="<c:out value="${oauth_callback}"/>"/>
          </c:if>
          <label>
            <input name="authorize" value="Authorize" type="submit"/></label>
      </form>
</body>
</html>
