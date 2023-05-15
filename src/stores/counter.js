import { defineStore } from 'pinia'

export const useRecordStore = defineStore('record', {
  state: () => ({
    record:['hello'],
  }),
  getters:{
    getRecord(state){
      return state.record
    }
  }
})
