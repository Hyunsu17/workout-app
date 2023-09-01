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
              REPS
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
            :radio-status="value.status"
        >
        </record-table>
      </v-col>
    </v-row>

  </v-card>

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
  },
  created() {
  console.log(this.record)
    },
  computed: {
    localParam: {
      get: function () {
        return this.record
      },
      set: function (record) {
        this.$emit('', record)
      }
    }
  }
  , methods: {
    onMethodRequest({ methodName, param }) {

      //메서드 목록 객체
      const methods = {
        setTrue: () => this.$refs.recordTable.forEach(table => table.setTrue()),
        returnValue: () => this.$refs.recordTable.map(table => table.returnValue())
      }
      //메서드 실행
      if(methods[methodName]){
        let ret =[]
        ret = methods[methodName](param)
        return ret
      }
      else{
        return null
      }

    },
  }

}
</script>

<style scoped>

</style>
