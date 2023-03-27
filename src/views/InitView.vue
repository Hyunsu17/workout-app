<template>
  <p id="wrapper" align="center">로그인 방법을 <br>선택해 주세요</p>
  <div id="footer">
    <v-row>
      <v-col cols="12"
      >
        <v-btn
            style="background-color: #f3dc00; height: 60px"
            block
            size="x-large"
            class="text-brown-darken-4"
            rounded="pill"
        >
          Kakao 로그인
        </v-btn>
      </v-col>
      <v-col cols="12">
        <v-btn
            style="background-color: #4086f4; height: 60px"
            block
            size="x-large"
            rounded="pill"
            class="text-white"
        >
          Google 로그인
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "InitView"
}

</script>

<style scoped>
#wrapper {
  height: 100%;
}

@font-face {
  font-family: "BMdohyeon";
  src: local("BMdohyeon"), url("../assets/fonts/BMDOHYEON_ttf.ttf") format("truetype");
}

#footer {
  position: relative;
  transform: translateY(-200%);
}

p {
  font-family: suit-regular, Serif;
  font-size: 80px;
  font-weight: bold;
  padding-top: 100px;
}

</style>
