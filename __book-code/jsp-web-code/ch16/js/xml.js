//����Ϊ��ʼ������
 var datanodes=data.documentElement.childNodes;
 //�������б��渱��,��ɾ���Ժ�ָ�
 var xmlDoc=document.all("data").XMLDocument;
 var xmlDocTemp=new ActiveXObject("MSXML.DOMDocument");
 xmlDocTemp.appendChild(xmlDoc.documentElement.cloneNode(true));
 
 var totalrow=datanodes.length;//����������
 var pagerow=10;//ÿҳ����
 var totalpage=0;//��ҳ��
 if(totalrow!=0) {
   if(totalrow%pagerow!=0) totalpage=parseInt(totalrow/pagerow)+1;
   else totalpage=parseInt(totalrow/pagerow);
 }
 var currPage=1;//��ǰҳ��
 var checkArr=new Array();//�Ƿ�ѡ��
 var theValueArr=new Array();//��¼ѡ�м�¼��checkbox��valueֵ
 for(var i=0;i<totalrow;i++)
 {
   checkArr[i]=0;//��¼ѡ�еļ�¼0Ϊδѡ��,1Ϊѡ��,��ʼΪ0
   theValueArr[i]="";
 }
 var buildArr=new Array();//���浱ǰҳҪ���ɵļ�¼���
 var sortOrder='asce';//���������ǽ���  

 var nodes=head.documentElement.childNodes;
 var nodeslen=nodes.length;
 var attributes=head.documentElement.firstChild.attributes;
 var objform=document.createElement("<FORM name='fjcl'>");
 var objtable=document.createElement("<TABLE bordercolor='#FFFFFF' border='1'>"); 
 var objtbody=document.createElement("TBODY");
 objtable.appendChild(objtbody);
 show('first');
 
 function first(form1)
 {
   if(form1.currentpage.value==1)
   {
   	return false; 
   }
   else
   {
   	form1.count.value='1';
   	form1.submit();
   	return true;	
   }
 } 
 function pageback(form1)
 {
   if(form1.currentpage.value==1)
   {
   	return false; 
   }
   else
   {
   	form1.count.value='2';
   	form1.submit();
   	return true;	
   }
 } 
 function pagenext(form1)
 {
   if(form1.currentpage.value==form1.maxpage.value)
   {
   	return false; 
   }
   else
   {
   	form1.count.value='3';
   	form1.submit();
   	return true;	
   }
 } 
 function end(form1)
 {
   if(form1.currentpage.value==form1.maxpage.value)
   {
   	return false; 
   }
   else
   {
   	form1.count.value='4';
   	form1.submit();
   	return true;	
   }
 }
 function gopage(form1)
 {
   if(form1.pageNum.value==""||form1.pageNum.value>form1.maxpage.value||form1.pageNum.value==form1.currentpage.value)
   {
   	return false; 
   }
   else
   {
   	form1.count.value='5';
   	form1.submit();
   	return true;
   }
 } 
 
