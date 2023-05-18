import { defineStore } from 'pinia'

export const useRecordStore = defineStore('record', {
  state: () => ({
    record:['hello'],
    wkSetData: null
  }),
  actions: {
    setData(data){
      this.wkSetData = data
    }
  }
    ,
  getters:{
    getData(state){
      return state.wkSetData
    }
  }
})
