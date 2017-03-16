//出牌判定,传入出牌的路径,返回布尔值
	function judge(chuPaiPath){
		daXiao=getDaXiao(chuPaiPath);
		//从大到小排序
		daXiao.sort(function(a,b){return a<b?1:-1});
		//先判断传入的牌路径有几张牌
		switch(daXiao.length){
			case 1:
				return judgeOne(daXiao);
			case 2:
				return judgeTwo(daXiao);
			case 3:
				return judgeThree(daXiao);
			case 4:
				return judgeFour(daXiao);
			case 5:
				return judgeFive(daXiao);
			case 6:
				return judgeSix(daXiao);
			case 7:
				return judgeSeven(daXiao);
			case 8:
				return judgeEight(daXiao);
			case 9:
				return judgeNine(daXiao);
			case 10:
				return judgeTen(daXiao);
			case 11:
				return judgeEleven(daXiao);
			case 12:
				return judgeTwelve(daXiao);
			case 13:
				return judgeThirteen(daXiao);
		}
	}
	
	//截取牌路径,获得牌的大小
	function getDaXiao(path){
		paths=path.split("link");
		var daXiao=[];
		for(var i=0;i<paths.length-1;i++){
			var n=paths[i].lastIndexOf("/");
			if(paths[i].length-n==8){
				daXiao[i]=parseInt(paths[i].substring(n+1, n+2));
			}else if(paths[i].length-n==9){
				daXiao[i]=parseInt(paths[i].substring(n+1, n+3));
				}
		}
		return daXiao;
	}
	
	//获得当前牌桌上的牌路径(带link),返回大小的数组
	function getDeskPai(){
		var path=""; 
		var ims=$("img[name='zhuo']")
		if($(ims).length==0){
			return true;
		}
		$(ims).each(function(index,element){
		path+=$(element).prop("src")+"link";
		})
		deskPai=getDaXiao(path);
		return deskPai;
	}
	
	//判断数组里的大小是否相等
	function isSame(daXiao){
		var tem=daXiao[0];
		for(var i=1;i<daXiao.length;i++){
			if(tem!=daXiao[i]){
				return false;
			}
		}
		return true;
	}
	//根据传进来的牌大小的数组(长度为2)判断该数组中是否为两张王
	function isTwoWang(daXiao){
		if((daXiao[0]==17&&daXiao[1]==16)||(daXiao[0]==16&&daXiao[1]==17)){
			return true;
		}
		return false;
	}
	//龙的判定,不是龙则返回false,是龙则返回json
	//size(1表示单龙,2表示双龙,3表示三龙),zhi表示最大的牌
	function judgeLong(daXiao){
		if(daXiao.length<5||daXiao[0]>14){
			return false;
		}
		var size=0;
		var msg={};
		//判断该数组中最大的重复数
		msg=judgeRepeat(daXiao);
		if(msg==false){
			//说明不重复
			size=1;
		}else{
			size=msg.kind;
		}
		if(daXiao.length<size*3){
			return false;
		}
		var tem=daXiao[0];
		for(var i=0;i<daXiao.length;i=i+size){
			if(tem==daXiao[i]){
				tem=tem-1;
			}else{
				return false;
			}
		}
		msg={size:size,zhi:daXiao[0]};
		return msg;
	}
	//判断数组中最大的重复数,如没有重复的则返回false,若有则返回json
	//size(2表示有最多有两个重复的,3.4以此类推)zhi表示重复的牌的大小
	//如果存在多个重复次数相同的元素,则zhi返回最大的元素
	//使用该函数前,必须对数组进行排序
	function judgeRepeat(daXiao){
		var msg={};
		var maxCount=1;
		var count=1;
		var tem=daXiao[0];
		var nowDaXiao=tem;
		for(var i=1;i<daXiao.length;i++){
			if(tem==daXiao[i]){
				count++;
				if(count>maxCount){
					maxCount=count;
					nowDaXiao=tem;
				}
			}else{
				count=1;
				tem=daXiao[i];
			}
		}
		if(maxCount==1){
			return false;
		}
		msg={size:maxCount,zhi:nowDaXiao};
		return msg;
	}
	
	//11张牌以上的普通判定
	function judgeOther(daXiao){
		
	}
		
	//判断玩家出的牌是否符合出牌规则
	//出1张牌
	function nengChuOnePai(){
		return true;
	}
	//出2张牌
	function nengChuTwoPai(daXiao){
		if(isSame(daXiao)==true){
			return true;
		}
		if(isTwoWang(daXiao)==true){
			return true;
		}
		return false;
	}
	//出3张牌,三张牌的大小必须相等
	function nengChuThreePai(daXiao){
		if(isSame(daXiao)==true){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 4张牌的小判定
	 */
	function nengChuFourPai(daXiao){
		//四个一样的,即为炸,返回大小,带zha
		var str=""
		if(isSame(daXiao)==true){
			str=daXiao[0]+"zha"
			return str;
		}
		//三带一,返回相同的大小,不带zha
		var count=1;
		var tem=daXiao[0];
		for(var i=1;i<daXiao.length;i++){
			if(tem==daXiao[i]){
				count=count+1;
				if(count==3){
					str=daXiao[i]+""
					return str;
				}
			}else{
				tem=daXiao[i];
				count=1;
			}
		}
		return false;
	}
	//5张牌的小判定,若不能出牌返回false,否则返回json
	//(kind为种类,1为四带一,2为三带二,3为龙)(zhi为大小,龙返回最大的值){kind: ,zhi:}返回有size属性的json的为龙
	function nengChuFivePai(daXiao){
		var msg={};
		//先判断是不是龙
		longmsg=judgeLong(daXiao);
		if(longmsg!=false){
			//得到龙的种类(单龙,双龙,三龙)
			msg.size=longmsg.size;
			msg.zhi=longmsg.zhi;
			msg.kind=3;
			return msg;
		}
		getmsg=judgeRepeat(daXiao);
		if(getmsg==false||getmsg.size==2){
			return false;
		}
		if(getmsg.size==4){
			msg.size=4;
			msg.kind=1;
			msg.zhi=getmsg.zhi;
			return msg;
		}
		//三带二
		var tem=getmsg.zhi;
		var temArr=[];
		var j=0;
		for(var i=0;i<daXiao.length;i++){
			if(tem!=daXiao[i]){
				temArr[j]=daXiao[i];
				j++;
			}
		}
		if(temArr[0]==temArr[1]){
			msg.kind=2;
			msg.size=3;
			msg.zhi=getmsg.zhi;
			return msg;
		}
		return false;
	}
	//6张牌的小判定
	//kind(1为3个3和3个4,2为4带一对,3为4带2单,4为单龙,5为双龙)
	//zhi标识最大的
	function nengChuSixPai(daXiao){
		var msg={};
		//先判断是不是龙
		var longmsg=judgeLong(daXiao);
		if(longmsg!=false){
			msg.zhi=longsize.zhi;
			if(longmsg.size==1){
				msg.kind=4;
			}
			msg.kind=5;
			return msg;
		}
		//判断有几个重复的
		var repeatmsg=judgeRepeat(daXiao);
		if(repeatmsg.size==4){
			//有四个重复的
			var size=0;
			var tem=repeatmsg.zhi;
			var arr=[];
			var j=0;
			for(var i=0;i<daXiao.length;i++){
				if(daXiao[i]!=tem){
					arr[j]=daXiao[i];
					j++;
				}
			}
			if(arr[0]==arr[1]){
				//说明是4带一对
				msg.kind=2;
				msg.zhi=repeatmsg.zhi;
			}else{
				//说明是4带2单
				msg.kind=3;
				msg.zhi=repeatmsg.zhi;
			}
			return msg;
		}else if(repeatmsg.size==3){
			var tem=repeatmsg.zhi;
			for(var i=0;i<daXiao.length;i++){
				if(tem!=daXiao[i]&&(tem-1)!=daXiao[i]){
					return false;
				}
			}
			msg.kind=1;
			msg.zhi=repeatmsg.zhi;
			return msg;
		}
		return false;
	}
	//7张牌的小判断
	//kind(1只有一种可能只能为龙)
	//zhi为最大的值
	function nengChuSevenPai(daXiao){
		var msg={};
		var longmsg=judgeLong(daXiao);
		if(longmsg!=false){
			msg.kind=1;
			msg.zhi=longmsg.zhi;
			return msg
		}
		return false;
	}
	//8张牌的小判断
	//kind(1为飞机带翅膀,2为单龙,3为双龙,4为4带2对)
	//zhi()
	function nengChuEightPai(daXiao){
		msg={};
		//先判断是否是龙
		var longmsg=judgeLong(daXiao);
		if(longmsg!=false){
			if(longmsg.size==1){
				msg.kind=2;
				msg.zhi=longmsg.zhi;
			}else if(longmsg.size==2){
				msg.kind=3;
				msg.zhi=longmsg.zhi;
			}
			return msg;
		}
		//判断是否为飞机带翅膀
		var repeatmsg=judgeRepeat(daXiao);
		if(repeatmsg.size==3){
			var tem=repeatmsg.zhi;
			var temArr=[];
			var j=0;
			for(var i=0;i<daXiao.length;i++){
				if(tem!=daXiao[i]){
					temArr[j]=daXiao;
					j++;
				}
			}
			temArr.sort(function(a,b){return a<b?1:-1});
			var repeatmsg2=judgeRepeat(temArr);
			if(repeatmsg2.size==3&&(repeatmsg2.zhi+1)==repeatmsg.zhi){
				msg.kind=1;
				msg.zhi=repeatmsg.zhi;
				return msg;
			}
		}else if(repeatmsg.size==4){
			//4带2对
			//数组都是排好序的,所以成对的两张牌是挨着的
			var tem=repeatmsg.zhi;
			var count=0;
			for(var i=0;i<daXiao.length;i++){
				if(tem!=daXiao[i]){
					tem=daXiao[i];
					count=count+1;
				}
			}
			if(count>2){
				msg.kind=4;
				msg.zhi=repeatmsg.zhi;
				return msg;
			}
		}
		return false;
	}
	//9张牌的小判定
	//kind(1为aaabbbccc,2为单龙)
	function nengChuNinePai(daXiao){
		msg={};
		//先判断是否是龙
		var longmsg=judgeLong(daXiao);
		if(longmsg!=false){
			msg.kind=2;
			msg.zhi=longmsg.zhi;
			return msg;
		}
		//判断是否为aaabbbccc
		var repeatmsg1=judgeRepeat(daXiao);
		if(repeatmsg1.size==3){
			msg.zhi=repeatmsg1.zhi;
			msg.kind=1;
			if(daXiao[3]==daXiao[4]&&daXiao[4]==daXiao[5]&&daXiao[6]==daXiao[7]&&daXiao[7]==daXiao[8]){
				return msg;
			}
		}
		return false;
	}
	//10张牌的小判定
	//1.飞机带一对,2.双龙,3.单龙
	function nengChuTenPai(daXiao){
		msg={};
		//先判断是否是龙
		var longmsg=judgeLong(daXiao);
		if(longmsg.size==2){
			//双龙
			msg.kind=2;
			msg.zhi=longmsg.zhi;
			return msg;
		}else if(longmsg.size==1){
			//单龙
			msg.kind=3;
			msg.zhi=longmsg.zhi;
			return msg;
		}
		var repeatmsg1=judgeRepeat(daXiao);
		if(repeatmsg1.size==3){
			var arr=[];
			var j=0;
			var tem=repeatmsg1.zhi;
			for(var i=0;i<daXiao.length;i++){
				if(daXiao[i]!=tem){
					arr[j]=daXiao[i];
					j++;
				}
			}
			var repeatmsg2=judgeRepeat(arr);
			if(repeatmsg2.size==3&&(repeatmsg2.zhi+1)==repeatmsg1.zhi){
				var arr2=[];
				var j2=0;
				var tem2=repeatmsg2.zhi;
				for(var i=0;i<arr.length;i++){
					if(tem2!=arr[i]){
						arr2[j2]=arr[i];
						j2++;
					}
				}
				var tem3=arr2[0];
				var count=0;
				for(var i=0;i<arr2.length;i++){
					if(tem3!=arr2[i]){
						tem3=arr2[i];
						count++;
					}
				}
				if(count==2){
					msg.kind=1;
					msg.zhi=repeatmsg1.zhi;
					return msg;
				}
			}
		}
	}
	//11张牌的小判定
	function nengChuElevenPai(daXiao){
		msg={};
		var longmsg=judgeLong(daXiao);
		if(longmsg!=false){
			msg.kind=1;
			msg.zhi=longmsg.zhi;
			return msg;
		}
		return false;
	}
	//12张牌的小判定
	//1.三个三的带三个单;2.双龙;3.单龙
	function nengChuTwelvePai(daXiao){
		msg={};
		var longmsg=judgeLong(daXiao);
		if(longmsg.size==2){
			msg.kind=2;
			msg.zhi=longmsg.zhi;
			return msg;
		}else if(longmsg.size==1){
			msg.kind=3;
			msg.zhi=longmsg.zhi;
			return msg;
		}
		//三个三的带三个单
		var repeatmsg1=judgeRepeat(daXiao);
		if(repeatmsg1.size==3){
			var arr1=[];
			var j1=0;
			var tem1=repeatmsg1.zhi;
			for(var i=0;i<daXiao.length;i++){
				if(tem1!=daXiao[i]){
					arr1[j1]=daXiao[i];
					j1++;
				}
			}
			var repeatmsg2=judgeRepeat(arr1);
			if(repeatmsg2.size==3&&(repeatmsg2.zhi+1)==repeatmsg1.zhi){
				var arr2=[];
				var j2=0;
				var tem2=repeatmsg2.zhi;
				for(var i=0;i<arr1.length;i++){
					if(tem2!=arr1[i]){
						arr2[j2]=arr1[i];
						j2++;
					}
				}
				var repeatmsg3=judgeRepeat(arr2);
				if(repeatmsg3.size==3&&(repeatmsg3.zhi+1)==repeatmsg2.zhi){
					msg.kind=1;
					msg.zhi=repeatmsg1.zhi;
					return msg;
				}
			}
		}
		return false;
	}
	//13张牌的小判定
	function nengChuThirteenPai(daXiao){
		
	}
	
	//其他数量牌的小判定 1.单龙;2.双龙
	function nengChuOtherPai(daXiao){
		msg={};
		var longmsg=judgeLong(daXiao);
		if(longmsg.size==2){
			msg.kind=2;
			msg.zhi=longmsg.zhi;
			return msg;
		}else if(longmsg.size==1){
			msg.kind=1;
			msg.zhi=longmsg.zhi;
			return msg;
		}
		return false;
	}
	
	//一张牌的总判定,传入出的牌的路径
	function judgeOne(daXiao){
		//首先判断玩家出的牌是否符合出牌规则
		if(nengChuOnePai()==false){
			return false;
		}
		//得到当前牌桌上的牌的大小的数组
		//若当前桌上的为空则返回getDeskPai函数返回true
		deskPai=getDeskPai();
		if(deskPai==true){
			//说明此时桌子上没有牌
			return true;
		}
		
		if(deskPai.length==1&&daXiao[0]>deskPai[0]){
			return true;
		}
		else{
			return false;
		}
	}
	
	//2张牌的总判定
	function judgeTwo(daXiao){
		if(nengChuTwoPai(daXiao)==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		if(isTwoWang(daXiao)==true){
			return true;
		}
		if(deskPai.length==2&&daXiao[0]>deskPai[0]){
			return true;
		}
		return false;
	}
	
	//3张牌的总判定
	function judgeThree(daXiao){
		if(nengChuThreePai(daXiao)==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		if(deskPai.length==3&&daXiao[0]>deskPai[0]){
			return true;
		}
		return false;
	}
	
	//4张牌的总判定
	function judgeFour(daXiao){
		msg=nengChuFourPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		//说明不是三带一就是炸
		msg=msg.split("zha");
		if(msg.length==2){
			//说明是炸
			zhi=parseInt(msg[0]);
			if(deskPai.length==4&&isSame(deskPai)==true&&zhi<deskPai[0]){
				//管不上比他大的炸
				return false;
			}else{
				//剩下的都能管上
				return true;
			}
		}else{
			//说明是三带一,三带一只能管三带一
			if(deskPai.length!=4){
				return false;
			}
			if(nengChuFourPai(deskPai).split("zha").length==2){
				return false;
			}
			deskZhi=parseInt(nengChuFourPai(deskPai));
			zhi=parseInt(msg);
			//先判断桌上的牌是不是炸
			if(isSame(deskPai)==true){
				return false;
			}
			if(zhi>deskZhi){
				return true;
			}
			return false;
		}
	}
	//5张牌的总判定
	function judgeFive(daXiao){
		var msg=nengChuFivePai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=5){
			return false
		}
		deskMsg=nengChuFivePai(deskPai);
		if(deskMsg.kind!=msg.kind){
			return false;
		}
		//1为四带一,2为三带二,3为龙
		if(msg.zhi>deskPai.zhi){
			return true;
		}
		return false;
	}
	
	//6张牌的总判定
	function judgeSix(daXiao){
		var msg=nengChuSixPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=6){
			return false;
		}
		deskmsg=nengChuSixPai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	
	//7张牌的总判定
	function judgeSeven(daXiao){
		var msg=nengChuSevenPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=7){
			return false;
		}
		deskmsg=nengChuSevenPai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	//8张牌的总判定
	function judgeEight(daXiao){
		var msg=nengChuEightPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=8){
			return false;
		}
		deskmsg=nengChuEightPai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	//9张牌的判定
	function judgeNine(daXiao){
		var msg=nengChuNinePai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=9){
			return false;
		}
		deskmsg=nengChuNinePai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	//10张牌的判定
	function judgeTen(daXiao){
		var msg=nengChuTenPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=10){
			return false;
		}
		deskmsg=nengChuTenPai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	//11张牌的判定
	function judgeEleven(daXiao){
		var msg=nengChuElevenPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=10){
			return false;
		}
		deskmsg     =nengChuElevenPai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	//12张牌的判定
	function judgeTwelve(daXiao){
		var msg=nengChuTwelvePai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=10){
			return false;
		}
		deskmsg=nengChuTwelvePai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	//13张牌的判定
	function judgeThirteen(daXiao){
		
	}
	
	//其他数量牌的判定
	function judgeOther(daXiao){
		var msg=nengChuOtherPai(daXiao);
		if(msg==false){
			return false;
		}
		deskPai=getDeskPai();
		if(deskPai==true){
			return true;
		}
		//判断是不是牌桌上是不是俩王
		if(deskPai.length==2&&isTwoWang(deskPai)==true){
			return false;
		}
		if(deskPai.length!=10){
			return false;
		}
		deskmsg=nengChuOtherPai(deskPai);
		if(deskmsg.kind==msg.kind&&msg.zhi>deskmsg.zhi){
			return true;
		}
		return false;
	}
	