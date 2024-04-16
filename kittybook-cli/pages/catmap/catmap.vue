<template>
	<view class="content">
		<view class="search">
			<view class="iconCat"></view>
			<input type="text" placeholder="Please enter kitten's name" confirm-type="search" v-model="inputValue" class="input" @confirm="search">
		</view>
		<view style="width: 100%;">
			<view style="margin: 10px 10px 10px 20px;">Top</view>
			<block v-for="(value,index) in catTopFiveList" :key="index">
				<view class="cats" hover-class="catsHover" @click="toCatDetail(value.id)">
					<image class="catImgIcon" :src="value.avatarUrl!=null?value.avatarUrl:'../../static/avatarDefault.png'" mode=""></image>
					<view class="catName">{{value.name}}</view>
				</view>
				<view class="line"></view>
			</block>
			<view class="cats" hover-class="catsHover" @click="toAllCats">
				<image class="catImgIcon" src="../../static/avatarDefault.png" mode=""></image>
				<view class="catName">All Cats</view>
			</view>
			<view class="line"></view>
		</view>
		<view class="staticText">
			<view class="text">If you encounter a stray cat,{{" "}}<span style="font-weight: bold;">remember not to feed{{" "}}</span>it human food!</view>
			<view class="text"><span style="font-weight: bold;">No Chocolate, Grape, Leeks, Onions</span></view>
			<view class="text"><span style="font-weight: bold;">No Chocolate, Grape, Leeks, Onions</span></view>
			<view class="text"><span style="font-weight: bold;">No Chocolate, Grape, Leeks, Onions</span></view>
		</view>
		<loading ref="loading"></loading>
	</view>
</template>

<script>
	import loading from '@/components/wyb-loading/wyb-loading.vue'
	export default {
		data() {
			return {
				inputValue: "",
				catTopFiveList: [{
					name: "",
					avatarUrl: null,
					id: ""
				}, {
					name: "",
					avatarUrl: null,
					id: ""
				}, {
					name: "",
					avatarUrl: null,
					id: ""
				}, {
					name: "",
					avatarUrl: null,
					id: ""
				}, {
					name: "",
					avatarUrl: null,
					id: ""
				}],
				lock: false
			}
		},
		components: {
			loading
		},
		mounted() {
			this.getCatTopFive();
		},
		methods: {
			search() {
				if (this.lock) {
					return;
				}
				this.lock = true;
				uni.request({
					url: this.$serverUrl + '/kitty/selectKittyByName',
					method: 'POST',
					data: {
						name: this.inputValue
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200) {
							if (res.data.data == "kittyNotFound") {
								uni.showToast({
									icon: 'none',
									title: 'No kitten found~',
									duration: 1000
								});
							} else {
								this.toCatDetail(res.data.data);
								this.inputValue = "";
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
			},
			toCatDetail(id) {
				if (id == "") {
					return;
				}
				uni.navigateTo({
					url: '/pages/catdetail/catdetail?id=' + id
				});
			},
			getCatTopFive() {
				this.$refs.loading.showLoading();
				uni.request({
					url: this.$serverUrl + '/kitty/selectKittyTopFive',
					method: 'POST',
					success: res => {
						if (res.data.code == 200) {
							this.catTopFiveList = res.data.data;
							this.$refs.loading.hideLoading();
						}
					},
					fail: res => {
						setTimeout(() => {
							this.$refs.loading.hideLoading();
						}, 4000);
					}
				});
			},
			toAllCats() {
				uni.navigateTo({
					url: '/pages/allcats/allcats'
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

	.search {
		width: 100%;
		height: 70px;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.iconCat {
		width: 40px;
		height: 40px;
		background: url(../../static/cat.png) center/100% 100% no-repeat;
		margin-right: 5px;
		margin-left: 30px;
	}

	.input {
		margin-top: 20px;
		padding: 5px;
		height: 40px;
		width: 70%;
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
		margin-left: 70px;
	}

	.staticText {
		width: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20px;
		box-sizing: border-box;
	}

	.text {
		font-size: 12px;
		margin-bottom: 10px;
		/* letter-spacing: 2.5px; */
	}
</style>
