<template>
  <v-pagination
      :length="4"
      rounded
  >

  </v-pagination>
  <v-card
      elevation="0"
  >
    <div class="d-flex">
      <router-link to="/workoutSelection">
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
        {{}}11:11
      </v-card-title>
    </div>
  </v-card>

  <record-card
      ref="recordCard"
      :name="'랫 풀 다운'"
      :record="record"
  >

  </record-card>

  <v-row
      class="justify-center mx-auto mt-2"
  >
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

</template>

<script>
import RecordCard from "@/components/RecordCard.vue";

export default {
  name: "WorkoutDetailsView",
  computed: {},
  components: {RecordCard},
  data: () => ({
    record: [
      {reps: 10},
      {reps: 10},
      {reps: 10},
      {reps: 10},
    ]
  }),
  methods: {
    changeAllStatus(){
      this.$refs.recordCard.intermediate()
    }
  }
}
</script>

<style scoped>

</style>
