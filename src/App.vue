<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
    <footer-button-group :is-working-out="this.isExercising"/>

    <v-bottom-navigation
    >
      <v-row justify="center"
             align="center"
      >

        <router-link :to=urlLink[index]
                     v-for="(value,key,index) in links" :key="index"
                     style="width:20%; position: relative; left: 10%
                      ;"

        >
          <v-btn
              block
          >
            <v-icon :icon="value"></v-icon>
            {{ key }}
          </v-btn>
        </router-link>
      </v-row>
    </v-bottom-navigation>
  </v-app>
</template>

<script>

import {mapActions, mapState} from "pinia";
import {useRecordStore} from "@/stores/counter";
import WKClass from "@/common/WKClass";
import FooterButtonGroup from "@/components/FooterButtonGroup.vue";

export default {
  name: 'App',
  mounted() {
    this.getRoutine()
  },
  computed: {
    ...mapState(useRecordStore, ['WKData','isExercising'],)
  },
  methods: {
    ...mapActions(useRecordStore, ['postCall', 'pushToWorkoutData','completeWorkingOut']),
    getRoutine() {
      this.postCall('/api/test', {username: 'test'}).then((rep) => {
        if (rep.status === 200) {
          for (let i = 0; i < rep.data.length; i++) this.pushToWorkoutData(new WKClass(rep.data[i].name))
        }
       })
    },
  },
  components: {
    FooterButtonGroup,
  }
  ,
  data: () => ({
    links: {
      요약: 'mdi-poll',
      캘린더: 'mdi-calendar-blank',
      루틴: 'mdi-dumbbell',
      피드: 'mdi-magnify',
      설정: 'mdi-cog'
    },
    urlLink: ['/summary', '/calendar', '/routine', 'feed', '/setting']
  })
  ,
}
</script>

<style>
@font-face {
  font-family: "suit-regular";
  src: local("suit-regular"), url("./assets/fonts/SUIT-Regular.ttf") format("truetype");
}

@font-face {
  font-family: "BMdohyeon";
  src: local("BMdohyeon"), url("./assets/fonts/BMDOHYEON_ttf.ttf") format("truetype");
}

.v-btn {
  font-family: BMdohyeon, Serif;
}

a {
  text-decoration: none;
}
</style>
