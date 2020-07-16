var selRoleType;
var roleType;
var btnSubmit;

$(function(){
    selRoleType = $("#selRoleType");
    roleType = $("#roleType");
    btnSubmit = $("#btnSubmit");

    btnSubmit.on("click", function(){
        add();
    })

})

function add(){
    if(confirm("确定添加乘客吗？")){
        roleType.val(selRoleType.get(0).selectedIndex);
        $("#passengerForm").submit();
    }
}