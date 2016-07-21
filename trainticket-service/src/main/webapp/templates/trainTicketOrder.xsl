<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output method="xml" encoding="UTF-8"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />

	<xsl:template match="/">
		<html>
			<head>
				    <style>
				    <![CDATA[
				     body, td, th{
    color: #333333; font: 12px "宋体" , arial, helvetica, clean, sans-serif;  line-height: 150%;
    }
    table {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.tdCenter th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.tdCenter td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
    .staticData{ 
			  font-size:9.0pt; line-height:125%;
			  font-family:Arial; 
			 } 
			 .tripData{ 
			  font-size:11.0pt; line-height:125%;
			  font-family:Arial; 
			 }
			 .tdCenter {
				text-align : center
			 }
			 .dynamicData{ 
			    font-size:9.0pt;
			    line-height:125%; 
			    font-family:Arial; 
			    color:blue; 
			 } 
			 .caption{
			   font-size:12.0pt; 
			   line-height:150%; 
			   font-family:Arial; 
			 }
			 .captionEN{
			    font-size:12.0pt; 
			    line-height:150%;
			    font-family:'Arial'; 
			 } 
			 .captionCN{
			    font-size:12.0pt;
			    line-height:150%; 
			    font-family:'宋体'; 
			 } 
			 .wishEN{
			    font-size:12.0pt; 
			    line-height:150%;
			    font-family:Arial;
			    font-weight: bold;
			    font-style:italic;
			 }
			 .wishCN{ 
			    font-size:12.0pt; 
			    line-height:150%;
			    font-family:'宋体';
			    font-weight: bold;
			    font-style:italic; 
			 }
			 .title{ 
			    font-size:14pt; 
			    line-height:125%; 
			    font-family:Arial;
			    font-weight:700;
			 }
			 .bigTitle{ 
			    font-size:26pt; 
			    line-height:125%; 
			    font-family:'隶书';
			    font-weight:700;
			 }
]]>
					</style>
			</head>
			<body>
				<table width="650">
					<tr>
						<td width="400">
							<font class="title">TRAVEL CONFIRMATION　 旅行确认单</font>
						</td>
						<td width="200">
							<img src="cid:mangoLogo" />
						</td>
					</tr>
				</table>
				<table width="650" class="tdCenter">
				  <tr>
					<td>
						<b>
							<FONT class="staticData">Company/公司名称:
								<xsl:value-of select="trainTicketOrder/company"/>
							</FONT>
						</b>
					</td>
					<td>
						<b>
							<FONT class="staticData">Company/公司名称:芒果网有限公司</FONT>
						</b>
					</td>
				  </tr>
				  <tr>
					<td>
						<b>
							<FONT class="staticData">Branch/分支机构:
							   <xsl:value-of select="trainTicketOrder/branch"/>
							</FONT>
						</b>
					</td>
					<td>
						<b>
							<FONT class="staticData">From: <xsl:value-of select="trainTicketOrder/operater" /></FONT>
						</b>
					</td>
				  </tr>
				  <tr>
					<td>
						<b>
							<FONT class="staticData">Tel: <xsl:value-of select="trainTicketOrder/telephone" /></FONT>
						</b>
					</td>
					<td>
						<b>
							<FONT class="staticData">Tel: 40066-20088 (24-hour)</FONT>
						</b>
					</td>
				  </tr>
				  <tr>
				    <td>
						<b>
							<FONT class="staticData">Reason Code/原因代码:<xsl:value-of select="trainTicketOrder/reasonCode" /></FONT>
						</b>
					</td>
					<td>
						<b>
							<FONT class="staticData">Cost Center/成本中心:<xsl:value-of select="trainTicketOrder/costCenter" /></FONT>
						</b>
					</td>
				  </tr>
				</table>
		        <br/><br/>
				<table width="650">
				  <tr>
					<td><FONT class="staticData">Dear traveler/尊敬的旅客,</FONT></td>
				  </tr>
				  <tr>
					<td>
						<FONT class="staticData">
							Please kindly find the information/请查看以下航班及其他信息:
						</FONT>
					</td>
				  </tr>
				  <tr>
					<td>
						<FONT class="staticData">
							订单:<xsl:value-of select="trainTicketOrder/orderCn" />
						</FONT>
					</td>
				  </tr>
				</table>
		<table width="650" id="flightInformation" class="tdCenter">
			<tr class="backgroundGray">
				<td><b><FONT class="staticData">NO</FONT></b></td>
                <td><b><FONT class="staticData">Name</FONT></b></td>
                <td><b><FONT class="staticData">Train</FONT></b></td>
                <td><b><FONT class="staticData">Date</FONT></b></td>
                <td><b><FONT class="staticData">Dep.city -- Arr.city</FONT></b></td>
                <td><b><FONT class="staticData">Seat</FONT></b></td>
                <td><b><FONT class="staticData">ServiceCharge</FONT></b></td>
			</tr>
			<xsl:for-each select="trainTicketOrder/ticketList/ticket">
			   <tr>
			    <td><xsl:value-of select="no" /></td>
			    <td><xsl:value-of select="name" /></td>
			    <td><xsl:value-of select="train" /></td>
			    <td><xsl:value-of select="date" /></td>
			    <td><xsl:value-of select="origStationName" />(<xsl:value-of select="startTime" />)--<xsl:value-of select="destStationName" />(<xsl:value-of select="endTime" />)</td>
			    <td><xsl:value-of select="seat" />(<xsl:value-of select="price" />)</td>
			    <td><xsl:value-of select="serviceCharge" /></td>
			  </tr>
			</xsl:for-each>
			<tr class="backgroundGray">
			   <td colspan="5"></td>
			   <td><b><FONT class="staticData">订单应付</FONT></b></td>
               <td>
               	   <b>
               	   	<FONT class="staticData">
               	    <xsl:value-of select="trainTicketOrder/allPrice"/>
               	    </FONT>
               	   </b>
               </td>														
		    </tr>
		    <tr>
		       <td colspan="7">退改规定：发车前2小时内免费退票</td>
		    </tr>
		</table>
		<table width="650">
		  <tr><td height="15"> </td></tr>
		  <tr class="backgroundGray">
			<td ><b><FONT class="staticData">Note/备注:</FONT></b></td>
		  </tr>
		  <tr><td height="15"> </td></tr>
		  <tr>
			<td>
				<FONT class="staticData">
					Your reservation must be in the same name as your National ID Card/Passport- If not please contact us immediately to change the reservation.
				</FONT>
			</td>
		  </tr>
		  
		  <tr>
			<td>
				<FONT class="staticData">
					旅客姓名要同身份证或护照等证件的名字相同。如果不同,请立即联系我们.
				</FONT>
			</td>
		  </tr>
		  <tr>
			<td>
				<FONT class="staticData">
					Would you please confirm all the details of booking within 30 minutes.The final price will follow the pricing of the momment after ticketing.
				</FONT>
			</td>
		  </tr>
		  <tr>
			<td>
				<FONT class="staticData">
					请您尽快在30分钟内确认出票，所有火车票价格以出票时为准，谢谢支持！
				</FONT>
			</td>
		  </tr>
		  <tr><td height="15"> </td></tr>
		  <tr>
			<td>
				<b><FONT class="staticData">
					If you need any help, please contact us.如果您需要帮助,请与我们联系。
				</FONT></b>
			</td>
		  </tr>
		  <tr>
			<td>
				<FONT class="staticData" color="red">
					24-hour hotline/24小时热线: 40066 20088
				</FONT>
			</td>
		  </tr>
		   <tr>
				<td>
                   <b><FONT class="staticData">Confirmed by:/确认签字: _____________</FONT></b>
				</td>
			</tr>
		</table>
		</body>
	  </html>
	</xsl:template>
</xsl:stylesheet>
