<template>
  <!-- 앱 상단 바-->
  <v-app-bar>
    <router-link to="/routine">
      <v-btn
          size="S"
          rounded="circle"
      >
        <v-icon
            size="400%"
            color="black"
            style="position: relative; right: 10px"
        >
          mdi-chevron-left
        </v-icon>
      </v-btn>
    </router-link>
    <v-app-bar-title
        class="text-center"
    >{{ $route.params.name }}
    </v-app-bar-title>
    <v-btn>
      <v-icon
          size="300%"
          style="position: relative; top: -20px"
      >
        mdi-settings-helper
      </v-icon>
    </v-btn>
  </v-app-bar>
  <!-- 운동 부위 사진 -->
  <v-container
  >
    <v-row
        style="min-height: 30%;
    background-color: #03a9f4;
    position: fixed; z-index: 2;
"
    >
    </v-row>
  </v-container>

  <!-- 운동 리스트 -->
  <router-link :to="'/workoutDetail/'+this.name+'/1'">
    <v-btn
        class="text-black"
        block
        min-height="100px"
        style="margin-top: 30%;"
    >
      머신 체스트 프레스
    </v-btn>
  </router-link>

  <router-link
      v-for="(data,index) in testData" :key="data"
      :to="'/workoutDetail/'+this.name+'/'+index+'/'+this.getData2(index)">
    <v-btn
        block
        min-height="100px"
    >
      {{ data.name }}
    </v-btn>
  </router-link>

  <v-btn
      @click="startWorkout"
  >
    시작
  </v-btn>
</template>

<script>
import {mapActions, mapState, mapStores} from "pinia";
import {useRecordStore} from "@/stores/counter";
import WKSetData from "@/common/WKSetData";

export default {
  name: "WorkoutSelection",
  props: ['name'],
  data() {
    return {
      testData: [],
      testData2: [],
      startCheck: false
    }
  },
  computed: {
    ...mapStores(useRecordStore),
    ...mapState(useRecordStore, ['getWKDetailData', 'getWKData'])
  }
  ,
  methods: {
    ...mapActions(useRecordStore, ['postCall', 'setWkDetailData', 'hasWkListData']),
    getData2(index) {
      const data = this.getWKDetailData
      let setDto = function (_id, _reps, _status, _weight) {
        this.reps = _reps
        this.status = _status
        this.id = _id
        this.weight = _weight
      }
      let arr = []
      for (let i = 0; i < data[index].length; i++) {
        arr.push(new setDto(data[index][i].id, data[index][i].reps, data[index][i].status, data[index][i].weight))
      }
      return JSON.stringify(arr)
    },
    fetchWKData(name, WKsetData) {
      const params = {
        user: {
          username: 'test'
        },
        routine: {
          name: name
        }
      }
      return this.postCall('/api/test3', params).catch((error) => {
        console.log(error)
      }).then((rep) => {
        console.log(rep)
        if (rep.status == 200) return this.parseToWKData(rep, WKsetData)
      })
    },
    parseToWKData(rep) {
      const retObject = []
      for (let i = 0; rep.data.length; i++) retObject.push({name: rep.data[i].name})
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
      return this.postCall('/api/test4',
          params).catch((error) => {
        console.log(error)
      }).then((rep) => {
        console.log(rep)
        if (rep.status == 200) return this.parseToWKSetData(rep)
      })
    },
    parseToWKSetData(rep) {
      let retObject = []
      for (let i = 0; rep.data.length; i++) {
        retObject.push(new WKSetData(rep.data[i].reps, rep.data[i].status, rep.data.weight))
      }
      return retObject
    },
    startWorkout() {
      this.startCheck = true
    }
  },
  created() {
    console.log(this.name)
    if (!this.startCheck) {
      this.fetchWKDetailData(this.name)
          .then((WKDetailData) => {
            this.fetchWKData(this.name)
                .then((WKData) => {
                  for (let i = 0; WKData.length; i++) {
                    this.getWKData[i].addToList(WKData[i].name,WKDetailData[i])
                  }
                })
          })
    }
  }
}
</script>

<style scoped>
</style>
