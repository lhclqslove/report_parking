<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html >
<head>
    <script src="layuimini-2/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <title>点击地图获取地址和经纬度map，address，lng，lat</title>
    <meta name="robots" content="noindex, nofollow"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 将百度地图API引入，设置好自己的key -->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7a6QKaIilZftIMmKGAFLG7QT1GLfIncg"></script>
    <script type="text/javascript">
        window.onload=function (ev) {
            function validate() {
                var sever_add = document.getElementsByName('sever_add')[0].value;
                if (isNull(sever_add)) {
                    alert('请选择地址');
                    return false;
                }
                return true;
            }
            function getStringDate() {
                var date = new Date();
                var Y = date.getFullYear() + '-';
                var M = (date.getMonth()+1 > 10 ?   date.getMonth()+1 + '-':'0'+(date.getMonth()+1))+'-';
                var D = date.getDate() + ' ';
                var h = date.getHours() + ':';
                var m = date.getMinutes() + ':';
                var s = date.getSeconds();
                return Y+M+D+h+m+s;
            }

            //判断是否是空
            function isNull(a) {
                return (a == '' || typeof(a) == 'undefined' || a == null) ? true : false;
            }
            document.getElementById('allmap').style.display = 'block';
            document.getElementById('tp').value=getStringDate();
            // console.log(myDate.toLocaleTimeString());
            /*
                        document.getElementById('open').onclick = function () {
                            if (document.getElementById('allmap').style.display == 'none') {
                                document.getElementById('allmap').style.display = 'block';
                            } else {
                                document.getElementById('allmap').style.display = 'none';
                            }
                        }*/
            var map = new BMap.Map("allmap");
            var geoc = new BMap.Geocoder();   //地址解析对象
            var markersArray = [];
            var geolocation = new BMap.Geolocation();


            var point = new BMap.Point(116.331398, 39.897445);
            map.centerAndZoom(point, 12); // 中心点
            geolocation.getCurrentPosition(function (r) {
                if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                    var mk = new BMap.Marker(r.point);
                    map.addOverlay(mk);
                    map.panTo(r.point);
                    document.getElementById('lng').value = r.point.lng;
                    document.getElementById('lat').value = r.point.lat;
                    map.enableScrollWheelZoom(true);
                }
                else {
                    alert('failed' + this.getStatus());
                }
            }, {enableHighAccuracy: true})
            map.addEventListener("click", showInfo);
            //提交举报信息
            var submit_=function () {
                var formdata=new FormData();
                var rep=document.getElementById("report_person");
                formdata.append(rep.name,rep.value);
                 rep=document.getElementById("tp");
                formdata.append(rep.name,rep.value);
                rep=document.getElementById("phone");
                formdata.append(rep.name,rep.value);
                 rep=document.getElementById("lic");
                formdata.append(rep.name,rep.value);
                 rep=document.getElementById("des");
                formdata.append(rep.name,rep.value);
                 rep=document.getElementById("lng");
                formdata.append(rep.name,rep.value);
                 rep=document.getElementById("lat");
                formdata.append(rep.name,rep.value);
                rep=document.getElementById("pic11");
                formdata.append(rep.name,rep.files[0]);
                rep=document.getElementById("pic22");
                formdata.append(rep.name,rep.files[0]);
                rep=document.getElementById("pic33");
                formdata.append(rep.name,rep.files[0]);
               /* var data_=document.getElementById('key');
                var formdata=new FormData();

                for (const  key in data) {
                    formdata.append(key, data[key]);
                    console.log(key.toString());
                }*/
                $.post({
                        url:'/ReportupServlet',
                        data:formdata,
                        cache:false,
                        processData: false,
                        contentType: false,
                        success:function (result) {
                            if(result=="scuess"){
                                $.messager.alert('提示', "举报并通知成功。", 'info');
                            }else{
                                $.messager.alert('提示', "填写失败。", 'info');
                            }
                        }
                    }

                )


            }
            document.getElementById('bton').onclick=submit_;
            //清除标识
            function clearOverlays() {
                if (markersArray) {
                    for (i in markersArray) {
                        map.removeOverlay(markersArray[i])
                    }
                }
            }
            //地图上标注
            function addMarker(point) {
                var marker = new BMap.Marker(point);
                markersArray.push(marker);
                clearOverlays();
                map.addOverlay(marker);
            }
            //点击地图时间处理
            function showInfo(e) {
                document.getElementById('lng').value = e.point.lng;
                document.getElementById('lat').value =  e.point.lat;
                geoc.getLocation(e.point, function (rs) {
                    var addComp = rs.addressComponents;
                    var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
                    if (confirm("确定要地址是" + address + "?")) {
                        document.getElementById('allmap').style.display = 'none';
                        document.getElementById('sever_add').value = address;
                    }
                });
                addMarker(e.point);
            }
        }
    </script>


</head>
<body>
<%
    String mes = (String)request.getAttribute("message");         // 获取错误属性
    if(mes != null) {
%>
<script type="text/javascript" language="javascript">
    alert("${sessionScope.message}");
    console.log("ssss");

</script>
<%
    }
%>

<div class="main-div">
    <form method="post" action="" name="theForm" enctype="multipart/form-data" οnsubmit="return validate()">
        <table cellspacing="1" cellpadding="3" width="100%">
            <tr>
                <td class="label">地址</td>
                <td>
                    <input type='text' value='' name='sever_add' id='sever_add' readonly>
                    <input type='button' value='点击显示地图获取地址经纬度' id='open'>
                </td>
            </tr>
        </table>
    </form>
    <div id='allmap' style='width: 50%; height: 50%; position: absolute; display: none'></div>
    <br><br> <br><br> <br><br> <br><br> <br><br> <br><br> <br><br> <br><br> <br><br>
   < <from action="/ReportupServlet" method="post"  enctype="multipart/form-data" id='key'>
        名字
        <input type="text" name="report_person_name" id="report_person"/>
        <br><br>
        时间
        <input type='text' name='date' id='tp'>
        <br><br>
        电话
        <input type='text' name='report_person_tel' id='phone'>
        <br><br>
        车牌
        <input type='text' name='license' id='lic'>
        <br><br>
        描述
        <input type="text" name="description" id="des">
        <tr>
            <td class="label">经度</td>
            <td><input type="text" name="longitude" id="lng" value=""/>
            </td>
        </tr>
        <tr>
            <td class="label">纬度</td>
            <td><input type="text" name="latitude" id="lat" value=""/>
            </td>
        </tr>
        <br><br>
        <td>
            图片1
            <input type="file" name="pic1" id="pic11" />
        </td>
        <br/><br/>
        <td>
            图片2
            <input type="file" name="pic2" id="pic22"/>
        </td>
        <br/><br/>
        <td>
            图片3
            <input type="file" name="pic3" id="pic33"/>
        </td>
        <br/><br/>
        <input type="submit" value="提交举报" id="bton" name="sda"/>
    </from>
</div>

</body>
</html>