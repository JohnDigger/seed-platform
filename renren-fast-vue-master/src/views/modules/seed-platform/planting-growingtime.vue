<template>
  <div class="container">
    <h2 class="text-center mb-4">{{ title }}</h2>
    <div class="row">
      <div class="col-md-6">
        <img class="img-fluid mb-4" src="../../../assets/img/种子产量图.png" alt="">
        <p>{{ image1Description }}</p>
      </div>
      <div class="col-md-6">
        <img class="img-fluid mb-4" src="../../../assets/img/种子产量图.png" alt="">
        <p>{{ image2Description }}</p>
      </div>
    </div>
    <div class="text-center mt-4">
      <p>{{ explanation }}</p>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'planting-growingtime',
    data() {
      return {
        title: "种子的种植信息",
        image1Description: "这是种子的生长时间与种植月份的信息。",
        image2Description: "这是种子种植方式的占比信息。",
        explanation:
          "通过以上图表数据，可以得到种子的最佳种植时间与最佳种植方式的占比，以便于更好的服务于用户。"
      };
    }
  }
</script>

<style scoped>
  .container {
    max-width: 800px;
    margin: auto;
    padding: 20px;
  }

  img {
    width: 100%;
  }

  p {
    font-size: 18px;
    line-height: 1.5;
  }

  h1 {
    font-size: 36px;
    margin-bottom: 40px;
  }

  @media screen and (max-width: 768px) {
    h1 {
      font-size: 24px;
    }
  }
</style>
