<template>
  <v-row
      style="width: 100%;"
  >
    <v-col
    >
      <v-card-subtitle

          class="text-center text-h3  pa-7"
          style="position: relative;
">
        {{ idx+1 }}
      </v-card-subtitle>

    </v-col>
    <v-col
    >
      <v-card-subtitle
          class="text-center text-h3  pa-7"
          style="position: relative;"
      >
        &nbsp;{{ reps }}
      </v-card-subtitle>

    </v-col>
    <v-col
    >
      <v-checkbox
          v-model="currentStatus"
          style="font-size: 250%; position: relative;
                left:50%; top:15%"
      >
      </v-checkbox>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "RecordTable",
  data: () => ({
    currentStatus:false
  }),
  created() {
    this.currentStatus = this.radioStatus
  },
  updated() {
    this.currentStatus = this.radioStatus
  },
  props: {
    reps: Number,
    idx: Number,
    radioStatus: Boolean
  },
  methods: {
    setTrue() {
      this.currentStatus = true
    },
    returnValue(){
      return this.currentStatus
    }
  }
}
</script>

<style scoped>

</style>