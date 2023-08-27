<template>
  <v-btn
      v-for="data in tempList" :key="data"
      block
      min-height="100px"
      @click="clickEvent(data)"
  >
    {{ data }}
  </v-btn>
  <v-row
      v-if="status"
      style="position: fixed;
      bottom: 10%;
      width: 100%;
"
  >
    <v-col
        cols="3"
    >
      <v-btn
          size="200%"
          style="font-size: 100%;
          left: 10px;
          "
          elevation="0"
          @click="returnToList"
          prepend-icon="mdi-chevron-left"
      >
        <template v-slot:prepend>
          <v-icon color="red"
                  style="font-size: 200%"
          ></v-icon>
        </template>
        돌아가기
      </v-btn>
    </v-col>
  </v-row>

  <div>

  </div>
</template>

<script>
import {useRecordStore} from "@/stores/counter";
import {mapActions, mapState} from "pinia";
import WKSetData from "@/common/WKSetData";
// import router  from "vue-router";

export default {
  name: "WorkoutList",
  computed: {
    ...mapState(useRecordStore, ['tempRoutineStorage'])
  },
  data() {
    return {
      tempList: null,
      AllList: {
        part: ['back', 'chest', 'shoulder', 'leg'],
        back: ['랫 풀다운', '케이블 풀 다운', '풀업', '바벨 로우', '덤벨 로우', '케이블 로우'],
        chest: ['벤치 프레스', '체스트 프레스', '인클라인 벤치 프레스', '디클라인 벤치 프레스', '딥스', '덤벨 프레스'],
        shoulder: ['밀리터리 프레스', '숄더 프레스', '사이드 레터럴 레이즈', '프론트 레이즈'],
        leg: ['스쿼트', '데드리프트', '레그 컬', '레그 익스텐션', '스플릿 스쿼트', '런지']
      },
      status: false
    }
  },
  created() {
    this.tempList = this.AllList['part']
  }
  ,
  methods: {
    ...mapActions(useRecordStore, ['getTempRoutineStorage'])
    ,
    clickEvent(key) {
      if (this.status === false) {
        this.status = true
        this.tempList = this.AllList[key]
      }
      else {
        let temp = this.tempRoutineStorage
        let i = 0;
        if (temp.workoutList.length === 0) {
          temp.addToList(key, [new WKSetData(1, false, 10)])
          this.$router.push({name:'AddRoutine',state:{index: this.tempRoutineStorage.workoutList.length}})
          console.log('추가완료')
        } else {
          for (i = 0; i < temp.workoutList.length; i++) {
            if (temp.workoutList[i].workoutName === key) {
              alert('이미 선택하였습니다')
              break
            }
          }
          if (i === temp.workoutList.length) {
            temp.addToList(key, [new WKSetData(1, false, 10)])
            alert('추가완료')
            console.log(this.tempRoutineStorage)
            this.$router.push({name:'AddRoutine',state:{index: this.tempRoutineStorage.workoutList.length}})
          }
        }
      }
    },
    returnToList() {
      this.status = false
      this.tempList = this.AllList['part']
    }
  }

}
</script>

<style scoped>

</style>
