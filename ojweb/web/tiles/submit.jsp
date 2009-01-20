<%-- 
    Document   : submit
    Created on : Jan 16, 2009, 8:46:56 PM
    Author     : yhu
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<f:subview id="submit">
    <div align="center">
    <h:form>
        <h:panelGrid columns="1" style="text-align:center">
            <h:panelGroup>
                <h:outputLabel value="Problem: " />
                <h:inputText id="problemId" value="#{SubmitBean.problemID}"></h:inputText>
                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                <h:outputLabel value="Language: " />
                <h:selectOneMenu id="language" value="#{SubmitBean.language}">
                    <f:selectItem id="item0" itemLabel="Choose a language" itemValue="0" />
                    <f:selectItem id="item1" itemLabel="GNU C" itemValue="1" />
                    <f:selectItem id="item2" itemLabel="GNU C++" itemValue="2" />
                    <f:selectItem id="item3" itemLabel="Free Pascal" itemValue="3" />
                    <f:selectItem id="item4" itemLabel="Java" itemValue="4" />
                </h:selectOneMenu>
            </h:panelGroup>
            <h:outputLabel value="Source Code: " />
            <h:inputTextarea id="source" cols="52" rows="25" value="#{SubmitBean.sourceCode}" />
            <h:commandButton id="submit" value="Submit" action="#{SubmitBean.submit_action}" />
        </h:panelGrid>
    </h:form>
    </div>
</f:subview>
