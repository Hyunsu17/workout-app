<template>
  <div
    v-if="!isEmpty"
  >
  <v-pagination
      v-model="idx"
      :length="4"
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
      class="d-flex align-center justify-center flex-wrap text-center ma-auto px-4"
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
      style="
      position: fixed;
      bottom: 100px;
"
  >
    <v-btn
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
    width: 100%;
    right: 20px;
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
import WKSetData from "@/common/WKSetData";

export default {
  components: {RecordCard},
  name: "AddRoutine",
  data() {
    return {
      name: '운동',
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
      handler(val) {
        console.log("val:" + val)
        this.name = this.tempRoutineStorage.workoutList[val - 1].workoutName
        this.record = this.tempRoutineStorage.workoutList[val - 1].workoutSetData
      }
      ,
      immediate: false
    }
  },
  computed: {
    ...mapState(useRecordStore, ['tempRoutineStorage'])
  },
  methods: {
    ...mapActions(useRecordStore, ['setTempRoutineStorage']),
    init() {
      if (this.tempRoutineStorage.length === 0) {
        this.isEmpty = true
        this.Routine = new WKClass('test-routine')
      } else {
        this.record = this.Routine.workoutList[this.idx - 1].workoutSetData
        this.isEmpty = false
        this.Routine = this.tempRoutineStorage
      }
      this.setTempRoutineStorage(this.Routine)
    },

  }
}
</script>

<style scoped>

</style>
