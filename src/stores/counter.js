import { defineStore } from 'pinia'

export const useRecordStore = defineStore('record', {
  state: () => ({
    routineName:'',
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
