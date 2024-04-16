<template>
	<view class="content">
		<view class="content" v-if="showPage">
			<image :src="catInfo.imgUrl!=null?catInfo.imgUrl:'../../static/imgDefault.jpg'" mode="aspectFill" class="catImg"></image>
			<view style="font-weight: bold;font-size: 20px;margin-top: 10px;width: 85%;">{{catInfo.name}}</view>
			<view style="width: 85%;">
				<view class="inner">
					<view class="titleText" v-if="catInfo.hairColor!=null">Hair Color</view>
					<view>{{catInfo.hairColor}}</view>
				</view>
				<view class="inner" v-if="catInfo.kittySex!=null">
					<view class="titleText">Sex</view>
					<view>{{catInfo.kittySex}}</view>
				</view>
				<view class="inner" v-if="catInfo.kittyHealth!=null">
					<view class="titleText">Health</view>
					<view>{{catInfo.kittyHealth}}</view>
				</view>
				<view class="inner" v-if="catInfo.kittySterilization!=null">
					<view class="titleText">Sterilization</view>
					<view>{{catInfo.kittySterilization}}</view>
				</view>
				<view class="inner" v-if="catInfo.kittySterilizationTime!=null">
					<view class="titleText">Sterilization Time</view>
					<view>{{catInfo.kittySterilizationTime}}</view>
				</view>
				<view class="inner" v-if="catInfo.kittyBirthday!=null">
					<view class="titleText">Birthday</view>
					<view>{{catInfo.kittyBirthday}}</view>
				</view>
				<view class="inner" v-if="catInfo.kittyCharacter!=null">
					<view class="titleText">Character</view>
					<view>{{catInfo.kittyCharacter}}</view>
				</view>
				<view class="inner" v-if="catInfo.firstSeen!=null">
					<view class="titleText">First Seen</view>
					<view>{{catInfo.firstSeen}}</view>
				</view>
				<view style="width:100%;margin-top: 20px;" v-if="catInfo.kittyAppearance!=null">
					<view class="titleText">Appearance</view>
					<view>{{catInfo.kittyAppearance}}</view>
				</view>
			</view>
			<view style="width:100%;margin-top:30px;margin-bottom: 100px;">
				<view class="content">
					<view class="flexOuter">
						<view class="flex">
							<view :class="{heartNum1:catInfo.heartNum==0,heartNum2:catInfo.heartNum!=0}" @click="clickHeart"></view>
							<view class="text">{{catInfo.heartNum}}</view>
						</view>
						<view class="flex">
							<view class="commentNum" @click="to250"></view>
							<view class="text">{{commentShowNum}}</view>
						</view>
					</view>
					<view class="title">Comments</view>
					<view v-if="commentList.length==0" class="noComment"></view>
					<view style="width: 100%;">
						<block v-for="(value,index) in commentList" :key="index">
							<view v-if="value.status">
								<view style="position:relative;">
									<view class="line"></view>
									<view style="margin-left: 80px;margin-right: 80px;">
										<view class="text1">{{value.name}}</view>
										<view class="text2" @click="changePlaceholder(value.name, value.id)">{{value.content}}</view>
										<view class="text3">{{checkDateTime(value.dateTime,value.date)}}</view>
										<view class="avatar"></view>
										<view class="likeBox">
											<view class="like" @click="clickLike(value.id)"></view>
											<view class="likeNum">{{value.likeNum}}</view>
										</view>
									</view>
								</view>
								<block v-for="(value1,index1) in value.children" :key="index1">
									<view style="position:relative;" v-if="value1.status">
										<view class="line1"></view>
										<view style="margin-left: 115px;margin-right: 80px;">
											<view class="text1">{{value1.name}}</view>
											<view class="text2" @click="changePlaceholder(value1.name, value.id)">Reply@{{value1.responseName}}:
												{{value1.content}}</view>
											<view class="text3">{{checkDateTime(value1.dateTime,value1.date)}}</view>
											<view class="avatar1"></view>
											<view class="likeBox">
												<view class="like" @click="clickLike(value1.id)"></view>
												<view class="likeNum">{{value1.likeNum}}</view>
											</view>
										</view>
									</view>
								</block>
							</view>
						</block>
					</view>
				</view>
			</view>
			<view :class="{bottomComment:true, displayNone:isShow, animated:true, fadeIn:true}">
				<!-- 这里不加:fixed="true"的话真机调试会出问题 -->
				<textarea class="textarea" :show-confirm-bar="false" :fixed="true" :placeholder="placeholderText" v-model="input"
				 maxlength="100" :cursor-spacing="20" />
				<view class="release" @click="release">Publish</view>
			</view>
		</view>
		<!-- <loading ref="loading"></loading> -->
	</view>
</template>

