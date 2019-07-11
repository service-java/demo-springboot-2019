<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="valuebean.GoodsSingle" %>
<!-- 通过动作标识，获取ShopCar类实例 -->

<jsp:useBean id="myCar" class="toolbean.ShopCar" scope="session"/>

<% 
	ArrayList<GoodsSingle> buylist = myCar.getBuylist();

	//获取实例中用来存储购买的商品的集合
	float total=0;								//用来存储应付金额
%>

<table border="1" width="450" rules="none" cellspacing="0" cellpadding="0">
	<tr height="50"><td colspan="5" align="center">购买的商品如下</td></tr>
	<tr align="center" height="30" bgcolor="lightgrey">
		<td width="25%">名称</td>
		<td>价格(元/斤)</td>
		<td>数量</td>
		<td>总价(元)</td>
		<td>移除(-1/次)</td>
	</tr>
	<%	if(buylist==null||buylist.size()==0){ %>
	<tr height="100"><td colspan="5" align="center">您的购物车为空！</td></tr>
	<% 
		}
		else{
			for(int i=0;i<buylist.size();i++){
				GoodsSingle single=(GoodsSingle)buylist.get(i);
				String name=single.getName();							//获取商品名称
				float price=single.getPrice();							//获取商品价格
				int num=single.getNum();								//获取购买数量				
				float money=((int)((price*num+0.05f)*10))/10f;			//计算当前商品总价，并进行四舍五入
				total+=money;											//计算应付金额
	%>
	<tr align="center" height="50">
		<td><%=name%></td>
		<td><%=price%></td>
		<td><%=num%></td>
		<td><%=money%></td>
		<td>
<a href="do-car.jsp?action=remove&name=<%=single.getName() %>">移除</a>
</td>
	</tr>
	<%							
			}
		}
	%>
	<tr height="50" align="center"><td colspan="5">应付金额：<%=total%></td></tr>
	<tr height="50" align="center">
		<td colspan="2"><a href="show.jsp">继续购物</a></td>
		<td colspan="3"><a href="do-car.jsp?action=clear">清空购物车</a></td>
	</tr>				
</table>