# bootstrap-table

## 示例

```javascript
var $table = $('#mychart1');
$table.bootstrapTable({
url: reqprojectname_w+'list/spectrumlist', 
dataType: "json",
toolbar: '#toolbar',                //工具按钮用哪个容器
striped: true,                      //是否显示行间隔色
singleSelect: false,
pagination: true, //分页
pageNumber:1,                       //初始化加载第一页，默认第一页
pageSize: 10,                       //每页的记录行数（*）
pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
search: false, //显示搜索框
sidePagination: "server", //服务端处理分页
      columns: [{
          field: 'id',
          title: '序号'
      }, {
          field: 'liushuiid',
          title: '交易编号'
      }, {
          field: 'orderid',
          title: '订单号'
      }, {
          field: 'receivetime',
          title: '交易时间'
      }, {
          field: 'price',
          title: '金额'
      }, {
          field: 'coin_credit',
          title: '投入硬币'
      },  {
          field: 'bill_credit',
          title: '投入纸币'
      },  {
          field: 'changes',
          title: '找零'
      }, {
          field: 'tradetype',
          title: '交易类型'
      },{
          field: 'goodmachineid',
          title: '货机号'
      },{
          field: 'inneridname',
          title: '货道号'
      },{
          field: 'goodsName',
          title: '商品名称'
      }, {
          field: 'changestatus',
          title: '支付'
      },{
          field: 'sendstatus',
          title: '出货'
      },
              {
                  title: '操作',
                  field: 'id',
                  align: 'center',
                  formatter:function(value,row,index){  
                       var e = '<a href="#" mce_href="#" onclick="edit(\''+ row.id + '\')">编辑</a> ';  
                       var d = '<a href="#" mce_href="#" onclick="del(\''+ row.id +'\')">删除</a> ';  
                    return e+d;  
                } 
              }
          ]
  });
```

```html
		<!-- 用户列表 -->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div id="toolbar">
						<button id="deleteSelectBtn" type="button" class="btn btn-danger">删除</button>
					</div>
					<table id="userList"></table>
				</div>
			</div>
		</div>

<!-- 		<script type="text/javascript" src="local/plugin/bootstrap-table/bootstrap-table.js"></script>
 -->		
		<script>
			require(["toastr", "bootstrapTableCN"], function(toastr) {
				showMsg("success", "删除成功!");
				var userListTable = $("#userList");
				var deleteSelectBtn = $("#deleteSelectBtn");

            	var tableOptions = {
					url: "user/list/show",
					method: "post",
					dataType: "json",
					toolbar: "#toolbar",
            		pagination: true,
        			sidePagination: "client",
        			pageNumber: 1,
        			pageSize: 10,
        			pageList: [10, 20, 40, 80],
        			search: true,
        			showColumns: true,
        			showToggle: true,
        			showPaginationSwitch: true,
	        		columns: [
	        			{
	        				checkbox: true
	        			},
            			{
            				title: "序号",
            				formatter: function(value, row, index) {
            					return index + 1;
            				},
            				cellStyle: function() { return { css: {"width": "5rem"} } }
            			}, {
            				field: "username",
            				title: "用户名",
            				searchable: "true",
            				formatter: function(value, row, index) {
            					return value;
            				}
/*            			}, {
            				title: "修改",
            				formatter: function(value, row, index) {
            					var html = "<i class='fa fa-pencil-square-o'></i>"
            					return html;
            				},
            				cellStyle: function() { return { classes: "icon-column" } }
*/            			}

            		]

                }

                initTable();
                
                function initTable() {
	                userListTable.bootstrapTable(tableOptions);
                }

                /*
                 * 删除选中项
                 **/
                deleteSelectBtn.click(function() {
                	showConfirmOrCancelModal("删除用户", "确定要删除选中的用户吗?", actionDelete);
                });

                function actionDelete() {
                	var selectedItems = userListTable.bootstrapTable("getSelections");
                	var selectedItemIds = [];
                	for (var i=0; i < selectedItems.length; ++i) {
                		selectedItemIds.push({id: selectedItems[i].id, username: selectedItems[i].username});
                	}
	 	            $.ajax({
	 	            	url: "user/delete", 
	 	            	data: JSON.stringify(selectedItemIds), 
	 	            	type: "post",
	 	            	contentType:"application/json",  
	 	            	dataType: "json",
	 	            	success: function(data) {
			                if (data.msg.toUpperCase() == "OK") {
			                	console.log("OK");
				                toastr["success"]("删除成功!");
			                } else {
				                toastr["error"](data["data"]);
			                }
			            }
	 	            });
                }
			});
		</script>
```