//���ɱ��ͷ��
function addHead()
{
 var objtr=document.createElement("<TR  bgColor='Tan'>");
 objtbody.appendChild(objtr);
 var objtd=document.createElement("TD");
 objtd.width="20";
 objtd.innerText=" ";
 objtr.appendChild(objtd);
 for(var i=0;i<nodeslen;i++)
 {
   var objtd=document.createElement("<TD onclick='' onmousemove=''>");
   objtd.width=head.documentElement.childNodes.item(i).getAttribute("width");
   objtd.onmousemove="style.cursor='hand'";
   var sortBy=head.documentElement.childNodes.item(i).getAttribute("sortBy");
   var sortType=head.documentElement.childNodes.item(i).getAttribute("sortType");
   objtd.onclick="sort('"+sortBy+"','"+sortType+"','"+i+"')";
   var tdtext=nodes.item(i).text+"��";
   var objtext=document.createTextNode(tdtext);
   objtd.appendChild(objtext);
   objtr.appendChild(objtd);
 }
 objform.appendChild(objtable);
 objbody.appendChild(objform);
 objbody.appendChild(tableSec);
}
//��ӷ��������ļ�¼
function addAll()
{
  var childdatanodes=data.documentElement.childNodes.item(0).childNodes;
  var childtotalrow=childdatanodes.length;//ÿ��������ܵ��������
  for(var i=0;i<buildArr.length;i++)
  {
    
    var objtr;
    if(i%2==0)
    { 
       objtr=document.createElement("<TR onmouseover='' bgColor='LightGoldenrodYellow'  class='first' onclick='changeColor()' >");
    }else
    {
       objtr=document.createElement("<TR onmouseover='' bgColor='seashell'  class='first' onclick='changeColor()' >");
    }
    if(checkArr[buildArr[i]-1]==1) objtr.className="later";
    objtr.onmouseover="style.cursor='hand'";
    var thetd=document.createElement("TD");
    var objCheckBox;
    if(checkArr[buildArr[i]-1]==0) 
    {
      objCheckBox=document.createElement("<input type='CHECKBOX' name='rad' value='' onclick=''>");
    }else
    {
      objCheckBox=document.createElement("<input type='CHECKBOX' name='rad' value='' onclick='' checked>");
    }
    
    objCheckBox.onclick=objCheckBox.onclick+";if(this.checked==true) checkArr["+parseInt(buildArr[i]-1)+"]='1';else checkArr["+parseInt(buildArr[i]-1)+"]=0";
    thetd.appendChild(objCheckBox);
    objtr.appendChild(thetd);
    for(var j=0;j<childtotalrow;j++)
    {
      var isShow=datanodes.item(buildArr[i]-1).childNodes.item(j).getAttribute("isShow");//�Ƿ���ʾ
      var isKey=datanodes.item(buildArr[i]-1).childNodes.item(j).getAttribute("isKey");  //�Ƿ�������
      if(isShow=='1')//isShow=1
      {
         var objtd=document.createElement("<TD onclick=''>");
         var tdtext=document.createTextNode(datanodes.item(buildArr[i]-1).childNodes.item(j).text);
         objtd.appendChild(tdtext);
         objtr.appendChild(objtd);
         
          objtd.onclick=objCheckBox.click;
      }
      if(isKey=='1')
      {
         if(objCheckBox.value=="")
             objCheckBox.value=objCheckBox.value+datanodes.item(buildArr[i]-1).childNodes.item(j).tagName+"="+datanodes.item(buildArr[i]-1).childNodes.item(j).text;
         else 
             objCheckBox.value=objCheckBox.value+"&"+datanodes.item(buildArr[i]-1).childNodes.item(j).tagName+"="+datanodes.item(buildArr[i]-1).childNodes.item(j).text;
      }
    }
    theValueArr[buildArr[i]-1]=objCheckBox.value;
    objtbody.appendChild(objtr);
  }
  objform.appendChild(objtable);
  objbody.appendChild(objform);
  objbody.appendChild(tableSec);
}
//��ҳ��ť����
function show(thePage)
{
  if(totalrow==0)
  {
   del();
   addHead();
   return false;
   }
  if(thePage=="first")
  {
    del();
    addHead();
     buildArr=new Array();
    if(totalrow>=pagerow){
       for(var i=1;i<=pagerow;i++)
          buildArr[i-1]=i;
    }else{
       for(var i=1;i<=totalrow;i++)
          buildArr[i-1]=i;
    }
    addAll();
    currPage=1;
  }else if(thePage=="prov")
  {
    if(currPage==1) return false;
    del();
    addHead();
    buildArr=new Array();
    for(var i=1;i<=pagerow;i++){
      buildArr[i-1]=(currPage-2)*pagerow+i;
    }
    addAll();
    currPage=currPage-1;
  }else if(thePage=="next")
  {
    if(currPage==totalpage) return false;
    del();
    addHead();
    buildArr=new Array();
    if((currPage+1)*pagerow>=totalrow){
       for(var i=1;i<=totalrow-currPage*pagerow;i++)
          buildArr[i-1]=currPage*pagerow+i;
    }else {
      for(var i=1;i<=pagerow;i++)
          buildArr[i-1]=currPage*pagerow+i;
    }
     addAll();
    currPage=currPage+1;
  }else if(thePage=="last")
  {
    if(currPage==totalpage) return false; 
    del();
    addHead();
    buildArr=new Array();
    if(totalrow%pagerow==0){
      for(var i=1;i<=pagerow;i++)
         buildArr[i-1]=totalrow-pagerow+i;
    }else{
      for(var i=1;i<=totalrow%pagerow;i++)
         buildArr[i-1]=totalrow-totalrow%pagerow+i;
    }
    addAll();
    currPage=totalpage;
  }
  changeMyDiv();
}
//ɾ��objform�������������
function del()
{
 objform.removeNode(true);
 objform=document.createElement("<FORM name='fjcl'>");
 objtable=document.createElement("<TABLE bordercolor='#1EC600' border='1'>"); 
 objtbody=document.createElement("TBODY");
 objtable.appendChild(objtbody);
}
//�ı���ɫ
function changeColor()
{
 for(var i=0;i<objtbody.childNodes.length-1;i++)
 {
   var theRow=buildArr[i]-1;
   if(checkArr[theRow]==0)
   {
     objtbody.childNodes.item(i+1).className="first";
   }
   else
   {
     objtbody.childNodes.item(i+1).className="later";
   }  
 }
 changeMyDiv();
}
function changeMyDiv()
{
 var totalChecked=0;//��¼��ǰѡ�м�¼��
 for(var i=0;i<totalrow;i++)
 {
   if(checkArr[i]==1) totalChecked+=1;
 }
}

