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
      v-for="(data,index) in WKNameList" :key="data"
      :to="'/workoutDetail/'+this.name+'/'+ (parseInt(index)+1) +'/'">
    <v-btn
        block
        min-height="100px"
    >
      {{ data }}
    </v-btn>
  </router-link>


  <v-row
      class="justify-center mx-auto"
  >
    <v-col
        class="my-16"
        cols="4"
        align-self="center"
    >
      <v-btn @click="this.callStartWorkingOut"
             width="100%"
      >
        시작
      </v-btn>
    </v-col>
    <v-col
        class="mb-16"
        cols="12">
    </v-col>
  </v-row>


  <!--  <v-dialog-->
  <!--    v-model="dialog"-->
  <!--    width="auto"-->
  <!--  >-->
  <!--    <v-card>-->
  <!--      <v-card-text>-->
  <!--        운동이 진행중입니다.종료하고 메인메뉴로 이동하시겠습니까?-->
  <!--      </v-card-text>-->
  <!--      <v-card-actions>-->
  <!--        <v-btn color="primary" block @click="dialog = false">Close Dialog</v-btn>-->
  <!--      </v-card-actions>-->
  <!--    </v-card>-->

  <!--  </v-dialog>-->

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
      dialog: false,
      WKNameList: [],
      testData2: [],
      startCheck: false
    }
  },
  computed: {
    ...mapStores(useRecordStore),
    ...mapState(useRecordStore, ['getWKDetailData', 'getWKData'])
  },
  beforeRouteLeave(to, from, next) {
    // if(this.dialog===false)
    //   this.dialog = true
    // else
    next()
  }
  ,
  methods: {
    ...mapActions(useRecordStore, ['postCall', 'setWkDetailData', 'hasWkListData', 'addToWKList', 'getRoutineIdxByName', 'getWKObjectByRoutine', 'startWorkingOut']),
    callStartWorkingOut() {
      this.startWorkingOut()
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
      for (let i = 0; i < rep.data.length; i++) retObject.push({name: rep.data[i].name})
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
      return this.postCall('/api/test4',
          params).catch((error) => {
        console.log(error)
      }).then((rep) => {
        console.log(rep)
        if (rep.status == 200) return this.parseToWKSetData(rep)
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
    initValue() {
      for (let i = 0; i < this.getWKData[this.getRoutineIdxByName(this.name)].workoutList.length; i++) {
        this.WKNameList.push(this.getWKData[this.getRoutineIdxByName(this.name)].workoutList[i].workoutName)
      }
    },
    /**
     *
     * @returns {boolean}
     */
    checkStatus() {
      if (this.getWKObjectByRoutine(this.name).workoutList.length === 0)
        return true
      else
        return false
    },

  }
  ,
  created() {
    if (this.checkStatus()) {
      this.fetchWKDetailData(this.name)
          .then((WKDetailData) => {
            this.fetchWKData(this.name)
                .then((WKData) => {
                  for (let i = 0; i < WKData.length; i++) {
                    this.addToWKList(this.getRoutineIdxByName(this.name), WKData[i].name, WKDetailData[i])
                  }
                })
                .then(() => {
                  this.initValue()
                  console.log(this.getWKData)
                })
          })
    } else {
      this.initValue()
    }
  }
}
</script>

<style scoped>
</style>
