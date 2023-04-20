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
        {{ idx }}
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
          v-model="radioStatus"
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
    radioStatus: false
  }),
  props: {
    reps: Number,
    idx: Number
  },
  methods: {
    setTrue() {
      this.radioStatus = true
    }
  }
}
</script>

<style scoped>

</style>