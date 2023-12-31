let index = {
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		}),
		$("#btn-delete").on("click",()=>{
			this.deleteById();
		}),
		$("#btn-update").on("click",()=>{
			this.update();
		}),
		$("#btn-reply-save").on("click",()=>{
			this.replySave();
		})
		;
	},
	save:function(){
		let data={
			title:$("#title").val(),
			content:$("#content").val(),
		};
		
			//alert('heyheyhey');
		$.ajax({
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(){
			alert("글쓰기가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	deleteById:function(){
		let id=$("#id").text();

		
		//alert('heyheyhey');
		$.ajax({
			type:"DELETE",
			url:"/api/board/"+id,
		}).done(function(){
			alert("삭제가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	 update:function(){
		let id=$("#id").val();
		let data={
			title:$("#title").val(),
			content:$("#content").val(),
		};
		
			//alert('heyheyhey');
		$.ajax({
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(){
			alert("수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	replySave:function(){
		let data={
			content:$("#reply-content").val(),
			userId:$("#userId").val(),
		 	boardId:$("#boardId").val()
		};
		
		
		
		console.log(data);
		
		$.ajax({
			type:"POST",
			url:`/api/board/${data.boardId}/reply`,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(){
			alert(`/board/${data.boardId}`);
			alert(`/api/board/${data.boardId}/reply`);
			location.href=`/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();