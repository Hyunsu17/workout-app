<template>
  <v-pagination
      v-model="localId"
      :length="pageLength"
      rounded
  >

  </v-pagination>
  <v-card
      elevation="0"
  >
    <div class="d-flex">
      <router-link :to="'/workoutSelection/'+$route.params.name">
        <v-btn
            elevation="0"
            color="red"
            variant="text"
            style="font-size: 200%"
            icon="mdi-chevron-left"
        >
        </v-btn>
      </router-link>
      <v-card-title
          class="text-h4"
      >
        {{ timeStr }}
      </v-card-title>
    </div>
  </v-card>

  <record-card
      ref="recordCard"
      :name="WKName"
      v-model="record"
  >
  </record-card>

  <v-row
      class="justify-center mx-auto mt-2"
  >
    <v-col
        cols="5"
    >
      <v-btn
          @click="addSet"
          color="blue-darken-3"
          width="100%"
          height="200%"
      >
        세트추가
      </v-btn>
    </v-col>
    <v-col
        class="justify-center"
        cols="5"
    >
      <v-btn
          @click="removeLastSet"
          color="red"
          width="100%"
          height="200%"
      >
        세트삭제
      </v-btn>
    </v-col>
    <v-col
        class="mt-5"
        cols="5"
    >
      <v-btn
          color="green-darken-2"
          width="100%"
          height="200%"
          @click="changeAllStatus"
      >
        모든세트 완료
      </v-btn>
      <v-btn
          color="green-darken-2"
          width="100%"
          height="200%"
          @click="setBreakTime"
      >
        타이머 테스트
      </v-btn>
    </v-col>
  </v-row>

</template>

<script>
import RecordCard from "@/components/RecordCard.vue";
import {mapActions, mapState} from "pinia";
import {useRecordStore} from "@/stores/counter";

export default {
  name: "WorkoutDetailsView",
  components: {RecordCard},
  data: () => ({
    previousLocalId: 0,
    localId: 0,
    WKName: '',
    routineName: '',
    timer: null,
    timeCounter: 0,
    timeStr: "00:00",
    record: [],
    pageLength: 0,
  }),
  created() {
    this.previousLocalId = this.$route.params.id
    this.initId(this.$route.params.id)
    this.initRoutineName(this.$route.params.name)
    this.initWKName(this.getWKNameByWKIdx(this.$route.params.name, this.localId - 1))
    this.initSetData(this.localId, this.routineName)
    this.pageLength = this.getWKData[this.getRoutineIdxByName(this.routineName)].workoutList.length
  },
  computed: {
    ...mapState(useRecordStore, ['getWKData'])
  },
  watch: {
    localId: {
      handler(val) {
        const WKName = this.getWKNameByWKIdx(this.$route.params.name, val - 1)
        this.initWKName(WKName)
        this.initSetData(this.localId, this.routineName)
      }
      ,
      immediate: false
    }
  }
  ,
  beforeRouteLeave(to, from, next) {
    next()
  },
  methods: {
    ...mapActions(useRecordStore, ['getRoutineIdxByName', 'getWKNameByWKIdx', 'deleteWorkoutByIdx']),
    setBreakTime() {
      if (this.timer != null) {
        this.timerStop(this.timer)
        this.timer = null
      }
      this.timer = this.timerStart()
    },
    timerStart() {
      this.timeCounter = 120
      const interval = setInterval(() => {
        this.timeCounter--;
        this.timeStr = this.changeToStr()
        if (this.timeCounter <= 0) this.timerStop(interval)
      }, 1000)
      return interval
    },
    timerStop(interval) {
      clearInterval(interval)
      this.timeCounter = 0
    },
    changeToStr() {
      let time = this.timeCounter / 60
      let minutes = parseInt(time)
      let seconds = Math.round((time - minutes) * 60)
      return (
          minutes.toString().padStart(2, "0") +
          ":" +
          seconds.toString().padStart(2, "0")
      )
    },
    changeAllStatus() {
      this.$refs.recordCard.onMethodRequest({methodName: 'setTrue', param: undefined})
    },
    addSet() {
      this.$refs.recordCard.onMethodRequest({methodName: 'addSet', param: {reps: 1, status: false, weight: 10}})
    },
    removeLastSet() {
      this.record.pop()
    },
    removeCurrentWorkout() {
      //하나만 있는 경우
      let number = this.localId
      if (this.localId === 1 && this.pageLength === 1) {
        console.log('대기')
      } //첫번째 요소 삭제인 경우
      else if (this.localId === 1) {
        this.localId = this.localId + 1
      } else {
        this.localId = this.localId - 1
      }
      this.deleteWorkoutByIdx(this.routineName, number)
      this.initSetData(this.localId)
      this.pageLength = this.pageLength - 1
    },
    initId(_localId) {
      this.localId = _localId
    },
    initWKName(_WKName) {
      this.WKName = _WKName
    },
    initRoutineName(_RoutineName) {
      this.routineName = _RoutineName
    },
    initSetData(_id, _name) {
      this.record = this.fetchWKSetData(_id, _name)
    },
    /**
     * 인덱스값에 해당 하는 운동 세트 데이터를 가져온다
     */
    fetchWKSetData(_id, _name) {
      return this.getWKData[this.getRoutineIdxByName(_name)].workoutList[_id - 1].workoutSetData
    },
  }
}
</script>

<style scoped>

</style>
