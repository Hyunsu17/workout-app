<template>
  <v-app-bar
      elevation="0"
  >
    <v-app-bar-title
        style="position: relative; left:44%"
        class="text-h3"
    >설정
    </v-app-bar-title>
  </v-app-bar>
  <v-card
      elevation="0"
      class="mx-auto"
  >
    <v-list>
      <v-list-subheader
          class="text-h3 my-6 font-weight-bold"
      >나
      </v-list-subheader>
      <v-list-item
          v-for="(item, i) in profile"
          :key="i"
      >
        <template v-slot:prepend>
          <v-icon
              size='400%'
              :icon="item.icon"></v-icon>
        </template>
        <v-spacer></v-spacer>
        <v-list-item-title
            class="text-h5"
        >{{ item.text }}
        </v-list-item-title>
      </v-list-item>
    </v-list>

    <v-list
    >

      <v-list-subheader
          class="text-h3 my-6 font-weight-bold"
      >앱
      </v-list-subheader>

      <v-list-item
          v-for="(item, i) in application"
          :key="i"
          :value="item"
      >
        <template v-slot:prepend>
          <v-icon
              size='400%'
              :icon="item.icon"></v-icon>
        </template>

        <v-list-item-title
            class="text-h5"
        >{{ item.text }}
        </v-list-item-title>
        <template v-slot:append>
          <v-icon
              icon="mdi-chevron-right"></v-icon>
        </template>
      </v-list-item>

    </v-list>

    <v-list>
      <v-list-subheader
          class="text-h3 my-6 font-weight-bold"
      >기타
      </v-list-subheader>

      <v-list-item
          v-for="(item, i) in otherMenu"
          :key="i"
      >
        <template v-slot:prepend>
          <v-icon
              size='400%'
              :icon="item.icon"></v-icon>
        </template>

        <v-list-item-title
            class="text-h5"
        >{{ item.text }}
        </v-list-item-title>
        <template v-slot:append>
          <v-icon
              icon="mdi-chevron-right"></v-icon>
        </template>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {
  name: "SettingView",
  data: () => ({
    profile: [
      {
        text: '플릭 PRO', icon: 'mdi-alpha-p-circle'
      }
    ],
    application: [
      {
        text: '앱 설정', icon: 'mdi-cog'
      }
    ]
    , otherMenu: [
      {
        text: '자주 물어보는 질문', icon: 'mdi-help-circle-outline'
      },
      {
        text: '건의사항 쪽지 보내기', icon: 'mdi-send'
      },
      {
        text: '건의사항 메일 보내기', icon: 'mdi-send'
      },
      {
        text: '간단하게 리뷰 남기기', icon: 'mdi-heart'
      },
      {
        text: '개인정보처리방침', icon: 'mdi-note'
      },
      {
        text: '서비스 이용약관', icon: 'mdi-note'
      },
      {
        text: '버전', icon: 'mdi mdi-git'
      },
      {
        text: '로그아웃', icon: 'mdi-logout'
      }
    ]
  })
}
</script>

<style scoped>

</style>