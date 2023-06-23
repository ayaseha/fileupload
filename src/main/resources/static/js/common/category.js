/**
 * 
 */
const CATE_EA=4;
$(function(){
	createCategoryTag(CATE_EA);
	category($(".cate-1"));
});


function createCategoryTag(depth){
	for(var i=1; i<= depth; i++){
		var tag=`
		<span class="cate-wrap">
        	<select name="categoryNo" class="cate-${i}" ${i==depth?'':'onchange="category(this)"'} >
        		<option value="0">${i}차카테고리</option>
        	</select>
        </span>
	`;
		$("#category").append(tag);
	}
	
}

function category(selectTag){
	var cateWrap=$(selectTag).parents(".cate-wrap");
	var cateNo=$(selectTag).val();
	var i=cateWrap.index();
	var target=(i==0&&cateNo==0)?cateWrap:cateWrap.next();
	if(cateNo==0 && i>0)return;
	//0이 아닌 경우 아래쪽 실행
	$.ajax({
		url:`/common/category-select/${cateNo}`,
		success:function(result){
			targetNextAllTagReset(target);
			target.find("select").append(result);
		}
	});
	
}


function targetNextAllTagReset(target) {
	var ti=target.index();
	$(".cate-wrap").each((i,el)=>{
		if(i>=ti)
			$(el).find(".cate").remove();
	});
}