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
            class="
             mt-3 text-h4 text-center"
        >
          {{ name }}
        </v-card-title>
      </v-col>

      <v-col>
        <v-btn
            size="100%"
            variant="text"
            color="red"
            style="font-size:300%;"
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
              SET
            </v-card-subtitle>
          </v-col>
          <v-col
              cols="4"
          >
            <v-card-subtitle
                class="text-h6 text-center font-weight-bold"
            >
              REP
            </v-card-subtitle>
          </v-col>
          <v-col
              cols="4"
          >
            <v-card-subtitle
                class="text-h6 text-center font-weight-bold"
            >
              CLEAR
            </v-card-subtitle>
          </v-col>
        </v-row>
      </v-col>

      <v-col
          class="mt-3"
          cols="12"
      >
        <record-table
            ref="recordTable"
            v-for="(value,idx) in record"
            :key="idx"
            :reps="value.reps"
            :idx="idx"
        >
        </record-table>
      </v-col>
    </v-row>

  </v-card>

  <v-row
      class="justify-center mx-auto mt-2"
  >
    <v-col
        cols="5"
    >
      <v-btn
          color="blue-darken-3"
          width="100%"
          height="200%"
      >
        세트추가
      </v-btn>
    </v-col>
    <v-col
        class="justify-center"
        cols="5"
    >
      <v-btn
          color="red"
          width="100%"
          height="200%"
      >
        세트삭제
      </v-btn>
    </v-col>
  </v-row>

</template>

<script>
import RecordTable from "@/components/RecordTable.vue"

export default {
  name: "RecordCard",
  props: {
    record: Array,
    name: String
  },
  components: {
    RecordTable
  }
  , methods: {
    intermediate() {
      for (let i = 0; i < this.record.length; i++) {
        this.$refs.recordTable[i].setTrue()
      }
    }
  }

}
</script>

<style scoped>

</style>
