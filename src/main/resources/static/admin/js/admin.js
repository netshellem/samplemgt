$(function () {
	$('#table').bootstrapTable({
		locale:'zh-CN',
        ajax : function (request) {
                $.ajax({
                    type : "post",
                    url : "/GetSamples",
                    contentType: "application/json;charset=utf-8",
                    dataType : 'json',
                    data : JSON.stringify(queryParams()),
                    success : function (msg) {
                        request.success({
                            row : msg
                        });
                        $('#table').bootstrapTable('load', msg);
                    },
                    error:function(){
                       console.log("failed to load data");
                    }
                });
            },

		cache:'false',
		pagination:'true',
		sidePagination: 'client',
		clickToSelect: true,
        pageNumber: 1,
        pageSize: 25,
        pageList: [10, 25, 50, 100],
        striped: true,
        sortName:'cid',
		sortOrder: 'desc',
		height: 680,
        uniqueId: 'cid',
        cardView: false,
        searchOnEnterKey: true,
        detailView: false,
        columns: [
 		{
			field: 'state',
			checkbox: true
 		},
 		{
 			field: 'id',
 			title: '序号',
 			formatter: function(value, row,index)
 			{
 				return index +1;
 			}
 		},
        {
            field: 'cid',
            title: '样衣编号',
            sortable: true,
            sorter:
            	function priceSorter(a, b) {
            	var tmp1 = a.substring(0,2);
            	var tmp2 = b.substring(0,2);
                if (tmp1 > tmp2) return 1;
                if (tmp1 < tmp2) return -1;
                a = a.substring(2,5);
                b = b.substring(2,5);

                var n1 = parseInt(a);
                var n2 = parseInt(b);
                if (!isNaN(n1) && !isNaN(n2))
                {
                	if (n1 > n2) return 1;
                	if (n1 < n2) return -1;
                }

                n1 = parseInt(a.substring(5));
                n2 = parseInt(a.substring(5));
                if (!isNaN(n1) && !isNaN(n2))
                {
                	if (n1 > n2) return 1;
                	if (n1 < n2) return -1;
                }
                return 0;
            }
        },
        {
            field: 'type',
            title: '样衣类型',
            sortable: false
        },
        {
            field: 'designer',
            title: '设计师',
            sortable: false
        },
        {
            field: 'model',
            title: '版师',
            sortable: false
        },
        {
            field: 'sample',
            title: '样衣师',
            sortable: false
        },
        {
            field: 'origin',
            sortable: true,
            editable: {
                        type: 'select',
                        title: '选择产地',
                        source:[{value:"北京",text:"北京"},{value:"杭州",text:"杭州"},{value:"柯桥",text:"柯桥"}]
             },
            title: '产地'
        },
        {
            field: 'status',
            sortable: true,
            sorter: function(a,b){return a.localeCompare(b)},
            editable: {
                        type: 'select',
                        title: '状态',
                        source:[{value:"库存",text:"库存"},{value:"售出",text:"售出"},{value:"退货",text:"退货"},
                        {value:"赠送",text:"赠送"},{value:"复色",text:"复色"},{value:"贴牌",text:"贴牌"},
                        {value:"合作",text:"合作"},{value:"处理",text:"处理"}]
             },
            title: '状态'
        },
        {
            field: 'start_date',
            title: '入库时间',
            sortable: true,
            sorter: function(a,b){
            	var tmp1 = a.substring(0,4);
            	var tmp2 = b.substring(0,4);
                if (tmp1 > tmp2) return 1;
                if (tmp1 < tmp2) return -1;
                n1 = a.substring(5,7);
                n2 = b.substring(5,7);
                if (n1 > n2) return 1;
                if (n1 < n2) return -1;

                n1 = a.substring(8);
                n2 = b.substring(8);
                if (n1 > n2) return 1;
                if (n1 < n2) return -1;
                return 0;
            }
        },
        {
            field: 'customer',
            editable: {
                       	type: 'text',
                       	title: '输入客户姓名：',
                       	validate: function (value) {
                               if ($.trim(value) == '') {
                                   return '名称不能为空!';
                               }
                           }
                       },
            title: '客户'
        },
        {
            field: 'end_date',
            sortable: true,
            sorter: function(a,b){
            	if (a === null || a === undefined || a === '') {
            		 a = '-';
            		}
            	if (b === null || b === undefined || b === '') {
           		 b = '-';
           		}
            	if((a.substring(0,1)=='-') &&
            			(b.substring(0,1)!='-'))
            		return -1;
            	if((b.substring(0,1)=='-') &&
            			(a.substring(0,1)!='-'))
            		return 1;
            	var tmp1 = a.substring(0,4);
            	var tmp2 = b.substring(0,4);

                if (tmp1 > tmp2) return 1;
                if (tmp1 < tmp2) return -1;
                n1 = a.substring(5,7);
                n2 = b.substring(5,7);
                if (n1 > n2) return 1;
                if (n1 < n2) return -1;

                n1 = a.substring(8);
                n2 = b.substring(8);
                if (n1 > n2) return 1;
                if (n1 < n2) return -1;
                return 0;
            },
             editable: {
                        	type: 'datetime',
                        	 format: 'yyyy-mm-dd',
                             viewformat: 'yyyy-mm-dd',
                             datetimepicker: {
                            		 language: 'zh-CN',
                                     autoclose: true,
                                     todayBtn: true,
                                     minView: 'month',
                                     pickerPosition: 'bottom-left'
                                }

             },
            title: '出库时间'

        },
        {
            field: 'comment',
            editable: {
                        	type: 'textarea',
                        	title: '输入备注内容：'
             },
            title: '备注'
        }],

        onEditableSave: function (field, row, oldValue, $el) {
                	$('#table').bootstrapTable('resetView');
                    $.ajax({
                        type: "post",
                        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                        url: "../sample/UpdateRow",
                        //date:row,
                        data:encodeURIComponent(JSON.stringify(row)) ,
                        success: function (data, status) {
                            if (status == "success") {
                                alert('提交数据成功');
                            }
                        },
                        error: function () {
                            alert('编辑失败');
                            console.log($el);
                            console.log(oldValue);
                            if ("undefined" === typeof oldValue) {
                                console.log("oldValue is undefined");
                                oldValue = '-';
                            }
                            $el[0].innerText = oldValue;

                        },
                        complete: function () {

                        }

                    });
                }

	});
	//$('#addbtn').click(function (){window.location.href="add.jsp";});
});
var $table = $('#table');
var $ok = $('#ok');
var $delbutton = $('#delbtn');
$(function () {
    $ok.click(function () {
        document.getElementById("activeyear").value="";
        $table.bootstrapTable('refresh');
    });
});

function queryParams() {
    var params = {};
    $('#querybar').find('input[name]').each(function () {
        params[$(this).attr('name')] = $(this).val();
    });
    $('#querybar').find('select[name]').each(function () {
        params[$(this).attr('name')] = $(this).val();
    });
    return params;
}

function responseHandler(res) {
    return res.rows;
}
var $delbutton = $('#delbtn');
$delbutton.click(function () {
    var ids =  $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.cid;
    });
    if(ids.length==0){
    alert("请选择数据");
    return;
    }
    if(confirm("确认删除吗?")){

    $.ajax({
   url:'/admin/DeleteSample?cid='+ ids.join(','),
   data:{
     json:JSON.stringify(ids)
   },
   dataType:'json',
   success:function(data) {
	    $table.bootstrapTable('remove', {
            field: 'cid',
            values: ids
        });
    }
});
    }

});