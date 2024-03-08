<template>
	<div class="page-box">
		<div class="introductionBox">
			<div class="introductionTitle">公司简介</div>
			
			<div class="conter">
				<div class="text">
					<div class="leftText">
						<div>
							GE 医疗拥有悠久的创新历史。在服务患者
							和医疗机构的 100 多年中，GE 医疗持续
							推进个性化、互联互通和更富同理心的医
							疗关爱，同时简化患者诊疗流程。
							
						</div>
						<div>
							GE 医疗的医学影像、超声、生命关爱和
							诊断药物业务覆盖从预防、筛查、诊断、
							治疗到监护各环节的医疗关爱。公司年营
							收 183 亿美元，全球装机量超过 400 万台，
							全球 50,000 名员工同心共创无界的医疗
							关爱。
						</div>
						<div>
							GE 医疗以创新探求无限可能。我们携手
							客户，通过突破性的医疗技术、智能互联
							设备和解决方案，发挥医疗的更大潜力，
							以更先进的工具提供更优质的患者关爱。
							携手奋进，我们不仅塑造一个更健康的未
							来，还将践行我们的愿景——共创无界的
							医疗关爱。
						</div>
					</div>
					<div class="rightText">
						<div>
							我们以提高全球患者、医疗服务提供者、
							医疗系统和研究人员的产出和医疗效果为
							中心，推进精准医疗和数字医疗发展。
						</div>
						<div>
							我们专注于开发、生产和销售一系列产品、
							服务及补充性数字解决方案，用于患者诊
							断、治疗和监护。
						</div>
						<div>
							我们积极借助新兴技术和机器学习，不断
							拓展面向未来的新能力。我们的目标是为
							尽可能多的人提供广泛的医疗工具和技术，
							尤其是那些未获得优质医疗服务的群体。
						</div>
						<div>
							我们的全套解决方案旨在应对医疗健康行
							业当前面临的重大挑战，包括帮助客户改
							善诊疗效果、提高生产效率。我们坚信，
							这将进一步巩固我们与全球客户之间的信
							赖度和合作伙伴关系。
						</div>
						<div>
							为实现目标，我们确定业务重点领域，并
							建立了企业日常文化准则
						</div>
					</div>
				</div>
				<div class="imgDetail">
					<img src="../../../utils/image/yl1.gif" alt="" />
				</div>
			</div>
		</div>
		
		
	</div>
</template>

<script>
	export default{
		name: "Introduction",
		data(){
			return {
				
			}
		},
		created(){
			
		},
		methods:{
			
		}
	}
</script>

<style lang="scss">
	.page-box{
		.introductionBox{
			width:1080px;
			height:700px;
			margin:100px auto;
			h3{
				text-align: center;
				font-size: 32px;
			}
			.introductionTitle{
				font-size:58px;
				text-align:center;
			}
			.conter{
				display: flex;
				.text{
					width:50%;
					display: flex;
					margin-right: 30px;
					.leftText{
						width: 50%;
						div{
							margin: 20px 0;
						}
					}
					.rightText{
						width: 50%;
						div{
							margin: 20px 0;
						}
					}
				}
				.imgDetail{
					width:50%;
					img{
						width: 100%;
						height: 100%;
					}
				}
			}
		}
	}
</style>