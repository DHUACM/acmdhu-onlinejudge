<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<style type="text/css">
#menu {
 font:12px verdana, arial, sans-serif;
}
#menu, #menu li {
 list-style:none;
 padding:0;
 margin:0;
}
#menu li {
 float:left;
}
#menu li a {
 display:block;
 padding:8px 50px;
 color:#fff;
 text-decoration:none;
}
#menu li a:hover {
 color:#fff;
 text-decoration:none;
 border-right:1px solid #000;
 font-weight: bold;
}
#menu li a.last {
 border-right:0;
}
</style>

<h:form>
<h:panelGrid columns="3" id="navigation" width="100%" bgcolor="#990000">
    <div id="menu">
        <ul>
            <li><a href="index.faces">Home</a></li>
            <li><a href="problemlist.faces">ProblemSet</a></li>
            <li><a href="contest.faces">Contest</a></li>
        </ul>
    </div>
    <h:panelGroup style="float: right" rendered="#{not UserBean.logonMode}">
        <h:outputLink value="login.faces" styleClass="hor-menu">
            <h:outputText value="Login"></h:outputText>
        </h:outputLink>
        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
        <h:outputLink value="regiser.faces" styleClass="hor-menu">
            <h:outputText value="Register"></h:outputText>
        </h:outputLink>
    </h:panelGroup>
    <h:panelGroup style="float: right" rendered="#{UserBean.logonMode}">
        <h:outputText value="Welcome, #{UserBean.user.userId}"></h:outputText>
        <h:commandLink value="Logout" action="#{UserBean.logout}"></h:commandLink>
    </h:panelGroup>
</h:panelGrid>
</h:form>