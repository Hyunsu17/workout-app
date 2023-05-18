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
  <router-link :to="'/workoutDetail/'+$route.params.name+'/1'">
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
      :to="'/workoutDetail/'+$route.params.name+'/'+index+'/'+this.getData2(index)">
    <v-btn
        block
        min-height="100px"
    >
      {{ index }}
      {{ data.name }}
    </v-btn>
  </router-link>

  <v-btn
      @click="fetchData2"
  >
    시작
  </v-btn>
</template>

<script>
import {mapStores} from "pinia";
import {useRecordStore} from "@/stores/counter";

export default {
  name: "WorkoutSelection",
  data() {
    return {
      testData: [],
      testData2: [],
      workoutList: ['바벨스쿼트', '랫풀다운', '바벨 숄더 프레스', '케이블 트라이셉 푸시다운', '바벨 바이셉 컬', '행잉 레그레이즈']
    }
  },
  computed: {
    ...mapStores(useRecordStore),

  }
  ,
  methods: {
    getData2(index) {
      let data = this.recordStore.wkSetData
      console.log(data.data)
      let setDto = function (_id, _reps, _status, _weight) {
        this.reps = _reps
        this.status = _status
        this.id = _id
        this.weight = _weight
      }
      let arr = []
      for (let i = 0; i < data.data[index].length; i++) {
        arr.push(new setDto(data.data[index][i].id, data.data[index][i].reps, data.data[index][i].status, data.data[index][i].weight))
      }
      return JSON.stringify(arr)
    },
    fetchData() {
      this.axios.post('/api/test3', {
        user: {
          username: 'test'
        },
        routine: {
          name: "초보"
        }
      }).then((rep) => {
        console.log(rep)
        console.log(this.recordStore.record)
        this.testData = rep.data
      })
    },
    fetchData2() {
      this.axios.post('/api/test4', {
        user: {
          username: 'test'
        },
        routine: {
          name: "초보"
        }
      }).catch((error) => {
        console.log(error)
      }).then((rep) => {
        console.log(rep)
        this.recordStore.setData(rep)
      })
    },
    mappingSetAndWorkout(setArray, workoutName) {
      for (let i = 0; i < setArray.length; i++) {
        if (workoutName === setArray[i].workoutElement.name) {
          return setArray[i]
        }
      }
    }
  },
  created() {
    this.fetchData()
    this.fetchData2()
  }
}
</script>

<style scoped>
</style>
