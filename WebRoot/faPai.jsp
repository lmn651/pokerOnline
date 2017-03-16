<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'faPai.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/faPai.css">
<script src="js/jquery-3.1.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/chuPai.js" type="text/javascript" charset="utf-8"></script>
</head>

<body background="images/bg.jpg" >


	<div style="position:absolute;top:20px; left:35%;">
		<div style="float:left;">
			<img src="images/poke/bg.png" name="dipai">
		</div>
		<div style="float:left; margin-left:10px;">
			<img src="images/poke/bg.png" name="dipai">
		</div>
		<div style="float:left; margin-left:10px;">
			<img src="images/poke/bg.png" name="dipai">
		</div>
	</div>
	<!-- 将牌桌分为三个区域[left,right,middle] -->
	<div class="paizhuo">
		<div name="location${(weizhi+3-1)%3 }" class="quyu">
<!-- 			<img name="zhizhen${(weizhi+3-1)%3 }" src="images/other/zhizhen.jpg" class="zhizhen"/> -->
		</div>
		<div name="location${weizhi }" class="quyu">
<!-- 			<img name="zhizhen${weizhi }" src="images/other/zhizhen.jpg" class="zhizhen"/> -->
		</div>
		<div name="location${(weizhi+1)%3 }" class="quyu">
<!-- 			<img name="zhizhen${(weizhi+1)%3 }" src="images/other/zhizhen.jpg" class="zhizhen" /> -->
		</div>
	</div>
	<!-- 存放隐藏的牌路径 -->
	<c:forEach items="${diPaiList }" var="path" varStatus="i">
	<input type="hidden" name="dipai${i.count }src" value="${path }"/>
	</c:forEach>
			
	<div style="position:absolute; left: 550px; top: 450px;">
		<input type="button" value="不要" name="buchu${weizhi }" class="anniu" onclick="chupai(this)" /> <input type="button" value="出牌" name="chu${weizhi }"  class="anniu" onclick="chupai(this)" />
			<input type="button" value="抢地主" name="qiang${weizhi }" onclick="chupai(this)" class="anniu"/> 
			<input type="button" value="不抢" name="qiang${weizhi }" onclick="chupai(this)"  class="anniu"/>
	</div>
	<!-- 当前玩家的位置 -->
	<input type="hidden" name="weizhi" value="${weizhi }"/>
	
	<%-- 隐藏的玩家的牌数用于显示背景牌 --%>	
<!-- 	<c:forEach items="${paiShu }" var="n" varStatus="i"> -->
<!-- 		<input type="hidden" value="${n }" name="paiShu${i.count }"/> -->
<!-- 	</c:forEach> -->
<%--存放左侧玩家的手牌数 --%>
	<div class="zuoPaiShu">
	<input type="text" value="50"/>
	</div>
	<%--存放右侧玩家的手牌数 --%>
	<div class="youPaiShu">
		<input type="text" value="50"/>
	</div>
	<div name="shouPai">
	</div>
	<!-- 左边的牌为当前的位置+3-1再对3取余 -->
	<div name="zuoBei">
	
	</div>
	<!-- 右边的位置为当前位置+1再对3取余  -->
	<div name="youBei">
	
	</div>
	
</body>

