<template>

  <div>
    <h2 class="title">种子溯源流程</h2>
    <div class="container">
      <div class="step">
        <img src="../../../../src/assets/img/录入信息.png" alt="录入种子信息">
        <h2>录入种子信息</h2>
        <p>将种子的基本信息录入系统，包括种子种类、产地、生产商、生产日期等。</p>
      </div>
      <div class="step">
        <img src="../../../../src/assets/img/解析.png" alt="提取信息">
        <h2>提取信息</h2>
        <p>通过录入的种子信息提取其中的种子种类、生产商、生产日期，并转为对应代码</p>
      </div>
      <div class="step">
        <img src="../../../../src/assets/img/生成二维码.png" alt="生成防伪码">
        <h2>生成防伪码</h2>
        <p>将提取的信息代码和随机生成的8位随机码转为每个种子唯一的防伪码，以保障种子的真实性和安全性。</p>
      </div>
    </div>

    <div class="img-container">
      <img src="../../../../src/assets/img/箭头.png" alt="">
    </div>

    <div class="container">
      <div class="step">
        <img src="../../../../src/assets/img/监控.png" alt="监控预警">
        <h2>监控预警</h2>
        <p>系统可以根据数据统计中的防伪码扫描情况，对扫码时间、地点、次数进行相应分析，给出红橙黄蓝四个不同等级的预警，以防止防伪码被恶意盗用。</p>
      </div>
      <div class="step">
        <img src="../../../../src/assets/img/可视化.png" alt="数据统计">
        <h2>数据统计</h2>
        <p>系统会自动统计所有种子防伪码的数量、以及每个防伪码被扫描的情况，包括扫码时间、地点、次数等。</p>
      </div>
      <div class="step">
        <img src="../../../../src/assets/img/手机扫码.png" alt="扫码溯源">
        <h2>扫码溯源</h2>
        <p>消费者可以通过使用扫码枪、手机等终端扫描种子上的二维码或条形码，了解种子的生产、加工、销售等全过程。</p>
      </div>
    </div>

  </div>

</template>

<script>
export default {

};
</script>

<style>
.container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  grid-gap: 40px;
  justify-items: center;
  align-items: center;
  margin: 0 auto;
  max-width: 1200px;
  padding: 10px;
}

.title {
  text-align: center;
  margin-bottom: 10px;
  font-size: 32px;
}

.step {
  text-align: center;
}

.step img {
  width: 80px;
  height: 80px;
  margin-bottom: 10px;
}

.step h2 {
  font-size: 18px;
  margin-bottom: 10px;
}

.step p {
  font-size: 16px;
  line-height: 1.5;
}

.img-container{
  display: flex;
  justify-content: center;
}

.img-container img{
  width: 95%;
}
</style>
