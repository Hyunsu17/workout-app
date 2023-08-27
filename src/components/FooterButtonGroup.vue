<template>
  <v-row
      class="justify-center mx-auto"
      style="
      position: fixed;
      bottom: 100px;
      width: 100%;
"
  >
    <v-col
        cols="5"
    >
      <v-btn
          v-if="this.isExercising"
          disabled="true"
          width="100%"
          height="200%"
      >
        운동 진행중
      </v-btn>
      <v-btn
          v-else
          width="100%"
          height="200%"
      >
        자유운동
      </v-btn>
    </v-col>
    <v-col
        class="justify-center"
        cols="5"
    >
      <v-btn
          @click="callCompleteWorkingOut"
          v-if="this.isExercising"
          width="100%"
          height="200%"
      >
        운동종료
      </v-btn>
      <router-link
          class="text-black"
          v-else
          to="/add-routine">
        <v-btn
          width="100%"
          height="200%"
      >
        루틴추가



      </v-btn>
      </router-link>

    </v-col>
  </v-row>
</template>

<script>
import {mapActions, mapState} from "pinia";
import {useRecordStore} from "@/stores/counter";

export default {
  name: "FooterButtonGroup",
  computed: {
    ...mapState(useRecordStore, ['isExercising'],)
  },
  methods: {
    ...mapActions(useRecordStore, ['completeWorkingOut', 'postCall']),
    callCompleteWorkingOut(params) {
      this.completeWorkingOut()
      this.postCall('', params).catch((error) => {
        console.log(error)
      }).then((rep) => {
        console.log(rep)
      })
    },

  }
}
</script>

<style scoped>

</style>
