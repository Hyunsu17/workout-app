<template>

  <div
      v-if="!isEmpty"
  >
    <v-card class="mx-auto mt-6" color="blue-lighten-5"  max-width="400">
      <v-card-subtitle
        class="mt-3 font-weight-black"
        style="font-size: 17px"
      >
        루틴 이름
      </v-card-subtitle>
      <v-card-text
          class="mt-2"
          style="position: relative; bottom:10px;"
      >
        <v-text-field
            class="elevation-0"
            density="compact"
            variant="solo"
            label="Custom"
            single-line
            hide-details
            v-model="routineName"
        ></v-text-field>
      </v-card-text>
    </v-card>

    <v-pagination
        class="mt-5"
        v-model="idx"
        :length="listLength"
        rounded
    >
    </v-pagination>

    <v-card
        elevation="0"
    >
      <router-link :to="'/routine/'">
        <v-btn
            elevation="0"
            color="red"
            variant="text"
            style="font-size: 200%"
            icon="mdi-chevron-left"
        >
        </v-btn>
      </router-link>
    </v-card>

    <record-card
        ref="recordCard"
        :name="name"
        v-model="record"
    >
    </record-card>
  </div>
  <v-sheet
      v-if="isEmpty"
      style="position: relative; top:30%"
      class="d-flex align-center justify-center flex-wrap text-center mx-auto px-4"
      elevation="4"
      height="250"
      rounded
      max-width="800"
      width="100%"
  >
    <div>
      <h2 class="text-h4 font-weight-black text-orange">선택된 운동이 없습니다</h2>

      <div class="text-h5 font-weight-medium mb-2">
        운동선택 버튼을 눌러주세요
      </div>

      <p class="text-body-2 mb-4">
      </p>
    </div>
  </v-sheet>
  <v-row
      v-if="!isEmpty"
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
    </v-col>
  </v-row>
  <v-row
      style="
      position: fixed;
      bottom: 100px;
"
  >
    <v-btn
        v-if="!isEmpty"
        @click="callCreateRoutine"
        class="ml-6"
        width="200px"
        height="100px"
    >
      루틴 저장
    </v-btn>
    <router-link to="/workout-list/AddRoutine"
                 style="
    display: grid;
    justify-content: end;
    position: fixed;
    bottom: 100px;
    right: 20px;
    background-color: black;
"
    >
      <v-btn
          class="text-black"
          width="200px"
          height="100px"
      >
        운동 선택
      </v-btn>
    </router-link>
  </v-row>

</template>

<script>
import RecordCard from "@/components/RecordCard.vue";
import WKClass from "@/common/WKClass";
import {mapActions, mapState} from "pinia";
import {useRecordStore} from "@/stores/counter";

export default {
  components: {RecordCard},
  name: "AddRoutine",
  data() {
    return {
      listLength: 0,
      name: '',
      idx: 1,
      Routine: null,
      record: null,
      isEmpty: false,
      routineName: ""
    }
  },
  props: ['propRoutineName']
  ,
  created() {
    this.init()
  },
  watch: {
    idx: {
      handler(newVal) {
        if (newVal !== undefined) {
          this.name = this.addRoutineTemp.workoutList[newVal - 1].workoutName
          this.record = this.addRoutineTemp.workoutList[newVal - 1].workoutSetData
        }
      }
      ,
      immediate: false
    },
    routineName:{
      handler(newVal) {
        this.addRoutineTemp.routineName = newVal
      }
    }
  },
  computed: {
    ...mapState(useRecordStore, ['addRoutineTemp'])
  },
  beforeRouteLeave(to, from, next) {
    next()
  },
  methods: {
    ...mapActions(useRecordStore, ['setAddRoutineTemp', 'postCall','formatAddRoutineTemp']),
    init() {
      if (history.state.index === undefined) {
        this.idx = 1
      } else {
        this.idx = history.state.index
        this.listLength = this.idx
      }
      if (this.isObjectEmpty(this.addRoutineTemp)) {
        this.isEmpty = true
        this.Routine = new WKClass('임시')
        this.setAddRoutineTemp(this.Routine)
      }
      else if(this.addRoutineTemp.workoutList.length === 0){
        this.isEmpty = true
      }
      else {
        this.addRoutineTemp.routineName = this.propRoutineName
        this.routineName = this.addRoutineTemp.routineName
        this.name = this.addRoutineTemp.workoutList[this.idx - 1].workoutName
        this.Routine = this.addRoutineTemp
        this.record = this.addRoutineTemp.workoutList[this.idx - 1].workoutSetData
        this.isEmpty = false
        this.listLength = this.Routine.workoutList.length
        this.setAddRoutineTemp(this.Routine)
      }
    },
    changeAllStatus() {
      this.$refs.recordCard.onMethodRequest({methodName: 'setTrue', param: undefined})
    },
    addSet() {
      this.$refs.recordCard.onMethodRequest({methodName:'addSet',param:{reps:1, status:false, weight:10}})
    },
    removeLastSet() {
      this.record.pop()
    },
    isObjectEmpty(obj) {
      return Object.keys(obj).length === 0;
    },
    callCreateRoutine() {
      this.postCall('/api/routine', this.changeFormat()).then((rep) => {
        if (rep.status === 200) {
          this.formatAddRoutineTemp()
          this.$router.push({
            name: 'WorkoutRoutine'
          })
        }
      }).catch((err)=>{
      })

    },
    changeFormat() {
      const dataFormat = {
        user: {},
        routine: {},
        workoutElement: [],
        workoutSet: []
      }

      dataFormat.user.username = 'test'
      dataFormat.routine.name = this.routineName
      this.addRoutineTemp.workoutList.forEach(item => dataFormat.workoutElement.push({workoutName: item.workoutName}))

      for (let i = 0; i < this.addRoutineTemp.workoutList.length; i++) {
        for (let j = 0; j < this.addRoutineTemp.workoutList[i].workoutSetData.length; j++) {
          dataFormat.workoutSet.push({
            elementName: this.addRoutineTemp.workoutList[i].workoutName,
            reps: this.addRoutineTemp.workoutList[i].workoutSetData[j].reps,
            status: this.addRoutineTemp.workoutList[i].workoutSetData[j].status,
            weight: this.addRoutineTemp.workoutList[i].workoutSetData[j].weight
          })
        }
      }
      return dataFormat
    },
  }
}
</script>

<style scoped>

</style>