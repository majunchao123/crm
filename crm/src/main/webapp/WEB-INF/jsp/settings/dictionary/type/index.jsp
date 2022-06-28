<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
		$(function () {
            //页面显示查询页面
		  function load() {
		      $.ajax({
                  url:'settings/dictionary/type/queryDicType.do',
                  type:'post',
                  dataType:'json',
                  success:function (data) {
                      var htmlStr = "";
                      $.each(data,function (index,obj) {
                         var str = '${(index)%2==0?"active":""}';
                              htmlStr += "<tr class="+str+">";
                              htmlStr += "<td><input type=\"checkbox\"/></td>";
                              htmlStr += "<td>"+index+"</td>";
                              htmlStr += "<td>"+obj.code+"</td>";
                              htmlStr += "<td>"+obj.name+"</td>";
                              htmlStr += "<td>"+obj.description+"</td>";
                              htmlStr += "</tr>";
                      })

                      $("#tBody").html(htmlStr);
                  }
              })
          }
          load();



            //添加
            //给全选按钮添加点击事件
            $("#checkAll").click(function () {
                if (this.checked==true){
                    $("#tBody input[type='checkbox']").prop("checked",true);
                }else {
                    $("#tBody input[type='checkbox']").prop("checked",false);
                }
            })
            //给单选按钮
            $("#tBody").on("click","input[type='checkbox']",function () {
                //如果列表中的所有checkbox都选中，则"全选"按钮也选中
                if($("#tBody input[type='checkbox']").size()==$("#tBody input[type='checkbox']:checked").size()){
                    $("#checkAll").prop("checked",true);
                }else{//如果列表中的所有checkbox至少有一个没选中，则"全选"按钮也取消
                    $("#checkAll").prop("checked",false);
                }
            })

		  //修改




          //删除





        })
    </script>


</head>
<body>

<div>
    <div style="position: relative; left: 30px; top: -10px;">
        <div class="page-header">
            <h3>字典类型列表</h3>
        </div>
    </div>
</div>
<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
    <div class="btn-group" style="position: relative; top: 18%;">
        <button type="button" class="btn btn-primary" onclick="window.location.href='save.html'"><span
                class="glyphicon glyphicon-plus"></span> 创建
        </button>
        <button type="button" class="btn btn-default" onclick="window.location.href='edit.html'"><span
                class="glyphicon glyphicon-edit"></span> 编辑
        </button>
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
    </div>
</div>
<div style="position: relative; left: 30px; top: 20px;">
    <table class="table table-hover">
        <thead>
        <tr style="color: #B3B3B3;">
            <td><input type="checkbox" id="checkAll"/></td>
            <td>序号</td>
            <td>编码</td>
            <td>名称</td>
            <td>描述</td>
        </tr>
        </thead>
        <tbody id="tBody">
        <%--<tr class="active">
            <td><input type="checkbox"/></td>
            <td>1</td>
            <td>sex</td>
            <td>性别</td>
            <td>性别包括男和女</td>
        </tr>--%>
        </tbody>
    </table>
</div>

</body>
</html>