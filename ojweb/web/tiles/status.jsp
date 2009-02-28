<%-- 
    Document   : status
    Created on : Jan 20, 2009, 9:33:26 PM
    Author     : yhu
--%>

<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<f:subview id="status">
    <div align="center">
    <h:form>
        <h:panelGrid columns="1" style="text-align:center" width="100%">
            <h2>Online Status List</h2>
            <h:panelGroup>
                <h:outputLabel value="Problem ID: " />
                <h:inputText id="problemId"></h:inputText>
                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

                <h:outputLabel value="User ID: " />
                <h:inputText id="userId"></h:inputText>
                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

                <h:outputLabel value="Result: " />
                <h:selectOneMenu id="result">
                    <f:selectItem id="item0" itemLabel="All" itemValue="0" />
                    <f:selectItem id="item1" itemLabel="Accepted" itemValue="1" />
                    <f:selectItem id="item2" itemLabel="Presentation Error" itemValue="2" />
                    <f:selectItem id="item3" itemLabel="Time Limit Exceeded" itemValue="3" />
                    <f:selectItem id="item4" itemLabel="Memory Limit Exceeded" itemValue="4" />
                    <f:selectItem id="item6" itemLabel="Wrong Answer" itemValue="5" />
                    <f:selectItem id="item7" itemLabel="Runtime Error" itemValue="6" />
                    <f:selectItem id="item8" itemLabel="Compile Error" itemValue="7" />
                </h:selectOneMenu>
            </h:panelGroup>
            <h:panelGroup>
            <h:dataTable headerClass="list-header" id="statusList"
                         rowClasses="list-row-even,list-row-odd"
                         
                         var="status" width="100%">
                <h:column>
                    <h:outputText />
                    <f:facet name="header">
                        <h:outputText value="ID"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:commandLink>
                        <h:outputText />
                    </h:commandLink>
                    <f:facet name="header">
                        <h:outputText value="User"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:commandLink>
                        <h:outputText />
                    </h:commandLink>
                    <f:facet name="header">
                        <h:outputText value="Problem"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:outputText />
                    <f:facet name="header">
                        <h:outputText value="Result"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:outputText />
                    <f:facet name="header">
                        <h:outputText value="Time"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:outputText />
                    <f:facet name="header">
                        <h:outputText value="Memory"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:outputText />
                    <f:facet name="header">
                        <h:outputText value="Language"/>
                    </f:facet>
                </h:column>
                <h:column>
                    <h:outputText />
                    <f:facet name="header">
                        <h:outputText value="Submit Time"/>
                    </f:facet>
                </h:column>
            </h:dataTable>
            </h:panelGroup>
        </h:panelGrid>
    </h:form>
    </div>
</f:subview>