<template>
  <v-pagination
      v-model="idx"
      :length="4"
      rounded
  >
  </v-pagination>

  <v-card
      elevation="0"
  >
    <div class="d-flex">
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
      <v-card-title
          class="text-h4"
      >
      </v-card-title>
    </div>
  </v-card>

  <record-card
      ref="recordCard"
      :name="name"
      :record="record"
  >
  </record-card>

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
      workoutClass: null,
      record:null
    }
  },
  created() {
    this.init()
  },
  computed: {
    ...mapState(useRecordStore, ['tempRoutineStorage'])
  },
  methods: {
    ...mapActions(useRecordStore,['setTempRoutineStorage']),
    init() {
      if (this.tempRoutineStorage.length === 0) {
        this.workoutClass = new WKClass('test-routine')
      } else {
        this.workoutClass = this.tempRoutineStorage
      }
      this.workoutClass.addToList('lat pull down', [new WKSetData(1,true,10)])

      this.setTempRoutineStorage(this.workoutClass)
      this.record = this.workoutClass.workoutList[this.idx-1].workoutSetData
    },
    addWorkoutList() {

    }
  }
}
</script>

<style scoped>

</style>
