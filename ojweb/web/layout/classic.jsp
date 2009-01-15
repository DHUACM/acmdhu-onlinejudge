<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsf/core"       prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><t:getAsString name="title"/></title>
        <link href="<c:url value='/resources/stylesheet.css' />" rel="stylesheet" type="text/css" />
    </head>
    <f:view>
    <body bgcolor="#666666" topmargin="10" leftmargin="0">
        <table width="883" height="426" border="0" align="center" cellpadding="0"
               cellspacing="0" bgcolor="#FFFFFF">
        <tr>
            <td height="118"><t:insertAttribute name="head"/></td>
        </tr>
        <tr>
            <td height="28"><t:insertAttribute name="navigation"/></td>
        </tr>
        <tr>
            <td><t:insertAttribute name="body"/></td>
        </tr>
        <tr>
            <td><t:insertAttribute name="foot"/></td>
        </tr>
        </table>
    </body>
    </f:view>
</html>
