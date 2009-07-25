<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
<h2>PPT上传</h2>
<font color="red">
	<s:actionerror/>
</font></div>
<form action="review.action" method="post" enctype="multipart/form-data">
     UserID:<input type="text" name="userId"/><br>
     Password:<input type="password" name="password"/><br>
     ProblemId:<input type="text" name="problemId"/><br>
     ContestId:<input type="text" name="contestId"/><br>
          选  择  文 件：<input type="file" name="upload"/><br>
     <input value="上传" type="submit"/>     
</form>
</div>