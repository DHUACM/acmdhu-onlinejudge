<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
<h2>PPT Review</h2>
<font color="red">
	<s:actionerror/>
</font></div>
<form action="review.action" method="post" enctype="multipart/form-data">
     SolutionID:<input type="text" name="solutionId"/><br>
     Result:<input type="radio" name="result" value = "yes">yes<br>
            <input type="radio" name="result" value = "no" checked>no<br>
     Upload Your Review Comment：<input type="file" name="upload"/><br>
     <input value="upload" type="submit"/>
</form>
</div>