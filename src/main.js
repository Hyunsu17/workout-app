import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import axios from "axios";

loadFonts()
const app = createApp(App);
app.config.globalProperties.axios = axios;
app.config.globalProperties.foo = 'bar';
app.use(router)
  .use(vuetify)
  .mount('#app')
