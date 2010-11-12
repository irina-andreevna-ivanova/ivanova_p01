<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>
    <xsl:template match="/" name="myTemplate">
        <xsl:for-each select="library/books/book">
            Book
                ID=<xsl:value-of select="@id"/>
                Name=<xsl:value-of select="@name"/>
                <xsl:for-each select="author">
                    Author=<xsl:value-of select="." />
                </xsl:for-each>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>