<template>
  <v-card
  >
    <v-row
        class="mb-2"
        no-gutters
    >
      <v-col
          cols="10"
          sm="10"
          md="11"
      >
        <v-card-title
            class="text-h4"
        >
          {{ name }}
        </v-card-title>
      </v-col>

      <v-col>
        <v-btn
            elevation="0"
            icon="mdi-alpha-x"
        >
        </v-btn>

      </v-col>

      <v-col
          cols="12"
      >
        <v-row
            class="mt-1"
        >
          <v-col
              cols="4"
          >
            <v-card-subtitle
                class="text-h6 text-center font-weight-bold"
            >
              세트
            </v-card-subtitle>
          </v-col>
          <v-col
              cols="4"
          >
            <v-card-subtitle
                class="text-h6 text-center font-weight-bold"
            >
              렙
            </v-card-subtitle>
          </v-col>
          <v-col
              cols="4"
          >
            <v-card-subtitle
                class="text-h6 text-center font-weight-bold"
            >
              완료
            </v-card-subtitle>
          </v-col>
        </v-row>
      </v-col>

      <v-col
          class="mt-3"
          cols="12"
      >
        <v-row
            style="width: 100%;"
        >
          <v-col
              cols="4"
          >
            <v-card-subtitle

                class="text-center text-h3  pa-7"
                style="position: relative;
">
              100
            </v-card-subtitle>

          </v-col>
          <v-col
              cols="4"
          >
            <v-card-subtitle
                class="text-center text-h3  pa-7"
                style="position: relative;"
            >
              &nbsp;
              {{ record.reps }}
            </v-card-subtitle>


          </v-col>
          <v-col
              cols="4"
          >
            <v-checkbox
                class
                style="font-size: 250%; position: relative;
                left:50%; top:15%"
            ></v-checkbox>
          </v-col>


        </v-row>

      </v-col>

    </v-row>

  </v-card>
</template>

<script>
export default {
  name: "RecordTable",
  props: {
    record: Object,
    name: String
  }
}
</script>

<style scoped>

</style>
