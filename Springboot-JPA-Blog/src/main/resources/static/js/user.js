let index = {
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		}),
		$("#btn-update").on("click",()=>{
			this.update();
		});
	},
	save:function(){
		let data={
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
		};
		//	alert('heyheyhey');
		
		//ajax호출시 default가 비동기 호출
		//ajax가 통신을 성공하고 나서 json을 리턴해주면 서버가 자동으로 자바스크립트 오브젝트로 변환
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), //htttp body데이터
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(JSON이라면)=> 자바스크립트 object로 변환해줌
		}).done(function(resp){
			if(resp.status===500){
				alert("회원가입실패")
			}
			else{
				alert("회원가입성공");
			}
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
	,
	update:function(){
		let data={
			id:$("#id").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
		};
		
		$.ajax({
			type:"PUT",
			url:"/user/",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json" 
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();