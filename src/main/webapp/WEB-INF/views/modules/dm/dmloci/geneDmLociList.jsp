<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户基因添加管理</title>
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
		<li class="active"><a href="${ctx}/dm/dmloci/geneDmLoci/">用户基因添加列表</a></li>
		<shiro:hasPermission name="dm:dmloci:geneDmLoci:edit"><li><a href="${ctx}/dm/dmloci/geneDmLoci/form">用户基因添加添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="geneDmLoci" action="${ctx}/dm/dmloci/geneDmLoci/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>基因位点：</label>
				<form:input path="loci" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>基因类型：</label>
				<form:input path="geneType" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>试管编码：</label>
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
				<th>基因位点</th>
				<th>基因类型</th>
				<th>试管编码</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="dm:dmloci:geneDmLoci:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="geneDmLoci">
			<tr>
				<td><a href="${ctx}/dm/dmloci/geneDmLoci/form?id=${geneDmLoci.id}">
					${geneDmLoci.loci}
				</a></td>
				<td>
					${geneDmLoci.geneType}
				</td>
				<td>
					${geneDmLoci.tubeId}
				</td>
				<td>
					<fmt:formatDate value="${geneDmLoci.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${geneDmLoci.remarks}
				</td>
				<shiro:hasPermission name="dm:dmloci:geneDmLoci:edit"><td>
    				<a href="${ctx}/dm/dmloci/geneDmLoci/form?id=${geneDmLoci.id}">修改</a>
					<a href="${ctx}/dm/dmloci/geneDmLoci/delete?id=${geneDmLoci.id}" onclick="return confirmx('确认要删除该用户基因添加吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>