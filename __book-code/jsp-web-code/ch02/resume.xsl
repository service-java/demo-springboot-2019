<?xml version="1.0" encoding="GB2312"?>
<html xmlns:xsl="http://www.w3.org/TR/WD-xsl">
<body> 
<xsl:for-each select="resume">
<table width="400" border="1" bordercolor="#336666">
<tr>
<th>����</th><th>�Ա�</th><th>����</th><th>רҵ</th>
</tr>
<tr>
<td><xsl:value-of select="name"/></td>
<td><xsl:value-of select="sex"/></td>
<td><xsl:value-of select="birthday"/></td>
<td><xsl:value-of select="major"/></td>
</tr>
</table>
</xsl:for-each>
</body>
</html>