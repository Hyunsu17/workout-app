<template>
  <v-app-bar
      elevation="0"
  >
    <router-link to="/routine">
      <v-btn
          rounded="pill"
          icon="mdi-chevron-left"
      >
      </v-btn>
    </router-link>
    <v-app-bar-title
        class="text-center font-weight-bold"
    >빠른 기록
    </v-app-bar-title>
    <v-app-bar-nav-icon
    ></v-app-bar-nav-icon>
  </v-app-bar>

  <v-select
      v-model="select"
      :items="items"
      item-title="title"
  >
  </v-select>

  <v-row
  >
    <v-col
        cols="9"
    >
      <v-select
          label="Select"
          :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
          variant="underlined"
      ></v-select>
    </v-col>
    <v-col
        cols="3"
    >
      <v-select
          class="align-center"
          v-model="selectTime"
          :items="requiredTime"
          variant="underlined"
      ></v-select>
    </v-col>
  </v-row>
  <!--  운동 종류-->
  <record-card
      :name="'name'"
      :record="record"
  >

  </record-card>

  <!--  운동 추가 운동 완료 버튼-->
  <v-row
      class="mt-12"
      no-gutters
  >
    <v-col
        cols="6"
    >
      <v-btn
          class="text-green-accent-4"
          prepend-icon="mdi-dumbbell"
          elevation="0"
          block
          size="x-large"
      >
        <router-link to="/list"
                     class="text-green-accent-4"
        >
          운동 추가
        </router-link>
      </v-btn>
    </v-col>

    <v-col
        cols="6"
    >
      <v-btn
          prepend-icon="mdi-flag"
          class="text-red"
          elevation="0"
          block
          size="x-large"
      >
        운동완료
      </v-btn>
    </v-col>
  </v-row>

</template>

<script>
import RecordCard from "@/components/RecordCard.vue"

export default {
  name: "QuickRecordView",
  components: {RecordCard},
  data: () => ({
    record: [
      {reps: 10},
      {reps: 10},
      {reps: 10},
      {reps: 10},
    ]
    ,
    select: {title: '자유운동'},
    items: [
      {title: '자유운동'},
      {title: '헬로'}
    ],
    requiredTime: Array.from({length: 120}, (v, i) => i + 1 + '분'),
    selectTime: '60분'
  })
  ,
  props: {
    routineList: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>

</style>