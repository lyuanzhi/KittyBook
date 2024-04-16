<template>
	<view>
		<view class="content" v-if="showPage">
			<view style="margin-top: 20px;width: 85%;font-weight: 600;font-size: 14px;">Default Ledger</view>
			<view class="total">
				<view class="flex" style="margin-bottom: 15px;">
					<view style="font-size: 14px;line-height: 35px;font-weight: 600;letter-spacing: 1.1px;">Balance</view>
					<view style="font-size: 26px;font-weight: 600;color: #de4d7e;">{{(totalPayIn-totalPayOut)<0?"-":""}} $
						{{(totalPayIn-totalPayOut)<0?(totalPayOut-totalPayIn):(totalPayIn-totalPayOut)}}</view>
				</view>
				<view class="flex" style="margin-bottom: 15px;">
					<view style="display:flex;">
						<view style="font-size: 12px;letter-spacing: 1.1px;color: #515151;">Expense</view>
						<view style="width: 120px;margin-left: 10px;"></view>
					</view>
					<view style="font-size: 12px;">$ {{totalPayOut}}</view>
				</view>
				<view class="flex">
					<view style="display:flex;">
						<view style="font-size: 12px;letter-spacing: 1.1px;color: #515151;">Income</view>
						<view style="width: 120px;margin-left: 10px;"></view>
					</view>
					<view style="font-size: 12px;">$ {{totalPayIn}}</view>
				</view>
			</view>
			<view style="margin-top: 20px;width: 85%;font-weight: 600;font-size: 14px;">RECENT TRANSACTIONS</view>
			<view style="overflow-y: scroll; width: 85%;height:350px;margin-top:15px;">
				<block v-for="(value,index) in payment" :key="index">
					<view style="display: flex;margin-bottom: 20px;margin-top:10px;">
						<view style="margin-right: 8px;background-color: #ffaa00;width: 4px;border-radius: 2px;"></view>
						<view style="margin-right: 8px;font-size: 14px;color: #515151;">{{value.date.slice(5)}}</view>
						<view style="margin-right: 8px;font-size: 14px;color: #515151;">
							{{(value.weekDay==0?'Sun':value.weekDay==1?'Mon':value.weekDay==2?'Tue':value.weekDay==3?
							'Wed':value.weekDay==4?'Thu':value.weekDay==5?'Fri':'Sat')}}
						</view>
					</view>
					<block v-for="(value1,index1) in value.fundsVoList">
						<view style="padding-left: 15px;padding-right: 15px;box-sizing: border-box;display: flex;margin-top:10px;margin-bottom: 10px;justify-content: space-between;">
							<view style="display: flex; align-items: center;">
								<view :class="{icon1:value1.payment<0,icon2:value1.payment>=0}" style="width:30px;height:30px;"></view>
								<view style="margin-left:15px;">
									<view style="font-weight: 600;">{{value1.title}}</view>
									<view style="font-size: 12px;color: #515151;">{{value1.content}}</view>
								</view>
							</view>
							<view style="display: flex;align-items: center;">{{value1.payment<0?"-":""}} $ {{Math.abs(value1.payment)}}</view>
						</view>
						<view style="display: flex;justify-content: center;">
							<view class="line"></view>
						</view>
					</block>
				</block>
			</view>
		</view>
		<loading ref="loading"></loading>
	</view>
</template>

<script>
	import loading from '@/components/wyb-loading/wyb-loading.vue'
	export default {
		data() {
			return {
				totalPayIn: 0,
				totalPayOut: 0,
				payment: [],
				showPage: false
			}
		},
		mounted() {
			this.$refs.loading.showLoading();
			this.getPayment();
		},
		components: {
			loading
		},
		methods: {
			getPayment() {
				uni.request({
					url: this.$serverUrl + '/kitty/selectMonthlyFund',
					method: 'POST',
					success: res => {
						if (res.data.code == 200) {
							this.payment = res.data.data;
							console.log(this.payment)
							for (let i = 0; i < this.payment.length; i++) {
								for (let j = 0; j < this.payment[i].fundsVoList.length; j++) {
									if (this.payment[i].fundsVoList[j].payment < 0) {
										this.totalPayOut += Math.abs(this.payment[i].fundsVoList[j].payment);
									} else {
										this.totalPayIn += this.payment[i].fundsVoList[j].payment;
									}
								}
							}
						}
						this.$refs.loading.hideLoading();
						this.showPage = true;
					},
					fail: res => {
						setTimeout(() => {
							this.$refs.loading.hideLoading();
							this.showPage = true;
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

	.flex {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.total {
		width: 85%;
		border: 2px solid #000000;
		margin-top: 20px;
		padding: 15px;
		border-radius: 10px;
		box-sizing: border-box;
	}

	.line {
		width: 90%;
		background-color: #dbdbdb;
		height: 1px;
	}

	.icon1 {
		background: url(../../static/sakura.png) center/100% 100% no-repeat
	}

	.icon2 {
		background: url(../../static/drum.png) center/100% 100% no-repeat
	}
</style>
