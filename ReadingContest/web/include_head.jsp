<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>DHU Online Judge</title>
<link href="dhu-style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">

<div id="banner">
         <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
         <tr>
        		  <td width="64" height="116" align="left" valign="middle"><img src="img/dhu.jpg" width="106" height="106" /></td>
	 	   <td width="" align="center" valign="middle" class="big-title">DHU Online Judge</td>
    	  		  <td width="319" align="right" valign="middle"><img src="img/logo.jpg" width="319" height="91" /></td>
        </tr>
        </table>
</div>        

<div id="menu">
         <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" >
         <tr bgcolor="#990000">
                
                <td width="182" >
                    <a href="index.jsp"  class="hor-menu">Online Judge</a>                   
                    <ul>
                    <li><a href="index.jsp" class="hor-menu">Home</a></li>
                 
                   
                    </ul>
                    </td>
               
                <td width="191" >
                    <a href="problemset.jsp"  class="hor-menu">ProblemSet</a>
                    <ul>
                    <li><a href="problemList.action" class="hor-menu">ProblemList</a></li>
                    <li><a href="submit.action" class="hor-menu">Submit</a></li>
                    <li><a href="showStatus.action" class="hor-menu">Status</a></li>
                    </ul>
                    </td>
               
                <td width="150">&nbsp;</td>
                <td width="159" >
                    <p><a href="userlist.action"   class="hor-menu">Users</a></p>
                    <s:if test="#session.User==null or #session.User=='guest'">
                           <s:form action="login" method="post" theme="simple">
                           <table width="155" align="left" height="85">
	                         <tr>
	                           <td width="107" ><font color="#80ffff" size="2"> 
		                     UserName</font> 
	                         </td>
	                         <td width="30"><input type=text name=username size=10 style='font-family:monospace'></td>
	                         </tr>
	                         <tr>
	                           <td ><font color="#80ffff" size="2"> 
		                 Password</font> 
	                         </td>
	                         <td ><input type=password name=password size=10 style='font-family:monospace'></td>
	                         </tr>
	                         <tr><td >
		                     <s:submit value="submit"/>
	                         </td>
	                           <td ><a href="register.jsp" class="hor-menu">Regist</a></td>
	                         </tr>
                            </table>
                      </s:form>
                  </s:if>
                    
                    
                    <s:else>
                    <ul>
                        <li><a href="userlist.action"   class="hor-menu">User Rank List</a></li>
                        <li><a href="#"  class="hor-menu">User:<s:property value="#session.User"/></li>
                        <li><a href="exit.action"  class="hor-menu">Exit</a></li>
                    </ul>  
                    </s:else>
                                 
           </td>
           </tr>
         </table>
</div>
         
<div align="center"> 
           <br/>  
<h3 align="center"><font color="#ff0000">${requestScope.tip}</font></h3>
</div>
    
