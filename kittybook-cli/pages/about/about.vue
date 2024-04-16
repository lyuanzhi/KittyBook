<template>
	<view class="content">
		<view class="title">About Us</view>
		<view class="icon"></view>
		<view style="width: 90%;margin-top: 30px;">
			<view style="font-weight: bold;font-size: 16px;">FIVE FREEDOMS</view>
			<view class="text1"><Strong>Freedom from Hunger and Thirst</Strong>{{" "}}by ready access to fresh water and diet to maintain health and vigor.</view>
			<view class="text1"><Strong>Freedom from Discomfort</Strong>{{" "}}by providing an appropriate environment including shelter and a comfortable resting area.</view>
			<view class="text1"><Strong>Freedom from Pain, Injury or Disease</Strong>{{" "}}by prevention or rapid diagnosis and treatment.</view>
			<view class="text1"><Strong>Freedom to Express Normal Behavior</Strong>{{" "}}by providing sufficient space, proper facilities and company of the animal's own kind.</view>
			<view class="text1"><Strong>Freedom from Fear and Distress</Strong>{{" "}}by ensuring conditions and treatment which avoid mental suffering.</view>
		</view>
		<view class="bottom">
			<view class="text2">04-05-2021</view>
			<view class="text2">lyuanzhi</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		onLoad() {

		},
		methods: {

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

	.title {
		font-size: 20px;
		font-weight: bold;
		padding: 15px;
	}

	.icon {
		margin: 10px;
		width: 30vw;
		height: 30vw;
		background: url(../../static/moon.png) center/100% 100% no-repeat;
		border-radius: 50%;
	}

	.text1 {
		margin-left: 10%;
		width: 80%;
		margin-right: 10%;
		margin-top: 10px;
		font-size: 12px;
		line-height: 24px;
	}
	
	.text2{
		margin: 5px;
		font-size: 10px;
		line-height: 10px;
	}

	.bottom {
		width: 90%;
		margin-top: 30px;
		margin-bottom: 30px;
		display: flex;
		flex-direction: column;
		align-items: flex-end;
	}
</style>