<script type="text/javascript">
	//记录当前的轮数
	count=0;
	//记录当前有几个人不要,有一个人不要buyao就加1
	buyao=0;
	//牌起立的状态为true
	var paiState = false;
	//对牌局进行初始化
	changedShouPai();
	changedBeiPai(17,17);
	//每当牌数发生变化时执行这个函数,只更新手牌
	function changedShouPai() {
		$.post("<%=request.getContextPath()%>/shouPai.do",function(msg){
			//得到从服务器session中传回来的牌,左边的牌数在最前面,右边的牌数在最后面
			//轮盘数也要传回来
			var shouPai=msg.split("link");
			//var zuoPaiShu=shouPai[0];
			//var youPaiShu=shouPai[shouPai.length-1];
			$("div[name='shouPai']").html("");
			//var shouPai="";
			for(var i=0;i<shouPai.length-1;i++){
				$("div[name='shouPai']").append("<img src='"+shouPai[i]+"' onclick='xuanZhong(this)' name='shouPai' style='position:absolute; top:500px; left:500px;'/>");
			}	
		})
	}
	
	//显示改变的背景牌
	function changedBeiPai(zuoPaiShu,youPaiShu){
		//显示左右两侧的背景牌
		//左
		$("div[name='zuoBei']").html("");
		for(var i=0;i<zuoPaiShu;i++){
			var top=150+i*10;
			$("div[name='zuoBei']").append("<img src='images/poke/bg.png' name='zuobei' style='position:absolute; top:"+top+"px; left:100px;'/>");
		}
		//右
		$("div[name='youBei']").html("");
		for(var i=0;i<youPaiShu;i++){
			var top=150+i*10;
			$("div[name='youBei']").append("<img src='images/poke/bg.png' name='youbei' style='position:absolute; top:"+top+"px; left:1120px;'/>");
		}
	}
	
	
	//把手牌发送回服务器中玩家的session中
	function sendPai(shoupai){
		$.ajax({
		url:"${pageContext.request.contextPath }/paiAct.do",
		type:"post",
		data:"path="+shoupai,
		dataType:"text",
		success:function(msg){},
		async:false,
		error:function(){},
		cache:false
		})
	}

	//使手牌时刻保持紧凑
	window.setInterval(function() {
		var ims = $("img[name='shouPai']");
		$(ims).each(function(index, element) {
			var old = 500 + index * 15;
			$(element).css("left", old + "px");
		})
	}, 100)

	//监测当前是否有牌处于起立状态
	window.setInterval(function() {
		var ims = $("img[name='shouPai']");
		for (var i = 0; i < ims.length; i++) {
			if (ims[i].style.top == "480px") {
				paiState = true;
				//console.log("起立");
				return;
			}
			paiState = false;
			//console.log("左下");
		}
	}, 100);
	
	function xuanZhong(eventObj) {
		if ($(eventObj).css("top") == "500px") {
			$(eventObj).css("top", "480px");
		} else if ($(eventObj).css("top") == "480px") {
			$(eventObj).css("top", "500px");
		}
	}

	$("input").hide();
	$("input[name='qiang0']").show();

	function panDuanLunPan() {
		var a=count%3;	
		if(count<3){
			$("input").hide();
			$("input[name='qiang"+a+"']").show();
			return ;
		}
		$("input").hide();
		$("input[name='chu"+a+"']").show();
		if(buyao!=2){
			$("input[name='buchu"+a+"']").show();
		}else{
			aa=$("input[name='weizhi']").val();
			$("div[name=location"+aa+"]").html("");
		}
	}
	
 	function showDiPai(){
 		var ims=$("img[name='dipai']")
			$(ims).each(function(index,element){
				src=$("input[name='dipai"+(index+1)+"src']").val();
				$(element).prop("src",src);
			})
 	}
 
	function chupai(eventObj) {
		//出牌向服务器发送的牌路径
		var paiPath="";
		//先得到当前玩家的位置
		var weizhi=$("input[name='weizhi']").val();
		//拼当前起立的牌的路径
		if (eventObj.value == "抢地主") {
			buyao=2;
			//lable1代表抢地主
			paiPath="label1";
			//根据当前count对3取余的值可判断是哪个玩家抢的地主
			count = count + 3;
			//得到抢地主的玩家,即位置
			//把抢地主的玩家的位置拼到向服务器请求字符串的首位
			var shoupai=weizhi+"link";
			//展示底牌,并分给地主
			showDiPai();
			var ims=$("img[name='shouPai']");
			//alert("手牌的数量"+ims.length);
			$(ims).each(function(index,element){
				shoupai+=$(element).prop("src")+"link";
			})
			var ims=$("img[name='dipai']")
			//alert("底牌的数量"+ims.length);
			$(ims).each(function(index,element){
				shoupai+=$(element).prop("src")+"link";
			})
			sendPai(shoupai);
		} else if(eventObj.value=="不抢"){
			count=count+1;
			//label2代表不抢
			paiPath="label2";		
		}else if(eventObj.value=="不要"){
			//label3代表不抢
			buyao=buyao+1;
			count=count+1;
			 paiPath="label3";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		}
	 	if (paiState && eventObj.value=="出牌") {
			var ims = $("img[name='shouPai']");
			$(ims).each(function(index, element) {
				if ($(element).css("top") == "480px") {
					paiPath += $(element).prop("src") + "link";
				}
			})
			//出牌判定
			if(judge(paiPath)==false){
				return ;
			}
			buyao=0;
	 		count=count+1;
	 		
		} else {
			//没有牌处于起立状态
		}
		$.ajax({
		url:"${pageContext.request.contextPath }/paiChuLi.do",
		type:"post",
		data:{weizhi:weizhi,count:count,paiPath:paiPath,buyao:buyao},
		dataType:"text",
		success:function(msg){},
		async:false,
		error:function(){},
		cache:false
		})
		changedShouPai();
	}
	//得到左右两侧的牌数,并显示出来