function showValue()
{
  var str="";
  var str2="";
  for(var i=0;i<totalrow;i++)
  {
    str+=checkArr[i]+";";
    if(checkArr[i]==1) str2+=theValueArr[i]+";";
  }
  alert(str);
  alert(str2);
}
//��ת��ĳ��ҳ
function goPageNum()
{
  var page=pageNum.value;
  if(totalrow==0)
  {
    alert("û�пɲ����ļ�¼!");
    return false;
  }
  if(page.length==0)
  {
     alert("������Ҫ��ת��ҳ��!");
     return false;
  }
  if(parseInt(page)==0||parseInt(page)>totalpage)
  {
    alert("����ҳ��Ӧ����1��"+totalpage+"֮��!");
    return false;
  } 
  if(page==currPage) return false;  
  if(page==1)
  {
    show('first');
  }else if(page==totalpage)
  {
    show('last');
  }else
  {
    del();
    addHead();
    buildArr=new Array();
    for(var i=0;i<pagerow;i++){
      buildArr[i]=(page-1)*pagerow+i+1;
    }
    addAll();
    currPage=page;
  }
  changeMyDiv();
}

//����ʵ�ֶԲ�ͬ�������ͽ�������
//�Ƚ������ַ���
function compare_text(text1,text2)
{
  if(text1>text2) return 1;
  else return 0;
}
//�Ƚ��������ֵĴ�С(��������)
function compare_number(number1,number2)
{
  if(parseFloat(number1)>parseFloat(number2)) return 1;
  else return 0;
}
//�Ƚ��������ڵĴ�С
function compare_date(kssj,jssj)
{
  var kssjArr=kssj.split("-");
  var jssjArr=jssj.split("-");
  var date1=new Date(kssjArr[0],kssjArr[1],kssjArr[2]);
  var date2=new Date(jssjArr[0],jssjArr[1],jssjArr[2]);
  if(date1>date2) return 1;
  else return 0;
}

