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
      루틴 업데이트
    </v-btn>
    <router-link to="/workout-list/UpdateRoutine"
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
import {mapActions, mapState} from "pinia";
import {useRecordStore} from "@/stores/counter";
import WKSetData from "@/common/WKSetData";

export default {
  components: {RecordCard},
  name: "UpdateRoutine",
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
  props: ['propRoutineName'],
  created() {
    this.init()
  },
  watch: {
    idx: {
      handler(newVal) {
        if (newVal !== undefined) {
          // this.savePreviousStatus(newVal)
          this.name = this.updateRoutineTemp.workoutList[newVal - 1].workoutName
          this.record = this.updateRoutineTemp.workoutList[newVal - 1].workoutSetData
          console.log(this.record)
        }
      }
      ,
      immediate: false
    },
    routineName:{
      handler(newVal) {
        this.updateRoutineTemp.routineName = newVal
      }
    }
  },
  computed: {
    ...mapState(useRecordStore, ['updateRoutineTemp'])
  },
  beforeRouteLeave(to, from, next) {
    if(to.name !== 'WorkoutList')
      this.formatUpdateRoutineTemp()
    next()
  },
  methods: {
    ...mapActions(useRecordStore, ['setUpdateRoutineTemp', 'postCall','getWKObjectByRoutine','getRoutineIdxByName','addToWKList','formatUpdateRoutineTemp']),
    init() {
      this.fetchDataIfStatusTrue().then(() => {
        if (history.state.index === undefined) {
          this.idx = 1
        } else {
          this.idx = history.state.index
          this.listLength = this.idx
        }


        if (this.isObjectEmpty(this.updateRoutineTemp)) {
          this.fetchRoutine(this.propRoutineName)
          this.initLocalValue(this.updateRoutineTemp.routineName,
              this.updateRoutineTemp.workoutList[this.idx - 1].workoutName,
              this.Routine = this.updateRoutineTemp,
              this.record = this.updateRoutineTemp.workoutList[this.idx - 1].workoutSetData,
              this.Routine.workoutList.length
          )
        }
        else if(this.updateRoutineTemp.workoutList.length === 0){
          console.log('오류입니다.(Update항목은 List길이가 ㅇ이면 안됩니다.)')
        }
        else {
          this.initLocalValue(this.updateRoutineTemp.routineName,
              this.updateRoutineTemp.workoutList[this.idx - 1].workoutName,
              this.Routine = this.updateRoutineTemp,
              this.record = this.updateRoutineTemp.workoutList[this.idx - 1].workoutSetData,
              this.Routine.workoutList.length
          )
          this.isEmpty = false
        }
      })

    },
    fetchRoutine(_routineName){
      this.setUpdateRoutineTemp(this.getWKObjectByRoutine(_routineName))
    },
    initLocalValue(_routineName,_name, _routine, _record, _listLength){
      this.routineName = _routineName
      this.name= _name
      this.Routine = _routine
      this.record = _record
      this.listLength = _listLength
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
          console.log('good')
          this.formatUpdateRoutineTemp()
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
      this.updateRoutineTemp.workoutList.forEach(item => dataFormat.workoutElement.push({workoutName: item.workoutName}))

      for (let i = 0; i < this.updateRoutineTemp.workoutList.length; i++) {
        for (let j = 0; j < this.updateRoutineTemp.workoutList[i].workoutSetData.length; j++) {
          dataFormat.workoutSet.push({
            elementName: this.updateRoutineTemp.workoutList[i].workoutName,
            reps: this.updateRoutineTemp.workoutList[i].workoutSetData[j].reps,
            status: this.updateRoutineTemp.workoutList[i].workoutSetData[j].status,
            weight: this.updateRoutineTemp.workoutList[i].workoutSetData[j].weight
          })
        }
      }
      return dataFormat
    },
    fetchWKData(name) {
      const params = {
        user: {
          username: 'test'
        },
        routine: {
          name: name
        }
      }
      return this.postCall('/api/workoutElement', params).catch((error) => {
      }).then((rep) => {
        if (rep.status === 200) return this.parseToWKData(rep)
      })
    },
    parseToWKData(rep) {
      const retObject = []
      for (let i = 0; i < rep.data.length; i++) retObject.push({name: rep.data[i].workoutName})
      return retObject
    },
    fetchWKDetailData(name) {
      const params = {
        user: {
          username: 'test'
        },
        routine: {
          name: name
        }
      }
      return this.postCall('/api/workoutSet',
          params).catch((error) => {
      }).then((rep) => {
        if (rep.status === 200) return this.parseToWKSetData(rep)
      })
    },
    parseToWKSetData(rep) {
      const retObject = []
      let arrObject = []
      for (let i = 0; i < rep.data.length; i++) {
        arrObject = []
        for (let t = 0; t < rep.data[i].length; t++) {
          arrObject.push(new WKSetData(rep.data[i][t].reps, rep.data[i][t].status, rep.data[i][t].weight))
        }
        retObject.push(arrObject)
      }
      return retObject
    },
    checkStatus() {
      return this.getWKObjectByRoutine(this.propRoutineName).workoutList.length === 0;
    },
    async fetchDataIfStatusTrue(){
      if (this.checkStatus()) {
        const WKDetailData = await this.fetchWKDetailData(this.propRoutineName);
        const WKData = await this.fetchWKData(this.propRoutineName);

        for (let i = 0; i < WKData.length; i++) {
          this.addToWKList(this.getRoutineIdxByName(this.propRoutineName), WKData[i].name, WKDetailData[i]);
        }
      }
    }
  }
}
</script>

<style scoped>

</style>