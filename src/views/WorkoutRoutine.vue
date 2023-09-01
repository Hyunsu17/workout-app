<template>
  <v-app-bar
      elevation="0"
  >
    <v-btn
        rounded="pill"
        icon="mdi-bell"
    >
    </v-btn>
    <v-app-bar-title
        class="text-center font-weight-bold"
    >나의 운동일지
    </v-app-bar-title>
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
    >
      <router-link

          to="/quickRecord">
        <v-btn
            class="text-black"
            height="100"
            block
        >
          빠른기록
        </v-btn>
      </router-link>
    </v-col>
    <v-col
        class="mx-2"
    >
      <router-link to="/famousRoutine">
        <v-btn
            class="text-black"
            height="100"
            block
        >
          인기루틴
        </v-btn>
      </router-link>
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
      v-for="(item, index) in testData" :key="index"
      class="mx-2"
      style="margin-top:80px;"
  >
    <router-link :to="'/workoutSelection/'+item.routineName"
                 style="width: 100%;"
    >
      <v-btn
          class="text-black"
          style="background-color:skyblue; width: 100%; height:200%">
        <v-icon size="50" icon="mdi-lightning-bolt"></v-icon>
        {{ item.routineName }}
        <v-btn
            style="position: absolute; left: 100% ; transform: translateX(-110%)"
            @click="DeleteRoutine"
            elevation="0">
          <v-icon
              size="50"
              class="mt-n11"
              icon="mdi-settings-helper"
          ></v-icon>
        </v-btn>
      </v-btn>
    </router-link>
  </v-row>
  <div style="height: 30%"/>
  <footer-button-group/>


</template>

<script>
import {mapActions, mapState} from "pinia";
import {useRecordStore} from "@/stores/counter";
import FooterButtonGroup from "@/components/FooterButtonGroup.vue";

export default {
  name: "WorkoutRoutine"
  ,
  components: {FooterButtonGroup},
  data: () => ({
    nameList: ['롤', '플'],
    testData: [''],
    order: ['최근 수행 순', '빈도 순', '전체 세트 순', '전체 볼륨 순', '가나다 순', '유저 세팅'],
    isWorkingOut: ''
  }),
  computed: {
    ...mapState(useRecordStore, ['WKData', 'isExercising'])
  },
  methods: {
    ...mapActions(useRecordStore, ['postCall', 'deleteCall']),
    initRoutine() {
      this.isWorkingOut = this.isExercising
      if (this.WKData) this.testData = this.WKData
    },
    DeleteRoutine() {
      this.deleteCall('/api/routine', {'userName': 'test', 'routineName': '테스트'}).then(rep => {
        console.log(rep)
      })
    }
  },
  created() {
    this.initRoutine()
  }
}
</script>

<style scoped>
@media all and (min-width: 1264px) and (max-width: 1904px ) {
}

</style>
