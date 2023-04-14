<template>
  <v-app-bar
      elevation="0"
  >
    <router-link to="/routine">
      <v-btn
          rounded="pill"
          icon="mdi-chevron-left"
      >
      </v-btn>
    </router-link>
    <v-app-bar-title
        class="text-center font-weight-bold"
    >빠른 기록
    </v-app-bar-title>
    <v-app-bar-nav-icon
    ></v-app-bar-nav-icon>
  </v-app-bar>

  <v-select
      v-model="select"
      :items="items"
      item-title="title"
  >
  </v-select>

  <v-row
      style="background-color: #4086f4"
  >
    <v-col
        cols="9"
    >
      <v-select
          label="Select"
          :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
          variant="underlined"
      ></v-select>
    </v-col>
    <v-col
        style="background-color: red"
        cols="3"
    >
      <v-select
          class="align-center"
          v-model="selectTime"
          :items="requiredTime"
          variant="underlined"
      ></v-select>
    </v-col>
  </v-row>
  <!--  운동 종류-->


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
                style="position: relative; right: 3%"
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
                style="position: relative;"
                class="text-center text-h3 pa-9"
            >
              1
            </v-card-subtitle>

          </v-col>
          <v-col
              cols="4"
          >
            <v-text-field
                style="
                position: relative;"
                variant="underlined"
            >

            </v-text-field>

          </v-col>
          <v-col
              cols="4"
          >
            <v-checkbox
                style="
                position: relative; left: 50%;bottom: -20%"
            >

            </v-checkbox>
          </v-col>


        </v-row>

      </v-col>

    </v-row>

  </v-card>

  <!--  운동 추가 운동 완료 버튼-->
  <v-row
      class="mt-12"
      no-gutters
  >
    <v-col
        cols="6"
    >
      <v-btn
          class="text-green-accent-4"
          prepend-icon="mdi-dumbbell"
          elevation="0"
          block
          size="x-large"
      >
        <router-link to="/list"
                     class="text-green-accent-4"
        >
          운동 추가
        </router-link>
      </v-btn>
    </v-col>

    <v-col
        cols="6"
    >
      <v-btn
          prepend-icon="mdi-flag"
          class="text-red"
          elevation="0"
          block
          size="x-large"
      >
        운동완료
      </v-btn>
    </v-col>
  </v-row>

</template>

<script>
export default {
  name: "QuickRecordView",
  data: () => ({
    select: {title: '자유운동'},
    items: [
      {title: '자유운동'},
      {title: '헬로'}
    ],
    requiredTime: Array.from({length: 120}, (v, i) => i + 1 + '분'),
    selectTime: '60분'
  })
  ,
  props: {
    routineList: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>

</style>