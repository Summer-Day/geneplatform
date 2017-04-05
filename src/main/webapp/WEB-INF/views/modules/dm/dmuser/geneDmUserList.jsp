<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dm/dmuser/geneDmUser/">用户列表</a></li>
		<shiro:hasPermission name="dm:dmuser:geneDmUser:edit"><li><a href="${ctx}/dm/dmuser/geneDmUser/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="geneDmUser" action="${ctx}/dm/dmuser/geneDmUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>用户电话：</label>
				<form:input path="userTele" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>试管编号：</label>
				<form:input path="tubeId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户姓名</th>
				<th>用户年龄</th>
				<th>用户电话</th>
				<th>试管编号</th>
				<th>更新时间</th>
				<shiro:hasPermission name="dm:dmuser:geneDmUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="geneDmUser">
			<tr>
				<td><a href="${ctx}/dm/dmuser/geneDmUser/form?id=${geneDmUser.id}">
					${geneDmUser.userName}
				</a></td>
				<td>
					${geneDmUser.userAge}
				</td>
				<td>
					${geneDmUser.userTele}
				</td>
				<td>
					${geneDmUser.tubeId}
				</td>
				<td>
					<fmt:formatDate value="${geneDmUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dm:dmuser:geneDmUser:edit"><td>
    				<a href="${ctx}/dm/dmuser/geneDmUser/form?id=${geneDmUser.id}">修改</a>
					<a href="${ctx}/dm/dmuser/geneDmUser/delete?id=${geneDmUser.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>