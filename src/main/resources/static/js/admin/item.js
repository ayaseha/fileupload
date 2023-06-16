/**
 * 
 */

function tempUpload(fileTag) {
	var img = $(fileTag)[0].files[0];
	var formData = new FormData();

	//Controller의 MultipartFile 매개변수와 일치하게 MultipartFile tempImg
	formData.append("tempImg", img);

	var target = $(fileTag);
	//*
	//csrf적용시 
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	//var target=$(this);

	$.ajax({
		beforeSend: function(xhr) {//csrf적용시 
			xhr.setRequestHeader(header, token);
		},
		url: "/admin/item/temp",
		type: "post",
		data: formData,
		contentType: false,
		processData: false,
		success: function(map) {
			console.log("이미지경로:" + map);
			target.parents(".img-wrap").find("span")
				.css("background-image", `url(${map.url})`)
			target.parents(".img-wrap").siblings(".bucket-key")
				.val(map.bucketKey)
			target.parents(".img-wrap").siblings(".org-name")
				.val(map.orgName)
		}
	})
	//*/
}