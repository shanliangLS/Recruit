function $(id) {
    return document.getElementById(id);
}
$("form").addEventListener("submit",function (ev) {
    var job = $("job").value;
    var salary = $("salary").value;
    var location = $("location").value;
    var experience = $("experience").value;
    var job_info = $("job_info").value;
    if (job == "") {
        ev.preventDefault();
        alert("职位名称不能为空！");
        $("job").focus();
    }
    else if (salary == "") {
        ev.preventDefault();
        alert("年薪不能为空！");
        $("salary").focus();
    }
    else if (location == "") {
        ev.preventDefault();
        alert("工作地点不能为空！");
        $("location").focus();
    }
    else if (experience == "") {
        ev.preventDefault();
        alert("工作经验不能为空！");
        $("experience").focus();
    }
    else if (job_info == "") {
        ev.preventDefault();
        alert("职业描述不能为空！");
        $("job_info").focus();
    }
    else {
        alert("提交成功！");
        //上传数据
    }

});
