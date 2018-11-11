$(document).ready(function(){
$('#password-modal-form').bootstrapValidator(
    {
        message : 'This value is not valid',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },

        fields : {

            pass : {
                group : '.controls',
                validators : {
                    notEmpty : {
                        message : '密码不能为空'
                    },
                    stringLength : {
                        min : 6,
                        max : 30,
                        message : '密码长度必须大于6小于30'
                    }
                }
            },
            confirm : {
                group : '.controls',
                validators : {
                    notEmpty : {
                        message : '密码不能为空'
                    },
                    identical : {
                        field : 'pass',
                        message : '二次密码不一致'
                    }
                }
            }
        }
    });
$("#subpass").click(function(e){
if($('#password-modal-form').data('bootstrapValidator').isValid()){
    var $passsobj = document.getElementById('pass');
	var passstr='password='+$passsobj.value;
     $.ajax({
                url: "/ChangePassword",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                type: "POST",
                data : passstr,
                success: function(data){
                    alert("密码修改成功");
                    $("#password-modal-form").data('bootstrapValidator').resetForm(true);
                    $("#ChangePasswordModal").modal('hide');
                },
                error:function(){
                    alert("密码修改失败");
                    $("#password-modal-form").data('bootstrapValidator').resetForm(true);
                    $("#ChangePasswordModal").modal('hide');
                }
                });
        }else{
        alert("校验失败");
        }

            });
 });
