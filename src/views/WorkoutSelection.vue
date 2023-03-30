<template>
  <!-- 앱 상단 바-->
  <v-app-bar>
    <router-link to="/routine">
      <v-btn
          size="S"
          rounded="circle"
      >
        <v-icon
            size="400%"
            style="position: relative; right: 10px"
        >
          mdi-chevron-left
        </v-icon>
      </v-btn>
    </router-link>
    <v-app-bar-title
        class="text-center"
    >?Title?
    </v-app-bar-title>
    <v-btn>
      <v-icon
          size="300%"
          style="position: relative; top: -20px"
      >
        mdi-settings-helper
      </v-icon>
    </v-btn>
  </v-app-bar>
  <!-- 운동 부위 사진 -->
  <v-container
  >
    <v-row
        style="min-height: 30%;
    background-color: #03a9f4;
    position: fixed; z-index: 2;
"
    >
    </v-row>
  </v-container>

  <!-- 운동 리스트 -->
  <v-btn
      block
      min-height="100px"
      style="margin-top: 30%;"
  >
    머신 체스트 프레스
  </v-btn>
  <v-btn
      v-for="data in workoutList" :key="data"
      block
      min-height="100px"
  >
    {{ data }}
  </v-btn>

</template>

<script>
export default {
  name: "WorkoutSelection",
  data() {
    return {
      workoutList: ['바벨스쿼트', '랫풀다운', '바벨 숄더 프레스', '케이블 트라이셉 푸시다운', '바벨 바이셉 컬', '행잉 레그레이즈']
    }
  }
}
</script>

<style scoped>
</style>
