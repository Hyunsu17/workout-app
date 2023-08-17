<template>
  <v-pagination
      v-model="localId"
      :length="pageLength"
      rounded
  >
  </v-pagination>

  <v-card
      elevation="0"
  >
    <div class="d-flex">
      <router-link :to="'/workoutSelection/'+$route.params.name">
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

</template>

<script>
import RecordCard from "@/components/RecordCard.vue";
export default {
  components: {RecordCard},
  name: "AddRoutine",
  data() {
    return {
      name: '운동'
    }
  },
  methods:{

  }
}
</script>

<style scoped>

</style>
