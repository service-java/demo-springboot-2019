项目中如果使用JasperReports导出PDF或HTML，PDF中文解决方案：
1、下载jasperreports-fonts.jar包
2、解压后把系统带有的中文字体文件放入到解压后的net\sf\jasperreports\fonts\dejavu目录中，如simhei.ttf黑体字体
3、在fonts.xml文件中增加如下描述
    <fontFamily name="黑体">
            <normal>net/sf/jasperreports/fonts/dejavu/simhei.ttf</normal>
            <bold>net/sf/jasperreports/fonts/dejavu/simhei.ttf</bold>
            <italic>net/sf/jasperreports/fonts/dejavu/simhei.ttf</italic>
            <boldItalic>net/sf/jasperreports/fonts/dejavu/simhei.ttf</boldItalic>
            <pdfEncoding>Identity-H</pdfEncoding>
            <pdfEmbedded>true</pdfEmbedded>
            <exportFonts>
                <export key="net.sf.jasperreports.html">'黑体', Arial, Helvetica, sans-serif</export>
                <export key="net.sf.jasperreports.xhtml">'黑体', Arial, Helvetica, sans-serif</export>
            </exportFonts>
        </fontFamily>
4、重新打包中jasperreports-fonts.jar包
5、此包以Classes的形式加入到项目中
6、在JasperReports Studio中使用中文时，选择字体为simhei字体

注：此模板项目中已经打包了包含有黑体的jar文件，此文件存放在zywork-common模块的lib目录下