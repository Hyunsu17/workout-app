<template>
  <v-app-bar>
    <v-btn
        rounded="pill"
        icon="mdi-bell"
    >
    </v-btn>
    <v-app-bar-title>My FLEEK</v-app-bar-title>
    <v-app-bar-nav-icon
    ></v-app-bar-nav-icon>
  </v-app-bar>
  <v-row
      class="pt-8"
  >
    <v-avatar
        class="ml-5"
        v-for="data in nameList" :key="data"
        size="64" color="primary">
      <span class="text-h5">{{ data }}</span>
    </v-avatar>
  </v-row>

  <v-row>
    <v-col
        cols="auto"
    >
      <v-avatar
          class="mt-5 ml-5"
          size="160" color="primary"
      >
      </v-avatar>
    </v-col>
    <v-col
        cols="auto"
   >
      <v-card
          elevation="0"
          height="100%"
      >
        <v-card-title
            class="text-h4 pt-9"
            style="height:60%;"
        >
          롤스로이스 유령
        </v-card-title>
        <v-card-subtitle
            class="text-h6"
        >
          다음 운동 알람을 설정해 주세요
        </v-card-subtitle>
      </v-card>
    </v-col>

  </v-row>
  <v-row
      class="mt-10"
      no-gutters>
    <v-col
        class="mx-2"
        v-for="button in buttons" :key="button"
    >
      <v-btn
          height="100"
          block
      >
        {{ button }}
      </v-btn>
    </v-col>
  </v-row>
  <v-row>
    <v-toolbar title="최근 수행 순"
               height="100"
    >
      <v-btn
          size="100"
      >
        <v-icon
            icon="mdi-menu"
            size="50"
        >
        </v-icon>
      </v-btn>

    </v-toolbar>
  </v-row>
  <v-row
      class="mx-2 mt-5"
  >
    <v-btn
        style="background-color:skyblue; width: 100%; height:100%">
      <v-icon size="50" icon="mdi-lightning-bolt"></v-icon>
      핼스 초보 무분할 루틴
      <v-btn
          style="position: absolute; left: 100% ; transform: translateX(-110%)"
          elevation="0">
        <v-icon
            size="50"
            class="mt-n11"
            icon="mdi-settings-helper"
        ></v-icon>
      </v-btn>


    </v-btn>
  </v-row>
  <v-row
      class="justify-center"
      style="position: sticky; top: 900px; "
  >
    <v-col
        cols="5"
    >
      <v-btn
          width="100%"
      >
        자유운동
      </v-btn>
    </v-col>
    <v-col
        class="justify-center"
        cols="5"
    >
      <v-btn
          width="100%"
      >
        루틴추가
      </v-btn>
    </v-col>
  </v-row>


</template>

<script>
export default {
  name: "WorkoutRoutine"
  , data: () => ({
    nameList: ['롤', '플'],
    buttons: ['빠른기록', '플릭 AI', '인기루틴'],
    order: ['최근 수행 순', '빈도 순', '전체 세트 순', '전체 볼륨 순', '가나다 순', '유저 세팅']
  })
  ,
}
</script>

<style scoped>
@media all and (min-width: 1264px) and (max-width: 1904px ) {
}

</style>
