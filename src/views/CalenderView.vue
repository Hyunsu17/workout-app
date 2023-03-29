<template>
  <v-row justify="center">
    <v-date-picker
        v-model="date"
        readonly
    ></v-date-picker>
  </v-row>
</template>

<script>
export default {
  name: "CalenderView",
  data() {
    return {
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
    }
  }
}
</script>

<style scoped>

</style>