<script>
	// import loading from '@/components/wyb-loading/wyb-loading.vue'
	export default {
		data() {
			return {
				id: "",
				catInfo: {
					imgUrl: null,
					name: "",
					hairColor: "",
					kittySex: "",
					kittyHealth: "",
					kittySterilization: "",
					kittySterilizationTime: "",
					kittyBirthday: "",
					kittyCharacter: "",
					firstSeen: "",
					kittyAppearance: "",
					heartNum: 0
				},
				commentList: [],
				count: 0,
				input: "",
				isShow: true,
				commentNum: 0,
				commentShowNum: 0,
				placeholderText: "Please enter your comments",
				parentId: "none",
				responseName: "none",
				showPage:false,
				myScrollTop: 0
			}
		},
		// components: {
		// 	loading
		// },
		onPageScroll(e) {
			this.myScrollTop = e.scrollTop;
			if(e.scrollTop>100) {
				this.isShow = false;
			} else {
				this.isShow = true;
			}
		},
		onLoad(value) {
			// this.$refs.loading.showLoading();
			this.id = value.id;
			this.getCatInfo();
			this.getCommentList();
		},
		methods: {
			to250(){
				this.changePlaceholder('none', 'none');
				if(this.myScrollTop < 250){
					uni.pageScrollTo({
						scrollTop:250,
						duration: 300
					});
				}
			},
			release(){
				if(this.input == ""){
					uni.showToast({
						icon:"none",
						title:"You haven't commented yet~",
						duration:1000
					})
					return;
				}
				uni.request({
					url: this.$serverUrl + '/kitty/addComment',
					method: 'POST',
					data: {
						kittyId: this.id,
						parentId: this.parentId,
						name: "Meow Mate "+ (this.commentNum + 1),
						responseName: this.responseName,
						content: this.input,
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200 && res.data.data == "success") {
							this.getCommentList();
							this.input = "";
							this.placeholderText = "Please enter your comments";
							this.parentId = "none";
							this.responseName = "none";
							uni.showToast({
								icon: 'none',
								title: 'Comment success~',
								duration: 1000
							});
						}
					},
					fail: res => {
						uni.showToast({
							icon: 'none',
							title: 'Comment fail~',
							duration: 1000
						});
					}
				});
			},
			changePlaceholder(name, id){
				if(name == "none" && id == "none"){
					this.placeholderText = "Please enter your comments";
				}else{
					this.placeholderText = "Reply @" + name;
				}
				this.responseName = name;
				this.parentId = id;
				this.input = "";
			},
			addLikeNum(commentId) {
				for (let i = 0; i < this.commentList.length; i++) {
					if (this.commentList[i].id == commentId) {
						if (this.commentList[i].likeLock == false) {
							this.commentList[i].likeNum++;
							this.commentList[i].likeLock = true;
							return this.commentList[i].likeNum;
						}
						return "likeLocked";
					}
					for (let j = 0; j < this.commentList[i].children.length; j++) {
						if (this.commentList[i].children[j].id == commentId) {
							if (this.commentList[i].children[j].likeLock == false) {
								this.commentList[i].children[j].likeNum++;
								this.commentList[i].children[j].likeLock = true;
								return this.commentList[i].children[j].likeNum;
							}
							return "likeLocked";
						}
					}
				}
			},
			clickLike(commentId) {
				var likeNum = this.addLikeNum(commentId);
				if (likeNum == "likeLocked") {
					uni.showToast({
						icon: 'none',
						title: 'Enough~',
						duration: 1000
					});
					return;
				}
				uni.request({
					url: this.$serverUrl + '/kitty/addLike',
					method: 'POST',
					data: {
						id: commentId,
						likeNum: likeNum
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200 && res.data.data == "success") {

						}
					},
					fail: res => {

					}
				});
			},
			checkDateTime(dateTime, date) {
				var dateCur = new Date();
				var dateCreate = new Date(dateTime);
				if (dateCur.getTime() - dateCreate.getTime() < 24 * 3600 * 1000) {
					if (dateCur.getTime() - dateCreate.getTime() < 3600 * 1000) {
						return Math.floor(1+(dateCur.getTime() - dateCreate.getTime()) / (1000 * 60)) + "min ago";
					} else {
						return Math.floor((dateCur.getTime() - dateCreate.getTime()) / (1000 * 60 * 60)) + "hour ago";
					}
				} else {
					return date;
				}
			},
			getCatInfo() {
				uni.request({
					url: this.$serverUrl + '/kitty/selectKittyInfo',
					method: 'POST',
					data: {
						id: this.id
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200) {
							this.catInfo = res.data.data;
						}
						// this.$refs.loading.hideLoading();
						this.showPage = true;
					},
					fail: res => {
						setTimeout(() => {
							// this.$refs.loading.hideLoading();
							this.showPage = true;
						}, 4000);
					}
				});
			},
			clickHeart() {
				if (this.catInfo.heartNum == undefined) {
					return;
				}
				if (this.count == 1) {
					uni.showToast({
						icon: 'none',
						title: 'Enough~',
						duration: 1000
					});
					return;
				}
				this.catInfo.heartNum++;
				this.count++;
				uni.request({
					url: this.$serverUrl + '/kitty/addHeart',
					method: 'POST',
					data: {
						id: this.id,
						heartNum: this.catInfo.heartNum
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200 && res.data.data == "success") {

						}
					}
				});
			},
			getCommentList() {
				uni.request({
					url: this.$serverUrl + '/kitty/getComment',
					method: 'POST',
					data: {
						kittyId: this.id
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						if (res.data.code == 200) {
							this.commentList = res.data.data;
							this.commentNum = 0;
							this.commentShowNum = 0;
							for (let i = 0; i < this.commentList.length; i++) {
								this.commentList[i].likeLock = false;
								this.commentNum++;
								if(this.commentList[i].status == 1){
									this.commentShowNum++;
								}
								for (let j = 0; j < this.commentList[i].children.length; j++) {
									this.commentList[i].children[j].likeLock = false;
									this.commentNum++;
									if(this.commentList[i].children[j].status == 1){
										this.commentShowNum++;
									}
								}
							}
						}
					},
					fail: res => {

					}
				});
			}
		}
	}
