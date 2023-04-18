<template>
  <RecordTable
    :name=""
  ></RecordTable>
</template>

<script>
import RecordTable from "@/components/RecordTable.vue";

export default {
  name: "WorkoutDetailsView",
  components: {RecordTable},
  data: () => ({
    rep: 12
  })
}
</script>

<style scoped>

</style>
