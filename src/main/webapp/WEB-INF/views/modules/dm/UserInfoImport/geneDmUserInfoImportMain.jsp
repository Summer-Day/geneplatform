`<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <style type="text/css">
        .a-upload {
            padding: 4px 10px;
            height: 20px;
            line-height: 20px;
            position: relative;
            cursor: pointer;
            color: #888;
            background: #fafafa;
            border: 1px solid #ddd;
            border-radius: 4px;
            overflow: hidden;
            display: inline-block;
            *display: inline;
            *zoom: 1
        }

        .a-upload  input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
            filter: alpha(opacity=0);
            cursor: pointer
        }

        .a-upload:hover {
            color: #444;
            background: #eee;
            border-color: #ccc;
            text-decoration: none
        }

    </style>
	<title>用户信息批量导入</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
        $(".a-upload").on("change","input[type='file']",function(){
            var filePath=$(this).val();
                var arr=filePath.split('\\');
                var fileName=arr[arr.length-1];
//                $(".showFileName").html(fileName);
        })
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dm/UserInfoImport/geneUserInfoImport">用户信息批量导入</a></li>
	</ul><br/>
	<h1 style="padding-left:20px;text-align:center;">用户基因数据批量导入</h1>

	<%--<form action="${ctx}/dm/UserInfoImport/geneUserInfoImport/UserInfoImport" method="post" enctype="multipart/form-data">  --%>
	<%--<input type="file" name="Photo">--%>
	<%-- <input type="submit" value="上传"> --%>
	<%--</form>--%>

	<div id="importBox">
		<form id="importForm" action="${ctx}/dm/UserInfoImport/geneUserInfoImport/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<%--<input id="uploadFile" name="file" type="file" style="width:330px;background-color: #0b2c89"/>　--%>
				<input id="uploadFile"  name="file" type="file" style="width:330px">
            <br/>
            <br/>

			<input id="btnImportSubmit" style="padding-left:20px;text-align:center;" class="btn btn-primary" type="submit" value="   导    入   "/>
		</form>
	</div>
	<sys:message content="${message}"/>

</body>
</html>