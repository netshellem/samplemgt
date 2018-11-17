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
                        //alert("错误");
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
            searchable: true,
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
            searchable: true,
            sortable: false
        },
        {
            field: 'model',
            title: '版师',
            searchable: true,
            sortable: false
        },
        {
            field: 'sample',
            title: '样衣师',
            searchable: true,
            sortable: false
        },
        {
            field: 'origin',
            sortable: true,
            searchable: false,
            title: '产地'
        },
        {
            field: 'copy',
            sortable: true,
            searchable: false,
            title: '复制'
        },
        {
            field: 'status',
            sortable: true,
            searchable: false,
            sorter: function(a,b){return a.localeCompare(b)},
            title: '状态'
        },
        {
            field: 'start_date',
            title: '入库时间',
            sortable: true,
            searchable: false,
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
            title: '客户',
            searchable: true
        },
        {
            field: 'end_date',
            sortable: true,
            searchable: false,
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
            title: '出库时间'

        },
        {
            field: 'comment',
            searchable: false,
            title: '备注'
        }]


	});
	//$('#addbtn').click(function (){window.location.href="add.jsp";});
});
var $table = $('#table');
var $ok = $('#ok');
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
