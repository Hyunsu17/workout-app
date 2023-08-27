<template>
  <div
      v-if="!isEmpty"
  >
    <v-pagination
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
        :record="record"
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
        @click="callCreateRoutine"
        class="ml-6"
        width="200px"
        height="100px"
    >
      루틴 저장
    </v-btn>
    <router-link to="/workout-list"
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
      isEmpty: false
    }
  },
  created() {
    this.init()
  },
  watch: {
    idx: {
      handler(newVal) {
        if (newVal !== undefined) {
          this.savePreviousStatus(newVal)
          this.name = this.tempRoutineStorage.workoutList[newVal - 1].workoutName
          this.record = this.tempRoutineStorage.workoutList[newVal - 1].workoutSetData
          console.log(this.record)
        }
      }
      ,
      immediate: false
    }
  },
  computed: {
    ...mapState(useRecordStore, ['tempRoutineStorage'])
  },
  beforeRouteLeave(to, from, next) {
    // this.savePreviousStatus()
    next()
  },
  methods: {
    ...mapActions(useRecordStore, ['setTempRoutineStorage','postCall']),
    init() {
      if (history.state.index === undefined) {
        this.idx = 1
      } else {
        this.idx = history.state.index
        this.listLength = this.idx
      }
      if (this.isObjectEmpty(this.tempRoutineStorage)) {
        this.isEmpty = true
        this.Routine = new WKClass('test-routine')
      } else {
        this.name = this.tempRoutineStorage.workoutList[this.idx - 1].workoutName
        this.Routine = this.tempRoutineStorage
        this.record = this.tempRoutineStorage.workoutList[this.idx - 1].workoutSetData
        this.isEmpty = false
        this.listLength = this.Routine.workoutList.length
      }
      this.setTempRoutineStorage(this.Routine)
    },
    changeAllStatus() {
      this.$refs.recordCard.onMethodRequest({methodName: 'setTrue', param: undefined})
    },
    addSet() {
      const newRecord = JSON.parse(JSON.stringify(this.record[this.record.length - 1]))
      this.record.push(newRecord)
    },
    removeLastSet() {
      this.record.pop()
    },
    savePreviousStatus() {
      const record = this.record
      const changedRecord = this.$refs.recordCard.onMethodRequest({methodName: 'returnValue', param: undefined})
      for (let i = 0; i < record.length; i++) {
        record[i].status = changedRecord[i]
      }
    },
    isObjectEmpty(obj) {
      return Object.keys(obj).length === 0;
    },
    callCreateRoutine(){
      this.postCall('/api/routine', this.changeFormat()).then((rep) => {
        if (rep.status === 200) {
          console.log('good')
        }
      }).catch((err)=>{
        console.log(err)
      })
    },
    changeFormat(){
      const dataFormat = {
        user: {},
        routine: {},
        workoutList: [],
        workoutSet: []
      }

      dataFormat.user.username = 'test'
      dataFormat.routine.name = "나만의 커스텀"
      this.tempRoutineStorage.workoutList.forEach(item => dataFormat.workoutList.push({workoutName: item.workoutName}))
      for(let i; i< this.tempRoutineStorage.workoutList.length;i++){
        for(let j; j<this.tempRoutineStorage.workoutList[i].length; j++){
          dataFormat.workoutSet.push({
            elementName: this.tempRoutineStorage.workoutList[i].workoutSetData.workoutName,
            reps:this.tempRoutineStorage.workoutList[i].workoutSetData.reps,
            status: this.tempRoutineStorage.workoutList[i].workoutSetData.status,
            weight: this.tempRoutineStorage.workoutList[i].workoutSetData.weight
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