//�������ťʱִ�д˺���
function sort(sortBy,sortType,sortNum)
{
if(data.documentElement.childNodes.length==0) return false;
  var sortByArr=new Array();
  for(var i=0;i<buildArr.length;i++){
    sortByArr[i]=datanodes.item(buildArr[i]-1).getElementsByTagName(sortBy).item(0).text;
  }  
  if(sortOrder=='asce') {   //����
    for(var i=0;i<buildArr.length;i++){
       for(var j=0;j<buildArr.length-1;j++){
         var tempArr;
         var tempSort;
         if(sortType=='text'){
             if(compare_text(sortByArr[j],sortByArr[j+1])==1){
                 tempArr=sortByArr[j];sortByArr[j]=sortByArr[j+1];sortByArr[j+1]=tempArr;
                 tempSort=buildArr[j];buildArr[j]=buildArr[j+1];buildArr[j+1]=tempSort;
             }
         }else if(sortType=='number'){
             if(compare_number(sortByArr[j],sortByArr[j+1])==1){
                 tempArr=sortByArr[j];sortByArr[j]=sortByArr[j+1];sortByArr[j+1]=tempArr;
                 tempSort=buildArr[j];buildArr[j]=buildArr[j+1];buildArr[j+1]=tempSort;
             }
         }else if(sortType=='date'){
             if(compare_date(sortByArr[j],sortByArr[j+1])==1){
                tempArr=sortByArr[j];sortByArr[j]=sortByArr[j+1];sortByArr[j+1]=tempArr;
                 tempSort=buildArr[j];buildArr[j]=buildArr[j+1];buildArr[j+1]=tempSort;
             }
         }
       }
    }
    sortOrder="desc";
  } else if(sortOrder=='desc'){  //����
    for(var i=0;i<buildArr.length;i++){
       for(var j=0;j<buildArr.length-1;j++){
         var tempArr;
         var tempSort;
         if(sortType=='text'){
             if(compare_text(sortByArr[j],sortByArr[j+1])==0){
                tempArr=sortByArr[j];sortByArr[j]=sortByArr[j+1];sortByArr[j+1]=tempArr;
                 tempSort=buildArr[j];buildArr[j]=buildArr[j+1];buildArr[j+1]=tempSort;
             }
         }else if(sortType=='number'){
             if(compare_number(sortByArr[j],sortByArr[j+1])==0){
                tempArr=sortByArr[j];sortByArr[j]=sortByArr[j+1];sortByArr[j+1]=tempArr;
                 tempSort=buildArr[j];buildArr[j]=buildArr[j+1];buildArr[j+1]=tempSort;
             }
         }else if(sortType=='date'){
             if(compare_date(sortByArr[j],sortByArr[j+1])==0){
                tempArr=sortByArr[j];sortByArr[j]=sortByArr[j+1];sortByArr[j+1]=tempArr;
                 tempSort=buildArr[j];buildArr[j]=buildArr[j+1];buildArr[j+1]=tempSort;
             }
         }
       }
    }
    sortOrder="asce";
  }
    del();
    addHead();
    addAll();
    if(sortOrder=="asce"){
       objtable.rows[0].cells[parseInt(sortNum)+1].innerText=nodes.item(parseInt(sortNum)).text+"��";
    }else{
       objtable.rows[0].cells[parseInt(sortNum)+1].innerText=nodes.item(parseInt(sortNum)).text+"��";
    }
}
//��ԭ�н���в���ʱҲɾ��data������node,Ȼ���xmlDocTemp������node���Ƶ�data����
function delAllNode()
{
  var len=datanodes.length;
  for(var i=0;i<len;i++)
  {
    datanodes.item(i).parentNode.removeChild(datanodes.item(i));
    i-=1;
    len-=1;
  }
}
//�˺���ʵ���ڽ���в��ҷ��������ļ�¼
function search()
{
  if(isResult.checked==false)
  {
    delAllNode();
    var xmlDocTempLen=xmlDocTemp.documentElement.childNodes.length;
    for(var i=0;i<xmlDocTempLen;i++)
    {
       data.documentElement.appendChild(xmlDocTemp.documentElement.childNodes.item(i).cloneNode(true));    
    }
    datanodes=data.documentElement.childNodes;
   
  }
  var nodeLen=datanodes.length;
  for(var i=0;i<nodeLen;i++)
  {
    if(!isShow(i)) //������������ɾ��
    {
      datanodes.item(i).parentNode.removeChild(datanodes.item(i));
      i-=1;
      nodeLen-=1;
    }
  }
 
 totalrow=datanodes.length;//����������
 totalpage=0;//��ҳ��
 if(totalrow!=0) {
   if(totalrow%pagerow!=0) totalpage=parseInt(totalrow/pagerow)+1;
   else totalpage=parseInt(totalrow/pagerow);
 }
 currPage=1;//��ǰҳ��
 checkArr=new Array();//�Ƿ�ѡ��
 theValueArr=new Array();//��¼ѡ�м�¼��checkbox��valueֵ
 for(var i=0;i<totalrow;i++)
 {
   checkArr[i]=0;//��¼ѡ�еļ�¼0Ϊδѡ��,1Ϊѡ��,��ʼΪ0
   theValueArr[i]="";
 }
 buildArr=new Array();//���浱ǰҳҪ���ɵļ�¼���
 sortOrder='asce';//���������ǽ��� 
 show('first');
}
//�������������Ҫ���Լ�ʵ��,���������ļ�¼(Ҫ��ʾ)����true,���򷵻�false
function isShow(nodeNo)
{

  var myxm=xm.value;
  var temp=datanodes.item(nodeNo).getElementsByTagName("username");
  if(temp.item(0).text.indexOf(myxm)!=-1) return true;
  return false;
}


