<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	page {
		background-color: #F8F8F8;
	}
	
	@media (min-width: 768px) {
	    .content {
	        max-width: 360px;
	        margin: auto;
	    }
	}
</style>
