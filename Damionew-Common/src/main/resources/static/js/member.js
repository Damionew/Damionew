$(function(){
		initDataGrid();
	});
	function initDataGrid(){
		//创建bootstraptable
		$("#dataGrid").bootstrapTable({
			method:"get",
			cache: false,         
			//缺失无法执行queryParams,传递Page参数
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			url : "/user/query",
			queryParams : queryParam,
			pagination : true,//显示分页条：页码，条数等
			striped : true,//隔行变色
			pageNumber : 1,//首页页码
			pageSize : 10,//分页，页面数据条数
			pageList: [10,20,30,40],
			uniqueId : "id",//Indicate an unique identifier for each row
			sidePagination : "server",//在服务器端分页
			height:dataGrid.height,
			sortName : "created_at",
			toolbar : "#toolbar",//工具栏
			columns : [{
				checkbox : "true",
				field : "box",
				width : 50
			},{
				title : "id",
				field : "id",
				visible : false
			},{
				title : "家庭账户",
				field : "familyAccount"
			},{
				title : "成员账户",
				field : "memberAccount"
			},{
				title : "成员昵称",
				field : "memberName"
			}],
			search : true,
			searchOnEnterkey : true,
			showRefresh : true,
			showToggle : true
		});
	}
	function queryParam(params){
		var param = {
				limit : this.limit,//页面大小
				offset : this.offset,//页码
				pageNumber : this.pageNumber,
				pageSize : this.pageSize,
				sortName : this.sortName,
				sortOrder : this.sortOtder
		};
		return param;
	}
	//删除成员
	function deleteMember(){
		var rows = $("#dataGrid").bootstrapTable("getSelections");
		if(rows.length==0){
			alert("请先选中记录！");
		}else{
			var ids = [];
			for(var i = 0;i < rows.length;i++){
				ids.push(rows[i].id);
			}
			$.ajax({
				url : "/gradesign/member/delete",
				dataType:"json",
				traditional: true,//属性在这里设置
				method:"post",
				data:{
					"ids":ids
				},
				success : function(){
					alert("删除成功！");
					$("#dataGrid").bootstrapTable("refresh");
				},
				error : function(){
					alert("系统错误！");
				}
			})
		}
	};
	//点击取消后清空表单中已写信息
	function resetIncomeModal(){
		document.getElementById("incomeModalForm").reset();
	};
