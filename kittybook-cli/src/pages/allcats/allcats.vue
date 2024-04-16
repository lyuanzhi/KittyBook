<template>
	<view class="content">
		<block v-for="(value,index) in allCatsList" :key="index">
			<view class="cats" hover-class="catsHover" @click="toCatDetail(value.id)">
				<image class="catImgIcon" :src="value.avatarUrl!=null?value.avatarUrl:'../../static/avatarDefault.png'" mode=""></image>
				<view class="catName">{{value.name}}</view>
			</view>
			<view class="line"></view>
		</block>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				allCatsList: [],
				pageIndex: 1,
				lock: false
			}
		},
		mounted() {
			this.getAllCats(this.pageIndex);
		},
		onReachBottom() {
			this.pageIndex++;
			this.getAllCats(this.pageIndex);
		},
		methods: {
			toCatDetail(id) {
				uni.navigateTo({
					url: '/pages/catdetail/catdetail?id=' + id
				});
			},
			getAllCats(pageIndex) {
				if (this.lock) {
					return;
				}
				this.lock = true;
				uni.request({
					url: this.$serverUrl + '/kitty/selectALLByPage',
					method: 'POST',
					data: {
						page: this.pageIndex
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200) {
							if (pageIndex > res.data.data.total) {
								uni.showToast({
									icon: 'none',
									title: "There's no more~",
									duration: 1000
								});
								//this.lock = false; 这里故意不解锁
								return;
							}
							let allCats = {};
							for (let i = 0; i < res.data.data.rows.length; ++i) {
								allCats = {
									"id": res.data.data.rows[i].id,
									"name": res.data.data.rows[i].name,
									"avatarUrl": res.data.data.rows[i].avatarUrl,
								};
								this.allCatsList.push(allCats);
							}
						}
						this.lock = false;
					},
					fail: res => {
						setTimeout(() => {
							this.lock = false;
						}, 4000);
					}
				});
			}
		}
	}
</script>

<style scoped>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.cats {
		display: flex;
		flex-direction: row;
		align-items: center;
		width: 100%;
	}

	.catsHover {
		background: #e6e6e6;
	}

	.catImgIcon {
		width: 30px;
		height: 30px;
		margin: 10px 10px 10px 35px;
		border-radius: 50%;
	}

	.catName {
		font-size: 13px;
	}

	.line {
		background-color: #dbdbdb;
		height: 1px;
		width: 70%;
		margin-left: 30px;
	}
</style>
