<script type="text/javascript">
	$(function(){
		function aaddd() {
	var treeaa = '<ul id="bbb" class="easyui-tree wu-side-tree"><li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="temp/layout-2.html" iframe="0">菜单导航</a></li></ul>';
	return treeaa;
	}

$('panel-tree-title-id').accordion('add', {
	id:'aaaddd',
	title: '快捷菜单',
	iconCls :'icon-application-cascade',
	style:'padding:5px;',
	selected: true,
	content: aaddd()
});


	});
</script>