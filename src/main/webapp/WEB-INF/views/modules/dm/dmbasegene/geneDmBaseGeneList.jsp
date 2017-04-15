<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基础数据管理</title>
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
		<li class="active"><a href="${ctx}/dm/dmbasegene/geneDmBaseGene/">基础数据列表</a></li>
		<shiro:hasPermission name="dm:dmbasegene:geneDmBaseGene:edit"><li><a href="${ctx}/dm/dmbasegene/geneDmBaseGene/form">基础数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="geneDmBaseGene" action="${ctx}/dm/dmbasegene/geneDmBaseGene/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>疾病名称：</label>
				<form:input path="sickness" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>基因简写：</label>
				<form:input path="geneLogogram" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>基因位点：</label>
				<form:input path="geneLoci" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>基因组合：</label>
				<form:input path="geneComposite" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>疾病名称</th>
				<th>基因简写</th>
				<th>基因位点</th>
				<th>基因组合</th>
				<th>基因类型</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="dm:dmbasegene:geneDmBaseGene:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="geneDmBaseGene">
			<tr>
				<td><a href="${ctx}/dm/dmbasegene/geneDmBaseGene/form?id=${geneDmBaseGene.id}">
					${geneDmBaseGene.sickness}
				</a></td>
				<td>
					${geneDmBaseGene.geneLogogram}
				</td>
				<td>
					${geneDmBaseGene.geneLoci}
				</td>
				<td>
					${geneDmBaseGene.geneComposite}
				</td>
				<td>
					${geneDmBaseGene.geneTypeFlag}
				</td>
				<td>
					<fmt:formatDate value="${geneDmBaseGene.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${geneDmBaseGene.remarks}
				</td>
				<shiro:hasPermission name="dm:dmbasegene:geneDmBaseGene:edit"><td>
    				<a href="${ctx}/dm/dmbasegene/geneDmBaseGene/form?id=${geneDmBaseGene.id}">修改</a>
					<a href="${ctx}/dm/dmbasegene/geneDmBaseGene/delete?id=${geneDmBaseGene.id}" onclick="return confirmx('确认要删除该基础数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>