// 	function showPaiShu(zuoPaiShu,youPaiShu){
// 		$("input[name=zuoPaiShu]").val(zuoPaiShu);
// 		$("input[name=youPaiShu]").val(youPaiShu);
// 	}
	//根据传入的牌路径以及牌位置,将牌画到牌桌上
	function drawPai(jieguo,paiWeiZhi){
		$("div[name=location"+paiWeiZhi+"]").html("");
		for (var i = 0; i < jieguo.length - 5; i++) {
			if (jieguo[i] != null || jieguo[i] != "") {
				var left = 5 + 15 * i;
				$("div[name=location"+paiWeiZhi+"]").append("<img src='"+jieguo[i]+"' name='zhuo' style='position:absolute; margin-left:"+left+"px;'/>");
			}
		}
		var zuoweizhi=(parseInt(paiWeiZhi)+3-1)%3;
		$("div[name=location"+zuoweizhi+"]").html("");
	}
	
	function drawWord(label,paiWeiZhi){
		$("div[name=location"+paiWeiZhi+"]").html("");
		if(label=="label1"){
			label="抢地主";
		}else if(label=="label2"){
			label="不抢";
		}else if(label=="label3"){
			label="不要";
		}
		$("div[name=location"+paiWeiZhi+"]").append("<span>"+label+"</span>");
		var zuoweizhi=(parseInt(paiWeiZhi)+3-1)%3;
		//$("div[name=location"+zuoweizhi+"]").html("");
	}
	
	 //在牌桌上显示已出的牌
	window.setInterval(
		function() {
			var zuoPaiShu="";
			var youPaiShu="";
			//用来记录用户的操作  不抢  抢地主  不要
			var label=""; 
			$.ajax({
			url:"${pageContext.request.contextPath }/refresh.do",
			type:"post",
			data:"",
			dataType:"text",
			success:function(msg){
				var jieguo = msg;
				if(jieguo==""){
					//alert("空");
				}else{
					jieguo = jieguo.split("link");
					//没有牌用户也没有点击按钮的情况下
					if(jieguo.length==1){
						count=parseInt(jieguo);
					}
					//没有出牌的情况下传回来的有countlinkpaiPath(label)linkpaiWeiZhilinkbuyao,所以split的结果的length为4
					else if (jieguo.length == 4) {
						count = parseInt(jieguo[0]);		
						label = jieguo[1];
						paiWeiZhi= parseInt(jieguo[2]);
						buyao=parseInt(jieguo[3]);		
						//在牌桌上根据用户的操作写文字
						drawWord(label,paiWeiZhi);		
						}
						//出牌的情况,则传buyao变量
					else {
						//根据paiWeiZhi中的值[0,1,2]将牌显示在不同的位置
						buyao=parseInt(jieguo[jieguo.length-1]);
						paiWeiZhi=jieguo[jieguo.length-2];
						youPaiShu = parseInt(jieguo[jieguo.length - 3]);
						zuoPaiShu = parseInt(jieguo[jieguo.length - 4]);
						count = parseInt(jieguo[jieguo.length - 5]);
						//$(".paizhuo").html("");
						//根据传入的牌路径数组以及牌的位置,出牌
						drawPai(jieguo,paiWeiZhi);
						//更新两侧玩家的手牌数
						changedBeiPai(zuoPaiShu,youPaiShu);
						}
					if(count>=3){
						showDiPai();
					}
					panDuanLunPan();
// 					if(zuoPaiShu!=""&&youPaiShu!=""){
//						把左右两个玩家的牌数显示到两侧
// 						showPaiShu(zuoPaiShu,youPaiShu);
// 					}
				}
			},
			async:false,
			error:function(){},
			cache:false
				})
		}, 100) 
		
</script>
</html>