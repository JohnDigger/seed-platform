<template>
  <div class="trace_procee_container">
    <h1>溯源流程</h1>
    <div class="trace_procee_img">
      <img src="../../../assets/img/溯源数据统计.png" alt="">
    </div>
  </div>
</template>

<script>
export default {
name: "trace-statistics"
}
</script>

<style scoped>

.trace_procee_container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.trace_procee_container h1{
  font-size: 40px;
}

.trace_procee_img {
  display: flex;
  justify-content: center;
}

.trace_procee_img img {
  max-width: 80%;
  display: block;
}

</style>
