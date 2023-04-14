<template>
  <v-card
      width="100%"
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
          1. 바이씨클 크런치
        </v-card-title>
      </v-col>

      <v-col>
        <v-btn
            block
            elevation="0"
        >
          <v-icon
              color="red"
              icon="mdi-alpha-x"
              size="300%"
          ></v-icon>
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
                class="text-center"
            >
              ssss
            </v-card-subtitle>

          </v-col>
          <v-col
              cols="4"
          >
            <v-text-field
                variant="solo"
            >

            </v-text-field>

          </v-col>
          <v-col
              cols="4"
          >
            <v-checkbox>

            </v-checkbox>

          </v-col>


        </v-row>

      </v-col>

    </v-row>
  </v-card>
</template>

<script>
export default {
  name: "RecordTable"
}
</script>

<style scoped>

</style>