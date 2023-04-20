import { defineStore } from 'pinia'

export const useRecordStore = defineStore('record', {
  state: () => ({
    record:[],
  }),
})
