<template>
	<div class="page-box">
		医疗器械追溯平台
		<el-carousel indicator-position="outside">
			<el-carousel-item v-for="(item,index) in list" :key="index">
				<!-- <h3>{{ item }}</h3> -->
				<img :src="item" alt="" />
			</el-carousel-item>
		</el-carousel>
		<!-- <img src="../utils/image/yl1.gif" alt="" /> -->
	</div>
</template>

<script>
	export default {
		name: "Index",
		data() {
			return {
				list:[
					'../utils/image/yl3.jpg',
					'../utils/image/yl3.jpg',
					'../utils/image/yl3.jpg',
				],
			};
		},
		methods: {
			goTarget(href) {
				window.open(href, "_blank");
			}
		}
	};
</script>

<style scoped lang="scss">
	.page-box {
		.el-carousel__item h3 {
			color: #475669;
			font-size: 18px;
			opacity: 0.75;
			line-height: 300px;
			margin: 0;
		}

		.el-carousel__item:nth-child(2n) {
			background-color: #99a9bf;
		}

		.el-carousel__item:nth-child(2n+1) {
			background-color: #d3dce6;
		}
	}
</style>