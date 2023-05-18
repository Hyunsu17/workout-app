<template>
  <v-pagination
      :length="4"
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
      :name="'랫 풀 다운'"
      :record="record"
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

export default {
  name: "WorkoutDetailsView",
  computed: {},
  props: ['SetData']
  ,
  components: {RecordCard},
  data: () => ({
    timer: null,
    timeCounter: 0,
    timeStr: "00:00",
    record: [
      {reps: 10},
      {reps: 10},
      {reps: 10},
      {reps: 10},
    ]
  }),
  created() {
    console.log(this.SetData)
  },
  methods: {
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
      this.$refs.recordCard.intermediate()
    },
    addSet() {
      this.record.push({reps: 10})
    },
    removeLastSet() {
      this.record.pop()
    }
  }
}
</script>

<style scoped>

</style>
