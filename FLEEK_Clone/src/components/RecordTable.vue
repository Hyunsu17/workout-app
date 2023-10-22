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
        {{ idx + 1 }}
      </v-card-subtitle>

    </v-col>
    <v-col
    >
      <v-card-subtitle
          class="text-center text-h3  pa-7"
          style="position: relative;"
      >
        <v-text-field
            variant="solo"
            flat="true"
            single-line
            type="number"
            class="text-center font-weight-bold"
            v-model="localReps"
        >
        </v-text-field>
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
    currentStatus: false,
    localReps: 0,
  }),
  created() {
    this.currentStatus = this.radioStatus
    this.localReps = this.reps
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
    returnValue() {
      return this.currentStatus
    }
  }
}
</script>

<style scoped>

</style>