</script>

<style scoped>
	@import url('../../common/animate.min.css');
	
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.catImg {
		margin: 10px;
		border-radius: 10px;
		width: 85%;
	}

	.inner {
		width: 50%;
		display: inline-block;
		margin-top: 20px;
	}

	.titleText {
		font-size: 13px;
		color: #949494;
	}


	.title {
		width: 85%;
		font-size: 16px;
		font-weight: bold;
		letter-spacing: 2px;
		margin-top: 20px;
		margin-bottom: 10px;
	}

	.flexOuter {
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
	}

	.flex {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.heartNum1 {
		width: 30px;
		height: 30px;
		background: url(../../static/heartNull.png) center/100% 100% no-repeat;
	}

	.heartNum2 {
		width: 30px;
		height: 30px;
		background: url(../../static/heart.png) center/100% 100% no-repeat;
	}

	.commentNum {
		background: url(../../static/comment.png) center/100% 100% no-repeat;
		width: 30px;
		height: 30px;
	}

	.share {
		background: url(../../static/share.png) center/100% 100% no-repeat;
		width: 30px;
		height: 30px;
	}

	.text {
		font-size: 12px;
		color: #2c2c2c;
		margin-top: 5px;
	}

	.line {
		margin-top: 10px;
		margin-bottom: 20px;
		margin-left: 6%;
		width: 94%;
		height: 1px;
		background-color: #dbdbdb;
	}

	.line1 {
		margin-top: 10px;
		margin-bottom: 18px;
		margin-left: 18%;
		width: 82%;
		height: 1px;
		background-color: #dbdbdb;
	}

	.text1 {
		font-size: 13px;
		margin-top: 10px;
		color: #191e64;
	}

	.text2 {
		font-size: 11px;
		margin-top: 10px;
		letter-spacing: 1.1px;
	}

	.text3 {
		font-size: 11px;
		margin-top: 10px;
		color: #515151;
	}

	.avatar {
		border-radius: 50%;
		background: url(../../static/userAvatar.png) center/100% 100% no-repeat;
		width: 30px;
		height: 30px;
		position: absolute;
		left: 30px;
		top: 20px;
	}

	.avatar1 {
		border-radius: 50%;
		background: url(../../static/userAvatar.png) center/100% 100% no-repeat;
		width: 25px;
		height: 25px;
		position: absolute;
		left: 80px;
		top: 20px;
	}

	.likeBox {
		position: absolute;
		left: 82%;
		top: 40px;
		display: flex;
	}

	.like {
		background: url(../../static/like.png) center/100% 100% no-repeat;
		width: 30px;
		height: 30px;
	}

	.likeNum {
		font-size: 12px;
		line-height: 30px;
	}

	.noComment {
		width: 200px;
		height: 200px;
		background: url(../../static/noComment.png) center/100% 100% no-repeat;
	}

	.bottomComment {
		position: fixed;
		width: 100%;
		height: 60px;
		bottom: 0;
		background: #F8F8F8;
		box-shadow:0px 0px 10px 1px #414141;
		display: flex;
		justify-content: center;
	}
	
	.textarea{
		margin-top: 10px;
		width: 75%;
		height:28px;
		font-size: 13px;
		background: #f1f1f1;
		border-radius: 20px;
		padding: 5px 10px;
		box-sizing: border-box;
	}
	
	.release{
		margin-left: 5%;
		width: 10%;
		height: 28px;
		margin-top: 10px;
		line-height: 28px;
		color: #0A98D5;
		font-size: 12px;
	}
	
	.displayNone{
		display: none;
	}
</style>
