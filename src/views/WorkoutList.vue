<template>
    <router-link
        v-for="data in part" :key="data"
        :to="'/workout-list/'+ data">
      <v-btn
          block
          min-height="100px"
      >
        {{ data }}
      </v-btn>
    </router-link>
</template>

<script>
export default {
  name: "WorkoutList",
  data(){
    return{
      part: ['등','어깨','가슴','하체']
    }
  }
}
</script>

<style scoped>

</style>
