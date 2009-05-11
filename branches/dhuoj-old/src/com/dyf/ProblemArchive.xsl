<?xml version="1.0"?>
<!-- Copyright (C) 2003 Dong Yunfeng -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>
<xsl:template match="/ProblemArchive">
<html>
<body>
    <center>
        <h1><font color="#0070e8"><xsl:value-of select="Title"/></font></h1>
        Time Limit: <xsl:value-of select="TestData/TimeLimit"/> sec<br/>
   </center>

    <h2><font color="#0070e8">Description</font></h2>
    <xsl:choose>
        <xsl:when test="Problem[@contentType='html']">
            <xsl:value-of select="Problem/Description" disable-output-escaping="yes"/>
        </xsl:when>
        <xsl:when test="Problem[@contentType='plain']">
            <pre><xsl:value-of select="Problem/Description"/></pre>
        </xsl:when>
    </xsl:choose>
    <br/>

    <h2><font color="#0070e8">The Input</font></h2>
    <xsl:choose>
        <xsl:when test="Problem[@contentType='html']">
            <xsl:value-of select="Problem/InputSpec" disable-output-escaping="yes"/>
        </xsl:when>
        <xsl:when test="Problem[@contentType='plain']">
            <pre><xsl:value-of select="Problem/InputSpec"/></pre>
        </xsl:when>
    </xsl:choose>
    <br/>

    <h2><font color="#0070e8">The Output</font></h2>
   <xsl:choose>
        <xsl:when test="Problem[@contentType='html']">
            <xsl:value-of select="Problem/OutputSpec" disable-output-escaping="yes"/>
        </xsl:when>
        <xsl:when test="Problem[@contentType='plain']">
            <pre><xsl:value-of select="Problem/OutputSpec"/></pre>
        </xsl:when>
    </xsl:choose>
    <br/>

    <h2><font color="#0070e8">
        Sample Input
        <xsl:if test="TestData[InputFile!='']">
            (file <xsl:value-of select="TestData/InputFile"/>)
        </xsl:if>
    </font></h2>
    <pre><xsl:value-of select="Problem/SampleInput"/></pre>
    <br/>

    <h2><font color="#0070e8">
        Sample Output
        <xsl:if test="TestData[OutputFile!='']">
            (file <xsl:value-of select="TestData/OutputFile"/>)
        </xsl:if>
    </font></h2>
    <pre><xsl:value-of select="Problem/SampleOutput"/></pre>
    <br/>

    <hr/>
    <i>Author: <xsl:value-of select="Author"/></i>
    <p/>

    <xsl:if test="Problem[@contentType='plain']">
        <xsl:for-each select="Problem/Figure">
            <image>
                <xsl:attribute name="src"><xsl:value-of select="@filename"/></xsl:attribute>
            </image><br/>
            <xsl:value-of select="Text"/>
            <p/>
        </xsl:for-each>
    </xsl:if>

</body>
</html>

</xsl:template>
</xsl:stylesheet>
