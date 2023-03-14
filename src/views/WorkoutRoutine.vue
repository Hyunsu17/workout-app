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
    <v-avatar
        class="mt-5 ml-5"
    size="160" color="primary"
    >
    </v-avatar>

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
        {{button}}
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


</template>

<script>
export default {
  name: "WorkoutRoutine"
  , data: () => ({
    nameList: ['롤', '플'],
    buttons: ['빠른기록', '플릭 AI', '인기루틴'],
    order: ['최근 수행 순','빈도 순','전체 세트 순','전체 볼륨 순', '가나다 순','유저 세팅']
  })
  ,
}
</script>

<style scoped>

</style>
