<template>
  <v-car>
    <v-tabs
      bg-color="black"
    >
      <v-tab
          v-for="data in workoutList" :key="data"
      >

      </v-tab>
    </v-tabs>
  </v-car>
  <v-container>
    <v-row
        style="background: #2c3e50"
    >
    </v-row>
  </v-container>



</template>

<script>
export default {
  name: "WorkoutInfoView",
  data() {
    return{
    }
  }
}
</script>

<style scoped>

</style>
