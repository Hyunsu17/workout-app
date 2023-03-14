<template>
  <HelloWorld />
  안녕하세요
</template>

<script>
import { defineComponent } from 'vue';

import HelloWorld from '../components/HelloWorld.vue';

export default defineComponent({
  name: 'hello',

  components: {
    HelloWorld,
  },
});
</script>
