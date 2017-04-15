`<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <style type="text/css">
		.monkeyb-cust-file{
			overflow: hidden;
			position: relative;
			display: inline-block;
			background-color:#9cf;
			color:#fff;
			text-align:center;
			-web-border-radius:10px;
			-moz-border-radius:10px;
			border-radius:10px;
			padding:10px 30px;
			font-size:26px;
			font-family:Arial,Microsoft JhengHei;
		}
		.monkeyb-cust-file input{
			position: absolute;
			opacity: 0;
			filter: alpha(opacity=0);
			top: 0;
			right: 0;
			width:100%;
			height:100%;
		}

    </style>
	<title>用户基因报表生成</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
function _s() {
    var f = document.getElementById("file").files;
    document.getElementById("div1").innerHTML=f[0].name;
}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dm/createWordFile/geneDmCreateWordFile">用户基因报表生成</a></li>
	</ul><br/>
	<h1 style="padding-left:20px;text-align:center;">用户基因word生成</h1>
	<div id="importBox">
		<form id="importForm" action="${ctx}/dm/createWordFile/geneDmCreateWordFile/export" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<div id="div" class="monkeyb-cust-file">
				<div id="div1">请输入试管编码</div>
				<%--<input id="file" name="file" type="file" onchange="_s()" />--%>
			</div>
			<br/>
			<input id="textInput" name="textInput" type="text" />
            <br/>
            <br/>
			<input id="btnImportSubmit" style="padding-left:20px;text-align:center;" class="btn btn-primary" type="submit" value="   生    成   "/>
		</form>
	</div>
	<sys:message content="${message}"/>

</body>
</html>